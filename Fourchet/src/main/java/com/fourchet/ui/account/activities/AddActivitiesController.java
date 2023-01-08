package com.fourchet.ui.account.activities;

import com.fourchet.bl.account.UserFacade;
import com.fourchet.bl.account.activities.ActivitiesFacade;
import com.fourchet.users.User;
import com.fourchet.users.actitvities.Activity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddActivitiesController implements Initializable {
    @FXML
    private ChoiceBox typeOfActivity;
    @FXML
    private Label nameOfActivity;
    @FXML
    private Label location;
    @FXML
    private TextField nameOfActivityField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField phoneNumberField;

    private ActivitiesFacade activitiesFacade = ActivitiesFacade.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeOfActivity.getItems().add("Restaurant");
        typeOfActivity.getItems().add("Store");
        typeOfActivity.setValue(typeOfActivity.getItems().get(0));
        typeOfActivity.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Update the label's text based on the selected item in the choice box
            nameOfActivity.setText("Name of the " + newValue);
            location.setText(newValue + " location:");
        });
    }

    public void addActivity(ActionEvent actionEvent) {
        String name = nameOfActivityField.getText();
        String type = (String) typeOfActivity.getSelectionModel().getSelectedItem();
        String locationFieldText = locationField.getText();
        String phoneNumber = phoneNumberField.getText();

        Activity activity = activitiesFacade.addActivity(new Activity(UserFacade.getInstance().getCurrentUser().getEmail(), name, type, locationFieldText, phoneNumber));

    }
}
