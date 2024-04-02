package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * This class is the controller for the warning window pop-up.
 */
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
     * Shows the warning window.
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
        getRoot().centerOnScreen();
    }

    /**
     * Shows warning page and waits for user action.
     */
    public void showAndWait() {
        logger.fine("Showing warning page about the application and waiting.");
        getRoot().showAndWait();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the warning window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the warning window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the warning window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
