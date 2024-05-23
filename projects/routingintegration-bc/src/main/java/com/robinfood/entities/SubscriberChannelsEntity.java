package com.robinfood.entities;

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
public class SubscriberChannelsEntity implements Serializable {

    private LocalDateTime created_at;
    private Long channel_id;
    private Long flow_id;
    private Long id;
    private Long subscriber_id;
    private LocalDateTime updated_at;

}
