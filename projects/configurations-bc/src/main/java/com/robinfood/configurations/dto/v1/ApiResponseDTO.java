package com.robinfood.configurations.dto.v1;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ApiResponseDTO")
public class ApiResponseDTO<T> implements Serializable {

    private static final long serialVersionUID = -4997447450628067757L;

    @Schema(example = "1")
    @JsonProperty(value = "code")
    private int code;

    @Schema(example = "Process completed successfully")
    @JsonProperty(value = "message")
    private String message;

    @Schema(example = "Error processing data")
    @JsonProperty(value = "error")
    private List<String> error;

    @JsonProperty(value = "data")
    private T data;

    @Schema(example = "additional information")
    @JsonProperty(value = "metaData")
    private Object metaData;

}

