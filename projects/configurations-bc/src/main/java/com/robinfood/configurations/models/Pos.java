package com.robinfood.configurations.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.configurations.serializers.PosSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pos")
@JsonSerialize(using = PosSerializer.class)
public class Pos extends AbstractBaseEntity {

    private static final long serialVersionUID = -4098610919121444302L;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;

    private Long id;

    @ManyToOne
    @JoinColumn(name = "stores_id", nullable = false)
    private Store store;

    @ManyToOne
    @JoinColumn(name = "pos_type_id", nullable = false)
    private PosType posType;

    @Column(name = "status", nullable = false)
    private Long status;

    @OneToMany(mappedBy = "pos")
    private List<Resolution> resolutionList;

    @ToString.Exclude
    @OneToMany(mappedBy = "pos")
    private List<PosPaymentMethod> posPaymentMethodList;

}
