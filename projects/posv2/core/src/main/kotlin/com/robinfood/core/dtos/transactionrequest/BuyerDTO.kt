package com.robinfood.core.dtos.transactionrequest

import com.fasterxml.jackson.annotation.JsonProperty

data class BuyerDTO (
        @JsonProperty("identifier")
        val identifier: String
)
