package com.fourchet.ui.recipe;

import com.fourchet.bl.recipe.RecipeFacade;
import com.fourchet.recipe.Recipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
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

    @FXML
    ChoiceBox<String> TypeOfRecipe;

    private RecipeFacade recipeFacade = RecipeFacade.getInstance();

    public BorderPane getGeneralPane(){
        return GeneralPane;
    }




    private ObservableList<HBox> steps = FXCollections.observableArrayList();

    private Recipe recipe;

    public void setRecipe (Recipe recipe){
        this.recipe = recipe;
    }

    @FXML
    private void initialize() {

        this.recipe = recipeFacade.getAll().get(1);

        setTitleRecipe(recipe.getTitle());
        setDescriptionRecipe(recipe.getDescription());
        setImageRecipe(recipe.getImage());
        setIngredientList(recipe.getIngredients());
        System.out.println(recipe.getSteps());
        setStepList(recipe.getSteps());
        setTypeOfRecipe(recipe.getType());

    }

    public void setTitleRecipe(String title) {
        titleRecipe.setText(title);
        titleRecipe.setFocusTraversable(false);
    }

    public void setDescriptionRecipe(String description) {
        descriptionRecipe.setText(description);
        descriptionRecipe.setFocusTraversable(false);
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
        selectedIngredientList.setFocusTraversable(false);
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
        selectedStepList.setFocusTraversable(false);
    }

    public void setTypeOfRecipe(String type) {
        TypeOfRecipe.setValue(type);
        TypeOfRecipe.setFocusTraversable(false);
    }

}