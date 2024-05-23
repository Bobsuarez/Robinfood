package co.com.robinfood.queue.view.controller;

import co.com.robinfood.queue.database.ConnectionMysql;
import co.com.robinfood.queue.view.controller.stageabstract.StageBuilderController;
import co.com.robinfood.queue.view.enums.EViewFxmlUrls;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Component
public class MainController extends StageBuilderController implements Initializable {

    @FXML
    private ImageView imageElectronicBilling;

    @FXML
    private ImageView imageTools;


    private final Duration durationImages = Duration.millis(45000);

    public void setStageController(Stage stage, ConfigurableApplicationContext applicationContext) {
        stageBuilderController(stage, applicationContext);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initImageRotationElectronicBilling();
        initImageRotationTools();
    }

    private void initImageRotationElectronicBilling() {
        transitionImageRotation(imageElectronicBilling);
    }

    private void initImageRotationTools() {
        transitionImageRotation(imageTools);
    }

    private void transitionImageRotation(ImageView imageView) {
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(imageView);
        rotateTransition.setDuration(durationImages);
        rotateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setByAngle(360);
        rotateTransition.play();
    }

    @FXML
    void openElectronicBilling(ActionEvent event) {
        showView(EViewFxmlUrls.ELECTRONIC_BILLING);
    }

    @FXML
    void openMessagingTools(ActionEvent event) {
        showView(EViewFxmlUrls.SEND_JMS);
    }

    @FXML
    private void exit(ActionEvent event) {
        ConnectionMysql.closeConnection();
        log.info("Se cerro el programa");
        System.exit(0);
    }
}
