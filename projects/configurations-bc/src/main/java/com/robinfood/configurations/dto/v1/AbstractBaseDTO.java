package com.robinfood.configurations.dto.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;

@SuperBuilder(toBuilder = true)
@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractBaseDTO implements Serializable {

    private static final long serialVersionUID = 3311027123877852284L;

    protected Long id;

    @JsonIgnore
    private transient LocalDateTime createdAt;

    @JsonIgnore
    private transient LocalDateTime updatedAt;

    @JsonIgnore
    private transient LocalDateTime deletedAt;

    protected AbstractBaseDTO(Long id) {
        this.id = id;
    }
}
