package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class WarningWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(WarningWindow.class);
    private static final String FXML = "WarningWindow.fxml";

    @FXML
    private Label warningMessage;

    private boolean isOkClicked = false;

    public WarningWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new WarningWindow.
     */
    public WarningWindow() {
        this(new Stage());
    }

    public void setMessage(String message) {
        warningMessage.setText(message);
    }

    public boolean isOkClicked() {
        return isOkClicked;
    }

    @FXML
    private void okClicked() {
        isOkClicked = true;
        hide();
    }

    @FXML
    private void cancelClicked() {
        hide();
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing warning page about the application.");
        getRoot().show();
//        getRoot().centerOnScreen();
    }

    public void showAndWait() {
        logger.fine("Showing warning page about the application.");
        getRoot().showAndWait();
//        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
