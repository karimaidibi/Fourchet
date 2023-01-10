package com.fourchet.ui.account.activities;

import com.fourchet.bl.account.activities.ActivitiesFacade;
import com.fourchet.ui.GeneralController;
import com.fourchet.users.actitvities.Activity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewActivitiesController implements Initializable {

    private ActivitiesFacade activitiesFacade = ActivitiesFacade.getInstance();
    @FXML
    private BorderPane activitiesFrame;
    @FXML private VBox providerList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loadActivitiesFromDatabase();
    }

    public void loadActivitiesFromDatabase() {
        for (Activity activity : this.activitiesFacade.getAll()) {
            HBox hBox = this.createActivityCard(activity);
            this.providerList.getChildren().add(hBox);
        }
    }

    public HBox createActivityCard(Activity activity) {
        Label nameLabel = new Label(activity.getName());

        HBox activityCard = new HBox(nameLabel);

        Button voirButton = new Button("Voir");
        voirButton.setOnAction(event -> {
            ActivitiesFacade.getInstance().setCurrentActivity(activity);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fourchet/ui/GeneralFrame.fxml"));
                Parent fxmlRoot = loader.load();
                GeneralController controller = loader.getController();
                controller.setCenter("/com/fourchet/ui/account/activities/ViewActivityFrame.fxml");
            }
            catch (Exception e) {
                e.getStackTrace();
            }
        });

        activityCard.getChildren().add(voirButton);
        activityCard.setAlignment(Pos.CENTER);
        activityCard.setStyle("-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: #000000; -fx-margin: 10 20 10 20;");
        return activityCard;
    }
}
