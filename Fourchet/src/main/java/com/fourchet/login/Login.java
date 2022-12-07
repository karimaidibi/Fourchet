package com.fourchet.login;

import com.fourchet.account.User;
import com.fourchet.account.UserFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Window;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login {
    @FXML
    private BorderPane GeneralPane;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private HBox errorFiled;
    @FXML
    protected void Login() throws Exception {
        String userEmail = email.getText();
        String userPwd = password.getText();

        if (userEmail.equals("") || userPwd.equals("")) {
            if (userEmail.equals("")) {
                email.setPromptText("email missing !");
                email.setStyle("-fx-prompt-text-fill: red;");
                //new animatefx.animation.Shake(email).play();
            }
            if (userPwd.equals("")) {
                password.setPromptText("password missing !");
                password.setStyle("-fx-prompt-text-fill: red;");
                //new animatefx.animation.Shake(password).play();
            }
        }

        else {
            // DEBUT DES MODIFS
            Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
            mongoLogger.setLevel(Level.OFF);
            UserFacade userFacade = new UserFacade();
            try {
                User user = userFacade.login("client@gmail.com", "client");
                System.out.println("welcome user!");
            }

            catch (Exception e) {
                errorFiled.setVisible(true);
                showAlert(Alert.AlertType.ERROR, GeneralPane.getScene().getWindow(), "Connection Failed", "email or password is incorrect");
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