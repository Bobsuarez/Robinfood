package com.robinfood.configurations.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.configurations.serializers.CountrySerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import java.util.List;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "countries")
@JsonSerialize(using = CountrySerializer.class)
public class Country extends AbstractBaseEntity {

    private static final long serialVersionUID = -4010609919121444302L;

    @Column(name = "name", nullable = false)
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<State> stateList;

    public Country(Long id) {
        super(id);
    }
}
