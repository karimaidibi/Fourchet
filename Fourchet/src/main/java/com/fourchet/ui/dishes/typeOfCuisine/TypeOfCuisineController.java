package com.fourchet.ui.dishes.typeOfCuisine;

import com.fourchet.bl.account.UserFacade;
import com.fourchet.bl.typeOfCuisine.TypeOfCuisineFacade;
import com.fourchet.dishes.typeCuisine.TypeOfCuisine;
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
import java.text.ParseException;
import java.util.ResourceBundle;

import static com.fourchet.ui.Popup.showAlert;

public class TypeOfCuisineController implements Initializable {

    // Declare FXML elements
    @FXML private VBox GeneralPane;
    @FXML private Button backToMenuButton;
    @FXML private Label nameOfUser;
    @FXML private ListView listOfTypeOfCuisine;
    @FXML private TextField typeOfCuisineInput;
    @FXML private Button validateButton;
    @FXML private Button cancelButton;
    @FXML private Label actionTypeOnTypeOfCuisine; // adding new ingredient or modifying ingredient

    private TypeOfCuisineFacade typeOfCuisineFacade;

    private TypeOfCuisine editedTypeOfCuisine;

    private HBox editedTypeOfCuisineHBox;

    private boolean isAdding = true;
    public TypeOfCuisineController() {
        this.typeOfCuisineFacade = TypeOfCuisineFacade.getInstance();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameOfUser.setText("Hello " + UserFacade.getInstance().getCurrentUser().getUsername());
    }

    // Declare ObservableList to store ingredients
    private ObservableList<HBox> typeOfCuisineBoxes = FXCollections.observableArrayList();

    /**
     * Method that is called when this controller is loaded
     * It will update the list of ingredients and the list of categories in the choice box
     */
    @FXML
    private void initialize() throws ParseException {
        // TODO: implement action to initialize the controller
        // Set the items of the ListView to the ObservableList
        this.loadTypeOfCuisineFromDatabase();
        this.listOfTypeOfCuisine.setItems(typeOfCuisineBoxes);
    }

    public void loadTypeOfCuisineFromDatabase() throws ParseException {
        // load ingredient categories from database and add them to the ObservableList
        for (TypeOfCuisine typeOfCuisine : this.typeOfCuisineFacade.getAllTypeOfCuisine()) {
            HBox hBox = this.createTypeOfCuisineCard(typeOfCuisine);
            this.typeOfCuisineBoxes.add(hBox);
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
        // validate new type of cuisine
        if (!((typeOfCuisineInput.getText().startsWith(" ")) || (typeOfCuisineInput.getText().equals("")))) {
            if (isAdding) {
                TypeOfCuisine typeOfCuisine = new TypeOfCuisine(typeOfCuisineInput.getText());
                try {
                    TypeOfCuisine newType = typeOfCuisineFacade.saveTypeOfCuisine(typeOfCuisine);
                    if (newType != null) {
                        HBox hBox = this.createTypeOfCuisineCard(newType);
                        this.typeOfCuisineBoxes.add(hBox);
                    } else {
                        showAlert(Alert.AlertType.ERROR, GeneralPane.getScene().getWindow(), "Attention !", "Type of Cuisine already registered !");
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else {
                try {
                    String[] params = new String[]{typeOfCuisineInput.getText()};
                    this.typeOfCuisineFacade.updateTypeOfCuisine(editedTypeOfCuisine, params);
                    VBox leftVBox = new VBox();
                    Label nameLabel = new Label("Name : " + params[0]);
                    leftVBox.getChildren().addAll(nameLabel);
                    this.editedTypeOfCuisineHBox.getChildren().set(0, leftVBox);
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
        this.typeOfCuisineInput.setText("");
        this.actionTypeOnTypeOfCuisine.setText("Add new Type of Cuisine");
        this.isAdding = true;
        this.editedTypeOfCuisine = null;
        this.editedTypeOfCuisineHBox = null;
    }

    /**
     * This method should be called by the validate button when the action type is "modifying ingredient"
     */
    @FXML
    private void updateTypeOfCuisine(TypeOfCuisine typeOfCuisine, HBox typeOfCuisineCard) {
        // TODO: implement action to update an ingredient
        this.actionTypeOnTypeOfCuisine.setText("Modify this Type of Cuisine");
        this.typeOfCuisineInput.setText(typeOfCuisine.getName());
        this.isAdding = false;
        this.editedTypeOfCuisine = typeOfCuisine;
        this.editedTypeOfCuisineHBox = typeOfCuisineCard;
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
    private void deleteTypeOfCuisine(TypeOfCuisine typeOfCuisine, HBox typeOfCuisineCard) {
        // TODO: implement action to delete an ingredient
        // il faut lier le bouton delete à cette méthode
        this.typeOfCuisineFacade.deleteTypeOfCuisine(typeOfCuisine);
        this.listOfTypeOfCuisine.getItems().remove(typeOfCuisineCard);
    }

    /**
     * This method will be called on each ingredient in the database (and when the user adds a new ingredient)
     * It will return a HBox with the ingredient name and the ingredient category
     * */
    public HBox createTypeOfCuisineCard(TypeOfCuisine typeOfCuisine) {
        // Create the left VBox to hold the ingredient name and category
        VBox leftVBox = new VBox();
        Label nameLabel = new Label( "Name : " + typeOfCuisine.getName());
        leftVBox.getChildren().addAll(nameLabel);


        // Create the HBox to hold the left and right VBoxes
        HBox typeOfCuisineCard = new HBox();
        // Create the modify and delete buttons
        Button modifyButton = new Button("Modify");
        modifyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateTypeOfCuisine(typeOfCuisine, typeOfCuisineCard);
                System.out.println("Update button clicked!");
            }
        });
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteTypeOfCuisine(typeOfCuisine, typeOfCuisineCard);
                System.out.println("Delete button clicked!");
            }
        });
        typeOfCuisineCard.getChildren().addAll(leftVBox, modifyButton, deleteButton);

        Double prefWidthOfListView = this.listOfTypeOfCuisine.getPrefWidth();
        typeOfCuisineCard.setPrefWidth(prefWidthOfListView);
        typeOfCuisineCard.alignmentProperty().setValue(javafx.geometry.Pos.CENTER_LEFT);
        typeOfCuisineCard.setSpacing(40);

        return typeOfCuisineCard;
    }


}
