package com.fourchet.ui.account.activities;

import com.fourchet.bl.account.activities.ActivitiesFacade;
import com.fourchet.ui.GeneralController;
import com.fourchet.users.actitvities.Activity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ActivityController implements Initializable {
    @FXML
    private Text nameOfActivity;
    @FXML
    private Text location;
    @FXML
    private Text phoneNumber;
    @FXML
    private VBox menuVbox;

    private Activity activity;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activity = ActivitiesFacade.getInstance().getCurrentActivity();
        nameOfActivity.setText(activity.getName());
        location.setText(activity.getLocation());
        phoneNumber.setText(activity.getPhoneNumber());
    }

    public void loadProducts(Activity activity) {

    }

    public void addProduct(ActionEvent actionEvent) {
        if (activity.getType().equals("Restaurant")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fourchet/ui/GeneralFrame.fxml"));
                Parent fxmlRoot = loader.load();
                GeneralController controller = loader.getController();
                controller.setCenter("/com/fourchet/ui/dishes/DishCreateFrame.fxml");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fourchet/ui/GeneralFrame.fxml"));
                Parent fxmlRoot = loader.load();
                GeneralController controller = loader.getController();
                controller.setCenter("/com/fourchet/ui/dishes/DishCreateFrame.fxml");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
