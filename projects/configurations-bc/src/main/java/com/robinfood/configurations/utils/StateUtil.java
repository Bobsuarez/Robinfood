package com.robinfood.configurations.utils;

import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.dto.v1.models.StateDTO;
import com.robinfood.configurations.models.State;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class StateUtil {

    private StateUtil() {
        // StateUtil constructor
    }

    @BasicLog
    public static StateDTO buildStateDTO(State state) {
        log.info("Generating mappedStore for State {}", JsonUtils.convertToJson(state));

        return StateDTO.builder()
            .id(state.getId())
            .name(state.getName())
            .build();
    }

}
