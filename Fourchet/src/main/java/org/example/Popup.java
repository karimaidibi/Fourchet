package com.example.jfxtest;
import javafx.scene.control.Alert;
import javafx.stage.Window;

public class Popup {
    static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
