package com.fourchet.ui.dishes;

import com.fourchet.bl.recipe.RecipeFacade;
import com.fourchet.recipe.Recipe;
import com.fourchet.recipe.TypeOfRecipe;
import com.fourchet.ui.recipe.RecipeViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.bson.Document;
import org.bson.types.Binary;

import java.nio.ByteBuffer;
import java.util.Objects;

public class DishesCreateController {
    public Button removeSelectedRecipeButton;
    public VBox selectedRecipeBox;
    public ListView searchResultsList;


    public Button searchButton;
    public TextField searchField;
    public ListView searchResultsStepListStepList;
    public ListView recipeList;
    @FXML
    private ImageView imageRecipe;

    @FXML
    private Button buttonPicture;

    @FXML
    private TextField descriptionRecipe;

    @FXML
    private TextField titleRecipe;

    @FXML
    private ObservableList<HBox> steps = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox<String> typeOfRecipe;

    private RecipeFacade recipeFacade = RecipeFacade.getInstance();


    @FXML
    private void initialize() {
        search();
        typeOfRecipe.getItems().addAll(TypeOfRecipe.getAllType());
        typeOfRecipe.setValue(TypeOfRecipe.BREAKFAST.toString());
    }

    @FXML
    private void search() {

        recipeList.getItems().clear();

        recipeFacade.getAll();

        for (Recipe recipe : recipeFacade.getAll()) {
            if (recipe.getTitle().contains(searchField.getText())) {
                recipeList.getItems().add(setRecipeItem(recipe));
            }
        }
    }

    private HBox setRecipeItem(Recipe recipe) {
        HBox hbox = new HBox();
        hbox.setSpacing(10);

        ImageView imageView = new ImageView();
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(getImageRecipe(recipe.getImage()));

        VBox vbox = new VBox();
        vbox.setSpacing(10);

        Label authorLabel = new Label("Author : " + recipe.getAuthor());
        authorLabel.setPrefWidth(213);
        Label titleLabel = new Label("Titre : "+recipe.getTitle());
        Label descriptionLabel = new Label("Description : "+recipe.getDescription());

        Label type = new Label("Type : "+recipe.getType());

        vbox.getChildren().addAll(authorLabel, titleLabel, descriptionLabel, type);

        HBox buttonHBox = new HBox();
        buttonHBox.setAlignment(Pos.CENTER);

        Button readButton = new Button("Lire");
        readButton.setOnAction(event -> {
            System.out.println("click on Lire");
            try {
                recipeList.getItems().remove(hbox);
                addRecipeList(recipe.getTitle());

            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        buttonHBox.getChildren().add(readButton);

        hbox.getChildren().addAll(imageView, vbox, buttonHBox);
        return hbox;
    }

    public WritableImage getImageRecipe(String imageJsonString) {

        Document imageDocument = Document.parse(imageJsonString);
        int width = imageDocument.getInteger("width");
        int height = imageDocument.getInteger("height");
        byte[] data = imageDocument.get("data", Binary.class).getData();

        int[] pixels = new int[width * height];
        ByteBuffer buffer = ByteBuffer.wrap(data);
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = buffer.getInt();
        }

        WritableImage image = new WritableImage(width, height);
        image.getPixelWriter().setPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), pixels, 0, width);
        return image;
    }

    /*
    @FXML
    private Button searchButton;


    @FXML
    private void initialize() {
        searchButton.setOnAction(event -> {
            // Code de la fonction de recherche et de mise à jour de la liste des résultats de recherche
        });
    }*/

    @FXML
    private void addRecipeList(String recipe) {

        Button deleteButton = new Button("X");;
        Label stepLabel = new Label(recipe);
        stepLabel.setStyle("-fx-padding: 0 0 0 4;");
        HBox stepHBox = new HBox(deleteButton, stepLabel);
        stepHBox.setAlignment(Pos.CENTER_LEFT);
        // Ajout de l'étape à la ObservableList
        searchResultsStepListStepList.getItems().add(stepHBox);

        deleteButton.setOnAction(event1 -> {
            // Suppression de l'étape de la ObservableList
            searchResultsStepListStepList.getItems().remove(stepHBox);
        });

        // Effacement du champ de texte
    }

}
