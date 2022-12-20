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
    @FXML private Label actionTypeOnIngredient; // adding new ingredient or modifying ingredient

    /**
     * Method that is called when this controller is loaded
     * It will update the list of ingredients and the list of categories in the choice box
     */
    @FXML
    private void initialize() {
        // TODO: implement action to initialize the controller
    }
    // Method to go back to the main menu
    @FXML
    private void backToMenu() {
        // TODO: implement action to go back to main menu
    }

    /**
     * Method to validate the new ingredient
     * This method will be called when the user clicks on the validate button
     * It will check if the ingredient name is not empty
     * If it is not empty, it will add the ingredient to the database
     * If it is empty, it will display an error message
     * It will also check if the ingredient already exists in the database
     * If it does, it will display an error message
     * If it does not, it will add the ingredient to the database
     * It will also update the list of ingredients in the list view
     * Finally, if the ingredient is added to the db, it will clear the text field and the choice box then display a success message
     */
    @FXML
    private void validate() {
        // TODO: implement action to validate new ingredient

    }

    /**
     * Method to cancel the new ingredient creation
     * This method will be called when the user clicks on the cancel button
     * It will erase the ingredient name input and the category choice
     * It will change the action type label to "adding new ingredient"
     * It will change the validate button text to "validate" instead of "modify"
     */
    @FXML
    private void cancel() {
        // TODO: implement action to cancel new ingredient creation

    }

    /**
     * Method to select an ingredient from the list view
     * This method will be called when the user clicks on an ingredient in the list view
     * It will display the ingredient name in the text field
     * It will display the ingredient category in the choice box
     * It will change the action type label to "modifying ingredient"
     * It will change the validate button text to "modify" instead of "validate"
     */
    @FXML
    private void selectIngredient() {
        // TODO: implement action to select an ingredient
    }

    /**
     * Method to delete an ingredient
     * This method will be called when the user clicks on the delete button
     * It will delete the ingredient from the database
     * It will update the list of ingredients in the list view
     * It will clear the text field and the choice box
     * It will display a success message
     */
    @FXML
    private void deleteIngredient() {
        // TODO: implement action to delete an ingredient
    }


}
