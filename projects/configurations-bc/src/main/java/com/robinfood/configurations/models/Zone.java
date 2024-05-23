package com.robinfood.configurations.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.configurations.serializers.ZoneSerializer;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Table(name = "zones")
@JsonSerialize(using = ZoneSerializer.class)
public class Zone extends AbstractBaseEntity {

    private static final long serialVersionUID = -450797321428176779L;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    public Zone(Long id) {
        super(id);
    }

}
