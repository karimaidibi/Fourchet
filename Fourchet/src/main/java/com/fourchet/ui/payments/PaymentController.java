package com.fourchet.ui.payments;

import com.fourchet.ingredients.Ingredient;
import com.fourchet.orders.Cart;
import com.fourchet.orders.CartItem;
import com.fourchet.users.User;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import com.fourchet.bl.orders.CartFacade;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.List;

public class PaymentController {
    @javafx.fxml.FXML
    private VBox GeneralPane;
    @javafx.fxml.FXML
    private Label nameOfUser;
    @javafx.fxml.FXML
    private Button backToCartButton;
    @javafx.fxml.FXML
    private ImageView VisaCard;
    @javafx.fxml.FXML
    private ImageView MasterCard;
    @javafx.fxml.FXML
    private TextField NameOnCardInput;
    @javafx.fxml.FXML
    private TextField CardNumberInput;
    @javafx.fxml.FXML
    private TextField expirationDateInput;
    @javafx.fxml.FXML
    private TextField cvvInput;
    @javafx.fxml.FXML
    private Label orderPrice;
    @javafx.fxml.FXML
    private Label deliveryPrice;
    @javafx.fxml.FXML
    private Label totalPrice;
    @javafx.fxml.FXML
    private Button payNowButton;

    @javafx.fxml.FXML
    public void backToCart(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void selectVisaCardMethod(Event event) {
    }

    @javafx.fxml.FXML
    public void selectMasterCardMethod(Event event) {
    }

    @javafx.fxml.FXML
    public void payNow(ActionEvent actionEvent) {
    }
}
