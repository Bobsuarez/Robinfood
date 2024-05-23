package com.robinfood.localorderbc.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderCommandExecutionId implements Serializable {

    @Column(name = "command_id", nullable = false)
    private Long commandId;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderCommandExecutionId that = (OrderCommandExecutionId) o;
        return orderId.equals(that.orderId) && commandId.equals(that.commandId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, commandId);
    }

}
