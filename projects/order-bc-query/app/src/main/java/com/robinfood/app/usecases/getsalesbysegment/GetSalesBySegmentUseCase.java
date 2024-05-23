package com.robinfood.app.usecases.getsalesbysegment;

import com.google.gson.Gson;
import com.robinfood.app.mappers.salesbysegment.GetCompanySaleSegmentDTOMappers;
import com.robinfood.app.usecases.getlistbrandSegment.IGetListBrandSegmentUseCase;
import com.robinfood.app.usecases.getlistchannelsSegment.IGetListChannelSegmentUseCase;
import com.robinfood.app.usecases.getlistpaymentSegment.IGetListPaymentMethodsSegmentUseCase;
import com.robinfood.app.usecases.getliststoreSegment.IGetListStoreSegmentUseCase;
import com.robinfood.app.usecases.getsalebysegmentreport.IGetSaleBySegmentReportUseCase;
import com.robinfood.core.dtos.report.salebysegment.BrandSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.ChannelSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.CompanyBySegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.DataIdsToFindTheSegment;
import com.robinfood.core.dtos.report.salebysegment.GetSaleBySegmentResponseDTO;
import com.robinfood.core.dtos.report.salebysegment.PaymentMethodSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.StoreSegmentDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.exceptions.AsyncOrderBcException;
import com.robinfood.core.utilities.KeyAnyWithTimezonesUtil;
import com.robinfood.core.utilities.LocalDateTimeUtil;
import com.robinfood.core.utilities.OrdersUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Component
@Slf4j
public class GetSalesBySegmentUseCase implements IGetSalesBySegmentUseCase {


    private final IGetListBrandSegmentUseCase getBrandListSegmentCaseUse;
    private final IGetListChannelSegmentUseCase getChannelListSegmentCaseUse;
    private final IGetListPaymentMethodsSegmentUseCase getPaymentMethodsListSegmentCaseUse;
    private final IGetListStoreSegmentUseCase getStoreListSegmentCaseUse;
    private final IGetSaleBySegmentReportUseCase getSaleBySegmentReportUseCase;

    public GetSalesBySegmentUseCase(
            IGetListBrandSegmentUseCase getBrandListSegmentCaseUse,
            IGetListChannelSegmentUseCase getChannelListSegmentCaseUse,
            IGetListPaymentMethodsSegmentUseCase getPaymentMethodsListSegmentCaseUse,
            IGetListStoreSegmentUseCase getStoreListSegmentCaseUse,
            IGetSaleBySegmentReportUseCase getSaleBySegmentReportUseCase
    ) {
        this.getBrandListSegmentCaseUse = getBrandListSegmentCaseUse;
        this.getChannelListSegmentCaseUse = getChannelListSegmentCaseUse;
        this.getPaymentMethodsListSegmentCaseUse = getPaymentMethodsListSegmentCaseUse;
        this.getStoreListSegmentCaseUse = getStoreListSegmentCaseUse;
        this.getSaleBySegmentReportUseCase = getSaleBySegmentReportUseCase;
    }

    @Override
    public GetSaleBySegmentResponseDTO invoke(
            List<String> timezones,
            DataIdsToFindTheSegment toFindTheSegment
    ) throws AsyncOrderBcException {

        List<Integer> idCompaniesToInt = toFindTheSegment.getCompaniesList()
                .stream().map(Long::intValue)
                .collect(Collectors.toList());

        final KeyAnyWithTimezonesUtil keyAnyWithTimezonesUtil = new KeyAnyWithTimezonesUtil(idCompaniesToInt, timezones);
        Map<Integer, ZoneId> companiesWithTimezones = keyAnyWithTimezonesUtil.withZoneId();

        log.info("Companies with timezones: {}", companiesWithTimezones);

        Map<Integer, LocalDateTime> companiesWithLocaltime = keyAnyWithTimezonesUtil.withLocalTimeByZoneIds(
                toFindTheSegment.getDateToSearch(),
                companiesWithTimezones
        );

        log.info(
                "Start of the process that obtains the orders by week ago: {} with companies list  {}",
                new Gson().toJson(idCompaniesToInt), companiesWithLocaltime
        );

        final List<CompanyBySegmentDTO> resultCompanyBySegment = new ArrayList<>();

        for (Integer idCompany : idCompaniesToInt) {

            toFindTheSegment.setCompaniesList(List.of(idCompany.longValue()));

            final LocalDateTime dateByCompany = companiesWithLocaltime.get(idCompany);

            final List<CompanyBySegmentDTO> companyBySegmentDTOS = toSearchInThreadsSumTotal(toFindTheSegment, dateByCompany);

            resultCompanyBySegment.addAll(companyBySegmentDTOS);
        }

        return GetSaleBySegmentResponseDTO.builder()
                .companiesBySegmentDTOList(resultCompanyBySegment)
                .build();
    }

