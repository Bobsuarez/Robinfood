package com.robinfood.decorator.agregadocomercial.decorator;

import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.agregadocomercial.AgregadoComercialDTO;

public interface IDecoratorAgregadoComercial {

    AgregadoComercialDTO invoke(TransactionRequestDTO transactionRequestDTO);
}
