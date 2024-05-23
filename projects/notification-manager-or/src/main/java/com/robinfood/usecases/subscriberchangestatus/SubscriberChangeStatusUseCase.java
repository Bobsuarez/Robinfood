package com.robinfood.usecases.subscriberchangestatus;

import com.robinfood.dtos.ChangeStatusDTO;
import com.robinfood.dtos.EventDTO;
import com.robinfood.dtos.SubscriberChannelDTO;
import com.robinfood.enums.AppLogsTraceEnum;
import com.robinfood.usecases.getsubscriberbychannelidandflow.GetSubscriberByChannelIdAndFlowUseCase;
import com.robinfood.usecases.getsubscriberbychannelidandflow.IGetSubscriberByChannelIdAndFlowUseCase;
import com.robinfood.usecases.gettokenbusinesscapability.GetTokenBusinessCapabilityUseCase;
import com.robinfood.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.usecases.replicateevent.IReplicateEventUseCase;
import com.robinfood.usecases.replicateevent.ReplicateEventUseCase;
import com.robinfood.usecases.saveevent.ISaveEventUseCase;
import com.robinfood.usecases.saveevent.SaveEventUseCase;
import com.robinfood.utils.LogsUtil;
import com.robinfood.utils.ObjectMapperSingleton;

import static com.robinfood.constants.Constants.CHANGE_STATUS;

public class SubscriberChangeStatusUseCase implements ISubscriberChangeStatusUseCase {

    private final IGetSubscriberByChannelIdAndFlowUseCase getSubscriberByChannelIdAndFlowUseCase;
    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final IReplicateEventUseCase replicateEventUseCase;
    private final ISaveEventUseCase saveEventUseCase;

    public SubscriberChangeStatusUseCase() {
        this.getSubscriberByChannelIdAndFlowUseCase = new GetSubscriberByChannelIdAndFlowUseCase();
        this.getTokenBusinessCapabilityUseCase = new GetTokenBusinessCapabilityUseCase();
        this.replicateEventUseCase = new ReplicateEventUseCase();
        this.saveEventUseCase = new SaveEventUseCase();
    }

    public SubscriberChangeStatusUseCase(
            IGetSubscriberByChannelIdAndFlowUseCase getSubscriberByChannelIdAndFlowUseCase,
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IReplicateEventUseCase replicateEventUseCase,
            ISaveEventUseCase saveEventUseCase
    ) {
        this.getSubscriberByChannelIdAndFlowUseCase = getSubscriberByChannelIdAndFlowUseCase;
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.replicateEventUseCase = replicateEventUseCase;
        this.saveEventUseCase = saveEventUseCase;
    }

    @Override
    public void invoke(ChangeStatusDTO changeStatusDTO, String eventId) {

        LogsUtil.info(
                AppLogsTraceEnum.SUBSCRIBER_HANDLER_CHANGE_STATUS_MAPPER.getMessage(),
                ObjectMapperSingleton.objectToJson(changeStatusDTO)
        );

        final String token = getTokenBusinessCapabilityUseCase.invoke();

        final SubscriberChannelDTO subscriberChannelDTO = getSubscriberByChannelIdAndFlowUseCase.invoke(
                changeStatusDTO.getChannelId(), CHANGE_STATUS, changeStatusDTO.getOrderUuid(), token
        );

        final EventDTO eventDTO = saveEventUseCase.invoke(changeStatusDTO, eventId, token);

        replicateEventUseCase.invoke(
                changeStatusDTO,
                eventDTO,
                subscriberChannelDTO.getSubscribers(),
                token,
                changeStatusDTO.getOrderUuid()
        );
    }
}
