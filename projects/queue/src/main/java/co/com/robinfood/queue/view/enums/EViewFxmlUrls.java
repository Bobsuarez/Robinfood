package co.com.robinfood.queue.view.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum EViewFxmlUrls {

    ALERT("/view/fxml/Alert.fxml"),
    CONFIRMATION("/view/fxml/Confirmation.fxml"),
    MAIN("/view/fxml/Main.fxml"),
    SEND_JMS("/view/fxml/DeliveryQueue.fxml"),
    SHOW_JSON_VIEW("/view/fxml/ShowJson.fxml"),
    ELECTRONIC_BILLING("/view/fxml/ElectronicBilling.fxml");

    private final String frontUrl;

    public static String getFrontUrl(EViewFxmlUrls viewEnums) {

        return Arrays.stream(values())
                .filter(data -> data.equals(viewEnums))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(("Invalid")))
                .getFrontUrl();

    }
}
