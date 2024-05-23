package com.robinfood.configurations.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "brand_company_channels")
public class BrandCompanyChannel extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "brand_company_id", nullable = false)
    private CompanyBrand brandCompany;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "channel_id", nullable = false)
    private Channel channel;

    @Column(name = "icon", nullable = false, length = 100)
    private String icon;

    @Column(name = "banner", nullable = false, length = 100)
    private String banner;

    @Column(name = "color", nullable = false, length = 15)
    private String color;

    @Column(name = "config_url", nullable = false, length = 15)
    private String configUrl;

}
