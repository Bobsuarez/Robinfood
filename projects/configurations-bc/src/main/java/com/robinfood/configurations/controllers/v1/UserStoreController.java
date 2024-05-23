package com.robinfood.configurations.controllers.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.controllers.v1.docs.UserStoreDocs;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.models.UserStoreDTO;
import com.robinfood.configurations.models.UserStore;
import com.robinfood.configurations.services.UserStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import static com.robinfood.configurations.constants.PermissionsConstants.SERVICE;

@Slf4j
@RestController
@RequestMapping(value = "v1/user-stores")
public class UserStoreController implements UserStoreDocs {

    private final UserStoreService userStoreService;

    private final ObjectMapper objectMapper;

    public UserStoreController(UserStoreService userStoreService,
                               ObjectMapper objectMapper){
        this.userStoreService = userStoreService;
        this.objectMapper = objectMapper;
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    @BasicLog
    @Override
    @GetMapping(value = "/users/{userId}/stores", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + SERVICE + "') ")
    public ResponseEntity<ApiResponseDTO<UserStoreDTO>> findStoreByUserId(
            @PathVariable("userId") @Valid @NotNull @Min(1) Long userId) throws JsonProcessingException {

        log.info("Finding store by user id {}", userId);

        UserStore userStore = userStoreService.findStoreByUserId(userId);
        log.info("Store info retrieved successfully {}.", userStore);

        UserStoreDTO userStoreDTO = objectMapper.convertValue(userStore, UserStoreDTO.class);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.<UserStoreDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message("Store by user id retrieved successfully")
                        .data(userStoreDTO)
                        .build());
    }
}
