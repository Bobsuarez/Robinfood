package com.robinfood.app.usecases.gettransactioninfo;

import java.util.Map;

public interface IGetTransactionInfoUseCase {
    Map<String,String> invoke (Long transactionId);
}
