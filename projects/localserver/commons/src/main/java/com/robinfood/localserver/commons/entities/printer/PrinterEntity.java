package com.robinfood.localserver.commons.entities.printer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PrinterEntity {

    private String ip;

    private String name;
}
