package com.fourchet.ui;

import com.fourchet.bl.account.UserFacade;
import com.fourchet.ui.account.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class HeaderController implements Initializable {
    @FXML
    private Button activitiesButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (UserFacade.getInstance().getCurrentUser() != null) {
            if (UserFacade.getInstance().getCurrentUser().getRole().equals("provider")) {
                activitiesButton.setVisible(true);
            } else {
                activitiesButton.setVisible(false);
            }
        }
    }

    public void showFavorites(ActionEvent actionEvent) {
        System.out.println("click on favorites");
    }

    public void showNotifications(ActionEvent actionEvent) {
        System.out.println("click on notifications");
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
    }
}