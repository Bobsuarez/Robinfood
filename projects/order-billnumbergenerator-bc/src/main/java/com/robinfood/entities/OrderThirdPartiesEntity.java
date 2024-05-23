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
public class OrderThirdPartiesEntity {

    private LocalDateTime created_at;

    private LocalDateTime deleted_at;

    private String document_number;

    private int document_type;

    private String email;

    private String full_name;

    private int id;

    private Long order_id;

    private String phone;

    private LocalDateTime updated_at;

}
