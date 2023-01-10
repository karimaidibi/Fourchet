package com.fourchet.ui.account.activities;

import com.fourchet.bl.account.activities.ActivitiesFacade;
import com.fourchet.bl.orders.CartFacade;
import com.fourchet.bl.products.ProductsFacade;
import com.fourchet.products.Product;
import com.fourchet.ui.GeneralController;
import com.fourchet.ui.orders.CartController;
import com.fourchet.users.actitvities.Activity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewActivityController implements Initializable {
    @FXML
    private Text nameOfActivity;

    @FXML
    private ListView listOfProducts;
    private ObservableList<HBox> productsBoxes = FXCollections.observableArrayList();

    private Activity activity;
    private ProductsFacade productsFacade = ProductsFacade.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activity = ActivitiesFacade.getInstance().getCurrentActivity();
        nameOfActivity.setText(activity.getName());
        loadProductsFromDatabase();
    }

    /**
     * Method that is called when this controller is loaded
     * It will update the list of products and the list of categories in the choice box
     */

    public void loadProductsFromDatabase() {
        // load products from database and add them to the ObservableList
        try {
            for (Product product : this.productsFacade.getAllByOwner(activity.getOwnerEmail(), activity.getName())) {
                HBox hBox = this.createProductCard(product);
                this.productsBoxes.add(hBox);
                listOfProducts.getItems().add(hBox);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HBox createProductCard(Product product) {
        // Create the left VBox to hold the product name and category
        VBox leftVBox = new VBox();
        Label nameLabel = new Label( "Product : " + product.getName());
        Label categoryLabel = new Label("Category : " + product.getCategory().getName());
        Label priceLabel = new Label( "Price : " + product.getPrice());
        leftVBox.getChildren().addAll(nameLabel, categoryLabel, priceLabel);


        // Create the HBox to hold the left and right VBoxes
        HBox productCard = new HBox();
        // Create the modify and delete buttons
        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    /*
                    CartController cartController = new CartController();
                    cartController.addProductToCart(product);

                     */
                    CartFacade.getInstance().addProductToCart(product);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        productCard.getChildren().addAll(leftVBox, addToCartButton);

        Double prefWidthOfListView = this.listOfProducts.getPrefWidth();
        productCard.setPrefWidth(prefWidthOfListView);
        productCard.alignmentProperty().setValue(javafx.geometry.Pos.CENTER_LEFT);
        productCard.setSpacing(40);

        return productCard;
    }
}
