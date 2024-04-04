package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    @FXML
    private Button okButton;

    private boolean isOkClicked = false;

    public WarningWindow(Stage root) {
        super(FXML, root);
        Scene scene = root.getScene();
        if (scene != null) {
            okButton.setDefaultButton(true);
        } else {
            logger.warning("Scene is null, unable to set default button.");
        }
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
