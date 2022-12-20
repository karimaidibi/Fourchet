package com.fourchet.ui.rates;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import static com.fourchet.ui.Popup.showAlert;

public class RatingController {
    @FXML
    private VBox GeneralPane;
    @FXML
    private Slider rate;

    @FXML
    private TextArea comment;

    public void cancel(ActionEvent actionEvent) {
    }

    public void publish(ActionEvent actionEvent) {
        showAlert(Alert.AlertType.CONFIRMATION, GeneralPane.getScene().getWindow(), "Rating Submitted !","Thank You for your rating !");
    }
}
