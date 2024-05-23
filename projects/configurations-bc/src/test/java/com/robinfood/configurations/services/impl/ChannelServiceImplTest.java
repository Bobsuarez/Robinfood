package com.robinfood.configurations.services.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.robinfood.configurations.dto.v1.ChannelDTO;
import com.robinfood.configurations.models.Channel;
import com.robinfood.configurations.repositories.jpa.ChannelRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class ChannelServiceImplTest {

    @InjectMocks
    private ChannelServiceImpl channelService;

    @Mock
    private Environment environment;

    @Mock
    private ChannelRepository channelRepository;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(channelService, "baseUrlLogo", "http://robinfood.com/");
    }

    @Test
    void test_FindById_Should_ReturnChannel_When_ChannelExists() {

        when(channelRepository.findById(anyLong()))
                .thenReturn(Optional.of(new Channel()));

        assertAll(() -> channelService.findById(1L));

        verify(channelRepository, times(1))
                .findById(anyLong());
    }

    @Test
    void test_FindByStoreId_Should_ReturnChannel_When_ChannelExists() {

        when(channelRepository.findByStoreId(anyLong()))
            .thenReturn(List.of(new ChannelDTO(1L,"domicilio", "icon.png")));

        assertAll(() -> channelService.findByStoreId(1L));

        verify(channelRepository, times(1))
            .findByStoreId(anyLong());
    }

    @Test
    void test_findById_Should_ThrowEntityNotFoundException_When_ChannelNotFound() {

        when(channelRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(
                EntityNotFoundException.class,
                () -> channelService.findById(1L)
        );

        verify(channelRepository, times(1)).findById(anyLong());
    }

    @Test
    void test_list_Should_ListChannel_When_Correct_Request() {

        when(channelRepository.findByNameContainingIgnoreCaseAndDeletedAtIsNull(
            anyString(), any(Pageable.class)))
            .thenReturn(new PageImpl<>(List.of(), PageRequest.of(0, 10), 10));

        assertAll(() -> channelService.list("name", PageRequest.of(0, 10)));

        verify(channelRepository, times(1))
            .findByNameContainingIgnoreCaseAndDeletedAtIsNull(anyString(), any(Pageable.class));
    }

}