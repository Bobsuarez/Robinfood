package co.com.robinfood.queue.view.util;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;

public class TableUtil {

    public static void makeHeaderWrappable(ObservableList list) {
        list.forEach(item -> {
                    makeHeaderWrappable((TableColumn) item);
                });
    }

    private static void makeHeaderWrappable(TableColumn col) {
        Label label = new Label(col.getText());
        label.setStyle("-fx-padding: 8px;");
        label.setWrapText(true);
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);

        System.out.println(col.getText());
        StackPane stack = new StackPane();
        stack.getChildren()
                .add(label);
        stack.prefWidthProperty()
                .bind(col.widthProperty()
                              .subtract(5));
        label.prefWidthProperty()
                .bind(stack.prefWidthProperty());
        col.setText(null);
        col.setGraphic(stack);
    }

}