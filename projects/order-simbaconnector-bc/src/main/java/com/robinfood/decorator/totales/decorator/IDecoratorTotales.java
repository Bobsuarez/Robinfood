package com.robinfood.decorator.totales.decorator;

import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.lineas.LineasDTO;
import com.robinfood.dtos.request.simba.totales.TotalesDTO;

import java.util.List;

public interface IDecoratorTotales {

    TotalesDTO invoke(TransactionRequestDTO transactionRequestDTO, List<LineasDTO> lineasDTOS);
}
