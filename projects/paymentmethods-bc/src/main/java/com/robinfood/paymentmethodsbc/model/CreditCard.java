package com.robinfood.paymentmethodsbc.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@NamedEntityGraph(
    name = "CreditCardsGraph",
    attributeNodes = {
        @NamedAttributeNode("creditCardType"), @NamedAttributeNode("country")
    }
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "credit_cards")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_card_type_id", nullable = false)
    private CreditCardType creditCardType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Column(name = "`number`")
    private String number;

    @Column(name = "card_holder_name")
    private String cardHolderName;

    @Column(name = "card_expiration_month")
    private String expirationMonth;

    @Column(name = "card_expiration_year")
    private String expirationYear;

    @Column(name = "user_identification_type")
    private int userIdentificationType;

    @Column(name = "user_identification_number")
    private String userIdentificationNumber;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "is_updated")
    private int isUpdated;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "user_city")
    private String userCity;

    @Column(
        name = "created_at",
        nullable = false,
        updatable = false,
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(
        name = "deleted_at",
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime deletedAt;

    public CreditCard(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append(getClass().getSimpleName())
            .append("(")
            .append("id=")
            .append(id)
            .append(", userId=")
            .append(userId)
            .append(", creditCardType=")
            .append(creditCardType)
            .append(", country=")
            .append(country)
            .append(", createdAt=")
            .append(createdAt)
            .append(", updatedAt=")
            .append(updatedAt)
            .append(")")
            .toString();
    }
}