package com.fourchet.ui.recipe;

import com.fourchet.bl.account.UserFacade;
import com.fourchet.bl.recipe.RecipeFacade;
import com.fourchet.recipe.Recipe;
import com.fourchet.ui.GeneralController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MyRecipesController implements Initializable {

    private RecipeFacade recipeFacade = RecipeFacade.getInstance();
    @FXML
    private BorderPane myRecipesFrame;
    @FXML private VBox listOfRecipes;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loadRecipesFromDatabase();
    }

    public void loadRecipesFromDatabase() {
        for (Recipe recipe : this.recipeFacade.findAllByAuthor(UserFacade.getInstance().getCurrentUser())) {
            HBox hBox = this.createRecipeCard(recipe);
            this.listOfRecipes.getChildren().add(hBox);
        }
    }

    public void goToRecipeCreateFrame(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fourchet/ui/GeneralFrame.fxml"));
            Parent fxmlRoot = loader.load();
            GeneralController controller = loader.getController();
            controller.setCenter("/com/fourchet/ui/recipe/RecipeCreateFrame.fxml");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public HBox createRecipeCard(Recipe recipe) {
        Label nameLabel = new Label(recipe.getTitle());
        /*
        ImageView imageView = new ImageView(String.valueOf(activity.getPicture()));
        imageView.setFitHeight(100.0);
        imageView.setFitWidth(100.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

         */

        HBox recipeCard = new HBox(nameLabel);

        Button editButton = new Button("Edit");
        editButton.setOnAction(event -> {
            RecipeFacade.getInstance().setCurrentRecipe(recipe);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fourchet/ui/GeneralFrame.fxml"));
                Parent fxmlRoot = loader.load();
                GeneralController controller = loader.getController();
                controller.setCenter("/com/fourchet/ui/account/RecipeViewFrame.fxml");
            }
            catch (Exception e) {
                e.getStackTrace();
            }
        });

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Are you sure you want to delete this item ?");
            alert.setContentText("This action cannot be undone.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                recipeFacade.delete(recipe);
                this.listOfRecipes.getChildren().remove(recipeCard);
            }
        });

        recipeCard.getChildren().addAll(editButton, deleteButton);
        recipeCard.setAlignment(Pos.CENTER);
        recipeCard.setStyle("-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: #000000; -fx-margin: 10 20 10 20;");
        return recipeCard;
    }
}
