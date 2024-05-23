package com.robinfood.configurations.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.dto.v1.ChannelDTO;
import com.robinfood.configurations.models.Channel;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChannelService {

    Channel findById(Long channelId);

    List<ChannelDTO> findByStoreId(Long storeId);

    Page<Channel> list(String filter, Pageable pageable) throws JsonProcessingException;
}
