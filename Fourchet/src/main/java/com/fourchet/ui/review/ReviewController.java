package com.fourchet.ui.review;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;

import static com.fourchet.ui.Popup.showAlert;

public class ReviewController {
    @FXML
    private VBox GeneralPane;
    @FXML
    private Rating rate;

    @FXML
    private TextArea comment;

    // close the RatingFrame
    public void cancel() {
    }

    // submit rating of user on product
    public void publish() {
        showAlert(Alert.AlertType.CONFIRMATION, GeneralPane.getScene().getWindow(), "Rating Submitted !","Thank You for your rating !");
    }
}
