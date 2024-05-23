package com.robinfood.configurations.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "companies")
public class Company extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "holding_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Holding holding;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "identification", nullable = false)
    private String identification;

    @Column(name = "currency_type", nullable = false)
    private String currencyType;

    @Column(name = "currency_symbol", nullable = false)
    private String currencySymbol;

    @Column(name = "status")
    private Long status;

    @OneToOne(mappedBy = "company")
    private Headquarter headquarter;

}
