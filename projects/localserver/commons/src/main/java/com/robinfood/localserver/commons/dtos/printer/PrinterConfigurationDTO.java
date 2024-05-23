package com.robinfood.localserver.commons.dtos.printer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PrinterConfigurationDTO {

    List<PrinterDTO> printers;
}
