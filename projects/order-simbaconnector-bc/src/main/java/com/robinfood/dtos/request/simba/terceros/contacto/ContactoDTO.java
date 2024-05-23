package com.robinfood.dtos.request.simba.terceros.contacto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactoDTO {

    @JsonProperty(value = "Nombre")
    private GenericContactoDTO nombre;

    @JsonProperty(value = "Telefono")
    private GenericContactoDTO telefono;

    @JsonProperty(value = "Email")
    private GenericContactoDTO email;
}
