package com.robinfood.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "order_flags")
public class OrderFlagEntity {

    private String flag;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Long orderId;

}
