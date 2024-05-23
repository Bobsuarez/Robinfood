package com.robinfood.decorator.extensiones.decorator;

import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.extensiones.ExtensionesDTO;

import java.util.List;

public interface IDecoratorExtensiones {

    List<ExtensionesDTO> invoke(TransactionRequestDTO transactionRequestDTO);
}
