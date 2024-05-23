package com.robinfood.app.usecases.validateuuid

import com.robinfood.core.constants.GlobalConstants.TEMPLATE_ORDER_FORMAT
import com.robinfood.core.dtos.transactionrequest.TransactionRequestDTO
import java.util.*
import org.apache.commons.lang3.StringUtils

class ValidateUuidUseCase() : IValidateUuidUseCase {

    override fun invoke(
            transactionRequestDTO: TransactionRequestDTO
    ): TransactionRequestDTO {

        if (Objects.isNull(transactionRequestDTO.uuid)) {
            return transactionRequestDTO;
        }

        val valueUuid = transactionRequestDTO.uuid

        processTemplateOrder(transactionRequestDTO);

        transactionRequestDTO.uuid = valueUuid?.let { processTemplate(it) }

        return transactionRequestDTO
    }

    private fun processTemplateOrder(transactionRequestDTO: TransactionRequestDTO) {
        transactionRequestDTO.orders.forEach { data -> data.uuid = data.uuid?.let { processTemplate(it) } };
    }

    private fun processTemplate(valueUuid: String): String {
        return if (valueUuid.contains(TEMPLATE_ORDER_FORMAT)) {
            valueUuid.replace(TEMPLATE_ORDER_FORMAT, StringUtils.EMPTY)
        } else {
            valueUuid
        }
    }
}