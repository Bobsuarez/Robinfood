package com.robinfood.core.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "pos_resolutions", schema = "orders")
public class PosResolutionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long id;

    @Column(name = "resolution_id")
    public long resolutionId;

    @Column(name = "pos_id")
    public long posId;

    @Column(name = "store_id")
    public long storeId;

    @Column(name = "dic_status_id")
    public int dicStatusId;

    @Column(name = "name")
    public String name;

    @Column(name = "order_number_initial")
    public int orderNumberInitial;

    @Column(name = "prefix")
    public String prefix;

    @Column(name = "invoice_number_initial")
    public int invoiceNumberInitial;

    @Column(name = "invoice_number_end")
    public int invoiceNumberEnd;

    @Column(name = "current")
    public int current;

    @Column(name = "initial_date")
    public LocalDate initialDate;

    @Column(name = "end_date")
    public LocalDate endDate;

    @Column(name = "type_document")
    public String typeDocument;

    @Column(name = "invoice_number_resolutions")
    public String invoiceNumberResolutions;

    @Column(name = "invoice_text")
    public String invoiceText;

    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @Column(name = "updated_at")
    public LocalDateTime updatedAt;
}
