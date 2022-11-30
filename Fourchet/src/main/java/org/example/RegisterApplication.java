package com.example.fourchet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegisterApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Scene definition
        Text scenetitle = new Text("Create your account");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        // Register Form
        Label userName = new Label("Email address:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Label pw_confirm = new Label("Confirm password:");
        grid.add(pw_confirm, 0, 3);

        PasswordField pwB_confirm_Box = new PasswordField();
        grid.add(pwB_confirm_Box, 1, 3);

        Label role = new Label("Role:");
        grid.add(role, 0, 4);

        ChoiceBox cb_role = new ChoiceBox();
        cb_role.getItems().addAll("Client", "Provider");
        grid.add(cb_role, 1, 4);


        // Confirm button
        Button btn = new Button("Sign up");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 5);

        Scene scene = new Scene(grid, 300, 275);
        stage.setTitle("Welcome to Fourchet !");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}