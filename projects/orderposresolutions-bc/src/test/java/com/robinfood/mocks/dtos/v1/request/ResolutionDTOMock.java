package com.robinfood.mocks.dtos.v1.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.robinfood.dtos.v1.request.ResolutionDTO;
import com.robinfood.dtos.v1.request.ResolutionUpdateDTO;

import java.time.LocalDateTime;

public class ResolutionDTOMock {

    public static ResolutionDTO build() {

        final LocalDateTime localDateTimeNow = LocalDateTime.now();

        final LocalDateTime localDateTime = LocalDateTime.of(
                localDateTimeNow.getYear(),
                localDateTimeNow.getMonth(),
                localDateTimeNow.getDayOfMonth(),
                localDateTimeNow.getHour(),
                localDateTimeNow.getMinute(),
                localDateTimeNow.getSecond()
        );

        return ResolutionDTO.builder()
                .confirmed(false)
                .document("test")
                .endDate(localDateTime.plusDays(1))
                .finalNumber(5)
                .id(1L)
                .invoiceNumber("test")
                .invoiceText("test")
                .name("test")
                .posId(1L)
                .prefix("test")
                .startDate(localDateTime)
                .serial("test")
                .startingNumber(1)
                .status(false)
                .build();
    }

    public static ResolutionUpdateDTO buildResolutionUpdateDTO() {

        final LocalDateTime localDateTimeNow = LocalDateTime.now();

        final LocalDateTime localDateTime = LocalDateTime.of(
                localDateTimeNow.getYear(),
                localDateTimeNow.getMonth(),
                localDateTimeNow.getDayOfMonth(),
                localDateTimeNow.getHour(),
                localDateTimeNow.getMinute(),
                localDateTimeNow.getSecond()
        );

        return ResolutionUpdateDTO.builder()
                .confirmed(false)
                .document("test")
                .endDate(localDateTime.plusDays(1))
                .finalNumber(5)
                .id(1L)
                .invoiceNumber("test")
                .invoiceText("test")
                .name("test")
                .posId(1L)
                .prefix("test")
                .startDate(localDateTime)
                .serial("test")
                .startingNumber(1)
                .status(false)
                .build();
    }


    public static String buildResolution(){

        ResolutionUpdateDTO resolutionDTO = ResolutionUpdateDTO.builder()
                .document("Doc Aut DIAN")
                .endDate(LocalDateTime.parse("2026-11-19T00:00:00"))
                .finalNumber(5)
                .invoiceNumber("18764007762306")
                .invoiceText("Doc Aut DIAN #18764007762306 Fecha 2020/11/19 - 2021/11/19 De 0 hasta 5 Reg. Comun")
                .name("Caja 1 - Muy Calle 83")
                .posId(1L)
                .prefix("RM93")
                .serial("123ABCD")
                .startDate(LocalDateTime.parse("2021-11-19T00:00:00"))
                .startingNumber(0)
                .status(false)
                .confirmed(false)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String jsonBody;

        try {
            jsonBody = objectMapper.writeValueAsString(resolutionDTO);
        } catch (JsonProcessingException e) {
            jsonBody = "{}";
        }
        return jsonBody;
    }


    public static ResolutionDTO buildWithPosId() {

        final LocalDateTime localDateTimeNow = LocalDateTime.now();

        final LocalDateTime localDateTime = LocalDateTime.of(
                localDateTimeNow.getYear(),
                localDateTimeNow.getMonth(),
                localDateTimeNow.getDayOfMonth(),
                localDateTimeNow.getHour(),
                localDateTimeNow.getMinute(),
                localDateTimeNow.getSecond()
        );

        return ResolutionDTO.builder()
                .confirmed(false)
                .document("test")
                .endDate(localDateTime.plusDays(1))
                .finalNumber(5)
                .id(1L)
                .invoiceNumber("test")
                .invoiceText("test")
                .name("test")
                .posId(null)
                .prefix("test")
                .startDate(localDateTime)
                .serial("test")
                .startingNumber(1)
                .status(false)
                .build();
    }
}
