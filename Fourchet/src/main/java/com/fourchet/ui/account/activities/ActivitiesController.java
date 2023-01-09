package com.fourchet.ui.account.activities;

import com.fourchet.bl.account.UserFacade;
import com.fourchet.bl.account.activities.ActivitiesFacade;
import com.fourchet.ui.GeneralController;
import com.fourchet.ui.Popup;
import com.fourchet.ui.account.Application;
import com.fourchet.users.actitvities.Activity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ActivitiesController implements Initializable {

    private ActivitiesFacade activitiesFacade = ActivitiesFacade.getInstance();
    @FXML
    private BorderPane activitiesFrame;
    @FXML private VBox listOfActivities;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loadActivitiesFromDatabase();
    }

    public void loadActivitiesFromDatabase() {
        for (Activity activity : this.activitiesFacade.getAllByUser(UserFacade.getInstance().getCurrentUser())) {
            HBox hBox = this.createActivityCard(activity);
            this.listOfActivities.getChildren().add(hBox);
        }
    }

    public void goToAddActivity(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("/com/fourchet/ui/GeneralFrame.fxml"));
            Parent fxmlRoot = loader.load();
            GeneralController controller = loader.getController();
            controller.setCenter("/com/fourchet/ui/account/activities/AddActivitiesFrame.fxml");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public HBox createActivityCard(Activity activity) {
        Label nameLabel = new Label(activity.getName());
        /*
        ImageView imageView = new ImageView(String.valueOf(activity.getPicture()));
        imageView.setFitHeight(100.0);
        imageView.setFitWidth(100.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

         */

        HBox activityCard = new HBox(nameLabel);

        Button editButton = new Button("Edit");
        editButton.setOnAction(event -> {
            ActivitiesFacade.getInstance().setCurrentActivity(activity);
            try {
                FXMLLoader loader = new FXMLLoader(Application.class.getResource("/com/fourchet/ui/GeneralFrame.fxml"));
                Parent fxmlRoot = loader.load();
                GeneralController controller = loader.getController();
                controller.setCenter("/com/fourchet/ui/account/activities/ActivityFrame.fxml");
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
                activitiesFacade.delete(activity);
                this.listOfActivities.getChildren().remove(activityCard);
            }
        });

        activityCard.getChildren().addAll(editButton, deleteButton);
        activityCard.setAlignment(Pos.CENTER);
        activityCard.setStyle("-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: #000000; -fx-margin: 10 20 10 20;");
        return activityCard;
    }
}
