package com.fourchet.ui.recipe;

import com.fourchet.bl.recipe.RecipeFacade;
import com.fourchet.recipe.Recipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import org.bson.Document;
import org.bson.types.Binary;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import static com.fourchet.ui.Popup.showAlert;

public class RecipeModifyController{

    @FXML
    private ImageView imageRecipe;

    @FXML
    private Button buttonPicture;

    @FXML
    private TextField descriptionRecipe;

    @FXML
    private TextField titleRecipe;

    @FXML
    private TextField stepField;

    @FXML
    private ListView<HBox> selectedStepList;

    private ObservableList<HBox> steps = FXCollections.observableArrayList();

    @FXML
    BorderPane GeneralPane;

    @FXML
    private ListView<Label> ingredientList;

    @FXML
    private ListView<Label> selectedIngredientList;


    @FXML
    private ChoiceBox<String> TypeOfRecipe;

    private Recipe recipe;

    private RecipeFacade recipeFacade = RecipeFacade.getInstance();

    @FXML
    private void initialize() {
        // Ajout d'un ChangeListener à l'objet selectionModel de la ListView
        ingredientList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Ajout de l'ingrédient sélectionné à la liste des ingrédients sélectionnés
            ObservableList<Label> selectedIngredients = selectedIngredientList.getItems();
            Label ingredient = new Label(newValue.getText());
            Button deleteButton = new Button("X");
            deleteButton.setOnAction(event -> {
                selectedIngredients.remove(ingredient);
            });
            ingredient.setGraphic(deleteButton);

            int contains = 0;
            for (Label selectedIngredient : selectedIngredients) {
                if (selectedIngredient.getText().equals(ingredient.getText())) {
                    contains = 1;
                }
            }
            if (contains == 0) {
                selectedIngredients.add(ingredient);
            }
        });
        selectedStepList.setItems(steps);


        this.recipe = recipeFacade.getAll().get(1);

        setTitleRecipe(recipe.getTitle());
        setDescriptionRecipe(recipe.getDescription());
        setImageRecipe(recipe.getImage());
        setIngredientList(recipe.getIngredients());
        setStepList(recipe.getSteps());
        setTypeOfRecipe(recipe.getType().toString());
        TypeOfRecipe.getItems().addAll(com.fourchet.recipe.TypeOfRecipe.getAllType());
    }

    public void Modifier() {

        if (checkFields() == 0) {
            Recipe newRecipe = setRecipeFromFields();
            recipeFacade.update(recipe, newRecipe);
            showAlert(Alert.AlertType.INFORMATION, GeneralPane.getScene().getWindow(), "Modification Recette","Vous avez bien modifié la recette");
            initialize();
        }
    }
    @FXML
    private void addStep(ActionEvent event) {
        // Vérification que le champ de texte n'est pas vide
        if (!stepField.getText().trim().isEmpty()) {
            // Création de l'étape avec le bouton de suppression
            String step = stepField.getText();
            Button deleteButton = new Button("X");;
            Label stepLabel = new Label(step);
            stepLabel.setStyle("-fx-padding: 0 0 0 4;");
            HBox stepHBox = new HBox(deleteButton, stepLabel);
            stepHBox.setAlignment(Pos.CENTER_LEFT);
            // Ajout de l'étape à la ObservableList
            steps.add(stepHBox);

            deleteButton.setOnAction(event1 -> {
                // Suppression de l'étape de la ObservableList
                steps.remove(stepHBox);
            });

            // Effacement du champ de texte
            stepField.clear();
        }
    }

    public void ChangePicture(ActionEvent event) {
        // Ouvrir une fenêtre de sélection de fichier
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionnez une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Tous les fichiers", "*.*")
        );
        File selectedFile = fileChooser.showOpenDialog(buttonPicture.getScene().getWindow());
        if (selectedFile == null) {
            return;
        }

        // Chargement de l'image à partir du fichier sélectionné
        Image image = new Image(selectedFile.toURI().toString());

        // Mise à jour de l'image dans le ImageView
        imageRecipe.setImage(image);
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

        ObservableList<Label> selectedIngredients = selectedIngredientList.getItems();
        for (String i : ingredient) {

            Label ingredientLabel = new Label(i);
            Button deleteButton = new Button("X");
            deleteButton.setOnAction(event -> {
                selectedIngredients.remove(ingredientLabel);
            });
            ingredientLabel.setGraphic(deleteButton);

            int contains = 0;
            for (Label label : selectedIngredients) {
                if (label.getText().equals(ingredientLabel.getText())) {
                    contains = 1;
                }
            }
            if (contains == 0) {
                selectedIngredients.add(ingredientLabel);
            }
        }
    }

    public void setStepList(List<String> step) {



        for (String s : step) {
            /*
            System.out.println(s);
            Label stepLabel = new Label(s);
            HBox stepBox = new HBox();
            stepBox.getChildren().add(stepLabel);
            selectedStepList.getItems().add(stepBox); */

            int contains = 0;
            for (HBox stepBox : steps) {
                if (stepBox.getChildren().get(1) instanceof Label) {
                    Label stepLabel = (Label) stepBox.getChildren().get(1);
                    if (stepLabel.getText().equals(s)) {
                        contains = 1;
                    }
                }
            }
            if (contains == 0) {
                Button deleteButton = new Button("X");;
                Label stepLabel = new Label(s);
                stepLabel.setStyle("-fx-padding: 0 0 0 4;");
                HBox stepHBox = new HBox(deleteButton, stepLabel);
                stepHBox.setAlignment(Pos.CENTER_LEFT);
                // Ajout de l'étape à la ObservableList
                steps.add(stepHBox);

                deleteButton.setOnAction(event1 -> {
                    // Suppression de l'étape de la ObservableList
                    steps.remove(stepHBox);
                });
            }
        }
    }

    public void setTypeOfRecipe(String type) {
        TypeOfRecipe.setValue(type);
    }

    public int checkFields() {
        int badField = 0;
        if (selectedIngredientList.getItems().isEmpty()) {
            selectedIngredientList.setStyle("-fx-border-color: red ;");
            badField += 1;
        }
        else {
            selectedIngredientList.setStyle("-fx-border-color:transparent ;");
        }

        if (selectedStepList.getItems().isEmpty()) {
            selectedStepList.setStyle("-fx-border-color: red ;");
            badField += 1;
        }
        else {
            selectedStepList.setStyle("-fx-border-color:transparent ;");
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

    public Recipe setRecipeFromFields() {
        Image image = this.imageRecipe.getImage();

// Convertir l'image en tableau de pixels
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        int[] pixels = new int[width * height];
        image.getPixelReader().getPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), pixels, 0, width);

// Convertir le tableau de pixels en tableau d'octets
        byte[] data = new byte[pixels.length * 4];
        ByteBuffer buffer = ByteBuffer.wrap(data);
        for (int pixel : pixels) {
            buffer.putInt(pixel);
        }


        Document imageDocument = new Document()
                .append("width", width)
                .append("height", height)
                .append("data", data);


        ObservableList<HBox> selectedSteps = selectedStepList.getItems();
        List<String> ingredientsList = new ArrayList<>();
        List<String> StepsList = new ArrayList<>();

        for (HBox step : selectedSteps) {
            if (step.getChildren().get(1) instanceof Label) {
                Label stepLabel = (Label) step.getChildren().get(1);
                StepsList.add(stepLabel.getText());
            }
        }

        ObservableList<Label> selectedIngredients = selectedIngredientList.getItems();
        for (Label ingredient : selectedIngredients) {
            ingredientsList.add(ingredient.getText());
        }


        Document newRecipeDocument = new Document()
                .append("Title", titleRecipe.getText())
                .append("Description", descriptionRecipe.getText())
                .append("Image", imageDocument.toJson())
                .append("Ingredients", ingredientsList)
                .append("Steps", StepsList)
                .append("Author", "test@gmail")
                .append("TypeOfRecipe", TypeOfRecipe.getValue().toString());
        Recipe newRecipe = new Recipe(newRecipeDocument);
        return newRecipe;
    }
}