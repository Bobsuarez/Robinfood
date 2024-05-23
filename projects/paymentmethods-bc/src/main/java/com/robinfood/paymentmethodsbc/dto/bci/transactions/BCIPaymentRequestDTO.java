package com.robinfood.paymentmethodsbc.dto.bci.transactions;

import com.robinfood.paymentmethodsbc.dto.bci.BCISettingsDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BCIPaymentRequestDTO implements Serializable {
    private static final long serialVersionUID = 6204730717613300327L;

    private Long countryId;
    private TransactionDetailsDTO transaction;
    private UserDetailsDTO user;
    private OriginDTO origin;
    private BCISettingsDTO settings;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TransactionDetailsDTO implements Serializable {
        private static final long serialVersionUID = -1906608380212071543L;
        private Long id;
        private String uuid;
        private String reference;
        private Long sourceId;

        @ToString.Exclude
        private String creditCardToken;
        private String creditCardTypeCode;
        private Long installments;
        private BigDecimal total;
        private BigDecimal tax;
        private BigDecimal subtotal;
        private List<TransactionReferencePaymentMethod> referencePaymentMethods;
        private String dataphoneCode;
        private String terminalUuid;
        private String terminalName;

        @Getter
        @Setter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class TransactionReferencePaymentMethod implements Serializable {
            private static final long serialVersionUID = 693144126474913749L;
            private Integer id;
        }
    }

    @Data
    @Builder(toBuilder = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserDetailsDTO implements Serializable {
        private static final long serialVersionUID = -4720586447566190675L;

        private Long id;
        private String name;
        private String phone;
        private int identificationType;
        private String identificationNumber;
        private String email;
        private int isUpdated;
        private String address;
        private String city;
        private String ipAddress;
        private String userAgent;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OriginDTO implements Serializable {
        private static final long serialVersionUID = 8478830049128370490L;

        private Long channelId;
        private Long storeId;
    }
}