    private List<CompanyBySegmentDTO> toSearchInThreadsSumTotal(
            DataIdsToFindTheSegment dataRequestDTO,
            LocalDateTime toSearchCompany
    ) throws AsyncOrderBcException {

        try {
            final LocalDateTime dateLastTimeWeek = new LocalDateTimeUtil(toSearchCompany).lastOfWeekSameHour();

            final CompletableFuture<List<OrderEntity>> orderEntityListCurrent = getSaleBySegmentReportUseCase
                    .invoke(dataRequestDTO, toSearchCompany);

            final CompletableFuture<List<OrderEntity>> orderEntityListLastTimeWeek = getSaleBySegmentReportUseCase
                    .invoke(dataRequestDTO, dateLastTimeWeek);

            CompletableFuture.allOf(orderEntityListCurrent, orderEntityListLastTimeWeek).join();

            return buildCompany(orderEntityListCurrent.get(),
                    orderEntityListLastTimeWeek.get(),
                    dataRequestDTO.getPaymentsMethodsList());

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AsyncOrderBcException("[toSearchInThreadsSumTotal] Error when making query threads");
        }
    }

    private List<CompanyBySegmentDTO> buildCompany(
            List<OrderEntity> listOrdersCurrent,
            List<OrderEntity> listOrdersLastWeek,
            List<Long> idsToFilter
    ) {
        log.info("Start Method buildCompany: current list size {} with size list before {}",
                listOrdersCurrent.size(), listOrdersLastWeek.size());

        List<CompanyBySegmentDTO> companyBySegmentDTOS = new ArrayList<>();

        Map<Long, List<OrderEntity>> groupedListCurrent = OrdersUtil
                .groupedMultipleByEntityAttribute(listOrdersCurrent, OrderEntity::getCompanyId);

        Map<Long, List<OrderEntity>> groupedListLastWeek = OrdersUtil
                .groupedMultipleByEntityAttribute(listOrdersLastWeek, OrderEntity::getCompanyId);

        List<Long> segmentIds = OrdersUtil
                .findAndGroupSegmentIds(groupedListCurrent, groupedListLastWeek);

        for (Long idCompany : segmentIds) {

            log.info("The identity is built by company identifier : {}", idCompany);

            List<OrderEntity> listOrdersByCompanyCurrent = groupedListCurrent.get(idCompany);
            List<OrderEntity> listOrdersByCompanyLastWeek = groupedListLastWeek.get(idCompany);

            List<BrandSegmentDTO> getBrandsList = getBrandListSegmentCaseUse
                    .invoke(listOrdersByCompanyCurrent, listOrdersByCompanyLastWeek);

            List<ChannelSegmentDTO> getChannelList = getChannelListSegmentCaseUse
                    .invoke(listOrdersByCompanyCurrent, listOrdersByCompanyLastWeek);

            List<StoreSegmentDTO> getStoreList = getStoreListSegmentCaseUse
                    .invoke(listOrdersByCompanyCurrent, listOrdersByCompanyLastWeek);

            List<PaymentMethodSegmentDTO> getPaymentList = getPaymentMethodsListSegmentCaseUse
                    .getListPaymentMethodsSegment(listOrdersByCompanyCurrent, listOrdersByCompanyLastWeek, idsToFilter);

            CompanyBySegmentDTO getCompany = GetCompanySaleSegmentDTOMappers
                    .filtersListToCompanyBySegmentDTO(getBrandsList,
                            getChannelList,
                            idCompany,
                            getPaymentList,
                            getStoreList);

            companyBySegmentDTOS.add(getCompany);

        }
        return companyBySegmentDTOS;
    }
}
