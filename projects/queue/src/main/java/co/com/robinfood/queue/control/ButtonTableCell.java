package co.com.robinfood.queue.control;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.util.Objects;
import java.util.function.Function;


/**
 * @param <T>
 *
 * @author AccessparkSoft
 */
public class ButtonTableCell<T> extends TableCell<T, Button> {

    private final Button button;

    private ButtonTableCell(String label, Function<T, T> function, String styleClass, String iconPath) {

        // Crear el ImageView con el icono
        this.button = new Button();
        this.button.setMaxWidth(Double.MAX_VALUE);

        this.button.setOnAction((ActionEvent e) -> {
            function.apply(getCurrentItem());
        });

        if(Objects.nonNull(label)){
            this.button.setText(label);
        }

        if (Objects.nonNull(iconPath)) {
            ImageView imageView = new ImageView(
                    new Image(
                            Objects.requireNonNull(getClass().getResourceAsStream(iconPath)))
            );
            imageView.setFitHeight(30);
            imageView.setFitWidth(30);
            this.button.setGraphic(imageView);
        }

        button.getStyleClass()
                .clear();

        if (styleClass == null) {
            button.getStyleClass()
                    .add("button-table-cell");
            return;
        }

        button.getStyleClass()
                .add(styleClass);
    }

    public T getCurrentItem() {
        return (T) getTableView().getItems()
                .get(getIndex());
    }

    public static <S> Callback<TableColumn<S, Button>, TableCell<S, Button>> tableColumn(
            String label, Function<S, S> function
    ) {
        return param -> new ButtonTableCell<>(label, function, null, null);
    }

    public static <S> Callback<TableColumn<S, Button>, TableCell<S, Button>> tableColumn(
            Function<S, S> function, String pathUrlImage, String styleClass
    ) {
        return param -> new ButtonTableCell<>(null, function, styleClass, pathUrlImage);
    }

    public static <S> Callback<TableColumn<S, Button>, TableCell<S, Button>> tableColumn(
            String label, String styleClass, Function<S, S> function
    ) {
        return param -> new ButtonTableCell<>(label, function, styleClass, null);
    }

    @Override
    public void updateItem(Button item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
            return;
        }
        setGraphic(button);
    }

}