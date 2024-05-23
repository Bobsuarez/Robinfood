package com.robinfood.core.entities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.Date;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "order_third_parties")
public class OrderThirdPartyEntity {

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    private Date deletedAt;

    @Column(nullable = false)
    private String documentNumber;

    @Column(nullable = false)
    private Long documentType;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String fullName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long orderId;

    private String phone;

    @UpdateTimestamp
    private Date updatedAt;
}