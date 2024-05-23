package com.robinfood.localserver.commons.dtos.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class ChangeStatusResponseDTO {

    private List<String> data;

    private List<String> error;

    private String msg;

    private String statusCode;

    private boolean success;

    private HttpStatus status;
}
