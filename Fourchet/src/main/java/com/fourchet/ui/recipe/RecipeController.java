package com.fourchet.ui.recipe;

import com.fourchet.persist.DaoFactory;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.bson.Document;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import static com.fourchet.ui.account.Popup.showAlert;

public class RecipeController {
    /*
    @FXML
    private ListView<String> ingredientList;

    @FXML
    private void initialize() {
        // Ajout des ingrédients à la liste

        ingredientList.getItems().addAll(
                "Farine",
                "Oeufs",
                "Beurre",
                "Sucre",
                "Lait",
                "Levure chimique"
        );


    }*/

    @FXML
    private ImageView imageRecipe;

    @FXML
    private Button buttonPicture;

    @FXML
    private VBox stepBox;

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
    private Button  publierButton;

    @FXML
    private ListView<Label> ingredientList;

    @FXML
    private ListView<Label> selectedIngredientList;


    public ImageView getImageRecipe() {
        return imageRecipe;
    }

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
            selectedIngredients.add(ingredient);
        });

        selectedStepList.setItems(steps);
    }

    public void Publier() throws IOException {
        //showAlert(Alert.AlertType.INFORMATION, GeneralPane.getScene().getWindow(), "Ingredient ",selectedIngredientList.getItems().toString());
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

        if (badField == 0) {



            Image image = this.getImageRecipe().getImage();

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

            Document recipeDocument = new Document()
                    .append("Title", titleRecipe.getText())
                    .append("Description", descriptionRecipe.getText())
                    .append("Image", imageDocument.toJson())
                    .append("Ingredients", ingredientsList)
                    .append("Steps", StepsList)
                    .append("Author", "test@gmail");

            Recipe addedRecipe = new Recipe(recipeDocument);
            DaoFactory daoFactory = new DaoFactory();
            daoFactory.getRecipeDaoMongoDB().save(addedRecipe);
            System.out.println("Recipe added");
            System.out.println(daoFactory.getRecipeDaoMongoDB().getAllRecipe().toString());
            showAlert(Alert.AlertType.INFORMATION, GeneralPane.getScene().getWindow(), "Steps ",StepsList.toString());

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
}

