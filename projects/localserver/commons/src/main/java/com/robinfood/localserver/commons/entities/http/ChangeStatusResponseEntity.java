package com.robinfood.localserver.commons.entities.http;

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
public class ChangeStatusResponseEntity {

    private List<String> data;

    private List<String> error;

    private String msg;

    private boolean success;

    private HttpStatus status;
}
