package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.exceptions.BaseException;

public interface SSOTokenRepository {
    String getServiceToken(boolean force) throws BaseException;
}
