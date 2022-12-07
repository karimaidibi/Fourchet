module com.fourchet.demo {
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
    //requires AnimateFX;
    requires java.logging;
    //requires org.mongodb.bson;
    requires mongo.java.driver;

    opens com.fourchet to javafx.fxml;
    exports com.fourchet;
    exports com.fourchet.login;
    opens com.fourchet.login to javafx.fxml;
}