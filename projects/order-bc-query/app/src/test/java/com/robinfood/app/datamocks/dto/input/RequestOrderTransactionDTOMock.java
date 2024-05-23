package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.request.transaction.RequestCompanyDTO;
import com.robinfood.core.dtos.request.transaction.RequestDeviceDTO;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.core.dtos.request.transaction.RequestPaymentMethodDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class RequestOrderTransactionDTOMock {

    public static RequestOrderTransactionDTO inputOrderTransactionValidatedDTO() {

        return new RequestOrderTransactionDTO(
                true,
                new RequestCompanyDTO("COP", 1L),
                null,
                new DeliveryDTODataMock().getDataDefault(),
                new RequestDeviceDTO("172.80.4.207", 1L, "America/Bogota", "1.5"),
                3L,
                new OrderDTODataMock().getDataDefaultList(),
                new OrderFiscalIdentifierDTODataMock().getDataDefault(),
                true,
                requestPaymentMethodDTOList(),
                requestPaymentsPaidDTOList(),
                BigDecimal.ZERO,
                100.0,
                new UserDataDTODataMock().getDataDefault(),
                1L,
                UUID.randomUUID()
        );
    }

    public static RequestOrderTransactionDTO requestOrderTransactionDTOFoodCoins() {

        return new RequestOrderTransactionDTO(
                true,
                new RequestCompanyDTO("COP", 1L),
                null,
                new DeliveryDTODataMock().getDataDefault(),
                new RequestDeviceDTO("172.80.4.207", 1L, "America/Bogota", "1.5"),
                3L,
                new OrderDTODataMock().getDataDefaultFoodCoins(),
                new OrderFiscalIdentifierDTODataMock().getDataDefault(),
                true,
                requestPaymentMethodDTOList(),
                requestPaymentsPaidDTOList(),
                BigDecimal.ZERO,
                100.0,
                new UserDataDTODataMock().getDataDefault(),
                1L,
                UUID.randomUUID()
        );
    }

    private static List<RequestPaymentMethodDTO> requestPaymentMethodDTOList() {

        return new ArrayList<>(
                Collections.singletonList(
                        new RequestPaymentMethodDTO(
                                new RequestPaymentDetailDTODataMock().getDataDefault(),
                                1L,
                                1L,
                                100.0
                        )
                ));
    }

    private static List<RequestPaymentMethodDTO> requestPaymentsPaidDTOList() {

        return new ArrayList<>(Collections.singletonList(
                new RequestPaymentMethodDTO(
                        new RequestPaymentDetailDTODataMock().getDataDefault(),
                        7L,
                        4L,
                        1000.0
                )
        ));
    }

    public static RequestOrderTransactionDTO inputOrderTransactionValidatedWithoutServicesDTO() {

        return new RequestOrderTransactionDTO(
                true,
                new RequestCompanyDTO("COP", 1L),
                null,
                new DeliveryDTODataMock().getDataDefault(),
                new RequestDeviceDTO("172.80.4.207", 1L, "America/Bogota", "1.5"),
                3L,
                new OrderDTODataMock().getData_Without_Services(),
                new OrderFiscalIdentifierDTODataMock().getDataDefault(),
                true,
                requestPaymentMethodDTOList(),
                requestPaymentsPaidDTOList(),
                BigDecimal.ZERO,
                100.0,
                new UserDataDTODataMock().getDataDefault(),
                1L,
                UUID.randomUUID()
        );
    }

    public static RequestOrderTransactionDTO inputOrderTransactionValidatedWithNullServicesDTO() {

        return new RequestOrderTransactionDTO(
                true,
                new RequestCompanyDTO("COP", 1L),
                null,
                new DeliveryDTODataMock().getDataDefault(),
                new RequestDeviceDTO("172.80.4.207", 1L, "America/Bogota", "1.5"),
                3L,
                new OrderDTODataMock().getData_Null_Services(),
                new OrderFiscalIdentifierDTODataMock().getDataDefault(),
                true,
                requestPaymentMethodDTOList(),
                requestPaymentsPaidDTOList(),
                BigDecimal.ZERO,
                100.0,
                new UserDataDTODataMock().getDataDefault(),
                1L,
                UUID.randomUUID()
        );
    }

}
