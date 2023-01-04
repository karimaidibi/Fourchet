package com.fourchet.ui.ingredients;

import com.fourchet.bl.ingredientCategories.IngredientCategoriesFacade;
import com.fourchet.bl.ingredients.IngredientsFacade;
import com.fourchet.ingredients.Ingredient;
import com.fourchet.ingredients.IngredientCategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static com.fourchet.ui.Popup.showAlert;

public class IngredientsController {

    // Declare FXML elements
    @FXML private VBox GeneralPane;
    @FXML private Button backToMenuButton;
    @FXML private Label nameOfUser;
    @FXML private ListView listOfIngredients;
    @FXML private TextField ingredientNameInput;
    @FXML private ChoiceBox selectCategory;
    @FXML private Button validateButton;
    @FXML private Button cancelButton;
    @FXML private Label actionTypeOnIngredient; // adding new ingredient or modifying ingredient

    private IngredientsFacade ingredientsFacade;

    private Ingredient editedIngredient;

    private HBox editedIngredientHBox;

    private boolean isAdding = true;
    public IngredientsController() {
        this.ingredientsFacade = IngredientsFacade.getInstance();
    }

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
        this.loadIngredientsFromDatabase();
        this.listOfIngredients.setItems(ingredientsBoxes);
    }

    public void loadIngredientsFromDatabase() {
        // load ingredients from database and add them to the ObservableList
        IngredientCategoriesFacade facade = IngredientCategoriesFacade.getInstance();
        ObservableList<String> existingCategories = FXCollections.observableArrayList();
        for (IngredientCategory category : facade.getAllCategories()) {
            existingCategories.add(category.getName());
        }
        this.selectCategory.setItems(existingCategories);
        this.selectCategory.setValue(selectCategory.getItems().get(0));
        for (Ingredient ingredient : this.ingredientsFacade.getAllIngredients()) {
            HBox hBox = this.createIngredientCard(ingredient);
            this.ingredientsBoxes.add(hBox);
        }
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
        // validate new ingredient
        if (!((ingredientNameInput.getText().startsWith(" ")) || (ingredientNameInput.getText().equals("")))) {
            if (isAdding) {
                IngredientCategory category = new IngredientCategory((String) selectCategory.getSelectionModel().getSelectedItem());
                Ingredient ingredient = new Ingredient(ingredientNameInput.getText(), category);
                try {
                    Ingredient newIngredient = ingredientsFacade.saveIngredient(ingredient);
                    if (newIngredient != null) {
                        HBox hBox = this.createIngredientCard(ingredient);
                        this.ingredientsBoxes.add(hBox);
                    } else {
                        showAlert(Alert.AlertType.ERROR, GeneralPane.getScene().getWindow(), "Attention !", "Ingredients already registered !");
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else {
                try {
                    String[] params = new String[]{ingredientNameInput.getText(), (String) selectCategory.getSelectionModel().getSelectedItem()};
                    this.ingredientsFacade.updateIngredient(editedIngredient, params);
                    VBox leftVBox = new VBox();
                    Label nameLabel = new Label("Ingredient : " + params[0]);
                    Label categoryLabel = new Label("Category : " + params[1]);
                    leftVBox.getChildren().addAll(nameLabel, categoryLabel);
                    this.editedIngredientHBox.getChildren().set(0, leftVBox);
                } catch (Exception exception) {
                    exception.getMessage();
                }
            }
        }
        else {
            showAlert(Alert.AlertType.ERROR, GeneralPane.getScene().getWindow(), "Attention !", "Invalid name");
        }
        cancel();
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
        this.ingredientNameInput.setText("");
        this.actionTypeOnIngredient.setText("Add new ingredient");
        this.isAdding = true;
        this.editedIngredient = null;
        this.editedIngredientHBox = null;
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
    private void addIngredient(Ingredient ingredient) {
        // TODO: implement action to add an ingredient
        ingredientsFacade.saveIngredient(ingredient);
    }

    /**
     * This method should be called by the validate button when the action type is "modifying ingredient"
     */
    @FXML
    private void updateIngredient(Ingredient ingredient, HBox ingredientCard) {
        // TODO: implement action to update an ingredient
        this.actionTypeOnIngredient.setText("Modify this ingredient");
        this.selectCategory.setValue(ingredient.getCategory().getName());
        this.ingredientNameInput.setText(ingredient.getName());
        this.isAdding = false;
        this.editedIngredient = ingredient;
        this.editedIngredientHBox = ingredientCard;
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
    private void deleteIngredient(Ingredient ingredient, HBox ingredientCard) {
        // TODO: implement action to delete an ingredient
        // il faut lier le bouton delete à cette méthode
        this.ingredientsFacade.deleteIngredient(ingredient);
        this.listOfIngredients.getItems().remove(ingredientCard);
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
    private void selectIngredient(Ingredient ingredient) {
        // TODO: implement action to select an ingredient
        // il faut lier le click sur un élément de la liste à cette méthode (ou bien le bouton modifier a coté de chaque élément)
        // de cette manière on peut modifier un ingredient sur le coté droit de l'interface
        this.ingredientNameInput.setText(ingredient.getName());
        this.selectCategory.setValue(ingredient.getCategory().getName());
        this.actionTypeOnIngredient.setText("Modify this ingredient");
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


        // Create the HBox to hold the left and right VBoxes
        HBox ingredientCard = new HBox();
        // Create the modify and delete buttons
        Button modifyButton = new Button("Modify");
        modifyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateIngredient(ingredient, ingredientCard);
                System.out.println("Update button clicked!");
            }
        });
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteIngredient(ingredient, ingredientCard);
                System.out.println("Delete button clicked!");
            }
        });
        ingredientCard.getChildren().addAll(leftVBox, modifyButton, deleteButton);

        Double prefWidthOfListView = this.listOfIngredients.getPrefWidth();
        ingredientCard.setPrefWidth(prefWidthOfListView);
        ingredientCard.alignmentProperty().setValue(javafx.geometry.Pos.CENTER_LEFT);
        ingredientCard.setSpacing(40);

        return ingredientCard;
    }


}
