package com.robinfood.configurations.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.configurations.serializers.MultiBrandStoreSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store_multi_brands")
@JsonSerialize(using = MultiBrandStoreSerializer.class)
public class StoreMultiBrand extends AbstractBaseEntity {

    private static final long serialVersionUID = -4098610719121444307L;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "name", nullable = false)
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "multiBrand")
    private List<Store> store;
}
