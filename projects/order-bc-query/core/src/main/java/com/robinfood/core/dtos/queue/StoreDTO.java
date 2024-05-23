package com.robinfood.core.dtos.queue;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO implements Serializable {

    private static final long serialVersionUID = -4401210888449376257L;

    private Long id;

    private String name;
}
