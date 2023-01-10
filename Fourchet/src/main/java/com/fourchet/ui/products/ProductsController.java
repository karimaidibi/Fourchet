package com.fourchet.ui.products;

import com.fourchet.bl.account.UserFacade;
import com.fourchet.bl.account.activities.ActivitiesFacade;
import com.fourchet.bl.products.ProductCategoriesFacade;
import com.fourchet.bl.products.ProductsFacade;
import com.fourchet.products.Product;
import com.fourchet.products.ProductCategory;
import com.fourchet.users.actitvities.Activity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.fourchet.ui.Popup.showAlert;

public class ProductsController implements Initializable {

    // Declare FXML elements
    @FXML private VBox GeneralPane;

    private ProductsFacade productsFacade = ProductsFacade.getInstance();

    private Product editedProduct;

    private HBox editedProductHBox;

    private boolean isAdding = true;
    @FXML
    private ListView listOfProducts;
    @FXML
    private Label actionTypeOnProduct;
    @FXML
    private TextField productNameInput;
    @FXML
    private ChoiceBox selectCategory;
    @FXML
    private TextField productPriceInput;
    @FXML
    private Button validateButton;
    @FXML
    private Button cancelButton;

    private Activity currentActivity = ActivitiesFacade.getInstance().getCurrentActivity();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loadProductsFromDatabase();
        this.listOfProducts.setItems(productsBoxes);
    }

    // Declare ObservableList to store products
    private ObservableList<HBox> productsBoxes = FXCollections.observableArrayList();

    /**
     * Method that is called when this controller is loaded
     * It will update the list of products and the list of categories in the choice box
     */

    public void loadProductsFromDatabase() {
        // load products from database and add them to the ObservableList
        ProductCategoriesFacade facade = ProductCategoriesFacade.getInstance();
        ObservableList<String> existingCategories = FXCollections.observableArrayList();
        for (ProductCategory category : facade.getAllCategories()) {
            existingCategories.add(category.getName());
        }
        this.selectCategory.setItems(existingCategories);
        this.selectCategory.setValue(selectCategory.getItems().get(0));
        for (Product product : this.productsFacade.getAllByOwner(currentActivity.getOwnerEmail(), currentActivity.getName())) {
            HBox hBox = this.createProductCard(product);
            this.productsBoxes.add(hBox);
        }
    }

    /**
     * Method to validate the new product
     * This method will be called when the user clicks on the validate button
     * This method will call either the addProduct() or the modifyProduct() method
     * depending on the action type label
     * It will also update the list of products in the list view using the loadProductsFromDatabase() method
     */
    @FXML
    private void validate() {
        // validate new product
        if (!((productNameInput.getText().startsWith(" ")) || (productNameInput.getText().equals("")))) {
            if (isAdding) {
                ProductCategory category = new ProductCategory((String) selectCategory.getSelectionModel().getSelectedItem());
                Product product = new Product(currentActivity.getOwnerEmail(), currentActivity.getName(), productNameInput.getText(), category, new Double(productPriceInput.getText()).doubleValue());
                try {
                    Product newProduct = productsFacade.saveProduct(product);
                    if (newProduct != null) {
                        HBox hBox = this.createProductCard(product);
                        this.productsBoxes.add(hBox);
                    } else {
                        showAlert(Alert.AlertType.ERROR, GeneralPane.getScene().getWindow(), "Attention !", "Products already registered !");
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            } else {
                try {
                    String[] params = new String[]{productNameInput.getText(), (String) selectCategory.getSelectionModel().getSelectedItem(), String.valueOf(new Double(productPriceInput.getText()).doubleValue())};
                    this.productsFacade.updateProduct(editedProduct, params);
                    VBox leftVBox = new VBox();
                    Label nameLabel = new Label("Product : " + params[0]);
                    Label categoryLabel = new Label("Category : " + params[1]);
                    Label priceLabel = new Label("Price : " + params[2]);
                    leftVBox.getChildren().addAll(nameLabel, categoryLabel, priceLabel);
                    this.editedProductHBox.getChildren().set(0, leftVBox);
                } catch (Exception exception) {
                    exception.getMessage();
                }
            }
        }
        else {
            showAlert(Alert.AlertType.ERROR, GeneralPane.getScene().getWindow(), "Attention !", "Invalid name");
        }
        cancel();
    }

    /**
     * Method to cancel the new product creation
     * This method will be called when the user clicks on the cancel button
     * It will erase the product name input and the category choice
     * It will change the action type label to "adding new product"
     * It will change the validate button text to "validate" instead of "modify"
     */
    @FXML
    private void cancel() {
        // TODO: implement action to cancel new product creation
        this.productNameInput.setText("");
        this.productPriceInput.setText("");
        this.actionTypeOnProduct.setText("Add new product");
        this.isAdding = true;
        this.editedProduct = null;
        this.editedProductHBox = null;
    }

    /**
     * This method should be called by the validate button when the action type is "modifying product"
     */
    @Deprecated
    private void updateProduct(Product product, HBox productCard) {
        // TODO: implement action to update an product
        this.actionTypeOnProduct.setText("Modify this product");
        this.selectCategory.setValue(product.getCategory().getName());
        this.productNameInput.setText(product.getName());
        this.productPriceInput.setText(String.valueOf(product.getPrice()));
        this.isAdding = false;
        this.editedProduct = product;
        this.editedProductHBox = productCard;
    }

    /**
     * Method to delete an product
     * This method will be called when the user clicks on the delete button
     * It will delete the product from the database
     * It will update the list of products in the list view
     * It will clear the text field and the choice box
     * It will display a success message
     */
    @Deprecated
    private void deleteProduct(Product product, HBox productCard) {
        // TODO: implement action to delete an product
        // il faut lier le bouton delete à cette méthode
        this.productsFacade.deleteProduct(product);
        this.listOfProducts.getItems().remove(productCard);
    }


    /**
     * This method will be called on each product in the database (and when the user adds a new product)
     * It will return a HBox with the product name and the product category
     * @param product the name of the product
     * */
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
        Button modifyButton = new Button("Modify");
        modifyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateProduct(product, productCard);
            }
        });
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete Confirmation");
                alert.setHeaderText("Are you sure you want to delete this item ?");
                alert.setContentText("This action cannot be undone.");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    deleteProduct(product, productCard);
                }
            }
        });
        productCard.getChildren().addAll(leftVBox, modifyButton, deleteButton);

        Double prefWidthOfListView = this.listOfProducts.getPrefWidth();
        productCard.setPrefWidth(prefWidthOfListView);
        productCard.alignmentProperty().setValue(javafx.geometry.Pos.CENTER_LEFT);
        productCard.setSpacing(40);

        return productCard;
    }


}
