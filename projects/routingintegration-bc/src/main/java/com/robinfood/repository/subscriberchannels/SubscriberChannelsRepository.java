package com.robinfood.repository.subscriberchannels;

import com.robinfood.constants.QueryConstants;
import com.robinfood.database.DatabaseManager;
import com.robinfood.entities.SubscriberChannelsEntity;
import lombok.SneakyThrows;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class SubscriberChannelsRepository extends DatabaseManager implements ISubscriberChannelsRepository {

    private static SubscriberChannelsRepository instance;

    public static SubscriberChannelsRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SubscriberChannelsRepository();
        }
        return instance;
    }

    @Override
    @SneakyThrows
    public List<SubscriberChannelsEntity> findByChannelIdAndFlowId(Long channelId, Long flowId) {

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("channel_id", channelId);
        parameters.put("flow_id", flowId);
        return Optional.ofNullable(executeQueryList(
                QueryConstants.SUBSCRIBERS_SELECT_ALL_WHERE_CHANNEL_ID_AND_FLOW_ID,
                SubscriberChannelsEntity.class,
                parameters
        )).orElse(Collections.emptyList());
    }
}
