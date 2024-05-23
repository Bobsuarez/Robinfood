package com.robinfood.localserver.commons.dtos.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.TimeZone;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class ApiResponseDTO<T> {

    private Integer code = HttpStatus.OK.value();

    private T data;

    private String locale = TimeZone.getTimeZone("Z").toString();

    private String message = HttpStatus.OK.getReasonPhrase();

    private String status = HttpStatus.OK.getReasonPhrase();
}
