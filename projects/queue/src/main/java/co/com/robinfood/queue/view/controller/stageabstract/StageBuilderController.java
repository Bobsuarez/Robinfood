package co.com.robinfood.queue.view.controller.stageabstract;

import co.com.robinfood.queue.utils.JsonUtils;
import co.com.robinfood.queue.view.controller.AlertController;
import co.com.robinfood.queue.view.controller.ConfirmationController;
import co.com.robinfood.queue.view.controller.ShowJsonController;
import co.com.robinfood.queue.view.enums.EViewFxmlUrls;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@Slf4j
public abstract class StageBuilderController {

    private static Stage stage;
    private static ConfigurableApplicationContext applicationContext;

    protected void stageBuilderController(Stage stage, ConfigurableApplicationContext applicationContext) {
        this.stage = stage;
        this.applicationContext = applicationContext;
        this.stage.initStyle(StageStyle.TRANSPARENT);
    }

    protected void showView(EViewFxmlUrls viewEnums) {
        loadView(viewEnums, this);
    }

    private <T> T loadView(EViewFxmlUrls viewEnums, Object controller) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    controller.getClass()
                            .getResource(EViewFxmlUrls.getFrontUrl(viewEnums)));

            loader.setControllerFactory(applicationContext::getBean);

            Parent root = loader.load();
            T loadedController = loader.getController();

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);

            stage.setScene(scene);
            stage.show();

            return loadedController;
        } catch (IOException ex) {
            log.error("Error loading FXML file: " + viewEnums.getFrontUrl(), ex);
            return null;
        }
    }

    protected void showViewConfirmationDialog(String messageConfirmation) {
        builderScene(messageConfirmation, EViewFxmlUrls.CONFIRMATION);
    }


    protected void showViewDialog(String messageAlert) {
        builderScene(messageAlert, EViewFxmlUrls.ALERT);
    }

    protected void showViewDialogJson(String messageJson) {
        builderScene(JsonUtils.formatJSON(messageJson), EViewFxmlUrls.SHOW_JSON_VIEW);
    }

    private void builderScene(String messageLabel, EViewFxmlUrls viewFxmlUrls) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(viewFxmlUrls.getFrontUrl()));

            Parent root = loader.load();
            root.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");

            Scene sceneView = new Scene(root);
            sceneView.setFill(Color.TRANSPARENT);

            Stage stageView = new Stage();
            stageView.initStyle(StageStyle.TRANSPARENT);

            stageView.setScene(sceneView);

            buildLoaderController(viewFxmlUrls, loader, stageView, messageLabel);
            stageView.showAndWait();

        } catch (IOException ex) {
            log.error(StageBuilderController.class.getName());
        }

    }

    private void buildLoaderController(EViewFxmlUrls eViewFxmlUrls, FXMLLoader loader, Stage stage, String message) {

        switch (eViewFxmlUrls) {
            case ALERT -> {
                AlertController alertController = loader.getController();
                alertController.init(message, stage);
                return;
            }
            case CONFIRMATION -> {
                ConfirmationController confirmationController = loader.getController();
                confirmationController.init(message, stage);
                return;
            }
            case SHOW_JSON_VIEW -> {
                ShowJsonController showJsonController = loader.getController();
                showJsonController.init(message, stage);
                return;
            }
        }
        loader.getController();
    }
}
