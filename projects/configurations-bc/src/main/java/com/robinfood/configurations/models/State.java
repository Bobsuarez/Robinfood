package com.robinfood.configurations.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.configurations.serializers.StateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import java.util.List;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "states")
@JsonSerialize(using = StateSerializer.class)
public class State extends AbstractBaseEntity {

    private static final long serialVersionUID = -4098609919112144302L;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "country_id")
    private Country country;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
    private List<City> cityList;

    public State(Long id) {
        super(id);
    }
}
