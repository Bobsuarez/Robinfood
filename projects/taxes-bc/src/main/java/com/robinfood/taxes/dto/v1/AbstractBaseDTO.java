package com.robinfood.taxes.dto.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractBaseDTO implements Serializable {

    private static final long serialVersionUID = 5702008634463519032L;

    private Long id;

    @JsonIgnore
    private transient LocalDateTime createdAt;

    @JsonIgnore
    private transient LocalDateTime updatedAt;

    @JsonIgnore
    private transient LocalDateTime deletedAt;

}

