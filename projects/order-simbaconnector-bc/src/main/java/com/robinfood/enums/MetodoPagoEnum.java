package com.robinfood.enums;

import com.robinfood.constants.HttpStatusConstants;
import com.robinfood.exceptions.ApiClientsException;
import com.robinfood.mappers.ResponseMapper;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static com.robinfood.constants.GeneralConstants.DEFAULT_BOOLEAN_TRUE;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_DATA_NOT_FOUND_COMPLEMENT;

public enum MetodoPagoEnum {
    EFECTIVO("1", "10", Set.of(1L), 1, "Efectivo"),
    TARJETA_DEBITO("2", "49", Set.of(2L, 4L), 1, "Tarjeta Débito"),
    TARJETA_CREDITO("3", "48", Set.of(3L, 5L, 10L), 1, "Tarjeta Crédito"),
    INTEGRACIONES("4", "19", Set.of(8L), 1, "Integraciones"),
    GIFT_CARD("5", "1", Set.of(26L), 1, "Gift Card"),
    PAYU("6", "2", Set.of(6L), 1, "Payu"),
    DAVIPLATA("7", "2", Set.of(11L, 28L), 1, "Daviplata"),
    NEQUI("8", "2", Set.of(27L), 1, "Nequi"),
    ZZZ("9", "ZZZ", Collections.emptySet(), 1, "Otro*");

    private final String id;
    private final String idCodDian;
    private final Set<Long> idsPaymentMethods;
    private final Integer idPago;
    private final String nameMetodo;

    MetodoPagoEnum(String id, String idCodDian, Set<Long> idsPaymentMethods, Integer idPago, String nameMetodo) {
        this.id = id;
        this.idCodDian = idCodDian;
        this.idsPaymentMethods = Collections.unmodifiableSet(new HashSet<>(idsPaymentMethods));
        this.idPago = idPago;
        this.nameMetodo = nameMetodo;
    }

    public String getId() {
        return id;
    }

    public String getIdCodDian() {
        return idCodDian;
    }

    public Set<Long> getIdsPaymentMethods() {
        return idsPaymentMethods;
    }

    public Integer getIdPago() {
        return idPago;
    }

    public String getNameMetodo() {
        return nameMetodo;
    }

    public static MetodoPagoEnum fromOrderPaymentMethodsId(Long paymentMethodId) {
        return Stream.of(values())
                .filter(paymentMethod -> paymentMethod.getIdsPaymentMethods().contains(paymentMethodId))
                .findFirst()
                .orElse(ZZZ);
    }

    public static MetodoPagoEnum fromId(String idPagoDian) {
        return Stream.of(values())
                .filter(prepayment -> prepayment.getIdCodDian().equals(idPagoDian))
                .findFirst()
                .orElseThrow(() -> new ApiClientsException(
                        ResponseMapper.buildWithError(
                                HttpStatusConstants.SC_PAYMENT_REQUIRED.getCodeHttp(),
                                ERROR_DATA_NOT_FOUND_COMPLEMENT.replaceComplement(
                                        "No matching Method Paid Dian for [" + idPagoDian + "]"),
                                DEFAULT_BOOLEAN_TRUE),
                        ERROR_DATA_NOT_FOUND_COMPLEMENT.replaceComplement(
                                "No matching Method Paid Dian for [" + idPagoDian + "]")));
    }
}
