package com.robinfood.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "services")
public class ServiceEntity {

    private Long companyId;

    private LocalDateTime createdAt;
    @Id
    private Long id;

    private Double maxValueCovered;

    private String name;

    private Double price;

    private Double tax;

    private Long typeId;

    private Long typeServiceId;

    private LocalDateTime updatedAt;

}
