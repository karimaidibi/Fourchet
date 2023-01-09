package com.fourchet.ui.account;


import com.fourchet.ui.Application;
import com.fourchet.users.User;
import javafx.fxml.FXML;

import com.fourchet.ui.GeneralController;
import com.fourchet.users.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Window;

import javafx.event.ActionEvent;

public class LoginFrame {
    @FXML
    private BorderPane GeneralPaneLogin;
    @FXML
    private GridPane GridpaneLogin;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

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

                // load the profile frame
                FXMLLoader loader = new FXMLLoader(Application.class.getResource("/com/fourchet/ui/GeneralFrame.fxml"));
                Parent fxmlRoot = loader.load();
                GeneralController controller = loader.getController();
                controller.setCenter("/com/fourchet/ui/orders/CartFrame.fxml");

                //FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFrameName));
                //Parent fxmlRoot = loader.load();
                //ProfileFrame profileController = loader.getController();

                //GridPane gridPaneProfile = profileController.getGridpaneProfile();
                //BorderPane root = (BorderPane)GeneralPane.getScene().getRoot();
                //root.setCenter(gridPaneProfile);


            }catch (Exception e){
                showAlert(Alert.AlertType.ERROR, GeneralPaneLogin.getScene().getWindow(), "Connection Failed", e.getMessage());
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

    @FXML
    public void goToRegister(MouseEvent keyEvent) {
        System.out.println("click on register");
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("/com/fourchet/ui/GeneralFrame.fxml"));
            Parent fxmlRoot = loader.load();
            GeneralController controller = loader.getController();
            controller.setCenter("/com/fourchet/ui/account/RegisterFrame.fxml");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}