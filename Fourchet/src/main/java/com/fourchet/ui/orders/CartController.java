package com.fourchet.ui.orders;

import com.fourchet.bl.account.UserFacade;
import com.fourchet.ingredients.Ingredient;
import com.fourchet.orders.Cart;
import com.fourchet.orders.CartItem;
import com.fourchet.ui.Application;
import com.fourchet.ui.GeneralController;
import com.fourchet.users.User;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.*;

import com.fourchet.bl.orders.CartFacade;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.List;

public class CartController {

    @FXML
    private VBox GeneralPane;
    @FXML
    private Label nameOfUser;
    @FXML
    private Button backToMenuButton;
    @FXML
    private Label YouHaveXItems;
    @FXML
    private ListView listOfItems;
    @FXML
    private Button GoToPaymentButton;
    @FXML
    private Label orderPrice;
    @FXML
    private Label deliveryPrice;
    @FXML
    private Label totalPrice;

    private ObservableList<HBox> itemsBoxes = FXCollections.observableArrayList();
    @FXML
    private TextArea addressInputField;

    private CartFacade cartFacade = CartFacade.getInstance();

    private Cart cart;
    @FXML
    private Button updateAddressButton;

    /**
     initialize method
     Loads the cart from the database and displays it
     */
    public void initialize(){
        loadCartFromDatabase();
        this.listOfItems.setItems(itemsBoxes);
        nameOfUser.setText("Hello " + UserFacade.getInstance().getCurrentUser().getUsername());
    }

    /**
     * This method is called by the initialize method to load the cart from the database
     * if the cart is empty, it displays it sets the text of the label "YouHaveXItems" to "Your cart is empty"
     * and disables the GoToPaymentButton and the listOfItems and sets the text of the labels orderPrice, deliveryPrice and totalPrice to 0
     * if the cart is not empty, it displays it and sets the text of the label "YouHaveXItems" to "You have X items in your cart"
     * and enables the GoToPaymentButton and the listOfItems and sets the text of the labels orderPrice, deliveryPrice and totalPrice to the calculated values
     */
    private void loadCartFromDatabase() {
        User user = this.cartFacade.getCurrentUser();

        this.itemsBoxes.clear();
        this.addressInputField.setDisable(true);
        this.cart = this.cartFacade.getCart(user);
        this.addressInputField.setText(this.cart.getDeliveryAddress());
        if (cart.getItems().size() == 0) {
            this.YouHaveXItems.setText("Your cart is empty");
            this.GoToPaymentButton.setDisable(true);
            this.listOfItems.setDisable(true);
        }else{
            this.YouHaveXItems.setText("You have " + cart.getItems().size() + " items in your cart");
            this.GoToPaymentButton.setDisable(false);
            this.listOfItems.setDisable(false);
            for (CartItem item : cart.getItems()) {
                HBox itemBox = this.createItemCard(item);
                this.itemsBoxes.add(itemBox);
            }
        }
        this.orderPrice.setText(String.valueOf(cart.getOrderPrice()));
        this.deliveryPrice.setText(String.valueOf(cart.getDeliveryPrice()));
        this.totalPrice.setText(String.valueOf(cart.getTotalPrice()));
    }

    //GoToPaymentButton method
    @FXML
    public void GoToPayment(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("/com/fourchet/ui/GeneralFrame.fxml"));
            Parent fxmlRoot = loader.load();
            GeneralController controller = loader.getController();
            controller.setCenter("/com/fourchet/ui/orders/payments/PaymentFrame.fxml");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * addItemToCart method called when the user clicks on the "Add to cart" button
     * or when the user clicks on the + button
     * if the product is not in the cart, it adds it to the cart with the quantity 1
     * if the product is in the cart, it increments the quantity of the product in the cart
     * */
    public void addProductToCart(HashMap<Object,Object> product){
        this.cart.addProduct(product);
        this.cartFacade.updateCart(this.cart);
        loadCartFromDatabase();
    }

    /**
     * removeItemFromCart method called when the user clicks on the -
     * if the product is in the cart, it removes it from the cart
     * */
    public void removeProductFromCart(HashMap<Object,Object> product) {
        this.cart.removeProduct(product);
        this.cartFacade.updateCart(this.cart);
        loadCartFromDatabase();
    }

    /**
     * This method will be called on each ItemCart int the cart in the database
     * It will return a HBox with the product name and the product quantity and price
     * @param cartItem the cartItem object
     * */
    public HBox createItemCard(CartItem cartItem) {
        // Create the left VBox to hold the ingredient name and category
        VBox leftVBox = new VBox();
        Label nameLabel = new Label( "product : " + cartItem.getProduct().get("name"));
        Label QuantityLabel = new Label("Quantity : " + cartItem.getQuantity());
        leftVBox.getChildren().addAll(nameLabel, QuantityLabel);

        // Create the right HBox to hold the ingredient price and the buttons
        HBox rightHBox = new HBox();
        Label priceLabel = new Label("Price : " + (int)cartItem.getProduct().get("price") * cartItem.getQuantity());
        // Create the + and - buttons
        Button increaseQuantityButton = new Button("+");
        increaseQuantityButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addProductToCart(cartItem.getProduct());
                System.out.println(" increase button clicked!");
            }
        });
        Button decreaseQuantityButton = new Button("-");
        decreaseQuantityButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                removeProductFromCart(cartItem.getProduct());
                System.out.println("decrease button clicked!");
            }
        });

        //set style for the right HBox
        rightHBox.getChildren().addAll(priceLabel, increaseQuantityButton, decreaseQuantityButton);
        rightHBox.alignmentProperty().setValue(javafx.geometry.Pos.CENTER_RIGHT);
        rightHBox.setSpacing(10);

        // Create the HBox to hold the left and right VBoxes
        HBox itemCard = new HBox();
        itemCard.getChildren().addAll(leftVBox, rightHBox);

        //set style for the big HBox
        Double prefWidthOfListView = this.listOfItems.getPrefWidth();
        itemCard.setPrefWidth(prefWidthOfListView);
        itemCard.alignmentProperty().setValue(javafx.geometry.Pos.CENTER_LEFT);
        itemCard.setSpacing(40);

        return itemCard;
    }

    /**
     *  updateAddrressButton method called when the user clicks on the "Update Address" button
     *  if the button label is "Update Address", and the addressInputField is not empty,
     *  it updates the delivery address of the cart and saves it in the database then change the text of the button to "Modify Address"
     *  if the button label is "Modify Address", it enables the addressInputField and change the text of the button to "Update Address"
     */
    @FXML
    public void updateAddress(ActionEvent actionEvent) {
        if (this.updateAddressButton.getText().equals("Update Address")) {
            if (!(this.addressInputField.getText() == null)) {
                this.cart.setDeliveryAddress(this.addressInputField.getText());
                this.cartFacade.updateDeliveryAddress(this.cart.getDeliveryAddress());
                this.addressInputField.setDisable(true);
                this.updateAddressButton.setText("Modify Address");
            }
        }else{
            this.addressInputField.setDisable(false);
            this.updateAddressButton.setText("Update Address");
        }
    }
}
