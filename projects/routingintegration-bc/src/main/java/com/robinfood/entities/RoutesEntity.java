package com.robinfood.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class RoutesEntity {

    private Long channel_id;
    private LocalDateTime created_at;
    private String description;
    private Long flow_id;
    private Long id;
    private String name;
    private LocalDateTime updated_at;
    private String url;

}
