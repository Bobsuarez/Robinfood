package com.robinfood.localserver.commons.enums.logs;

public enum SatHubLogEnum {

    XML_SEND_TO_SATHUB("200", "XML Send to SATHUB"),
    XML_TRANSMISSION_SUCCESSFUL("201", "XML Transmission Successful"),
    SAT_UNAVAILABLE("150", "Sat Unavailable"),
    SAT_HUB_ERROR("151", "Communication cannot be established"),
    XML_REJECTED("152","XML Rejected"),
    XML_TRANSMISSION_CANCEL_SUCCESSFUL("202", "XML Cancel Sale Transmission Successful"),
    XML_SEND_CANCEL_SALE_TO_SATHUB("203", "XML Send cancel sale to SATHUB"),
    XML_CANCEL_REJECTED("153","XML Cancel Sale Rejected");


    private String code;
    private String message;

    SatHubLogEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage(){
        return this.code + " " + this.message;
    }

}
