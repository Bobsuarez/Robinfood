package co.com.robinfood.queue.view.controller;

import co.com.robinfood.queue.view.controller.stageabstract.StageInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowJsonController implements Initializable {

    @FXML
    private TextArea txtMessageIngress;

    private Stage stageJsonShow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void onAccept(ActionEvent event) {
        this.stageJsonShow.close();
    }

    public void init(String error, Stage stageAlert) {
        txtMessageIngress.setText(error);
        this.stageJsonShow = stageAlert;
    }
}
