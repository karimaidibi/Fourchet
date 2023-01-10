package com.fourchet.ui;


import com.fourchet.persist.DaoFactory;
import com.fourchet.persist.productCategories.ProductCategoriesDao;
import com.fourchet.persist.productCategories.ProductCategoriesDaoMongoDB;
import com.fourchet.persist.products.ProductsDaoMongoDB;
import com.fourchet.products.Product;
import com.fourchet.products.ProductCategory;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Application extends javafx.application.Application {
    public static Stage generalStage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("GeneralFrame.fxml"));
        //GeneralFrame.fxml
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Welcome to Fourchet !");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        this.generalStage = stage;

    }


    public static void main(String[] args) {
        launch();
    }
}