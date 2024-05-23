package com.robinfood.enums;

import static com.robinfood.constants.GeneralConstants.DEFAULT_SPACE_STRING_EMPTY;

public enum AppLogsEnum {

    PROGRAM_START("0001", "Starting Lambda function {}"),

    CREATE_RESOLUTION_DATA("0002", "Entered path parameters with store id: {} list object: {}"),

    RESPONSE_DATA_OK("0003", "Data response OK {} , Response : {}"),

    CREATED_RESOLUTION_SUCCESSFULLY("0004", "Resolutions created successfully"),

    DATA_RESPONSE_REPOSITORY("0005", "Data obtained from queries {}"),

    UPDATE_RESOLUTION_SUCCESSFULLY("0006","Updated resolution successfully {}"),

    UPDATE_RESOLUTION_DATA("0007", "Entered path value : {} and resolution id : {}"),

    UPDATE_RESOLUTION_START_PROCESS_DATA("0008", "Start update a resolution in the status = {} and resolution id {}"),

    UPDATE_INVOKE("0009", "Invoke update parameter resolutionId = {} and resolution = {}"),

    UPDATE_SUCCESSFULLY("0010","Updated resolution successfully"),

    UPDATE_PARAMETERS_REQUEST("0011","resolutionId = {},  body = {}"),

    SEARCH_SUCCESSFULLY("0012","Search resolution successfully"),

    SEARCH_PARAMETERS_REQUEST("0013"," input parameters to check resolution = {}"),

    UPDATE_PARAMETERS_RESOLUTION_AND_POS_REQUEST("0014","update pos_resolution by id = {}, posId = {}"),

    UPDATE_BY_POS_ID_INVOKE("0014", "Invoke update parameter resolutionId = {} and posId = {}"),

    INPUT_PARAMETER_FIND_ALL_RESOLUTIONS("0015", "Parameters: page = {}, size = {}, status = {}, " +
            "valueCustomFilter = {}, orderByEndDate = {}, withPos = {}, resolutionId = {}");


    private final String code;
    private final String message;

    AppLogsEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageWithCode() {
        return code + DEFAULT_SPACE_STRING_EMPTY + message;
    }
}
