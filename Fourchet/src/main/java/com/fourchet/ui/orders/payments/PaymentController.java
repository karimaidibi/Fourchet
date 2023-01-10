package com.fourchet.ui.orders.payments;

import com.fourchet.bl.orders.payments.PaymentFacade;
import com.fourchet.orders.Cart;
import com.fourchet.ui.Application;
import com.fourchet.ui.GeneralController;
import com.fourchet.users.User;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Window;

import java.text.ParseException;

public class PaymentController {
    @FXML
    private VBox GeneralPane;
    @FXML
    private Label nameOfUser;
    @FXML
    private Button backToCartButton;
    @FXML
    private TextField NameOnCardInput;
    @FXML
    private TextField CardNumberInput;
    @FXML
    private TextField expirationDateInput;
    @FXML
    private TextField cvvInput;
    @FXML
    private Label orderPrice;
    @FXML
    private Label deliveryPrice;
    @FXML
    private Label totalPrice;
    @FXML
    private Button payNowButton;

    private PaymentFacade paymentFacade = PaymentFacade.getInstance();
    @FXML
    private CheckBox visaCardChoice;
    @FXML
    private CheckBox masterCardChoice;

    private String selectedPaymentMethod;

    public void initialize() throws ParseException {
        // get the current cart from the facade
        Cart cart = paymentFacade.getCart();
        this.setDisplayPrices(cart.getOrderPrice(), cart.getDeliveryPrice(), cart.getTotalPrice());
    }

    /**
     * Sets the prices in the display
     *
     * @param orderPrice
     * @param deliveryPrice
     * @param totalPrice
     */
    private void setDisplayPrices(double orderPrice, double deliveryPrice, double totalPrice) {
        this.orderPrice.setText(String.valueOf(orderPrice));
        this.deliveryPrice.setText(String.valueOf(deliveryPrice));
        this.totalPrice.setText(String.valueOf(totalPrice));
    }

    @FXML
    public void backToCart(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("/com/fourchet/ui/GeneralFrame.fxml"));
            Parent fxmlRoot = loader.load();
            GeneralController controller = loader.getController();
            controller.setCenter("/com/fourchet/ui/orders/CartFrame.fxml");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void selectVisaCardMethod(Event event) {
        if (visaCardChoice.isSelected()) {
            masterCardChoice.setSelected(false);
            this.selectedPaymentMethod = "visa card";
        }
    }

    @FXML
    public void selectMasterCardMethod(Event event) {
        if (masterCardChoice.isSelected()) {
            visaCardChoice.setSelected(false);
            this.selectedPaymentMethod = "master card";
        }
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }


    /**
     * method to call the pay method in the facade
     * it will parse the prices labels to floats
     */
    private void pay() {
        paymentFacade.pay(
                Float.parseFloat(orderPrice.getText()),
                Float.parseFloat(deliveryPrice.getText()),
                Float.parseFloat(totalPrice.getText()),
                selectedPaymentMethod,
                CardNumberInput.getText(),
                NameOnCardInput.getText(),
                expirationDateInput.getText(),
                cvvInput.getText());
    }


    /**
     * This method is called when the user clicks on the "Pay now" button
     * First, it checks if the user has selected a payment method
     * Then, it checks if the user has filled all the fields and if the fields are valid
     * If everything is ok, it calls the facade to save the payment
     */
    @FXML
    public void payNow(ActionEvent actionEvent) {
        if (this.selectedPaymentMethod == null) {
            showAlert(Alert.AlertType.ERROR, GeneralPane.getScene().getWindow(), "Form Error!", "Please select a payment method");
        } else {
            String nameOnCard = NameOnCardInput.getText();
            String cardNumber = CardNumberInput.getText();
            String expirationDate = expirationDateInput.getText();
            String cvv = cvvInput.getText();
            if (nameOnCard.isEmpty() || cardNumber.isEmpty() || expirationDate.isEmpty() || cvv.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, GeneralPane.getScene().getWindow(), "Form Error!", "Please fill all the fields");
                if (nameOnCard.equals("")) {
                    NameOnCardInput.setPromptText("Name missing !");
                    NameOnCardInput.setStyle("-fx-prompt-text-fill: red;");
                }
                if (cardNumber.equals("")) {
                    CardNumberInput.setPromptText("Card Number missing !");
                    CardNumberInput.setStyle("-fx-prompt-text-fill: red;");
                }
                if (nameOnCard.equals("")) {
                    expirationDateInput.setPromptText("expiration date missing !");
                    expirationDateInput.setStyle("-fx-prompt-text-fill: red;");
                }
                if (nameOnCard.equals("")) {
                    cvvInput.setPromptText("cvv missing !");
                    cvvInput.setStyle("-fx-prompt-text-fill: red;");
                }
            }
            // if the NameOnCard field is not empty, we check if it contains only letters
            else if (!nameOnCard.matches("[a-zA-Z]+")) {
                NameOnCardInput.setText("");
                NameOnCardInput.setPromptText("Name must contain only letters !");
                NameOnCardInput.setStyle("-fx-prompt-text-fill: red;");
            }
            // if the card number field is not empty, we check if it contains only numbers
            else if (!cardNumber.matches("[0-9]+")) {
                CardNumberInput.setText("");
                CardNumberInput.setPromptText("Card number must contain only numbers !");
                CardNumberInput.setStyle("-fx-prompt-text-fill: red;");
            }
            // if the expiration date field is not empty, we check if it contains only 4 numbers
            else if (!expirationDate.matches("[0-9]+") || expirationDate.length() != 4) {
                expirationDateInput.setText("");
                expirationDateInput.setPromptText("Expiration date must contain only 4 numbers !");
                expirationDateInput.setStyle("-fx-prompt-text-fill: red;");
            }
            // if the cvv field is not empty, we check if it contains only 3 numbers
            else if (!cvv.matches("[0-9]+") || cvv.length() != 3) {
                cvvInput.setText("");
                cvvInput.setPromptText("CVV must contain only 3 numbers !");
                cvvInput.setStyle("-fx-prompt-text-fill: red;");
            } else {
                // if everything is ok, we call the facade to pay
                this.pay();

                //tell the the payment facade to delete the user cart
                paymentFacade.deleteCart();

                // tell the order facade to make the order
                // TODO : make the order

                // we go to success page
                goToSuccessPage();
            }
        }
    }

    private void goToSuccessPage() {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("/com/fourchet/ui/GeneralFrame.fxml"));
            Parent fxmlRoot = loader.load();
            GeneralController controller = loader.getController();
            controller.setCenter("/com/fourchet/ui/orders/payments/PaymentSuccessFrame.fxml");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

