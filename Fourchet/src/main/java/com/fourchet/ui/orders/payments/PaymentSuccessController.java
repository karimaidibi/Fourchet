package com.fourchet.ui.orders.payments;

import com.fourchet.bl.orders.payments.PaymentFacade;
import com.fourchet.orders.payments.Payment;
import com.fourchet.orders.payments.PaymentMethod;
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

public class PaymentSuccessController {
    @FXML
    private VBox GeneralPane;
    @FXML
    private Label nameOfUser;
    @FXML
    private Button backToCartButton;
    @FXML
    private ImageView visaCard;
    @FXML
    private ImageView MasterCard;
    @FXML
    private TextField CardNumberInput;
    @FXML
    private Label orderPrice;
    @FXML
    private Label deliveryPrice;
    @FXML
    private Label totalPrice;

    private PaymentFacade paymentFacade = PaymentFacade.getInstance();

    public void initialize() {
        // get the current payment from the facade
        Payment payment = paymentFacade.getPayment();

        // display the payment details
        this.setDisplayPrices(payment.getOrderPrice(), payment.getDeliveryPrice(), payment.getTotalPrice());

        //display the payment method
        PaymentMethod paymentMethod = payment.getPaymentMethod();
        if (paymentMethod.getPaymentMethod().contains("visa")) {
            this.visaCard.setVisible(true);
            this.MasterCard.setVisible(false);
        } else {
            this.MasterCard.setVisible(true);
            this.visaCard.setVisible(false);
        }

        // hide the first 4 digits of the card number
        String cardNumber = paymentMethod.getPaymentInfos().get("cardNumber");
        String hiddenCardNumber = "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
        this.CardNumberInput.setText(hiddenCardNumber);
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
}
