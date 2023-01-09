package com.fourchet.ui;

import com.fourchet.bl.account.UserFacade;
import com.fourchet.users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HeaderController implements Initializable {
    @FXML
    private Button activitiesButton;
    @FXML
    private Button ingredientsButton;
    @FXML
    private Button ingredientCategoriesButton;
    @FXML
    private Button typeOfCuisineButton;
    @FXML
    private Button myRecipesButton;
    @FXML
    private Button favoritesButton;
    @FXML
    private Button notificationsButton;
    @FXML
    private Button cartButton;
    @FXML
    private HBox buttonsHbox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user = UserFacade.getInstance().getCurrentUser();
        if (user != null) {
            if (!user.getRole().equals("premium")) {
                buttonsHbox.getChildren().remove(myRecipesButton);
            }
            if (user.getRole().equals("provider")) {
                buttonsHbox.getChildren().removeAll(ingredientsButton, ingredientCategoriesButton, typeOfCuisineButton);
            } else if (user.getRole().equals("admin")) {
                buttonsHbox.getChildren().removeAll(notificationsButton, cartButton, activitiesButton, favoritesButton);
            } else {
                buttonsHbox.getChildren().removeAll(activitiesButton, ingredientsButton, ingredientCategoriesButton, typeOfCuisineButton);
            }
        }
    }

    public void showFavorites(ActionEvent actionEvent) {
        // TODO : go to favorites page
    }

    public void showNotifications(ActionEvent actionEvent) {
        // TODO : go to notifications page
    }

    public void showProfile(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("/com/fourchet/ui/GeneralFrame.fxml"));
            Parent fxmlRoot = loader.load();
            GeneralController controller = loader.getController();
            controller.setCenter("/com/fourchet/ui/account/ProfileFrame.fxml");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void logout(ActionEvent actionEvent) {
        System.out.println("click on logout");
        try {
            UserFacade.getInstance().setCurrentUser(null);
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("/com/fourchet/ui/GeneralFrame.fxml"));
            Parent fxmlRoot = loader.load();
            GeneralController controller = loader.getController();
            controller.setCenter("/com/fourchet/ui/account/Login.fxml");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //GeneralController.goToNextScene(actionEvent, "/com/fourchet/ui/account/Login.fxml");
    }

    public void showActivities(ActionEvent actionEvent) {
        System.out.println("click on activities");
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("/com/fourchet/ui/GeneralFrame.fxml"));
            Parent fxmlRoot = loader.load();
            GeneralController controller = loader.getController();
            controller.setCenter("/com/fourchet/ui/account/activities/ActivitiesFrame.fxml");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showIngredients(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("/com/fourchet/ui/GeneralFrame.fxml"));
            Parent fxmlRoot = loader.load();
            GeneralController controller = loader.getController();
            controller.setCenter("/com/fourchet/ui/ingredients/IngredientsManagement.fxml");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showIngredientCategories(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("/com/fourchet/ui/GeneralFrame.fxml"));
            Parent fxmlRoot = loader.load();
            GeneralController controller = loader.getController();
            controller.setCenter("/com/fourchet/ui/ingredients/IngredientsCategoryManagement.fxml");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showTOC(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("/com/fourchet/ui/GeneralFrame.fxml"));
            Parent fxmlRoot = loader.load();
            GeneralController controller = loader.getController();
            controller.setCenter("/com/fourchet/ui/typeOfCuisine/TypeOfCuisineManagement.fxml");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showMyRecipes(ActionEvent actionEvent) {
        // TODO : go to my recipes page
    }

    public void showListOfRecipes(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("/com/fourchet/ui/GeneralFrame.fxml"));
            Parent fxmlRoot = loader.load();
            GeneralController controller = loader.getController();
            controller.setCenter("/com/fourchet/ui/recipe/RecipeSearchFrame.fxml");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showCart(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("/com/fourchet/ui/GeneralFrame.fxml"));
            Parent fxmlRoot = loader.load();
            GeneralController controller = loader.getController();
            controller.setCenter("/com/fourchet/ui/orders/CartFrame.fxml");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}