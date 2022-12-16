package com.fourchet.UI;

import com.fourchet.users.User;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Window;

public class LoginFrame {
    @FXML
    private BorderPane GeneralPane;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private HBox errorFiled;

    private UserController userController = new UserController();
    @FXML
    protected void Login() throws Exception {


        if (email.getText().equals("") || password.getText().equals("")) {
            if (email.getText().equals("")) {
                email.setPromptText("email missing !");
                email.setStyle("-fx-prompt-text-fill: red;");
                //new animatefx.animation.Shake(email).play();
            }
            if (password.getText().equals("")) {
                password.setPromptText("password missing !");
                password.setStyle("-fx-prompt-text-fill: red;");
                //new animatefx.animation.Shake(password).play();
            }
        }

        else {

            //errorFiled.setVisible(true);
            //showAlert(Alert.AlertType.ERROR, GeneralPane.getScene().getWindow(), "Connection Failed", "email or password is incorrect");

            // if success
            try{
                // send the log in to the data
                User user = userController.login(email.getText(), password.getText());
                showAlert(Alert.AlertType.CONFIRMATION, GeneralPane.getScene().getWindow(), "Connection Success", "Welcome " + user.getUsername());
            }catch (Exception e){
                showAlert(Alert.AlertType.ERROR, GeneralPane.getScene().getWindow(), "Connection Failed", e.getMessage());
            }

        }
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    private class Shake {
        public Shake(TextField email) {
        }
    }
}