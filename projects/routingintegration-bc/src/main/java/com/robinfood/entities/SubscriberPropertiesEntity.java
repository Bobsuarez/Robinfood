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
public class SubscriberPropertiesEntity implements Serializable {

    private LocalDateTime created_at;
    private String description;
    private Long id;
    private String key;
    private Long subscriber_id;
    private LocalDateTime updated_at;
    private String value;

}
