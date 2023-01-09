package com.fourchet.ui.account.activities;

import com.fourchet.bl.account.activities.ActivitiesFacade;
import com.fourchet.users.actitvities.Activity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ActivityController implements Initializable {
    @FXML
    private Text nameOfActivity;
    @FXML
    private Text location;
    @FXML
    private Text phoneNumber;

    @FXML
    private VBox listOfCategories;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Activity activity = ActivitiesFacade.getInstance().getCurrentActivity();
        nameOfActivity.setText(activity.getName());
        location.setText(activity.getLocation());
        phoneNumber.setText(activity.getPhoneNumber());

        TextField categoryField = new TextField();
        categoryField.setText("Enter new category name...");
        Button addButton = new Button("Add");
        addButton.setOnAction(actionEvent -> {
            createNewCategoryCard(categoryField.getText());
        });
        HBox hbox = new HBox();
        hbox.getChildren().addAll(categoryField, addButton);
        listOfCategories.getChildren().add(hbox);
    }

    public void loadProducts(Activity activity) {

    }

    public HBox createNewCategoryCard(String categoryName) {
        Text category = new Text(categoryName);
        Button addProductBtn = new Button("Add new product");
        addProductBtn.setOnAction(actionEvent -> {
            // TODO : go to create dish / ingredient page
        });
        HBox categoryCard = new HBox();
        categoryCard.getChildren().addAll(category, addProductBtn);
        return categoryCard;
    }
}
