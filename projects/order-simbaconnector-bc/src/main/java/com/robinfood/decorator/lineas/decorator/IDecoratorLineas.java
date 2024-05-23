package com.robinfood.decorator.lineas.decorator;

import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.lineas.LineasDTO;

import java.util.List;

public interface IDecoratorLineas {

    List<LineasDTO> invoke(TransactionRequestDTO transactionRequestDTO);
}
