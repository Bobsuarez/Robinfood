package com.robinfood.core.entities.userentities;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "UserDetailEntity")
public class UserDetailEntity {
    private Long id;

    private String firstName;

    private String lastName;

    private String phoneCode;

    private String phoneNumber;

    private String email;

    private Boolean isEmployee;

    private List<Counter> counters;

    @Builder
    @Data
    public static class Counter {
        private String key;

        private Long reference;

        private Long value;
    }
}
