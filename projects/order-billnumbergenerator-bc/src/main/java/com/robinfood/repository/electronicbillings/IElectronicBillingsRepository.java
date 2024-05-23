package com.robinfood.repository.electronicbillings;

import com.robinfood.entities.OrderElectronicBillingsEntity;

import java.math.BigInteger;

public interface IElectronicBillingsRepository {

    BigInteger save(OrderElectronicBillingsEntity orderElectronicBillingsEntity);
}
