package co.com.robinfood.queue.view.controller;

import co.com.robinfood.queue.app.SharedData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

import static co.com.robinfood.queue.app.lasting.GlobalConstants.ParametersSharedBetween.BUTTON_ACCEPTED;
import static co.com.robinfood.queue.app.lasting.GlobalConstants.ParametersSharedBetween.BUTTON_CANCELED;

@Component
public class ConfirmationController implements Initializable {

    @FXML
    private Label txtMessageInfo;

    private Stage stageConfirmationShow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void onAccept(ActionEvent event) {
        SharedData.getInstance()
                .addParameter(BUTTON_ACCEPTED, Boolean.TRUE);
        this.stageConfirmationShow.close();
    }

    @FXML
    void onCancel(ActionEvent event) {
        SharedData.getInstance()
                .addParameter(BUTTON_CANCELED, Boolean.TRUE);
        this.stageConfirmationShow.close();
    }

    public void init(String error, Stage stageConfirmationShow) {
        txtMessageInfo.setText(error);
        this.stageConfirmationShow = stageConfirmationShow;
    }
}
