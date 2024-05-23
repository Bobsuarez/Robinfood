package co.com.robinfood.queue.utils;

import co.com.robinfood.queue.Exceptions.ValidateFieldsException;

import java.util.Objects;

public class ValidatedUtil {

    public static void validateString(Object object) {

        if (Objects.isNull(object) || Objects.equals(object, "")) {
            throw new ValidateFieldsException("El valor es nulo no es admitido");
        }
    }

}
