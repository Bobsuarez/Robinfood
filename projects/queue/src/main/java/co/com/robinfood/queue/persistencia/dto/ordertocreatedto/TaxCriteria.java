package co.com.robinfood.queue.persistencia.dto.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaxCriteria {
    @JsonProperty("flow_id")
    public Integer flowId;
    @JsonProperty("platform_id")
    public Integer platformId;
    @JsonProperty("store")
    public Store store;
    @JsonProperty("country_id")
    public Integer countryId;
    @JsonProperty("brand_id")
    public Integer brandId;
}