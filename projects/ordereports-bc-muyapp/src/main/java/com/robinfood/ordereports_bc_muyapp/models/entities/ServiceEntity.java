package com.robinfood.ordereports_bc_muyapp.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Builder
@Entity
@NoArgsConstructor
@Table(name = "services")
public class ServiceEntity {

    private Short companyId;

    private LocalDateTime createdAt;

    @Id
    private Long id;

    private Double maxValueCovered;

    private String name;

    private Double price;

    private Double tax;

    private Short typeId;

    private Short typeServiceId;

    private LocalDateTime updatedAt;

}
