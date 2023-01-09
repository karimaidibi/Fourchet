package com.fourchet.ui.ingredients;

import com.fourchet.bl.account.UserFacade;
import com.fourchet.bl.ingredientCategories.IngredientCategoriesFacade;
import com.fourchet.ingredients.IngredientCategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

import static com.fourchet.ui.Popup.showAlert;

public class IngredientCategoriesController implements Initializable {

    // Declare FXML elements
    @FXML private VBox GeneralPane;
    @FXML private Button backToMenuButton;
    @FXML private Label nameOfUser;
    @FXML private ListView listOfIngredientsCategories;
    @FXML private TextField ingredientCategoryNameInput;
    @FXML private Button validateButton;
    @FXML private Button cancelButton;
    @FXML private Label actionTypeOnIngredientCategory; // adding new ingredient or modifying ingredient

    private IngredientCategoriesFacade ingredientCategoriesFacade;

    private IngredientCategory editedCategory;

    private HBox editedCategoryHBox;

    private boolean isAdding = true;
    public IngredientCategoriesController() {
        this.ingredientCategoriesFacade = IngredientCategoriesFacade.getInstance();
    }

    // Declare ObservableList to store ingredients
    private ObservableList<HBox> ingredientCategoriesBoxes = FXCollections.observableArrayList();

    /**
     * Method that is called when this controller is loaded
     * It will update the list of ingredients and the list of categories in the choice box
     */
    @FXML
    private void initialize() {
        // TODO: implement action to initialize the controller
        // Set the items of the ListView to the ObservableList
        this.loadIngredientCategoriesFromDatabase();
        this.listOfIngredientsCategories.setItems(ingredientCategoriesBoxes);
    }

    public void loadIngredientCategoriesFromDatabase() {
        // load ingredient categories from database and add them to the ObservableList
        for (IngredientCategory category : this.ingredientCategoriesFacade.getAllCategories()) {
            HBox hBox = this.createIngredientCategoryCard(category);
            this.ingredientCategoriesBoxes.add(hBox);
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
        // validate new ingredient category
        if (!((ingredientCategoryNameInput.getText().startsWith(" ")) || (ingredientCategoryNameInput.getText().equals("")))) {
            if (isAdding) {
                IngredientCategory category = new IngredientCategory(ingredientCategoryNameInput.getText());
                try {
                    IngredientCategory newCategory = ingredientCategoriesFacade.saveIngredientCategory(category);
                    if (newCategory != null) {
                        HBox hBox = this.createIngredientCategoryCard(category);
                        this.ingredientCategoriesBoxes.add(hBox);
                    } else {
                        showAlert(Alert.AlertType.ERROR, GeneralPane.getScene().getWindow(), "Attention !", "Category already registered !");
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else {
                try {
                    String[] params = new String[]{ingredientCategoryNameInput.getText()};
                    this.ingredientCategoriesFacade.updateIngredientCategory(editedCategory, params);
                    VBox leftVBox = new VBox();
                    Label nameLabel = new Label("Name : " + params[0]);
                    leftVBox.getChildren().addAll(nameLabel);
                    this.editedCategoryHBox.getChildren().set(0, leftVBox);
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
        this.ingredientCategoryNameInput.setText("");
        this.actionTypeOnIngredientCategory.setText("Add new Ingredient Category");
        this.isAdding = true;
        this.editedCategory = null;
        this.editedCategoryHBox = null;
    }

    /**
     * This method should be called by the validate button when the action type is "modifying ingredient"
     */
    @FXML
    private void updateIngredientCategory(IngredientCategory category, HBox ingredientCategoryCard) {
        // TODO: implement action to update an ingredient
        this.actionTypeOnIngredientCategory.setText("Modify this ingredient category");
        this.ingredientCategoryNameInput.setText(category.getName());
        this.isAdding = false;
        this.editedCategory = category;
        this.editedCategoryHBox = ingredientCategoryCard;
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
    private void deleteIngredientCategory(IngredientCategory category, HBox ingredientCategoryCard) {
        // TODO: implement action to delete an ingredient
        // il faut lier le bouton delete à cette méthode
        this.ingredientCategoriesFacade.deleteIngredientCategory(category);
        this.listOfIngredientsCategories.getItems().remove(ingredientCategoryCard);
    }

    /**
     * This method will be called on each ingredient in the database (and when the user adds a new ingredient)
     * It will return a HBox with the ingredient name and the ingredient category
     * */
    public HBox createIngredientCategoryCard(IngredientCategory category) {
        // Create the left VBox to hold the ingredient name and category
        VBox leftVBox = new VBox();
        Label nameLabel = new Label( "Name : " + category.getName());
        leftVBox.getChildren().addAll(nameLabel);


        // Create the HBox to hold the left and right VBoxes
        HBox ingredientCategoryCard = new HBox();
        // Create the modify and delete buttons
        Button modifyButton = new Button("Modify");
        modifyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateIngredientCategory(category, ingredientCategoryCard);
                System.out.println("Update button clicked!");
            }
        });
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteIngredientCategory(category, ingredientCategoryCard);
                System.out.println("Delete button clicked!");
            }
        });
        ingredientCategoryCard.getChildren().addAll(leftVBox, modifyButton, deleteButton);

        Double prefWidthOfListView = this.listOfIngredientsCategories.getPrefWidth();
        ingredientCategoryCard.setPrefWidth(prefWidthOfListView);
        ingredientCategoryCard.alignmentProperty().setValue(javafx.geometry.Pos.CENTER_LEFT);
        ingredientCategoryCard.setSpacing(40);

        return ingredientCategoryCard;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameOfUser.setText("Hello " + UserFacade.getInstance().getCurrentUser().getUsername());
    }
}
