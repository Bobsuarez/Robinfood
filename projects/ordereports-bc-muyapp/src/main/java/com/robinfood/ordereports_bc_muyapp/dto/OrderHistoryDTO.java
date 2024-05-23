package com.robinfood.ordereports_bc_muyapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class OrderHistoryDTO {

    private LocalDateTime createdAt;

    private LocalDateTime date;

    private Long id;

    private String observation;

    private Integer orderId;

    private Short orderStatusId;

    private Long userId;

}
