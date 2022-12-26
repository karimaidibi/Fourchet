package com.fourchet.ui.account;

import com.fourchet.users.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Window;

public class RegisterFrame {
    @FXML
    private BorderPane GeneralPane;

    @FXML
    private GridPane GridpaneRegister;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    @FXML
    private RadioButton role;

    @FXML
    private HBox errorFiled;

    private RegisterController registerController = new RegisterController();
    @FXML
    protected void Register() throws Exception {


        if (username.getText().equals("") || email.getText().equals("") || password.getText().equals("")) {
            if (email.getText().equals("")) {
                email.setPromptText("email missing !");
                email.setStyle("-fx-prompt-text-fill: red;");
            }
            if (password.getText().equals("")) {
                password.setPromptText("password missing !");
                password.setStyle("-fx-prompt-text-fill: red;");
            }
        }

        else {

            //errorFiled.setVisible(true);
            //showAlert(Alert.AlertType.ERROR, GeneralPane.getScene().getWindow(), "Connection Failed", "email or password is incorrect");

            // if success
            try{
                // send the log in to the data
                User user = registerController.register(username.getText(), email.getText(), password.getText(), role.getText());
                showAlert(Alert.AlertType.CONFIRMATION, GeneralPane.getScene().getWindow(), "Registration Success", "Welcome " + user.getUsername());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileFrame.fxml"));
                Parent profilePage;
                profilePage = loader.load();

                ProfileFrame profileController = loader.getController();
                GridPane gridpaneProfile = profileController.getGridpaneProfile();

                BorderPane root = (BorderPane)GeneralPane.getScene().getRoot();
                root.setCenter(gridpaneProfile);

            }catch (Exception e){
                showAlert(Alert.AlertType.ERROR, GeneralPane.getScene().getWindow(), "Registration Failed", e.getMessage());
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

}