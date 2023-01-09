package com.fourchet.ui;

import com.fourchet.bl.account.UserFacade;
import com.fourchet.ui.account.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GeneralController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private GridPane header;


    public GeneralController(){
    }

    public static void goToNextScene(Event event, String fxmlFrameName) {
        try{

            FXMLLoader loader = new FXMLLoader(Application.class.getResource(fxmlFrameName));
            Parent fxmlRoot = loader.load();
            // Create a new scene with the destination scene as the root node
            Scene scene = new Scene(fxmlRoot);

            // Get the stage from the event source (e.g., a button)
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.hide();
            // Set the scene for the stage
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setCenter(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource(fxmlPath));
            Parent fxmlRoot = loader.load();
            // borderPane.setCenter(fxmlRoot);

            Scene scene = new Scene(new FXMLLoader(Application.class.getResource("/com/fourchet/ui/GeneralFrame.fxml")).load());

            // Get the stage from the event source (e.g., a button)
            Stage stage = com.fourchet.ui.Application.generalStage;
            BorderPane borderPane1 = (BorderPane) scene.lookup("#borderPane");
            borderPane1.setCenter(fxmlRoot);
            stage.hide();
            // Set the scene for the stage
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (UserFacade.getInstance().getCurrentUser() == null){
            header.setVisible(false);
        }
        else {
            header.setVisible(true);
        }
    }
}
