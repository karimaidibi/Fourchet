package com.fourchet.ui.ingredients;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class IngredientsController {

    // Declare FXML elements
    @FXML private Button backToMenuButton;
    @FXML private Label nameOfUser;
    @FXML private ListView listOfIngredients;
    @FXML private TextField ingredientNameInput;
    @FXML private ChoiceBox selectCategory;
    @FXML private Button validateButton;
    @FXML private Button cancelButton;

    // Method to go back to the main menu
    @FXML
    private void backToMenu() {
        // TODO: implement action to go back to main menu
    }

    // Method to validate the new ingredient
    @FXML
    private void validate() {
        // TODO: implement action to validate new ingredient
    }

    // Method to cancel the new ingredient creation
    @FXML
    private void cancel() {
        // TODO: implement action to cancel new ingredient creation
    }
}
