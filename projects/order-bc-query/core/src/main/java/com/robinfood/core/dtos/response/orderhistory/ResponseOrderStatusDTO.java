package com.robinfood.core.dtos.response.orderhistory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ResponseOrderStatusDTO {

    private LocalDateTime created;

    private String description;

    private Long id;

    private String name;

    private List<ResponseOrderTraceDTO> trace;

}
