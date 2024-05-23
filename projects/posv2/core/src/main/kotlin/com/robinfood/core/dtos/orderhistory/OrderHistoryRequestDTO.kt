package com.robinfood.core.dtos.orderhistory

import java.time.LocalDate

data class OrderHistoryRequestDTO private constructor(

        val channelsId: Int?,
        val currentPage: Int?,
        val localDateStart: LocalDate?,
        val localDateEnd: LocalDate?,
        val perPage: Int?,
        val isDelivery: Boolean?,
        val searchText: String?,
        val storeId: Long?,
        val timeZone: String?
){
    class Builder(
            private var channelsId: Int? = null,
            private var currentPage: Int? = null,
            private var localDateStart: LocalDate? = null,
            private var localDateEnd: LocalDate? = null,
            private var perPage: Int? = null,
            private var isDelivery: Boolean? = null,
            private var searchText: String? = null,
            private var storeId: Long? = null,
            private var timeZone: String? = null

    ) {

        fun channelsId(channelsId: Int?) = apply { this.channelsId = channelsId }
        fun currentPage(currentPage: Int?) = apply { this.currentPage = currentPage }
        fun localDateStart(localDateStart: LocalDate) = apply { this.localDateStart = localDateStart }
        fun localDateEnd(localDateEnd: LocalDate) = apply { this.localDateEnd = localDateEnd }
        fun perPage(perPage: Int?) = apply { this.perPage = perPage }
        fun isDelivery(isDelivery: Boolean) = apply { this.isDelivery = isDelivery }
        fun searchText(searchText: String?) = apply { this.searchText = searchText }
        fun storeId(storeId: Long) = apply { this.storeId = storeId }
        fun timeZone(timeZone: String) = apply { this.timeZone = timeZone }

        fun build(): OrderHistoryRequestDTO {
            return OrderHistoryRequestDTO(channelsId, currentPage, localDateStart, localDateEnd,
                    perPage, isDelivery, searchText, storeId, timeZone )
        }
    }
}
