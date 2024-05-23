package com.robinfood.configurations.dto.v1.pageable;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDTO<T> implements Serializable {

    private static final long serialVersionUID = 8946373775756716550L;

    @JsonProperty
    private List<T> content;

    @JsonProperty
    private PageableDTO pageable;

    @JsonProperty
    private int totalPages;

    @JsonProperty
    private long totalElements;

    @JsonProperty
    private boolean last;

    @JsonProperty
    private int numberOfElements;

    @JsonProperty
    private int number;

    @JsonProperty
    private int size;

    @JsonProperty
    private SortDTO sort;

    @JsonProperty
    private boolean first;

    @JsonProperty
    private boolean empty;

}
