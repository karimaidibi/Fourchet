package com.fourchet.ui.recipe;

import com.fourchet.persist.DaoFactory;
import com.fourchet.persist.recipe.RecipeDaoMongoDB;
import com.fourchet.recipe.Recipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.bson.Document;
import org.bson.types.Binary;

import java.nio.ByteBuffer;
import java.util.List;

public class RecipeViewController {
    @FXML
    BorderPane GeneralPane;

    @FXML
    private ImageView imageRecipe;

    @FXML
    private TextField descriptionRecipe;

    @FXML
    private TextField titleRecipe;

    @FXML
    private ListView<HBox> selectedStepList;

    @FXML
    private ListView<Label> selectedIngredientList;


    private ObservableList<HBox> steps = FXCollections.observableArrayList();

    private Recipe recipe;

    @FXML
    private void initialize() {

        DaoFactory daoFactory = DaoFactory.getInstance();
        RecipeDaoMongoDB recipeDaoMongoDB = daoFactory.getRecipeDaoMongoDB();
        this.recipe = recipeDaoMongoDB.getAllRecipe().get(3);

        setTitleRecipe(recipe.getTitle());
        setDescriptionRecipe(recipe.getDescription());
        setImageRecipe(recipe.getImage());
        setIngredientList(recipe.getIngredients());
        System.out.println(recipe.getSteps());
        setStepList(recipe.getSteps());


        /*
        setTitleRecipe("Tarte aux pommes");
        setDescriptionRecipe("Une tarte aux pommes");
        setIngredientList(List.of("Farine", "Oeufs", "Beurre", "Sucre", "Lait", "Levure chimique"));
        setStepList(List.of("Etape 1", "Etape 2", "Etape 3"));

         */

    }

    public void setTitleRecipe(String title) {
        titleRecipe.setText(title);
    }

    public void setDescriptionRecipe(String description) {
        descriptionRecipe.setText(description);
    }

    public void setImageRecipe(String imageJsonString) {

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

        imageRecipe.setImage(image);

    }

    public void setIngredientList(List<String> ingredient) {
        for (String i : ingredient) {
            Label ingredientLabel = new Label(i);
            selectedIngredientList.getItems().add(ingredientLabel);
        }
    }

    public void setStepList(List<String> step) {
        System.out.println(step);
        for (String s : step) {
            System.out.println(s);
            Label stepLabel = new Label(s);
            HBox stepBox = new HBox();
            stepBox.getChildren().add(stepLabel);
            selectedStepList.getItems().add(stepBox);
        }
    }

}
