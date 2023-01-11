module com.fourchet {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    //requires eu.hansolo.tilesfx;
    //requires eu.hansolo.fx.heatmap;
    //requires eu.hansolo.fx.countries;
    //requires com.almasb.fxgl.all;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires java.logging;
    requires java.desktop;

    // persist
    exports com.fourchet.persist;
    opens com.fourchet.persist to javafx.fxml;

    exports com.fourchet.persist.account;
    opens com.fourchet.persist.account to javafx.fxml;

    exports com.fourchet.persist.dishes;
    opens com.fourchet.persist.dishes to javafx.fxml;

    exports com.fourchet.persist.ingredientCategories;
    opens com.fourchet.persist.ingredientCategories to javafx.fxml;

    exports com.fourchet.persist.ingredients;
    opens com.fourchet.persist.ingredients to javafx.fxml;

    exports com.fourchet.persist.orders;
    opens com.fourchet.persist.orders to javafx.fxml;

    exports com.fourchet.persist.payments;
    opens com.fourchet.persist.payments to javafx.fxml;

    exports com.fourchet.persist.productCategories;
    opens com.fourchet.persist.productCategories to javafx.fxml;

    exports com.fourchet.persist.products;
    opens com.fourchet.persist.products to javafx.fxml;

    exports com.fourchet.persist.recipe;
    opens com.fourchet.persist.recipe to javafx.fxml;

    exports com.fourchet.persist.reviews;
    opens com.fourchet.persist.reviews to javafx.fxml;

    exports com.fourchet.persist.typeOfCuisine;
    opens com.fourchet.persist.typeOfCuisine to javafx.fxml;

    // classes
    exports com.fourchet.users;
    opens com.fourchet.users to javafx.fxml;

    exports com.fourchet.ingredients;
    opens com.fourchet.ingredients to javafx.fxml;

    exports com.fourchet.review;
    opens com.fourchet.review to javafx.fxml;

    exports com.fourchet.recipe;
    opens com.fourchet.recipe to javafx.fxml;

    exports com.fourchet.products;
    opens com.fourchet.products to javafx.fxml;

    exports com.fourchet.orders;
    opens com.fourchet.orders to javafx.fxml;

    exports com.fourchet.dishes;
    opens com.fourchet.dishes to javafx.fxml;

    //UI
    exports com.fourchet.ui;
    opens com.fourchet.ui to javafx.fxml;

    exports com.fourchet.ui.account;
    opens com.fourchet.ui.account to javafx.fxml;

    exports com.fourchet.ui.account.activities;
    opens com.fourchet.ui.account.activities to javafx.fxml;

    exports com.fourchet.ui.dishes;
    opens com.fourchet.ui.dishes to javafx.fxml;

    exports com.fourchet.ui.dishes.typeOfCuisine;
    opens com.fourchet.ui.dishes.typeOfCuisine to javafx.fxml;

    exports com.fourchet.ui.ingredients;
    opens com.fourchet.ui.ingredients to javafx.fxml;

    exports com.fourchet.ui.orders;
    opens com.fourchet.ui.orders to javafx.fxml;

    exports com.fourchet.ui.orders.payments;
    opens com.fourchet.ui.orders.payments to javafx.fxml;

    exports com.fourchet.ui.products;
    opens com.fourchet.ui.products to javafx.fxml;

    exports com.fourchet.ui.rates;
    opens com.fourchet.ui.rates to javafx.fxml;

    exports com.fourchet.ui.recipe;
    opens com.fourchet.ui.recipe to javafx.fxml;

    // Business logic
    exports com.fourchet.bl.account;
    opens com.fourchet.bl.account to javafx.fxml;

    exports com.fourchet.bl.account.activities;
    opens com.fourchet.bl.account.activities to javafx.fxml;

    exports com.fourchet.bl.dishes;
    opens com.fourchet.bl.dishes to javafx.fxml;

    exports com.fourchet.bl.ingredientCategories;
    opens com.fourchet.bl.ingredientCategories to javafx.fxml;

    exports com.fourchet.bl.ingredients;
    opens com.fourchet.bl.ingredients to javafx.fxml;

    exports com.fourchet.bl.orders;
    opens com.fourchet.bl.orders to javafx.fxml;

    exports com.fourchet.bl.orders.payments;
    opens com.fourchet.bl.orders.payments to javafx.fxml;

    exports com.fourchet.bl.products;
    opens com.fourchet.bl.products to javafx.fxml;

    exports com.fourchet.bl.recipe;
    opens com.fourchet.bl.recipe to javafx.fxml;

    exports com.fourchet.bl.reviews;
    opens com.fourchet.bl.reviews to javafx.fxml;

    exports com.fourchet.bl.typeOfCuisine;
    opens com.fourchet.bl.typeOfCuisine to javafx.fxml;

}