package com.robinfood.repository.thirdparty;

import com.robinfood.entities.OrderThirdPartiesEntity;

import java.math.BigInteger;

public interface IOrderThirdPartiesRepository {

    BigInteger save(OrderThirdPartiesEntity orderThirdPartiesEntity);

}
