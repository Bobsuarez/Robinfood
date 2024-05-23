package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class FlowEventLogsEntity implements Serializable {

    private LocalDateTime created_at;
    private String event_id;
    private Long flow_id;
    private Long id;
    private String payload;
    private LocalDateTime updated_at;

}
