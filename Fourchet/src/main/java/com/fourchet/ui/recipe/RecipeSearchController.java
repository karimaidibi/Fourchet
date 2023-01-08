package com.fourchet.ui.recipe;

import com.fourchet.persist.DaoFactory;
import com.fourchet.persist.recipe.RecipeDaoMongoDB;
import com.fourchet.recipe.Recipe;
import com.fourchet.recipe.TypeOfRecipe;
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

public class RecipeSearchController {
    @FXML
    private TextField searchField;

    @FXML
    private ListView<HBox> recipeList;

    @FXML
    private ChoiceBox<String> FilterTypeRecipe;

    @FXML
    private BorderPane GeneralPane;

    public BorderPane getGeneralPane(){
        return GeneralPane;
    }

    private ObservableList<Label> searchResult = FXCollections.observableArrayList();

    @FXML
    public void goToRecipeView(ActionEvent event) {
        System.out.println("click on register");
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("/com.fourchet.ui.recipe/RecipeViewFrame.fxml"));
            Parent fxmlRoot = loader.load();
            RecipeViewController controller = loader.getController();
            GeneralPane.setCenter(controller.getGeneralPane().getCenter());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void initialize() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        RecipeDaoMongoDB recipeDaoMongoDB = daoFactory.getRecipeDaoMongoDB();
        recipeDaoMongoDB.getAllRecipe();
        for (Recipe recipe : recipeDaoMongoDB.getAllRecipe()) {
            recipeList.getItems().add(setRecipeItem(recipe));
        }
        FilterTypeRecipe.getItems().clear();
        FilterTypeRecipe.getItems().addAll(TypeOfRecipe.getAllType());
        FilterTypeRecipe.getItems().add("ALL");
    }

    @FXML
    private void search() {
        recipeList.getItems().clear();
        DaoFactory daoFactory = DaoFactory.getInstance();
        RecipeDaoMongoDB recipeDaoMongoDB = daoFactory.getRecipeDaoMongoDB();
        recipeDaoMongoDB.getAllRecipe();
        for (Recipe recipe : recipeDaoMongoDB.getAllRecipe()) {
            if (recipe.getTitle().contains(searchField.getText())) {
                if(FilterTypeRecipe.getValue()==null || FilterTypeRecipe.getValue().equals("ALL")) {
                    recipeList.getItems().add(setRecipeItem(recipe));
                } else if (recipe.getType().equals(TypeOfRecipe.getType(FilterTypeRecipe.getValue()))) {
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
            System.out.println("click on Lire");
            try {
                System.out.println("try to load RecipeViewFrame");
                /*
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.fourchet.ui.recipe/RecipeViewFrame.fxml"));
                Parent fxmlRoot = loader.load();
                RecipeViewController controller = loader.getController();
                controller.setRecipe(recipe);
                GeneralPane.setCenter(controller.getGeneralPane().getCenter());*/
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.fourchet.ui.recipe/RecipeViewFrame.fxml"));
                Parent ViewRecipe = loader.load();
                RecipeViewController ViewController = loader.getController();
                VBox VBoxView = (VBox)ViewController.getGeneralPane().getCenter();

                BorderPane root = (BorderPane)GeneralPane.getScene().getRoot();
                root.setCenter(VBoxView);
                System.out.println("load RecipeViewFrame");
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
