package com.robinfood.configurations.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.dto.v1.ChannelDTO;
import com.robinfood.configurations.models.Channel;
import com.robinfood.configurations.repositories.jpa.ChannelRepository;
import com.robinfood.configurations.services.ChannelService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;

    @Value("${url.base.logo.channel}")
    private String baseUrlLogo;

    public ChannelServiceImpl(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @BasicLog
    @Override
    public Channel findById(Long channelId) {
        return channelRepository.findById(channelId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Channel with id %d not found.", channelId)));
    }

    @BasicLog
    @Override
    public List<ChannelDTO> findByStoreId(Long storeId) {
        List<ChannelDTO> channelDTOList = channelRepository.findByStoreId(storeId);
        log.info("channels found {}", channelDTOList.size());
        return mapChannelLogo(channelDTOList);
    }

    private List<ChannelDTO> mapChannelLogo(List<ChannelDTO> channelDTOList) {
        log.info("adding logo path channels");
        return channelDTOList.stream()
            .peek((ChannelDTO channelDTO) -> channelDTO.setLogo(baseUrlLogo.concat(channelDTO.getLogo())))
            .collect(Collectors.toList());
    }

    @BasicLog
    @Override
    public Page<Channel> list(String filter, Pageable pageable) throws JsonProcessingException {
        log.info("Listing channels");
        return channelRepository
            .findByNameContainingIgnoreCaseAndDeletedAtIsNull(filter, pageable);
    }

}
