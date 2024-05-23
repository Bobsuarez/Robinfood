package com.robinfood.configurations.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stores")
public class Store extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_identifier_type_id", nullable = false)
    private StoreIdentifierType storeIdentifierType;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "zones_id", nullable = false)
    private Zone zones;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_type_id", nullable = false)
    private StoreType storeType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "store_multi_brand_id", nullable = false)
    private StoreMultiBrand multiBrand;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "location", nullable = false, length = 20)
    private String location;

    @Column(name = "phone", nullable = false, length = 25)
    private String phone;

    @Column(name = "email", nullable = false, length = 60)
    private String email;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "internal_name", nullable = false)
    private String internalName;

    @Column(name = "identifier", length = 45)
    private String identifier;

    @Column(name = "uuid", length = 45)
    private String uuid;

    @Column(name = "currency_type", length = 45)
    private String currencyType;

    @Column(name = "currency_symbol", length = 45)
    private String currencySymbol;

    @Column(name = "postal_code", length = 45)
    private String postalCode;

    @Column(name = "tax_regime", length = 45)
    private String taxRegime;

}
