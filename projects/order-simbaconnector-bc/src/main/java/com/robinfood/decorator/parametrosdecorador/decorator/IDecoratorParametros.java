package com.robinfood.decorator.parametrosdecorador.decorator;

import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.parametros.ParametrosDTO;

public interface IDecoratorParametros {

    ParametrosDTO invoke(TransactionRequestDTO transactionRequestDTO);
}
