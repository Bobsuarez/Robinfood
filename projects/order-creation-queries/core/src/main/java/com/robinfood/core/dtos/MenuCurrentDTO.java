package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class MenuCurrentDTO {

    private List<MenuHallsDTO> halls;

    private String name;

}
