<<<<<<<< HEAD:Fourchet/src/main/java/com/fourchet/UI/Application.java
package com.fourchet.UI;
========
package com.fourchet.ui.account;
>>>>>>>> 1e2495129ac64cfa790518da8a9ad2cbba42027a:Fourchet/src/main/java/com/fourchet/ui/account/Application.java

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Login or Sign-Up Form!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}