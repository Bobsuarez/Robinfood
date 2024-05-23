package com.robinfood.configurations.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.configurations.serializers.CitySerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cities")
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = CitySerializer.class)
public class City extends AbstractBaseEntity {

    private static final long serialVersionUID = -4098609911031444302L;

    @Column(name = "code")
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "timezone")
    private String timezone;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    public City(Long id) {
        super(id);
    }
}
