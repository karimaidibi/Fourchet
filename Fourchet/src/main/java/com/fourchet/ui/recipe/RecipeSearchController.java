package com.fourchet.ui.recipe;

import com.fourchet.persist.DaoFactory;
import com.fourchet.persist.recipe.RecipeDaoMongoDB;
import com.fourchet.recipe.Recipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.bson.Document;
import org.bson.types.Binary;

import java.nio.ByteBuffer;

public class RecipeSearchController {
    @FXML
    private TextField searchField;

    @FXML
    private ListView<HBox> recipeList;

    private ObservableList<Label> searchResult = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        RecipeDaoMongoDB recipeDaoMongoDB = daoFactory.getRecipeDaoMongoDB();
        recipeDaoMongoDB.getAllRecipe();
        for (Recipe recipe : recipeDaoMongoDB.getAllRecipe()) {
            recipeList.getItems().add(setRecipeItem(recipe));
        }



    }

    @FXML
    private void search() {
        recipeList.getItems().clear();
        DaoFactory daoFactory = DaoFactory.getInstance();
        RecipeDaoMongoDB recipeDaoMongoDB = daoFactory.getRecipeDaoMongoDB();
        recipeDaoMongoDB.getAllRecipe();
        for (Recipe recipe : recipeDaoMongoDB.getAllRecipe()) {
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

        vbox.getChildren().addAll(authorLabel, titleLabel, descriptionLabel);

        HBox buttonHBox = new HBox();
        buttonHBox.setAlignment(Pos.CENTER);

        Button readButton = new Button("Lire");

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
