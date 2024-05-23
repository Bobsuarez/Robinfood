package co.com.robinfood.queue.view.controller;

import co.com.robinfood.queue.Exceptions.ApplicationException;
import co.com.robinfood.queue.app.SharedData;
import co.com.robinfood.queue.control.ButtonTableCell;
import co.com.robinfood.queue.database.DatabaseManager;
import co.com.robinfood.queue.enums.PropertiesConnectionEnum;
import co.com.robinfood.queue.persistencia.dto.OrderBillingJsonResponseDTO;
import co.com.robinfood.queue.repository.OrderElectronicRepository;
import co.com.robinfood.queue.usecases.saveordersbilling.ISaveOrdersBillingUseCase;
import co.com.robinfood.queue.utils.ObjectMapperSingleton;
import co.com.robinfood.queue.utils.PropertiesUtil;
import co.com.robinfood.queue.view.controller.stageabstract.StageBuilderController;
import co.com.robinfood.queue.view.enums.EViewFxmlUrls;
import co.com.robinfood.queue.view.util.TableUtil;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static co.com.robinfood.queue.app.lasting.GlobalConstants.Icons.URL_SEND_ICON;
import static co.com.robinfood.queue.app.lasting.GlobalConstants.Icons.URL_SHOW_ICON;
import static co.com.robinfood.queue.app.lasting.GlobalConstants.ParametersSharedBetween.BUTTON_ACCEPTED;
import static co.com.robinfood.queue.app.lasting.GlobalConstants.StylesGlobal.BUTTON_TABLE_CELL_IMAGEVIEW;
import static co.com.robinfood.queue.utils.ValidatedUtil.validateString;

@Component
@Slf4j
public class ElectronicBillingController extends StageBuilderController implements Initializable {

    @FXML
    private TextField txtMsqlURL;

    @FXML
    private TextField txtMysqlPassword;

    @FXML
    private TextField txtMysqlUser;

    @FXML
    private Button btnPurgeData;

    @FXML
    private Button btnSearchData;

    @FXML
    private TableColumn<OrderBillingJsonResponseDTO, String> tblColumnCode;

    @FXML
    private TableColumn<OrderBillingJsonResponseDTO, String> tblColumnOrderId;

    @FXML
    private TableColumn<OrderBillingJsonResponseDTO, Button> tblColumnPayload;

    @FXML
    private TableColumn<OrderBillingJsonResponseDTO, String> tblColumnRetry;

    @FXML
    private TableColumn<OrderBillingJsonResponseDTO, Button> tblColumnError;

    @FXML
    private TableColumn<OrderBillingJsonResponseDTO, Button> tblColumnSend;

    @FXML
    private TableView<OrderBillingJsonResponseDTO> tblDataOrders;

    private DatabaseManager databaseManager;

    private List<OrderBillingJsonResponseDTO> orderBillingJsonResponseDTOS;

    private OrderElectronicRepository orderElectronicRepository;

    private ISaveOrdersBillingUseCase saveOrdersBillingUseCase;

    @Value("${feign.client.url.orderBillNumberGeneratorBc}")
    private String urlBillGeneratorBc;

    @Value("${date-to-search-query}")
    private String dateToSearchQuery;

    private int retryQuery = 1;

    public ElectronicBillingController() {
    }

    @Autowired
    public ElectronicBillingController(ISaveOrdersBillingUseCase saveOrdersBillingUseCase) {
        this.saveOrdersBillingUseCase = saveOrdersBillingUseCase;
    }

    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    @FXML
    void exit(ActionEvent event) {
        showView(EViewFxmlUrls.MAIN);
    }

    @FXML
    void onClickDev(MouseEvent event) {
        builderView(PropertiesConnectionEnum.CONNECTION_DEV_PROPERTIES);
    }

    @FXML
    void onClickProd(MouseEvent event) {
        builderView(PropertiesConnectionEnum.CONNECTION_PROD_PROPERTIES);
    }

    @FXML
    void onSearchData(ActionEvent event) {

        if (retryQuery == 2) {
            showViewDialog("La tabla está con los datos más recientes");
            return;
        }

        orderBillingJsonResponseDTOS = orderElectronicRepository.findAllOrdersByStatusCodeAndDateInitialAndDateFinal(
                dateToSearchQuery);
        initColumn();
        processOrders(orderBillingJsonResponseDTOS);
        retryQuery = retryQuery + 1;
        SharedData.getInstance()
                .clearAll();
    }

    @FXML
    void purgeData(ActionEvent event) {

        List<JsonNode> listDataRequest =
                this.orderBillingJsonResponseDTOS.stream()
                        .map(OrderBillingJsonResponseDTO::getRequestPayload)
                        .collect(Collectors.toList());

        validateSentOrderProd(listDataRequest);
        this.onSearchData(new ActionEvent());
    }

