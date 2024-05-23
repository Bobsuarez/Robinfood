package com.robinfood.localserver.commons.entities.printer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PrinterConfigurationEntity {

    List<PrinterEntity> printers;
}
