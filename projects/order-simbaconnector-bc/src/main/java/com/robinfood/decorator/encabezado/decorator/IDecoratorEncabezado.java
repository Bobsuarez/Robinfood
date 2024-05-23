package com.robinfood.decorator.encabezado.decorator;

import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.encabezado.EncabezadoDTO;

public interface IDecoratorEncabezado {

    EncabezadoDTO invoke(TransactionRequestDTO transactionRequestDTO);
}
