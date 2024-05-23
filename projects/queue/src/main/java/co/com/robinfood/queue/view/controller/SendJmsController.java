/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.com.robinfood.queue.view.controller;

import co.com.robinfood.queue.Exceptions.ApplicationException;
import co.com.robinfood.queue.bussiness.SendMessageQueue;
import co.com.robinfood.queue.bussiness.SendMessageTopic;
import co.com.robinfood.queue.bussiness.SendSQS;
import co.com.robinfood.queue.chainqueue.IProcessMessageQueue;
import co.com.robinfood.queue.chainqueue.concreteHandler.ChangeStatusQueueHandler;
import co.com.robinfood.queue.chainqueue.concreteHandler.OrderOrLocalServerQueueHandler;
import co.com.robinfood.queue.chainsqs.IProcessMessageSQS;
import co.com.robinfood.queue.chainsqs.concreteHandler.OrderToCreateSQSHandler;
import co.com.robinfood.queue.chaintopic.IProcessMessageTopic;
import co.com.robinfood.queue.chaintopic.concreteHandler.OrderToCreatedTopicHandler;
import co.com.robinfood.queue.enums.PropertiesOrderEnum;
import co.com.robinfood.queue.persistencia.dto.DataRequestQueue;
import co.com.robinfood.queue.utils.JsonUtils;
import co.com.robinfood.queue.utils.PropertiesUtil;
import co.com.robinfood.queue.view.controller.stageabstract.StageBuilderController;
import co.com.robinfood.queue.view.enums.EViewFxmlUrls;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import static co.com.robinfood.queue.utils.ValidatedUtil.validateString;

/**
 * FXML Controller class
 *
 * @author Bobsu
 */
@Slf4j
@Component
public class SendJmsController extends StageBuilderController implements Initializable {

    private SendMessageTopic sendMessageTopic;
    private SendMessageQueue sendMessageQueue;
    private SendSQS sendSQS;

    @FXML
    private Button btnSend;

    @FXML
    private CheckBox chkIsConfigPos;

    @FXML
    private TextField txtBrokerURL;

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtNameQueue;

    @FXML
    private TextField txtpassword;

    @FXML
    private CheckBox chkEnableTopic;

    @FXML
    private CheckBox chkEnableFirm;

    @FXML
    private CheckBox chkSqs;

    @FXML
    private TextArea txtMessageAmq;

    @FXML
    private GridPane pnlConfigProperties;


    private boolean isSystemExecute = false;

    private Stage stageSendJms;

    public SendJmsController() {
    }

