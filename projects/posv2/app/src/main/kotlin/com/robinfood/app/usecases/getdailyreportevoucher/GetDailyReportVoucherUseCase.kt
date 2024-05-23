package com.robinfood.app.usecases.getdailyreportevoucher

import com.robinfood.app.usecases.getposrelatedtoastore.IGetConfigurationPosByStoreUseCase
import com.robinfood.app.usecases.getstoreconfigurations.IGetStoreConfigurationUseCase
import com.robinfood.core.constants.GlobalConstants.BRAZIL
import com.robinfood.core.constants.GlobalConstants.FORMAT_DATE_WITH_HOUR_AND_PM_AM
import com.robinfood.core.constants.GlobalConstants.FORMAT_DOUBLE_TWO_DECIMAL
import com.robinfood.core.constants.GlobalConstants.NAME_REPORT_DAILY
import com.robinfood.core.constants.GlobalConstants.PERCENTAGE
import com.robinfood.core.constants.ReportConstants
import com.robinfood.core.constants.ReportConstants.Companion.getUrlReport
import com.robinfood.core.dtos.dailyreportvoucher.CategoryReportDTO
import com.robinfood.core.dtos.dailyreportvoucher.CategoryTitleDTO
import com.robinfood.core.dtos.dailyreportvoucher.DailyReportVoucherDTO
import com.robinfood.core.dtos.dailyreportvoucher.DailyReportVoucherRequestDTO
import com.robinfood.core.dtos.dailyreportvoucher.PaymentMethodTitleDTO
import com.robinfood.core.dtos.posrelatedtoastore.StorePosDTO
import com.robinfood.core.dtos.posresolutionsequence.PosResolutionSequenceDTO
import com.robinfood.core.dtos.report.ReportResponseDTO
import com.robinfood.core.dtos.store.StoreConfigurationsDTO
import com.robinfood.core.dtos.witnesstape.StoreOrderRequestDTO
import com.robinfood.core.helpers.JasperReportManagerHelper
import com.robinfood.core.mappers.dailyreportvoucher.toCategoryReportDTO
import com.robinfood.core.mappers.dailyreportvoucher.toPaymentMethodListDTO
import com.robinfood.core.utils.getCurrentDateTimeInTimeZone
import java.util.Arrays
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class GetDailyReportVoucherUseCase(
    private val getPosResolutionUseCase: IGetPosResolutionUseCase,
    private val getStoreConfigurationUseCase: IGetStoreConfigurationUseCase,
    private val getOrderGroupCategoriesUseCase: IGetOrderGroupCategoriesUseCase,
    private val getOrderGroupPaymentsUseCase: IGetOrderGroupPaymentsUseCase,
    private val getConfigurationPosByStoreUseCase: IGetConfigurationPosByStoreUseCase,
    private val jasperReportManagerHelper: JasperReportManagerHelper
) : IGetDailyReportVoucherUseCase {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override suspend fun invoke(
        storeOrderRequestDTO: StoreOrderRequestDTO,
        token: String
    ): ReportResponseDTO {

        log.info("Generate pdf voucher daily")

        val dailyReportVoucherList: MutableList<DailyReportVoucherDTO> = ArrayList()

        val storeConfigurationsDTO = getInfoStoreConfigurations(storeOrderRequestDTO, token)
        val storePosDTOs = getConfigurationPosByStoreUseCase.invoke(storeOrderRequestDTO.storeId, token)

        if (storePosDTOs.isEmpty()) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found")
        }

        storePosDTOs.forEach { pos: StorePosDTO ->
            generateListDailyReportVoucherToPDF(
                storeOrderRequestDTO, dailyReportVoucherList,
                pos.id, storeConfigurationsDTO, token
            )
        }

        val params = HashMap<String, Any>()
        val result = jasperReportManagerHelper.export(
            getUrlReport(
                storeConfigurationsDTO.company.country.id.toInt(),
                ReportConstants.REPORT_DAILY_VOUCHER_DIR
            ),
            params, dailyReportVoucherList
        )

        return ReportResponseDTO.Builder()
            .reportByteArray(Arrays.toString(result.toByteArray()))
            .fileName(NAME_REPORT_DAILY + "-" + storeOrderRequestDTO.localDateStart.toString())
            .build()

    }

    private suspend fun getInfoPosResolution(
        dailyReportVoucherRequestDTO: DailyReportVoucherRequestDTO, token: String
    ): PosResolutionSequenceDTO? {
        return getPosResolutionUseCase.invoke(
            dailyReportVoucherRequestDTO,
            token
        )
    }

    private suspend fun getInfoStoreConfigurations(
        storeOrderRequestDTO: StoreOrderRequestDTO,
        token: String
    ): StoreConfigurationsDTO {
        return getStoreConfigurationUseCase.invoke(
            storeOrderRequestDTO.storeId,
            token
        )
    }

    private suspend fun generateListDailyReportVoucherToPDF(
        storeOrderRequestDTO: StoreOrderRequestDTO,
        dailyReportVoucherList: MutableList<DailyReportVoucherDTO>,
        posId: Long,
        storeConfigurationsDTO: StoreConfigurationsDTO,
        token: String
    ) {

        val dailyReportVoucherRequestDTO = DailyReportVoucherRequestDTO(
            storeOrderRequestDTO.localDateEnd,
            storeOrderRequestDTO.localDateStart,
            posId,
            storeOrderRequestDTO.timeZone
        )

        val posResolutionSequenceDTO = getInfoPosResolution(dailyReportVoucherRequestDTO, token) ?: return

        val categoryReportDTOs = buildCategorySection(
            dailyReportVoucherRequestDTO,
            storeConfigurationsDTO.company.country.id.toInt(), token
        )

        val categoryTitleDTO = buildCategoryTitleToReport(
            posResolutionSequenceDTO, storeConfigurationsDTO, categoryReportDTOs, dailyReportVoucherRequestDTO
        )

        val paymentMethodTitleDTO = buildVoucherPaymentMethodToSubReport(
            dailyReportVoucherRequestDTO, storeConfigurationsDTO, token, categoryReportDTOs
        )

        addToDailyReportVoucherList(
            dailyReportVoucherList, categoryTitleDTO, paymentMethodTitleDTO, storeConfigurationsDTO
        )

    }

    private fun buildCategoryTitleToReport(
        posResolutionSequenceDTO: PosResolutionSequenceDTO,
        storeConfigurationsDTO: StoreConfigurationsDTO,
        categoryReportDTOs: List<CategoryReportDTO>,
        dailyReportVoucherRequestDTO: DailyReportVoucherRequestDTO
    ): CategoryTitleDTO {

        val position = posResolutionSequenceDTO.name?.indexOf("-")
        val posType = if (position == -1) posResolutionSequenceDTO.name
        else position?.let { posResolutionSequenceDTO.name!!.substring(0, it) }

        return CategoryTitleDTO.Builder()
            .businessName(storeConfigurationsDTO.company.name)
            .businessNit(storeConfigurationsDTO.company.identification)
            .storeName(storeConfigurationsDTO.name)
            .storeAddress(storeConfigurationsDTO.address)
            .posType(posType.toString())
            .initialNumber(posResolutionSequenceDTO.prefix + " " + posResolutionSequenceDTO.startNumber)
            .finalNumber(posResolutionSequenceDTO.prefix + " " + posResolutionSequenceDTO.endNumber)
            .quantityEquivalentDocs(posResolutionSequenceDTO.effectiveInvoices.toString())
            .canceledAmount(posResolutionSequenceDTO.cancelledInvoices.toString())
            .createAt(dailyReportVoucherRequestDTO.localDateEnd.toString())
            .subReportCategory(
                jasperReportManagerHelper.getSubReportToInputStream(
                    getUrlReport(
                        storeConfigurationsDTO.company.country.id.toInt(),
                        ReportConstants.SUB_REPORT_DAILY_VOUCHER_CATEGORY_DIR
                    )
                )
            )
            .listCategoryDet(
                jasperReportManagerHelper
                    .convertCollectionToJRBeanCollectionDataSource(categoryReportDTOs)
            )
            .build()

    }

    private suspend fun buildCategorySection(
        dailyReportVoucherRequestDTO: DailyReportVoucherRequestDTO,
        countryId: Int,
        token: String
    ): List<CategoryReportDTO> {

        val categoryDTOs = getOrderGroupCategoriesUseCase.invoke(
            dailyReportVoucherRequestDTO, token
        )

        val orderGroupCategoriesReportDTOs =
            categoryDTOs.mapNotNull { orderGroupCategoryDTO ->
                orderGroupCategoryDTO.toCategoryReportDTO()
            }

        orderGroupCategoriesReportDTOs.forEach { categoryReportDTO ->
            val totalDifference = if (countryId == BRAZIL) categoryReportDTO.total
            else categoryReportDTO.subtotal - categoryReportDTO.discounts
            categoryReportDTO.taxPercentage = FORMAT_DOUBLE_TWO_DECIMAL.format(
                (categoryReportDTO.taxes / totalDifference) * PERCENTAGE
            ).toDouble()
        }

        return orderGroupCategoriesReportDTOs
    }

    private fun addToDailyReportVoucherList(
        dailyReportVoucherList: MutableList<DailyReportVoucherDTO>,
        categoryTitleDTO: CategoryTitleDTO,
        paymentMethodTitleDTO: PaymentMethodTitleDTO,
        storeConfigurationsDTO: StoreConfigurationsDTO
    ) {

        val dailyReportVoucherDTO = buildDailyReportVoucherToReport(
            storeConfigurationsDTO.company.country.id.toInt(), categoryTitleDTO, paymentMethodTitleDTO,
            storeConfigurationsDTO.timezone
        )

        dailyReportVoucherList.add(dailyReportVoucherDTO)
    }

    private fun buildDailyReportVoucherToReport(
        companyId: Int,
        categoryTitleDTO: CategoryTitleDTO,
        paymentMethodTitleDTO: PaymentMethodTitleDTO,
        timeZone: String
    ): DailyReportVoucherDTO {

        return DailyReportVoucherDTO.Builder()
            .datePrint(getCurrentDateTimeInTimeZone(timeZone, FORMAT_DATE_WITH_HOUR_AND_PM_AM))
            .subReportCategoryTitle(
                jasperReportManagerHelper.getSubReportToInputStream(
                    getUrlReport(
                        companyId,
                        ReportConstants.SUB_REPORT_DAILY_VOUCHER_CATEGORY_TITLE_DIR
                    )
                )
            )
            .subReportPaymentTitle(
                jasperReportManagerHelper.getSubReportToInputStream(
                    getUrlReport(
                        companyId,
                        ReportConstants.SUB_REPORT_DAILY_VOUCHER_PAYMENT_METHOD_TITLE_DIR
                    )
                )
            )
            .listCategoryTitle(
                jasperReportManagerHelper
                    .convertCollectionToJRBeanCollectionDataSource(listOf(categoryTitleDTO))
            )
            .listPaymenTitle(
                jasperReportManagerHelper.convertCollectionToJRBeanCollectionDataSource(
                    listOf(
                        paymentMethodTitleDTO
                    )
                )
            )
            .build()
    }

    private suspend fun buildVoucherPaymentMethodToSubReport(
        dailyReportVoucherRequestDTO: DailyReportVoucherRequestDTO,
        storeConfigurationsDTO: StoreConfigurationsDTO,
        token: String,
        categoryReportDTOs: List<CategoryReportDTO>
    ): PaymentMethodTitleDTO {
        val ordersGroupPaymentDTOs = getOrderGroupPaymentsUseCase.invoke(
            dailyReportVoucherRequestDTO, token
        )

        val orderGroupPaymentsDTOs = ordersGroupPaymentDTOs.mapNotNull { orderGroupPaymentDTO ->
            orderGroupPaymentDTO.toPaymentMethodListDTO()
        }

        var totalCoCompensation = 0.0
        var totalCategory = 0.0

        if (categoryReportDTOs.isNotEmpty()) {
            totalCoCompensation = categoryReportDTOs.stream().mapToDouble(CategoryReportDTO::co2Compensation).sum()
            totalCategory = categoryReportDTOs.stream().mapToDouble(CategoryReportDTO::total).sum()
        }

        return PaymentMethodTitleDTO.Builder()
            .totalCompensation(FORMAT_DOUBLE_TWO_DECIMAL.format(totalCoCompensation).toDouble())
            .totalOwnIncome(FORMAT_DOUBLE_TWO_DECIMAL.format(totalCategory).toDouble())
            .subReportPayment(
                jasperReportManagerHelper.getSubReportToInputStream(
                    getUrlReport(
                        storeConfigurationsDTO.company.country.id.toInt(),
                        ReportConstants.SUB_REPORT_DAILY_VOUCHER_PAYMENT_DIR
                    )
                )
            )
            .listPaymentDet(
                jasperReportManagerHelper.convertCollectionToJRBeanCollectionDataSource(
                    orderGroupPaymentsDTOs
                )
            )
            .build()
    }

}