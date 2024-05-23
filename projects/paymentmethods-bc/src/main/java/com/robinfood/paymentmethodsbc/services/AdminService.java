package com.robinfood.paymentmethodsbc.services;

import com.robinfood.paymentmethodsbc.exceptions.BaseException;

public interface AdminService {
    /**
     * Reinicia el cache de configuración en redis
     * @return {@linkplain String}
     * @throws BaseException
     * @author Edwin Artunduaga
     */
    String cacheReset() throws BaseException;
}
