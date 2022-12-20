module com.fourchet {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
<<<<<<< HEAD
    requires com.almasb.fxgl.all;
=======
    //requires eu.hansolo.tilesfx;
    //requires eu.hansolo.fx.heatmap;
    //requires eu.hansolo.fx.countries;
    //requires com.almasb.fxgl.all;
>>>>>>> 1e2495129ac64cfa790518da8a9ad2cbba42027a
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires java.logging;
    requires org.junit.jupiter.api;

<<<<<<< HEAD
    opens com.fourchet.UI to javafx.fxml;
    exports com.fourchet.UI;

    exports com.fourchet.persist;
    opens com.fourchet.persist to javafx.fxml;

    exports com.fourchet.bl;
    opens com.fourchet.bl to javafx.fxml;

    exports com.fourchet.users;
    opens com.fourchet.users to javafx.fxml;
=======


    exports com.fourchet.persist;
    opens com.fourchet.persist to javafx.fxml;
    exports com.fourchet.persist.account;
    opens com.fourchet.persist.account to javafx.fxml;

    exports com.fourchet.users;
    opens com.fourchet.users to javafx.fxml;

    exports com.fourchet.ui.account;
    opens com.fourchet.ui.account to javafx.fxml;

    exports com.fourchet.bl.account;
    opens com.fourchet.bl.account to javafx.fxml;

>>>>>>> 1e2495129ac64cfa790518da8a9ad2cbba42027a

}