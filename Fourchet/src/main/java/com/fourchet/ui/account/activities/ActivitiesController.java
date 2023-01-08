package com.fourchet.ui.account.activities;

import com.fourchet.bl.account.UserFacade;
import com.fourchet.bl.account.activities.ActivitiesFacade;
import com.fourchet.ui.GeneralController;
import com.fourchet.ui.account.Application;
import com.fourchet.users.actitvities.Activity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ActivitiesController implements Initializable {

    private ActivitiesFacade activitiesFacade = ActivitiesFacade.getInstance();
    @FXML private VBox listOfActivities;

    private ObservableList<StackPane> activitiesBoxes = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loadActivitiesFromDatabase();
    }

    public void loadActivitiesFromDatabase() {
        for (Activity activity : this.activitiesFacade.getAllByUser(UserFacade.getInstance().getCurrentUser())) {
            StackPane stackPane = this.createActivityCard(activity);
            // this.activitiesBoxes.add(stackPane);
            this.listOfActivities.getChildren().add(stackPane);
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

    public StackPane createActivityCard(Activity activity) {
        Label nameLabel = new Label(activity.getName());
        /*
        ImageView imageView = new ImageView(String.valueOf(activity.getPicture()));
        imageView.setFitHeight(100.0);
        imageView.setFitWidth(100.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

         */

        StackPane activityCard = new StackPane(nameLabel);
        return activityCard;
    }
}
