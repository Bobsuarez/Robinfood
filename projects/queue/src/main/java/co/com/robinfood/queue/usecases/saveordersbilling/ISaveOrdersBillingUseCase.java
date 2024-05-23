package co.com.robinfood.queue.usecases.saveordersbilling;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface ISaveOrdersBillingUseCase {

    void invoke(List<JsonNode> requestPayloadList);
}
