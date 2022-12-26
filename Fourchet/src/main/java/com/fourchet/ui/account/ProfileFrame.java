package com.fourchet.ui.account;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

public class ProfileFrame {

    @FXML
    private ImageView imageProfile;
    @FXML
    private BorderPane GeneralPane;

    @FXML
    private Button buttonPicture;

    @FXML
    private Button buttonUpdate;

    @FXML
    private Button buttonApply;

    @FXML
    private GridPane GridpaneProfile;

    @FXML
    private Label passwordConfirmationLabel;

    @FXML
    private PasswordField passwordConfirmation;
    @FXML
    private ChoiceBox Role;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    //private profileController profileController = new profileController();

    public GridPane getGridpaneProfile() {
        return GridpaneProfile;
    }
    @FXML
    protected void UpdateFrame() throws Exception {

        buttonApply.setVisible(true);
        buttonUpdate.setVisible(false);
        buttonPicture.setVisible(true);
        username.setDisable(false);
        Role.setDisable(false);
        password.setDisable(false);
        passwordConfirmation.setVisible(true);
        passwordConfirmationLabel.setVisible(true);


        /*
        String select = (String) Role.getSelectionModel().getSelectedItem();
        showAlert(Alert.AlertType.ERROR, GeneralPane.getScene().getWindow(), "Connection Failed", select); */
    }

    public void ApplyChange() throws Exception{
        if (password.getText().equals("")||passwordConfirmation.getText().equals("")) {
            if (password.getText().equals("")) {
                password.setPromptText("password missing !");
                password.setStyle("-fx-prompt-text-fill: red;");
            }
            if (passwordConfirmation.getText().equals("")) {
                passwordConfirmation.setPromptText("password missing !");
                passwordConfirmation.setStyle("-fx-prompt-text-fill: red;");
            }
        }
        else if(!password.getText().equals(passwordConfirmation.getText())){
            passwordConfirmation.setPromptText("password and confirmation are different !");
            passwordConfirmation.setStyle("-fx-prompt-text-fill: red;");
        }
        else {
            showAlert(Alert.AlertType.ERROR, GeneralPane.getScene().getWindow(), "Connection Failed","img"+ imageProfile.toString() +" psw :"+password.getText());
        }
    }

    public void ChangePicture(ActionEvent event) {
        // Ouvrir une fenêtre de sélection de fichier
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionnez une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Tous les fichiers", "*.*")
        );
        File selectedFile = fileChooser.showOpenDialog(buttonPicture.getScene().getWindow());
        if (selectedFile == null) {
            return;
        }

        // Chargement de l'image à partir du fichier sélectionné
        Image image = new Image(selectedFile.toURI().toString());

        // Mise à jour de l'image dans le ImageView
        imageProfile.setImage(image);
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