    public SendJmsController(SendMessageTopic sendMessageTopic, SendMessageQueue sendMessageQueue, SendSQS sendSQS) {
        this.sendMessageTopic = sendMessageTopic;
        this.sendMessageQueue = sendMessageQueue;
        this.sendSQS = sendSQS;
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void systemTimeout() {
        if (!isSystemExecute) {
            isSystemExecute = true;
            btnSend.setDisable(true);
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(5000); // Simulating work
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } finally {
                    isSystemExecute = false;
                    btnSend.setDisable(false);
                }
            });
            thread.start();
        }
    }


    private void initProcessSendMessage(DataRequestQueue data) {
        if (data.isEnableTopic()) {
            initProcessTopic(data);
            return;
        }
        if (data.isEnableSQS()) {
            initProcessSQS(data);
            return;
        }
        initProcessQueue(data);
    }

    private void initProcessTopic(DataRequestQueue data) {

        IProcessMessageTopic orderCreation = new OrderToCreatedTopicHandler();
        orderCreation.convertToString(data);
        sendMessageTopic.sendMessage(data);
    }

    private void initProcessSQS(DataRequestQueue data) {

        IProcessMessageSQS orderCreation = new OrderToCreateSQSHandler();
        orderCreation.convertToString(data);
        sendSQS.sendMessage(data);
    }

    private void initProcessQueue(DataRequestQueue data) {

        IProcessMessageQueue localServerProcessFirst = new OrderOrLocalServerQueueHandler();
        IProcessMessageQueue changeProcessSecond = new ChangeStatusQueueHandler();

        localServerProcessFirst.setNextChain(changeProcessSecond);
        localServerProcessFirst.convertToString(data);

        sendMessageQueue.sendMessage(data);
    }

    @FXML
    private void cancel(ActionEvent event) {
        log.info("Show windows main");
        showView(EViewFxmlUrls.MAIN);
    }

    @FXML
    private void onClickChangeStatus(MouseEvent event) {
        if (chkIsConfigPos.isSelected()) {
            builderView(PropertiesOrderEnum.CHANGE_STATUS_POS_PROPERTIES);
            return;
        }
        builderView(PropertiesOrderEnum.CHANGE_STATUS_OLD_PROPERTIES);
    }

    @FXML
    private void onClickLocalServer(MouseEvent event) {
        if (chkIsConfigPos.isSelected()) {
            builderView(PropertiesOrderEnum.ORDER_OR_LOCALSERVER_POS_PROPERTIES);
            return;
        }
        builderView(PropertiesOrderEnum.ORDER_OR_LOCALSERVER_OLD_PROPERTIES);

    }

    @FXML
    private void onClickOrderCreation(MouseEvent event) {
        if (chkIsConfigPos.isSelected()) {
            builderView(PropertiesOrderEnum.ORDER_POS_PROPERTIES);
            return;
        }
        builderView(PropertiesOrderEnum.ORDER_OLD_PROPERTIES);
    }

    @FXML
    private void onIsEnable(MouseEvent event) {
        disableConfigDefault();
    }

    private void disableConfigDefault() {
        txtBrokerURL.clear();
        txtUser.clear();
        txtpassword.clear();
        txtNameQueue.clear();
        txtMessageAmq.clear();
        chkEnableTopic.setSelected(false);
        chkEnableFirm.setSelected(false);
    }

    @FXML
    private void save(ActionEvent event) {

        try {
            validateString(txtBrokerURL.getText());
            validateString(txtUser.getText());
            validateString(txtpassword.getText());
            validateString(txtMessageAmq.getText());

            var data = DataRequestQueue.builder()
                    .brokerURL(txtBrokerURL.getText()
                                       .trim())
                    .userQueue(txtUser.getText()
                                       .trim())
                    .passQueue(txtpassword.getText()
                                       .trim())
                    .messageJson(txtMessageAmq.getText()
                                         .trim())
                    .nameQueue(txtNameQueue.getText()
                                       .trim())
                    .isEnableFirm(chkEnableFirm.isSelected())
                    .isEnableTopic(chkEnableTopic.isSelected())
                    .isEnableSQS(chkSqs.isSelected())
                    .build();

            initProcessSendMessage(data);
            showViewDialog("successfully sent message");
            systemTimeout();

        } catch (ApplicationException e) {
            showViewDialog(e.getMessage());
        }
    }

    @FXML
    void sqsActive(ActionEvent event) {

        if (chkSqs.isSelected()) {
            disableConfigDefault();
            txtBrokerURL.setPromptText("SQS URL");
            txtUser.setPromptText("SQS access-key");
            txtpassword.setPromptText("SQS secret-key");
            txtNameQueue.setVisible(false);
            chkDisable(true);
            return;
        }
        txtBrokerURL.setPromptText("Activemq Broker URL");
        txtUser.setPromptText("Activemq User");
        txtpassword.setPromptText("Activemq Password");
        txtNameQueue.setVisible(true);
        chkDisable(false);
        disableConfigDefault();
    }

    private void chkDisable(boolean isEnable) {
        txtNameQueue.setDisable(isEnable);
        chkEnableFirm.setDisable(isEnable);
        chkEnableTopic.setDisable(isEnable);
        chkIsConfigPos.setDisable(isEnable);
        pnlConfigProperties.setDisable(isEnable);
    }

    private void builderView(PropertiesOrderEnum propertiesOrderEnum) {
        try {
            Properties prop = new PropertiesUtil()
                    .cargarArchivoProperties(propertiesOrderEnum.getConfigName());

            String[] properties = propertiesOrderEnum.getProperties();

            var brokerUrl = prop.getProperty(properties[0]);
            var userAmq = prop.getProperty(properties[1]);
            var passAmq = prop.getProperty(properties[2]);
            var nameQueue = prop.getProperty(properties[3]);
            var isTopic =
                    Boolean.parseBoolean(prop.getProperty(properties[4]));
            var isFirm =
                    Boolean.parseBoolean(prop.getProperty(properties[5]));
            var messageJson = prop.getProperty(properties[6]);

            txtBrokerURL.setText(brokerUrl);
            txtBrokerURL.setAlignment(Pos.CENTER_LEFT);
            txtUser.setText(userAmq);
            txtpassword.setText(passAmq);
            txtNameQueue.setText(nameQueue);
            txtMessageAmq.setText(JsonUtils.formatJSON(messageJson));
            chkEnableTopic.setSelected(isTopic);
            chkEnableFirm.setSelected(isFirm);
            chkSqs.setSelected(false);

        } catch (ApplicationException e) {
            showViewDialog(e.getMessage());
        }
    }

}
