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
public class SubscriberEventHistoryLogsEntity implements Serializable {

    private LocalDateTime created_at;
    private Integer event_dispatched;
    private Long flow_event_log_id;
    private Long id;
    private Long subscriber_id;
    private LocalDateTime updated_at;

}
