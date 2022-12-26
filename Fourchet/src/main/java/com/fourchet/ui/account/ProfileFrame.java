package com.fourchet.ui.account;

import com.fourchet.users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.bson.types.Binary;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;

import java.io.ByteArrayInputStream;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    private ProfileController profileController = new ProfileController();

    private Binary currentPicture;

    /**
     * This method is called at the runtime whenever the profile frame is loaded
     * it will get user data from the user facade and display it
     */
    @FXML
    public void initialize() {
        this.loadInfos();
    }

    public void loadInfos(){
        // get the user data from the facade
        User user = profileController.getUser();

        // set the user data to the text fields
        username.setText(user.getUsername());
        email.setText(user.getEmail());
        password.setText(user.getPassword());
        Role.setValue(user.getRole());

        // set the image to the image view if the user has one
        Binary binaryPicture = user.getPicture();
        if (binaryPicture != null) {
            byte[] bytes = binaryPicture.getData();
            // Create directory if it does not exist
            this.createDirectory("pictures");
            // Write the byte array to a new file
            try {
                // Create a new file if it does not exist
                File outputFile = this.createFile("pictures/" + user.getUsername() + ".png");
                FileOutputStream fos = new FileOutputStream(outputFile);
                fos.write(bytes);
                fos.close();
                // Create a new Image object from the output file
                Image image = new Image(outputFile.toURI().toString());
                // Update the image in the ImageView
                imageProfile.setImage(image);
                this.currentPicture = binaryPicture;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

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

    }

    protected void FrameUpdated() throws Exception {

        buttonApply.setVisible(false);
        buttonUpdate.setVisible(true);
        buttonPicture.setVisible(false);
        username.setDisable(true);
        Role.setDisable(true);
        password.setDisable(true);
        passwordConfirmation.setVisible(false);
        passwordConfirmationLabel.setVisible(false);

    }

    @FXML
    public void ApplyChange() throws Exception{
        // check if the email and password are correct
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
        // if the password and the confirmation are the same
        else {
            try {
                // get the user data from the text fields
                String username = this.username.getText();
                String email = this.email.getText();
                String password = this.password.getText();
                String role = (String) Role.getSelectionModel().getSelectedItem();
                User currentUser = profileController.getUser();
                // wrap data in params
                String[] params = {username, email, password, role};
                // update the user data
                profileController.updateUserInfos(currentUser, params, this.currentPicture);
                // show a success message

                // Code that triggers the error
                showAlert(Alert.AlertType.CONFIRMATION, this.GeneralPane.getScene().getWindow(), "Success", "Your profile has been updated");

                this.FrameUpdated();
            }
            catch (Exception e) {
                // show an error message
                showAlert(Alert.AlertType.ERROR, GeneralPane.getScene().getWindow(), "Failed to update", e.getMessage());
            }

        }

    }

    public void ChangePicture(ActionEvent event) throws IOException {
        // Ouvrir une fenêtre de sélection de fichier
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionnez une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Tous les fichiers", "*.*")
        );
        File selectedFile = fileChooser.showOpenDialog(buttonPicture.getScene().getWindow());
        if (selectedFile == null) {
            System.out.println("Aucun fichier sélectionné");
            return;
        }

        // Chargement de l'image à partir du fichier sélectionné
        Image image = new Image(selectedFile.toURI().toString());
        // Mise à jour de l'image dans le ImageView
        imageProfile.setImage(image);

        // Read the selected file into a byte array
        byte[] bytes = Files.readAllBytes(selectedFile.toPath());
        // Create a new Binary object
        Binary binary = new Binary(bytes);

        // store the current image to be updated when the user clicks on apply
        this.currentPicture = binary;

    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public void createDirectory(String directoryPath) {
        Path path = Paths.get(directoryPath);
        if (!Files.exists(path)) {
            try{
                Files.createDirectory(path);
                System.out.println("Directory : " + directoryPath + " created");
            } catch (IOException e) {
                System.out.println("Failed to create directory : " + directoryPath);
            }

        } else {
            System.out.println("Directory : " + directoryPath + " already exists");
        }
    }

    public File createFile(String filePath){
        File outputFile = new File(filePath);
        if (!outputFile.exists()) {
            try {
                outputFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error while creating the file");
            }
        }
        return outputFile;
    }
}
