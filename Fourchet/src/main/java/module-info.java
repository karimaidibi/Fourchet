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



    exports com.fourchet.persist;
    opens com.fourchet.persist to javafx.fxml;
    exports com.fourchet.persist.account;
    opens com.fourchet.persist.account to javafx.fxml;

    exports com.fourchet.users;
    opens com.fourchet.users to javafx.fxml;
    exports com.fourchet.ingredients;
    opens com.fourchet.ingredients to javafx.fxml;

    exports com.fourchet.ui.account;
    opens com.fourchet.ui.account to javafx.fxml;
    exports com.fourchet.ui.ingredients;
    opens com.fourchet.ui.ingredients to javafx.fxml;

    exports com.fourchet.bl.account;
    opens com.fourchet.bl.account to javafx.fxml;



}