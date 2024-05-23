package com.robinfood.configurationsposbc.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserStorePosId implements Serializable {

    @Column(name = "pos_id", nullable = false)
    private Long posId;

    @Column(name = "store_id", nullable = false)
    private Long storeId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStorePosId that = (UserStorePosId) o;
        return posId.equals(that.posId) && storeId.equals(that.storeId) && userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(posId, storeId, userId);
    }
}
