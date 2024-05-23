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
public class SubscriberTypesEntity implements Serializable {

    private LocalDateTime created_at;
    private String description;
    private Long id;
    private String name;
    private LocalDateTime updated_at;

}
