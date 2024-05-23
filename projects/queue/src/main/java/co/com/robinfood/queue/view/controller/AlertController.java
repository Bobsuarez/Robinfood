/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.com.robinfood.queue.view.controller;

import co.com.robinfood.queue.view.controller.stageabstract.StageInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Bobsu
 */
@Component
public class AlertController implements Initializable {

    @FXML
    private Label txtMessageInfo;

    private Stage stageAlert;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void onAccept(ActionEvent event) {
        this.stageAlert.close();
    }

    public void init(String error, Stage stageAlert) {
        txtMessageInfo.setText(error);
        this.stageAlert = stageAlert;
    }
}
