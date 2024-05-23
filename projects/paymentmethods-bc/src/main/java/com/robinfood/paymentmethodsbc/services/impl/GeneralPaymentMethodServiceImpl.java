package com.robinfood.paymentmethodsbc.services.impl;

import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.dto.api.paymentmethods.GeneralPaymentMethodDetailsDTO;
import com.robinfood.paymentmethodsbc.exceptions.ResourceNotFoundException;
import com.robinfood.paymentmethodsbc.mappers.PaymentMethodMapper;
import com.robinfood.paymentmethodsbc.model.GeneralPaymentMethod;
import com.robinfood.paymentmethodsbc.model.PaymentMethodStoreFlowChannel;
import com.robinfood.paymentmethodsbc.model.Terminal;
import com.robinfood.paymentmethodsbc.model.TerminalParameter;
import com.robinfood.paymentmethodsbc.repositories.PaymentMethodStoreFlowChannelsCachedRepository;
import com.robinfood.paymentmethodsbc.repositories.TerminalPaymentMethodSettingsCachedRepository;
import com.robinfood.paymentmethodsbc.repositories.TerminalsRepository;
import com.robinfood.paymentmethodsbc.services.GeneralPaymentMethodService;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.DASH;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
@Service
public class GeneralPaymentMethodServiceImpl implements GeneralPaymentMethodService {

    private final PaymentMethodStoreFlowChannelsCachedRepository paymentMethodStoreFlowChannelsRepository;
    private final TerminalPaymentMethodSettingsCachedRepository terminalPaymentMethodSettingsRepository;
    private final TerminalsRepository terminalsRepository;

    public GeneralPaymentMethodServiceImpl(
        PaymentMethodStoreFlowChannelsCachedRepository paymentMethodStoreFlowChannelsRepository,
        TerminalPaymentMethodSettingsCachedRepository terminalPaymentMethodSettingsRepository,
        TerminalsRepository terminalsRepository
    ) {
        this.paymentMethodStoreFlowChannelsRepository = paymentMethodStoreFlowChannelsRepository;
        this.terminalPaymentMethodSettingsRepository = terminalPaymentMethodSettingsRepository;
        this.terminalsRepository = terminalsRepository;
    }

    @BasicLog
    @Override
    @SneakyThrows
    public List<GeneralPaymentMethodDetailsDTO> getPaymentMethodsByStoreChannelFlowAndTerminal(
        final Long storeId,
        final Long channelId,
        final Long flowId,
        final String terminalUuid
    ) {
        final Terminal terminal = getTerminalByUuid(terminalUuid);

        final List<PaymentMethodStoreFlowChannel> result =
            paymentMethodStoreFlowChannelsRepository.findByStoreIdAndChannelIdAndFlowIdAndStatus(
                storeId, channelId, flowId, PaymentMethodStoreFlowChannel.STATUS_ENABLED
            );

        if (result.isEmpty()) {
            throw new ResourceNotFoundException(
                result.getClass().getSimpleName().concat("<")
                    .concat(PaymentMethodStoreFlowChannel.class.getSimpleName()).concat(">"),
                EMPTY.concat(String.valueOf(storeId)).concat(DASH)
                    .concat(String.valueOf(channelId)).concat(DASH)
                    .concat(String.valueOf(flowId))
            );
        }

        return result.stream()
            .map(PaymentMethodStoreFlowChannel::getGeneralPaymentMethod)
            .map((GeneralPaymentMethod generalPaymentMethod) ->
                PaymentMethodMapper.toGeneralPaymentMethodDetailsDTO(
                    generalPaymentMethod,
                    getTerminalSettings(terminal, generalPaymentMethod.getPaymentGateway().getId())
                )
            ).collect(Collectors.toList());
    }

    private Terminal getTerminalByUuid(String terminalUuid) {
        log.info("querying terminal with uuid {}", terminalUuid);

        return terminalsRepository.findByUuidAndStatusAndDeletedAt(
            terminalUuid,
            Terminal.STATUS_ENABLED,
            null
        ).orElseThrow(
            () -> new ResourceNotFoundException(Terminal.class.getSimpleName(), terminalUuid)
        );
    }

    private List<GeneralPaymentMethodDetailsDTO.TerminalPaymentMethodSettingDetailDTO> getTerminalSettings(
        final Terminal terminalModel,
        final Long paymentGatewayId
    ) {

        return Optional.ofNullable(terminalModel)
            .stream()
            .map((Terminal terminal) ->
                terminalPaymentMethodSettingsRepository.findByTerminalIdAndPaymentGatewayIdAndTerminalParameterVisible(
                    terminal.getId(),
                    paymentGatewayId,
                    TerminalParameter.VISIBLE_ENABLED
                )
            )
            .flatMap(Collection::stream)
            .map(PaymentMethodMapper::toTerminalPaymentMethodSettingDetailDTO)
            .collect(Collectors.toList());
    }
}
