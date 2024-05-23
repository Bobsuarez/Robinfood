package com.robinfood.core.enums

enum class OrderStatusEnum(val value: String, val groupValue: String, val groupValueBrazil: String, val code: String) {

    ORDER_CREATED("Pedido", "Efectiva","Efetiva","ORDER_CREATED"),
    ORDER_IN_PROGESS("Proceso","Efectiva","Efetiva","ORDER_IN_PROGESS"),
    ORDER_READY_TO_DELIVERED("Listo para entregar", "Efectiva","Efetiva","ORDER_READY_TO_DELIVERED"),
    ORDER_IN_COURSE("En camino", "Efectiva","Efetiva","ORDER_IN_COURSE"),
    ORDER_DELIVERED("Entregado", "Efectiva","Efetiva","ORDER_DELIVERED"),
    ORDER_CANCELED("Cancelada", "Cancelada","Cancelada","ORDER_CANCELED"),
    ORDER_ROLLBACK("Retroceso", "Cancelada","Cancelada","ORDER_ROLLBACK"),
    ORDER_REJECTED("Rechazada", "Cancelada","Cancelada","ORDER_REJECTED"),
    ORDER_DISCARDED("Descartada", "Cancelada","Cancelada","ORDER_DISCARDED"),
    DELIVERY_ARRIVED_STORE("Tu aliado llegó a la tienda", "Efectiva","Efetiva","DELIVERY_ARRIVED_STORE"),
    DELIVERY_ONGOING("Tu aliado está en camino", "Efectiva","Efetiva","DELIVERY_ONGOING"),
    DELIVERY_ARRIVE_DESTINATION("Tu aliado ha llegado", "Efectiva","Efetiva","DELIVERY_ARRIVE_DESTINATION"),
    ORDER_DELIVERED_SUCCES("Pedido entregado", "Efectiva","Efetiva","ORDER_DELIVERED_SUCCES"),
    ORDER_DELIVERED_FAIL("Pedido no entregado", "Efectiva","Efetiva","ORDER_DELIVERED_FAIL"),
    ORDER_COMING("En camino", "Efectiva","Efetiva","ORDER_COMING"),
    ORDER_TO_DELIVERED_TO_DELIVER("orden para entregar al entregador", "Efectiva","Efetiva","ORDER_TO_DELIVERED_TO_DELIVER"),
    VALIDATING_ORDER("orden en validacion", "Efectiva","Efetiva","VALIDATING_ORDER"),
    ORDER_PAID("orden pagada", "Efectiva","Efetiva","ORDER_PAID"),
    DELIVER_ARRIVE_AT_THE_STORE("el domiciliario llego a la tienda", "Efectiva","Efetiva","DELIVER_ARRIVE_AT_THE_STORE"),
    DELIVER_ARRIVE_AT_THE_SITE("el domiciliario llego al lugar", "Efectiva","Efetiva","DELIVER_ARRIVE_AT_THE_SITE"),
    DELIVER_DELIVERED_THE_ORDER("el domiciliario entrego la order", "Efectiva","Efetiva","DELIVER_DELIVERED_THE_ORDER"),
    DELIVERY_GOING_TO_STORE("Tu aliado va camino a la tienda", "Efectiva","Efetiva","DELIVERY_GOING_TO_STORE");

    companion object {

        private val status = values()

        fun valueOfIdStatus(code: String): OrderStatusEnum {
            for (status in status) {
                if (status.code == code) {
                    return status
                }
            }
            throw IllegalArgumentException("No matching Status for [$code]")
        }
    }

}