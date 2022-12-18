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

    opens com.fourchet to javafx.fxml;
    exports com.fourchet;

    opens com.fourchet.account.loginUI to javafx.fxml;
    exports com.fourchet.account.loginUI;

    exports com.fourchet.account.persist;
    opens com.fourchet.account.persist to javafx.fxml;

    exports com.fourchet.account;
    opens com.fourchet.account to javafx.fxml;

    exports com.fourchet.account.bl;
    opens com.fourchet.account.bl to javafx.fxml;

    exports com.fourchet.account.users;
    opens com.fourchet.account.users to javafx.fxml;

}