    @FXML
    void onConnect(ActionEvent event) {

        try {
            validateField();

            if (Objects.nonNull(databaseManager)) {
                showViewDialog("Connect successfully");
                return;
            }

            databaseManager = new DatabaseManager(
                    txtMsqlURL.getText(),
                    txtMysqlUser.getText(),
                    txtMysqlPassword.getText()
            );

            showViewDialog("Connect successfully");
            btnSearchData.setDisable(false);
        } catch (ApplicationException e) {
            showViewDialog(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.orderElectronicRepository = OrderElectronicRepository.getInstance();
        btnPurgeData.setDisable(true);
        btnSearchData.setDisable(true);
        TableUtil.makeHeaderWrappable(tblDataOrders.getColumns());
    }

    private void initColumn() {

        tblColumnOrderId.setCellValueFactory(col -> {
            OrderBillingJsonResponseDTO responseDTO = col.getValue();
            String orderId = String.valueOf(responseDTO.getOrderId());
            return new SimpleStringProperty(orderId);
        });

        tblColumnCode.setCellValueFactory(col -> {
            OrderBillingJsonResponseDTO responseDTO = col.getValue();
            String code = String.valueOf(responseDTO.getStatusCode());
            return new SimpleStringProperty(code);
        });

        tblColumnPayload.setCellFactory(ButtonTableCell.tableColumn(
                novelty -> {
                    showViewDialogJson(novelty.getRequestPayload()
                                               .toString());
                    return novelty;
                },
                URL_SHOW_ICON, BUTTON_TABLE_CELL_IMAGEVIEW
        ));

        tblColumnError.setCellFactory(
                ButtonTableCell.tableColumn(
                        novelty -> {
                            try {

                                JsonNode nodeSearch =
                                        ObjectMapperSingleton.findAttribute(novelty.getResponsePayload(), "Novedad");

                                if (Objects.isNull(nodeSearch)) {
                                    throw new Exception("Error no se hay data");
                                }

                                showViewDialogJson(nodeSearch.toString());

                            } catch (Exception e) {

                                try {
                                    var stringData = novelty.getResponsePayload()
                                            .toString()
                                            .substring(155);

                                    if (stringData.contains(", exception : {}\"}")) {
                                        stringData = stringData.replace(", exception : {}", "");
                                    }

                                    if (stringData.contains("\\")) {
                                        stringData = stringData.replace("\\", "");
                                    }

                                    JsonNode jsonNode = ObjectMapperSingleton.stringToJsonNode(stringData);

                                    JsonNode nodeSearch = ObjectMapperSingleton.findAttribute(jsonNode, "Novedad");

                                    if (Objects.isNull(nodeSearch)) {
                                        throw new Exception("Error no se hay data Novedad en message string");
                                    }

                                    showViewDialogJson(nodeSearch.toString());

                                } catch (Exception exception) {

                                    log.error(
                                            "Exception occurred while processing JSON response {}",
                                            exception.getMessage()
                                    );

                                    showViewDialogJson(novelty.getResponsePayload()
                                                               .toString());
                                }
                            }
                            return novelty;
                        },
                        URL_SHOW_ICON, BUTTON_TABLE_CELL_IMAGEVIEW
                ));

        tblColumnRetry.setCellValueFactory(col -> {
            OrderBillingJsonResponseDTO responseDTO = col.getValue();
            String retry = String.valueOf(responseDTO.getRetry());
            return new SimpleStringProperty(retry);
        });

        tblColumnSend.setCellFactory(ButtonTableCell.tableColumn(
                novelty -> {
                    validateSentOrderProd(Collections.singletonList(novelty.getRequestPayload()));
                    return novelty;
                },
                URL_SEND_ICON, BUTTON_TABLE_CELL_IMAGEVIEW
        ));
    }

    private void validateSentOrderProd(List<JsonNode> requestPayloadList) {
        this.retryQuery = 1;

        boolean isPROD = urlBillGeneratorBc.contains("muy.com");
        boolean isBDProd = txtMsqlURL.getText()
                .contains("muy.com");

        if (!(isPROD && isBDProd)) {
            if (isPROD) {
                showViewDialog("No es posible enviar datos de ambientes diferentes Dev - Prod");
                return;
            }
            reloadData(requestPayloadList);
            return;
        }

        showViewConfirmationDialog("¿Desea enviar los datos a producción?");

        Boolean isAccepted = (Boolean) SharedData.getInstance()
                .getParameters()
                .get(BUTTON_ACCEPTED);

        if (Boolean.TRUE.equals(isAccepted)) {
            reloadData(requestPayloadList);
        }
    }

    private void reloadData(List<JsonNode> requestPayloadList) {
        Platform.runLater(
                () -> {
                    saveOrdersBillingUseCase.invoke(requestPayloadList);
                    showViewDialog("Datos enviados");
                    this.onSearchData(new ActionEvent());
                });
    }

    private void processOrders(List<OrderBillingJsonResponseDTO> jsonResponseDTOS) {

        ObservableList<OrderBillingJsonResponseDTO> items = FXCollections.observableArrayList();

        if (Objects.isNull(jsonResponseDTOS)) {
            showViewDialog("No hay datos para mostrar");
            btnPurgeData.setDisable(true);
            return;
        }

        items.addAll(jsonResponseDTOS);

        Platform.runLater(() -> {
            tblDataOrders.getItems()
                    .clear();
            tblDataOrders.setItems(items);
            btnPurgeData.setDisable(false);
        });
    }

    private void builderView(PropertiesConnectionEnum propertiesConnectionEnum) {
        try {
            Properties prop = new PropertiesUtil()
                    .cargarArchivoProperties(propertiesConnectionEnum.getConfigName());

            String[] properties = propertiesConnectionEnum.getProperties();

            var mysqlUrl = prop.getProperty(properties[0]);
            var user = prop.getProperty(properties[1]);
            var pass = prop.getProperty(properties[2]);

            txtMsqlURL.setText(mysqlUrl);
            txtMysqlUser.setText(user);
            txtMysqlPassword.setText(pass);

        } catch (ApplicationException e) {
            showViewDialog(e.getMessage());
        }
    }

    private void validateField() {
        validateString(txtMsqlURL.getText());
        validateString(txtMysqlUser.getText());
        validateString(txtMysqlPassword.getText());
    }
}
