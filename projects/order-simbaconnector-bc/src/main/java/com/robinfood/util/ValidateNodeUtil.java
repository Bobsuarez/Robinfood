package com.robinfood.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.robinfood.constants.HttpStatusConstants;
import com.robinfood.exceptions.ApiClientsException;
import com.robinfood.mappers.ResponseMapper;

import static com.robinfood.constants.GeneralConstants.DATOS_BASICOS;
import static com.robinfood.constants.GeneralConstants.DEFAULT_BOOLEAN_TRUE;
import static com.robinfood.constants.GeneralConstants.DOC_ELECTRONICO_ESTENDIDO;
import static com.robinfood.constants.GeneralConstants.RESPUESTA_UNITARIA;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_DATA_NOT_FOUND_COMPLEMENT;

public class ValidateNodeUtil {

    private ValidateNodeUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void validateNode(JsonNode jsonNodeResponseSimba) {

        if (!isValidNode(jsonNodeResponseSimba, RESPUESTA_UNITARIA)) {
            throwException(RESPUESTA_UNITARIA);
        }

        JsonNode respuestaUnitaria = jsonNodeResponseSimba.get(RESPUESTA_UNITARIA);

        if (!isValidNode(respuestaUnitaria, DOC_ELECTRONICO_ESTENDIDO)) {
            throwException(DOC_ELECTRONICO_ESTENDIDO);
        }

        JsonNode docElectronicoExtendido = respuestaUnitaria.get(DOC_ELECTRONICO_ESTENDIDO);

        if (!isValidNode(docElectronicoExtendido, DATOS_BASICOS)) {
            throwException(DATOS_BASICOS);
        }

    }

    private static void throwException(String node) {
        throw new ApiClientsException(
                ResponseMapper
                        .buildWithError(HttpStatusConstants.SC_BAD_REQUEST.getCodeHttp(),
                                ERROR_DATA_NOT_FOUND_COMPLEMENT
                                        .replaceComplement("Object in response simba not exists [" + node + "]"),
                                DEFAULT_BOOLEAN_TRUE),
                ERROR_DATA_NOT_FOUND_COMPLEMENT
                        .replaceComplement("Object in response simba not exists [" + node + "]"));

    }

    private static boolean isValidNode(JsonNode parentNode, String childNodeName) {
        return parentNode.has(childNodeName) && !parentNode.get(childNodeName).isNull();
    }


}
