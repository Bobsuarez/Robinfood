package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;
import jssc.SerialPortList;
import lombok.extern.slf4j.Slf4j;

import static jssc.SerialPort.MASK_RXCHAR;

@Slf4j
public class Main extends Application {
    SerialPort arduinoPort = null;
    ObservableList<String> portList;

    Label labelValue;

    private void detectPort() {

        portList = FXCollections.observableArrayList();

        String[] serialPortNames = SerialPortList.getPortNames();
        for (String name : serialPortNames) {
            System.out.println(name);
            portList.add(name);
        }
    }

    @Override
    public void start(Stage primaryStage) {

        labelValue = new Label();
        labelValue.setFont(new Font("Arial", 150));

        detectPort();
        final ComboBox comboBoxPorts = new ComboBox(portList);
        comboBoxPorts.valueProperty()
                .addListener(new ChangeListener<String>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends String> observable,
                            String oldValue, String newValue
                    ) {

                        System.out.println(newValue);
                        disconnectArduino();
                        connectArduino(newValue);
                    }

                });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                comboBoxPorts, labelValue);

        StackPane root = new StackPane();
        root.getChildren().add(vBox);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public boolean connectArduino(String port) {

        System.out.println("connectArduino");

        boolean success = false;
        SerialPort serialPort = new SerialPort(port);
        try {
            serialPort.openPort();
            serialPort.setParams(
                    SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.setEventsMask(MASK_RXCHAR);
            serialPort.addEventListener((SerialPortEvent serialPortEvent) -> {
                if (serialPortEvent.isRXCHAR()) {
                    try {

                        byte[] b = serialPort.readBytes();
                        int value = b[0] & 0xff;    //convert to int
                        String st = String.valueOf(value);


                        //Update label in ui thread
                        Platform.runLater(() -> {
                            labelValue.setText(st);
                        });

                    } catch (SerialPortException ex) {
                        log.error(ex.getMessage());
                        ex.printStackTrace();
                    }

                }
            });

            arduinoPort = serialPort;
            success = true;
        } catch (SerialPortException ex) {
            log.error(ex.getMessage());
            System.out.println("SerialPortException: " + ex.toString());
            ex.printStackTrace();
        }

        return success;
    }

    public void disconnectArduino() {

        System.out.println("disconnectArduino()");
        if (arduinoPort != null) {
            try {
                arduinoPort.removeEventListener();

                if (arduinoPort.isOpened()) {
                    arduinoPort.closePort();
                }

            } catch (SerialPortException ex) {
                log.error(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void stop() throws Exception {
        disconnectArduino();
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}