package com.robinfood.configurations.controllers.v1;

import static com.robinfood.configurations.constants.ConfigurationsBCConstants.MIN_CHARACTERS_TO_SEARCH;
import static com.robinfood.configurations.constants.ConfigurationsBCConstants.UNSORTED;
import static com.robinfood.configurations.constants.PermissionsConstants.CONFIGURATIONS_PREFIX;
import static com.robinfood.configurations.constants.PermissionsConstants.LIST_CHANNELS;
import static com.robinfood.configurations.constants.PermissionsConstants.SERVICE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.controllers.v1.docs.ChannelControllerDocs;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.models.ChannelDTO;
import com.robinfood.configurations.dto.v1.pageable.PageResponseDTO;
import com.robinfood.configurations.models.Channel;
import com.robinfood.configurations.services.ChannelService;
import com.robinfood.configurations.utils.PageableUtils;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "v1/channels")
public class ChannelController implements ChannelControllerDocs {

    private final ModelMapper modelMapper;

    private final ChannelService channelService;


    public ChannelController(ModelMapper modelMapper, ChannelService channelService) {
        this.modelMapper = modelMapper;
        this.channelService = channelService;
    }

    @BasicLog
    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('" + SERVICE + "') ")
    public ResponseEntity<ApiResponseDTO<ChannelDTO>> findChannelById(
            @PathVariable("id") @Valid @NotNull @Min(1) Long id
    ) {
        log.info("Getting Channel info with channel id {}", id);

        Channel channel = channelService.findById(id);

        log.info("Channel info retrieved successfully {}.", channel);

        ChannelDTO channelDTO = modelMapper.map(channel, ChannelDTO.class);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.<ChannelDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message("Channel retrieved successfully")
                        .data(channelDTO)
                        .build());
    }

    @BasicLog
    @Override
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(
        "hasRole('" + SERVICE + "')  || hasRole('" + CONFIGURATIONS_PREFIX + LIST_CHANNELS + "')")
    public ResponseEntity<ApiResponseDTO<PageResponseDTO<ChannelDTO>>> listChannel(
        @RequestParam(value = "name", required = false, defaultValue = "") String name,
        @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
        @RequestParam(value = "size", required = false, defaultValue = "10") Integer size, Sort sort)
        throws JsonProcessingException {

        String nameFilter = "";
        if (name.length() >= MIN_CHARACTERS_TO_SEARCH) {
            nameFilter = name;
        }

        Pageable pageable = PageableUtils.createPage(page, size, sort);
        log.info("Request received on channel controller [listChannel] ");
        Page<Channel> channelList = channelService.list(nameFilter, pageable);

        log.info("Generating list channelDTO from list channels.");
        Page<ChannelDTO> channelDTOS = channelList
            .map(channel -> modelMapper.map(channel, ChannelDTO.class));
        log.info("List DTO generated successfully.");

        List<ChannelDTO> channelSorted = new ArrayList<>(channelDTOS.getContent());

        if (!sort.toString().equals(UNSORTED)) {
            sortObject(channelSorted, sort.toString());
        }

        PageResponseDTO<ChannelDTO> pageResponse = PageableUtils
            .createPageResponse(channelDTOS, channelSorted, sort);

        return getApiResponseDTO(pageResponse);
    }


    private ResponseEntity<ApiResponseDTO<PageResponseDTO<ChannelDTO>>> getApiResponseDTO(
        PageResponseDTO<ChannelDTO> pageResponse) {

        log.info("Creating ApiResponse from list Channels");
        ResponseEntity<ApiResponseDTO<PageResponseDTO<ChannelDTO>>> apiResponseDTOReasonsDTO =
            ResponseEntity.status(
                    HttpStatus.OK)
                .body(ApiResponseDTO.<PageResponseDTO<ChannelDTO>>builder()
                    .code(HttpStatus.OK.value())
                    .message("Channels retrieved successfully.")
                    .data(pageResponse)
                    .build());
        log.info("Api response obtained list ChannelDTO successfully.");

        return apiResponseDTOReasonsDTO;

    }

    public static void sortObject(List<ChannelDTO> channelDTOList, String sort) {

        switch (sort) {
            case "name: ASC":
                channelDTOList.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
                break;
            case "name: DESC":
                channelDTOList.sort((o1, o2) -> o2.getName().compareToIgnoreCase(o1.getName()));
                break;
            case "id: DESC":
                channelDTOList.sort((o1, o2) -> o2.getId().compareTo(o1.getId()));
                break;
            default:
                break;
        }

    }

}
