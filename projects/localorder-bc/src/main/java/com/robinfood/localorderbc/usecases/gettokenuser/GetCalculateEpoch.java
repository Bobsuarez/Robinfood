package com.robinfood.localorderbc.usecases.gettokenuser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.time.Instant.ofEpochSecond;

@Service
@Slf4j
public class GetCalculateEpoch implements IGetCalculateEpoch {

    @Override
    public Long minutesExpiration(Long expiresInEpoch) {
        LocalDateTime dateNow = LocalDateTime.now();
        LocalDateTime dateToken = LocalDateTime
                .ofInstant(ofEpochSecond(expiresInEpoch), ZoneId.systemDefault());

        return Duration.between(dateNow, dateToken).toMinutes();
    }
}
