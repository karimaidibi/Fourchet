package com.fourchet.ui.recipe;

import com.fourchet.bl.recipe.RecipeFacade;
import com.fourchet.recipe.Recipe;
import com.fourchet.recipe.TypeOfRecipe;
import com.fourchet.ui.GeneralController;
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

public class RecipeSearchController {
    @FXML
    private TextField searchField;

    @FXML
    private ListView<HBox> recipeList;

    @FXML
    private ChoiceBox<String> FilterTypeRecipe;

    @FXML
    private BorderPane GeneralPane;

    private RecipeFacade recipeFacade = RecipeFacade.getInstance();

    public BorderPane getGeneralPane(){
        return GeneralPane;
    }

    private ObservableList<Label> searchResult = FXCollections.observableArrayList();


    @FXML
    private void initialize() {

        recipeList.getItems().clear();
        for (Recipe recipe :  recipeFacade.getAll()) {
            recipeList.getItems().add(setRecipeItem(recipe));
        }

        FilterTypeRecipe.getItems().clear();
        FilterTypeRecipe.getItems().addAll(TypeOfRecipe.getAllType());
        FilterTypeRecipe.getItems().add("ALL");
    }

    @FXML
    private void search() {

        recipeList.getItems().clear();

        recipeFacade.getAll();

        for (Recipe recipe : recipeFacade.getAll()) {
            if (recipe.getTitle().contains(searchField.getText())) {
                if(FilterTypeRecipe.getValue()==null || FilterTypeRecipe.getValue().equals("ALL")) {
                    recipeList.getItems().add(setRecipeItem(recipe));
                } else if (Objects.equals(recipe.getType(), FilterTypeRecipe.getValue())) {
                    recipeList.getItems().add(setRecipeItem(recipe));
                }
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
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fourchet/ui/GeneralFrame.fxml"));
                Parent fxmlRoot = loader.load();
                GeneralController controller = loader.getController();
                controller.setCenter("/com/fourchet/ui/recipe/RecipeViewFrame.fxml");
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
}