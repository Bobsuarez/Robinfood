package co.com.robinfood.queue.chainqueue;

import co.com.robinfood.queue.persistencia.dto.DataRequestQueue;

public interface IProcessMessageQueue {

    void setNextChain(IProcessMessageQueue iValidateDtoClassChain);

    void convertToString(DataRequestQueue dataRequestQueue);

}
