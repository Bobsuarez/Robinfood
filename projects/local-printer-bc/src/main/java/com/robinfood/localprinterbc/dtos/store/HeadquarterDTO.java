package com.robinfood.localprinterbc.dtos.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class HeadquarterDTO {
    private Long id;
    private String phone;
    private String email;
    private String address;
}
