package com.fourchet.ui.ingredients;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class IngredientsCategoriesController {

    // Declare FXML elements
    @FXML
    private Button backToMenuButton;
    @FXML private Label nameOfUser;
    @FXML private ListView listOfIngredientsCategories;
    @FXML private TextField ingredientCategoryNameInput;
    @FXML private Button validateButton;
    @FXML private Button cancelButton;
    @FXML private Label actionTypeOnIngredientCategory; // adding new ingredient category or modifying ingredient category

    /**
     * Method that is called when this controller is loaded
     * It will update the list of ingredients categories
     */
    @FXML
    private void initialize() {
        // TODO : implement action to initialize the controller
    }

    // Method to go back to the main menu
    @FXML
    private void backToMenu() {
        // TODO : implement action to go back to main menu
    }

    /**
     * Method to validate the new ingredient Category
     * This method will be called when the user clicks on the validate button
     * It will check if the ingredient category name is not empty
     * If it is not empty, it will add the ingredient Category to the database
     * If it is empty, it will display an error message
     * It will also check if the ingredient Category already exists in the database
     * If it does, it will display an error message
     * If it does not, it will add the ingredient Category to the database
     * It will also update the list of ingredients Categories in the list view
     * Finally, if the ingredient Category is added to the db, it will clear the text field and the choice box then display a success message
     */
    @FXML
    private void validate() {
        // TODO : implement action to validate new ingredient

    }

    /**
     * Method to cancel the new ingredient Category creation
     * This method will be called when the user clicks on the cancel button
     * It will erase the ingredient Category name input
     * It will change the action type label to "adding new ingredient Category"
     * It will change the validate button text to "validate" instead of "modify"
     */
    @FXML
    private void cancel() {
        // TODO : implement action to cancel new ingredient creation

    }

    /**
     * Method to select an ingredient category from the list view
     * This method will be called when the user clicks on an ingredient category in the list view
     * It will display the ingredient category name in the text field
     * It will change the action type label to "modifying ingredient category"
     * It will change the validate button text to "modify" instead of "validate"
     */
    @FXML
    private void selectIngredientCategory() {
        // TODO : implement action to select an ingredient
    }

    /**
     * Method to delete an ingredient category
     * This method will be called when the user clicks on the delete button
     * It will delete the ingredient category from the database
     * It will update the list of ingredients categories in the list view
     * It will clear the text field
     * It will display a success message
     */
    @FXML
    private void deleteIngredient() {
        // TODO : implement action to delete an ingredient
    }

}
