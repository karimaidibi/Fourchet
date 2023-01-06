package com.fourchet.ui.account;

import com.fourchet.ui.GeneralController;
import com.fourchet.users.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Window;

import javafx.event.ActionEvent;

public class RegisterFrame {
    @FXML
    private BorderPane GeneralPane;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField passwordConfirmation;

    @FXML
    private RadioButton role;

    @FXML
    private HBox errorFiled;

    private RegisterController registerController = new RegisterController();
    @FXML
    protected void Register(ActionEvent event) throws Exception {
        if (username.getText().equals("") || email.getText().equals("") || password.getText().equals("") || passwordConfirmation.getText().equals("")) {
            if (username.getText().equals("")) {
                username.setPromptText("username missing !");
                username.setStyle("-fx-prompt-text-fill: red;");
                //new animatefx.animation.Shake(username).play();
            }
            if (email.getText().equals("")) {
                email.setPromptText("email missing !");
                email.setStyle("-fx-prompt-text-fill: red;");
            }
            if (password.getText().equals("")) {
                password.setPromptText("password missing !");
                password.setStyle("-fx-prompt-text-fill: red;");
            }
            if (passwordConfirmation.getText().equals("")) {
                passwordConfirmation.setPromptText("password confirmation missing !");
                passwordConfirmation.setStyle("-fx-prompt-text-fill: red;");
            }
        }
        // check if the password and the confirmation are the same
        else if(!password.getText().equals(passwordConfirmation.getText())){
            passwordConfirmation.clear();
            passwordConfirmation.setPromptText("passwords does not match !");
            passwordConfirmation.setStyle("-fx-prompt-text-fill: red;");

            password.clear();
            password.setPromptText("passwords does not match !");
            password.setStyle("-fx-prompt-text-fill: red;");
            // show an error message
            showAlert(Alert.AlertType.ERROR, GeneralPane.getScene().getWindow(), "Error in passwords", "password and confirmation are different !");
        }
        // if success
        else {
            try{
                // send the log in to the data
                User user = registerController.register(username.getText(), email.getText(), password.getText(), "client");
                showAlert(Alert.AlertType.CONFIRMATION, GeneralPane.getScene().getWindow(), "Registration Success", "Welcome " + user.getUsername());

                // load the profile frame
                Application.goToNextScene(event,"/com/fourchet/ui/account/ProfileFrame.fxml");

                //FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileFrame.fxml"));
                //Parent profilePage;
                //profilePage = loader.load();

                //ProfileFrame profileController = loader.getController();
                //GridPane gridPaneProfile = profileController.getGridpaneProfile();

                //BorderPane root = (BorderPane)GeneralPane.getScene().getRoot();
                //root.setCenter(gridPaneProfile);

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


    public void goToLogin(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("/com/fourchet/ui/GeneralFrame.fxml"));
            Parent fxmlRoot = loader.load();
            GeneralController controller = loader.getController();
            controller.setCenter("/com/fourchet/ui/account/Login.fxml");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}