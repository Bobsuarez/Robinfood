package co.com.robinfood.queue.chainsqs;

import co.com.robinfood.queue.persistencia.dto.DataRequestQueue;

public interface IProcessMessageSQS {

    void setNextChain(IProcessMessageSQS iValidateDtoClassChain);

    void convertToString(DataRequestQueue dataRequestQueue);

}
