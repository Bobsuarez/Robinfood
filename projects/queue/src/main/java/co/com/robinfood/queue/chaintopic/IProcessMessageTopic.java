package co.com.robinfood.queue.chaintopic;

import co.com.robinfood.queue.persistencia.dto.DataRequestQueue;

public interface IProcessMessageTopic {

    void setNextChain(IProcessMessageTopic iValidateDtoClassChain);

    void convertToString(DataRequestQueue dataRequestQueue);

}
