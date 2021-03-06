package afterschoolcreatives.polaris.javafx.scene.control;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * NOTICE: To use the official JavaFX Dialogs you need JDK 8u40 or later.
 *
 * Simplified Dialog Creation for Java FX.
 *
 * @author Jhon Melvin
 */
public class PolarisDialog {

    /**
     * Types.
     */
    public enum Type {
        INFORMATION, ERROR, CONFIRMATION, WARNING
    }

    private Alert alert;

    private PolarisDialog() {
        this.alert = null;
    }

    //--------------------------------------------------------------------------
    // Content Methods.
    //--------------------------------------------------------------------------
    public static PolarisDialog create(Type type) {
        PolarisDialog polarisFx = new PolarisDialog();
        polarisFx.alert = new Alert(AlertType.valueOf(type.toString()));
        return polarisFx;
    }

    /**
     * special create for exception dialogs.
     *
     * @param exception
     * @return
     */
    public static PolarisDialog createException(Exception exception) {
        PolarisDialog polarisFx = new PolarisDialog();
        polarisFx.alert = new Alert(AlertType.ERROR);

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        String exceptionText = sw.toString();
        
        String labelText = "Exception Details: ";
        if (exception.getMessage() != null) {
            labelText = exception.getMessage();
        }

        Label label = new Label(labelText);

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        polarisFx.alert.getDialogPane().setExpandableContent(expContent);

        // return
        return polarisFx;
    }

    public PolarisDialog setTitle(String title) {
        this.alert.setTitle(title);
        return this;
    }

    public PolarisDialog setHeaderText(String header) {
        this.alert.setHeaderText(header);
        return this;
    }

    public PolarisDialog setContentText(String message) {
        this.alert.setContentText(message);
        return this;
    }

    //--------------------------------------------------------------------------
    // Class Methods.
    //--------------------------------------------------------------------------
    /**
     * Sets the owner of this dialog.
     *
     * @param owner parent stage.
     * @return
     */
    public PolarisDialog setOwner(Stage owner) {
        this.alert.initOwner(owner);
        return this;
    }

    /**
     * Modify the style of the created dialog.
     *
     * @param style
     * @return
     */
    public PolarisDialog setStyle(StageStyle style) {
        this.alert.initStyle(style);
        return this;
    }

    /**
     * Set Custom buttons to this dialog.
     *
     * @param buttons
     * @return
     */
    public PolarisDialog setButtons(ButtonType... buttons) {
        this.alert.getButtonTypes().setAll(buttons);
        return this;
    }

    public PolarisDialog setGraphic(Node graphic) {
        this.alert.setGraphic(graphic);
        return this;
    }

    //--------------------------------------------------------------------------
    // Display Methods.
    //--------------------------------------------------------------------------
    public void show() {
        this.alert.show();
    }

    public Optional<ButtonType> showAndWait() {
        return this.alert.showAndWait();
    }

}
