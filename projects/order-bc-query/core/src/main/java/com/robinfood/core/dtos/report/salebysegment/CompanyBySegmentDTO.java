package com.robinfood.core.dtos.report.salebysegment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@Getter
@Setter
public class CompanyBySegmentDTO {

    @NotBlank
    @Schema(example = "1")
    @Size(min = 1)
    @JsonProperty("id")
    public Long idCompany;

    @JsonProperty("brands")
    public List<BrandSegmentDTO> brandDTOList;

    @JsonProperty("channels")
    public List<ChannelSegmentDTO> channelDTOList;

    @JsonProperty("paymentMethods")
    public List<PaymentMethodSegmentDTO> paymentMethodsList;

    @JsonProperty("stores")
    public List<StoreSegmentDTO> storesList;

}
