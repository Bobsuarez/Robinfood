package co.com.robinfood.queue;

import co.com.robinfood.queue.app.SharedData;
import co.com.robinfood.queue.usecases.saveordersbilling.SaveOrdersBillingUseCase;
import co.com.robinfood.queue.view.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication(
        scanBasePackages = {
                "co.com.robinfood.queue.*"
        }
)
@EnableFeignClients(basePackages = {"co.com.robinfood.queue.*"})
public class QueueApplication extends Application {

    public ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(QueueApplication.class);
        applicationContext = builder.run(getParameters().getRaw().toArray(new String[0]));
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryScene) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/Main.fxml"));

        loader.setControllerFactory(applicationContext::getBean);

        Parent root = loader.load();
        Scene sceneDelivery = new Scene(root, 1024, 685);
        sceneDelivery.setFill(Color.TRANSPARENT);

        primaryScene.setScene(sceneDelivery);

        MainController mainController = loader.getController();
        mainController.setStageController(primaryScene, applicationContext);

        // Configurar la escena
        // Configurar la ventana principal
        primaryScene.initStyle(StageStyle.TRANSPARENT);
        primaryScene.show();
    }

    @Override
    public void stop() {
        applicationContext.close();
    }
}
