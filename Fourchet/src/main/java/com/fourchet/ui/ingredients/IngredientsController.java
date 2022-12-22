package com.fourchet.ui.ingredients;

import com.fourchet.ingredients.Ingredient;
import com.fourchet.ingredients.IngredientCategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

    // Declare ObservableList to store ingredients
    private ObservableList<HBox> ingredientsBoxes = FXCollections.observableArrayList();

    /**
     * Method that is called when this controller is loaded
     * It will update the list of ingredients and the list of categories in the choice box
     */
    @FXML
    private void initialize() {
        // TODO: implement action to initialize the controller
        // Set the items of the ListView to the ObservableList
        this.listOfIngredients.setItems(ingredientsBoxes);
        //this.loadIngredientsFromDatabase();
    }

    public void loadIngredientsFromDatabase() {
        // TODO: load ingredients from database and add them to the ObservableList
        // ingredients.addAll(...);
    }

    // Method to go back to the main menu
    @FXML
    private void backToMenu() {
        // TODO: implement action to go back to main menu
    }

    /**
     * Method to validate the new ingredient
     * This method will be called when the user clicks on the validate button
     * This method will call either the addIngredient() or the modifyIngredient() method
     * depending on the action type label
     * It will also update the list of ingredients in the list view using the loadIngredientsFromDatabase() method
     */
    @FXML
    private void validate() {
        // TODO: implement action to validate new ingredient
        // this is a test to check if the validate button works, replace it with data from input fields and implement the method
        IngredientCategory category = new IngredientCategory("test");
        Ingredient ingredient = new Ingredient("test", category);
        HBox hBox = this.createIngredientCard(ingredient);
        this.ingredientsBoxes.add(hBox);

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
     * This method should be called by the validate button when the action is "adding new ingredient"
     * It will check if the ingredient name is not empty
     * If it is empty, it will display an error message
     * It will also check if the ingredient already exists in the database
     * If it does, it will display an error message
     * If it is not empty, and the ingredient is not in the database
     * it will add the ingredient to the database
     * Finally, if the ingredient is added to the db, it will clear the text field and the choice box then display a success message
     */
    @FXML
    private void addIngredient() {
        // TODO: implement action to add an ingredient
    }

    /**
     * This method should be called by the validate button when the action type is "modifying ingredient"
     */
    @FXML
    private void updateIngredient() {
        // TODO: implement action to update an ingredient
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
        // il faut lier le bouton delete à cette méthode
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
        // il faut lier le click sur un élément de la liste à cette méthode (ou bien le bouton modifier a coté de chaque élément)
        // de cette manière on peut modifier un ingredient sur le coté droit de l'interface

    }

    /**
     * This method will be called on each ingredient in the database (and when the user adds a new ingredient)
     * It will return a HBox with the ingredient name and the ingredient category
     * @param ingredient the name of the ingredient
     * */
    public HBox createIngredientCard(Ingredient ingredient) {
        // Create the left VBox to hold the ingredient name and category
        VBox leftVBox = new VBox();
        Label nameLabel = new Label( "Ingredient : " + ingredient.getName());
        Label categoryLabel = new Label("Category : " + ingredient.getCategory().getName());
        leftVBox.getChildren().addAll(nameLabel, categoryLabel);

        // Create the modify and delete buttons
        Button modifyButton = new Button("Modify");
        Button deleteButton = new Button("Delete");

        // Create the HBox to hold the left and right VBoxes
        HBox ingredientCard = new HBox();
        ingredientCard.getChildren().addAll(leftVBox, modifyButton, deleteButton);

        Double prefWidthOfListView = this.listOfIngredients.getPrefWidth();
        ingredientCard.setPrefWidth(prefWidthOfListView);
        ingredientCard.alignmentProperty().setValue(javafx.geometry.Pos.CENTER_LEFT);
        ingredientCard.setSpacing(40);

        return ingredientCard;
    }


}
