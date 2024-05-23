package com.robinfood.localserver.commons.dtos.sathub.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlType(propOrder = {
    "cnpj",
    "ie",
    "im",
    "cRegTribISSQN",
    "indRatISSQN"
})
public final class EmitResponse {
    private String CNPJ;
    private String ie;
    private String im;
    private String cRegTribISSQN;
    private String indRatISSQN;

    @JsonProperty("CNPJ")
    @XmlElement(name = "CNPJ")
    public String getCnpj() {
        return CNPJ;
    }

    public void setCnpj(String cnpj) {
        this.CNPJ = cnpj;
    }

    @JsonProperty("IE")
    @XmlElement(name = "IE")
    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    @JsonProperty("IM")
    @XmlElement(name = "IM")
    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    @JsonProperty("cRegTribISSQN")
    @XmlElement(name = "cRegTribISSQN")
    public String getcRegTribISSQN() {
        return cRegTribISSQN;
    }

    public void setcRegTribISSQN(String cRegTribISSQN) {
        this.cRegTribISSQN = cRegTribISSQN;
    }
}
