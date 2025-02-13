package viscount;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Viscount viscount;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image viscountImage = new Image(this.getClass().getResourceAsStream("/images/DaViscount.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Viscount instance
     */
    public void setViscount(Viscount viscount) {
        this.viscount = viscount;
        try {
            this.viscount.loadFromStorage();
        } catch (ViscountException e) {
            displayViscountErrorDialog("Old tasks were not loaded, starting afresh");
        }
        displayViscountDialog("Hello what may I do for you?");
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Viscount's reply and then appends
     * them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        String response;
        try {
            response = viscount.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getViscountDialog(response, viscountImage)
            );
        } catch (ViscountException e) {
            response = e.getMessage();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getViscountErrorDialog(response, viscountImage)
            );
        }
        userInput.clear();
    }

    private void displayViscountDialog(String message) {
        dialogContainer.getChildren().addAll(DialogBox.getViscountDialog(message, viscountImage));
    }

    private void displayUserDialog(String message) {
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(message, userImage));
    }

    private void displayViscountErrorDialog(String message) {
        dialogContainer.getChildren().addAll(DialogBox.getViscountErrorDialog(message, viscountImage));
    }
}
