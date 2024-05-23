package com.robinfood.configurations.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.configurations.serializers.ResolutionSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import java.time.LocalDateTime;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder(toBuilder = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "resolutions")
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = ResolutionSerializer.class)
public class Resolution extends AbstractBaseEntity {

    private static final long serialVersionUID = -4098609919111149792L;

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT")
    private Integer status;

    @Column(name = "starting_number", nullable = false)
    private String startingNumber;

    @Column(name = "final_number", nullable = false)
    private String finalNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "prefix", nullable = false)
    private String prefix;

    @Column(name = "invoice_text", nullable = false)
    private String invoiceText;

    @Column(name = "serial")
    private String serial;

    @Column(name = "invoice_number_resolutions")
    private String invoiceNumberResolutions;

    @Column(name = "document")
    private String document;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "pos_id", insertable = false, updatable = false)
    private Pos pos;

    @Column(name = "pos_id")
    private Long posId;
}
