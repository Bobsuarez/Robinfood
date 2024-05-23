package com.robinfood.decorator.terceros.decorator;

import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.terceros.TercerosDTO;

public interface IDecoratorTerceros {

    TercerosDTO invoke(TransactionRequestDTO transactionRequestDTO);
}
