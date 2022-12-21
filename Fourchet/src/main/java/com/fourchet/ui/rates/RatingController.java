package com.fourchet.ui.rates;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;

import static com.fourchet.ui.Popup.showAlert;

public class RatingController {
    @FXML
    private VBox GeneralPane;
    @FXML
    private Rating rate;

    @FXML
    private TextArea comment;

    public void cancel() {
    }

    public void publish() {
        showAlert(Alert.AlertType.CONFIRMATION, GeneralPane.getScene().getWindow(), "Rating Submitted !","Thank You for your rating !");
    }
}
