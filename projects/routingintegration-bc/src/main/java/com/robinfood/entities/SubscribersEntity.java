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
public class SubscribersEntity implements Serializable {

    private LocalDateTime created_at;
    private String description;
    private Long id;
    private String name;
    private Long subscriber_type_id;
    private LocalDateTime updated_at;
}
