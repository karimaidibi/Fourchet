<<<<<<<< HEAD:Fourchet/src/main/java/com/fourchet/UI/Popup.java
package com.fourchet.UI;
========
package com.fourchet.ui.account;
>>>>>>>> 1e2495129ac64cfa790518da8a9ad2cbba42027a:Fourchet/src/main/java/com/fourchet/ui/account/Popup.java

import javafx.scene.control.Alert;
import javafx.stage.Window;

public class Popup {
    static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
