package com.fourchet.ui.dishes;

import com.fourchet.bl.dishes.DishesFacade;
import com.fourchet.bl.recipe.RecipeFacade;
import com.fourchet.bl.typeOfCuisine.TypeOfCuisineFacade;
import com.fourchet.dishes.Dish;
import com.fourchet.recipe.Recipe;
import com.fourchet.recipe.TypeOfRecipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
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
import java.text.ParseException;
import java.util.List;

public class DishesViewController {
    public Button searchButton;
    public TextField searchField;
    public ListView searchResultsStepListStepList;
    public ListView recipeList;

    @FXML
    private Spinner<Integer> spinner;
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
    private ChoiceBox<String> TypeOfCuisine;
    @FXML
    private ChoiceBox<String> typeOfRecipe;

    private DishesFacade dishesFacade = DishesFacade.getInstance();

    private Dish dish;


    @FXML
    private void initialize() throws ParseException {

        setDish(dishesFacade.getAllDishes().get(0));
        setTitleRecipe(dish.getTitle());
        titleRecipe.setEditable(false);


        setDescriptionRecipe(dish.getDescription());
        descriptionRecipe.setEditable(false);
        spinner.setEditable(false);


    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    @FXML
    private void search() {
        /*

        recipeList.getItems().clear();

        recipeFacade.getAll();

        for (Recipe recipe : recipeFacade.getAll()) {
            if (recipe.getTitle().contains(searchField.getText())) {
                int alreadyInList = 0;
                if (searchResultsStepListStepList.getItems().size() >0) {
                    for (int i = 0; i < searchResultsStepListStepList.getItems().size(); i++) {
                        if (recipe.getTitle().equals(((Label) ((HBox) searchResultsStepListStepList.getItems().get(i)).getChildren().get(1)).getText())) {
                            alreadyInList = 1;
                        }
                    }
                }
                if (alreadyInList == 0) {
                    recipeList.getItems().add(setRecipeItem(recipe));
                }



                for (Object hBox : searchResultsStepListStepList.getItems()) {


                    VBox vbox = (VBox) ((HBox) hBox).getChildren().get(1);
                    Label label = (Label) vbox.getChildren().get(1);
                    String title = label.getText();

                    Label label1 = (Label) vbox.getChildren().get(2);
                    String title1 = label1.getText();

                    System.out.println("\n");
                    System.out.println(title1);

                    System.out.println(title);

                    int startIndex = title.indexOf(":") + 2;
                    int endIndex = title.length();
                    String recipeName = title.substring(startIndex, endIndex);
                    System.out.println("title list : "+recipeName);
                    System.out.println("title recipe : "+recipe.getTitle()+"\n");
                    if (recipe.getTitle().contains(recipeName)) {

                        System.out.println("item already in list");
                        alreadyInList ++;
                    }
                }
                if (alreadyInList == 0) {
                    recipeList.getItems().add(setRecipeItem(recipe));
                }

            }


        }

         */
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

        Label type = new Label("Type : "+recipe.getType());

        vbox.getChildren().addAll(authorLabel, titleLabel, type);

        HBox buttonHBox = new HBox();
        buttonHBox.setAlignment(Pos.CENTER);

        Button readButton = new Button("Ajouter");
        readButton.setOnAction(event -> {
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

    @FXML
    private void Publier() {
        if (checkFields()==0) {

        }
    }

    public int checkFields() {
        int badField = 0;
        if (searchResultsStepListStepList.getItems().isEmpty()) {
            searchResultsStepListStepList.setStyle("-fx-border-color: red ;");
            badField += 1;
        }
        else {
            searchResultsStepListStepList.setStyle("-fx-border-color:transparent ;");
        }


        if (titleRecipe.getText().isEmpty()) {
            titleRecipe.setStyle("-fx-border-color: red ;");
            badField += 1;
        }
        else {
            titleRecipe.setStyle("-fx-border-color:transparent ;");
        }
        if (descriptionRecipe.getText().isEmpty()) {
            descriptionRecipe.setStyle("-fx-border-color: red ;");
            badField += 1;
        }
        else {
            descriptionRecipe.setStyle("-fx-border-color:transparent ;");
        }
        return badField;
    }

    public void setTitleRecipe(String title) {
        titleRecipe.setText(title);
        titleRecipe.setFocusTraversable(false);
    }

    public void setDescriptionRecipe(String description) {
        descriptionRecipe.setText(description);
        descriptionRecipe.setFocusTraversable(false);
    }

}