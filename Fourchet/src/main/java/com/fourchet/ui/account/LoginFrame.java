package com.fourchet.ui.account;

import com.fourchet.users.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Window;

import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.io.IOException;

public class LoginFrame {
    @FXML
    private BorderPane GeneralPane;
    @FXML
    private GridPane GridpaneLogin;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private HBox errorField;

    private LoginController loginController = new LoginController();

    public GridPane getGridpaneLogin() {
        return GridpaneLogin;
    }

    @FXML


    protected void Login(ActionEvent event) throws Exception {

        // check if the email and password are correct
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
        // if success
        else {
            try{
                // send the log in to the data
                User user = loginController.login(email.getText(), password.getText());
                showAlert(Alert.AlertType.CONFIRMATION, GeneralPane.getScene().getWindow(), "Connection Success", "Welcome " + user.getUsername());

                // load the profile frame
                Application.goToNextScene(event,"/com/fourchet/ui/account/ProfileFrame.fxml");

                //FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFrameName));
                //Parent fxmlRoot = loader.load();
                //ProfileFrame profileController = loader.getController();

                //GridPane gridPaneProfile = profileController.getGridpaneProfile();
                //BorderPane root = (BorderPane)GeneralPane.getScene().getRoot();
                //root.setCenter(gridPaneProfile);


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