package com.robinfood.taxes.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.taxes.serializers.tax.ArticleFamilySerializer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "article_families")
@JsonSerialize(using = ArticleFamilySerializer.class)
public class ArticleFamily extends AbstractBaseEntity {

    @Column(name = "product_type_id")
    private Long productTypeId;

    @Column(name = "article_id")
    private Long articleId;

    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    @Column(name = "status")
    private Integer status;

    public ArticleFamily(Long id) {
        super(id);
    }
}
