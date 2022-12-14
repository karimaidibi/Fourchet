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
    requires com.almasb.fxgl.all;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires java.logging;

    opens com.fourchet.UI to javafx.fxml;
    exports com.fourchet.UI;

    exports com.fourchet.persist;
    opens com.fourchet.persist to javafx.fxml;

    exports com.fourchet.bl;
    opens com.fourchet.bl to javafx.fxml;

    exports com.fourchet.users;
    opens com.fourchet.users to javafx.fxml;

}