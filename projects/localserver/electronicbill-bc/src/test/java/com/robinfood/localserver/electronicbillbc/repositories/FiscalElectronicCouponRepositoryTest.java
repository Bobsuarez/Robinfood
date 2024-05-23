package com.robinfood.localserver.electronicbillbc.repositories;

import com.robinfood.localserver.commons.dtos.electronicbill.FiscalElectronicCouponDTO;
import com.robinfood.localserver.commons.dtos.electronicbill.cancelsale.CancelElectronicCouponDTO;
import com.robinfood.localserver.commons.dtos.electronicbillbcsaopaulo.SatHubResultDto;
import com.robinfood.localserver.commons.entities.electronicbill.FiscalElectronicCouponEntity;
import com.robinfood.localserver.commons.entities.electronicbill.cancelsale.CancelElectronicCouponEntity;
import com.robinfood.localserver.commons.mappers.electronicbill.ICancelElectronicCouponMapperImpl;
import com.robinfood.localserver.commons.mappers.electronicbill.IFiscalElectronicCouponMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        FiscalElectronicCouponRepository.class,
        IFiscalElectronicCouponMapperImpl.class,
        ICancelElectronicCouponMapperImpl.class
})
class FiscalElectronicCouponRepositoryTest {

    @MockBean
    private ElectronicBillSaoPauloDataSource electronicBillSaoPauloDataSource;

    @Autowired
    private FiscalElectronicCouponRepository fiscalElectronicCouponRepository;

    @Test
    void test_When_Fiscal_Electronic_Coupon_Repository_sendOrderDetail_Is_OK() {

        when(electronicBillSaoPauloDataSource.sendFiscalElectronicCoupon(
                any(FiscalElectronicCouponEntity.class), anyLong())).thenReturn(new SatHubResultDto());

        SatHubResultDto response = fiscalElectronicCouponRepository.sendFiscalElectronicCoupon(
                new FiscalElectronicCouponDTO(),
                1L);

        assertNotNull(response);
    }

    @Test
    void test_When_Fiscal_Electronic_Coupon_Repository_send_Cancel_OrderDetail_Is_OK() {

        when(electronicBillSaoPauloDataSource.sendCancelElectronicCoupon(
                any(CancelElectronicCouponEntity.class), anyLong(), anyString())).thenReturn(new SatHubResultDto());

        SatHubResultDto response = fiscalElectronicCouponRepository.sendCancelElectronicCoupon(
                new CancelElectronicCouponDTO(),
                1L,"12345");

        assertNotNull(response);
    }
}
