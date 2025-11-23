import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class CartScreen {

    private Scene scene;

    public CartScreen() {
        Label title = new Label("Your Cart");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        VBox itemsList = new VBox(10);
        itemsList.setPadding(new Insets(10));
        itemsList.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-border-radius: 5;");

        if (ScreenManager.currentCart != null) {
            Order order = ScreenManager.currentCart.getOrder();

            for (int i = 0; i < order.getOrderList().size(); i++) {
                Pizza pizza = order.getOrderList().get(i);

                Label pizzaLabel = new Label((i + 1) + ". " + pizza.toString());
                pizzaLabel.setWrapText(true);

                itemsList.getChildren().add(pizzaLabel);
                itemsList.getChildren().add(new Separator());
            }

            // Cart summary
            double subtotal = ScreenManager.currentCart.getCartTotal();
            double tax = subtotal * 0.07;
            double total = subtotal + tax;

            Label subtotalLbl = new Label(String.format("Subtotal: $%.2f", subtotal));
            Label taxLbl = new Label(String.format("Tax (7%%): $%.2f", tax));
            Label totalLbl = new Label(String.format("Total: $%.2f", total));
            totalLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

            VBox summary = new VBox(8, subtotalLbl, taxLbl, totalLbl);
            summary.setAlignment(Pos.CENTER_RIGHT);

            itemsList.getChildren().addAll(summary);
        } else {
            Label empty = new Label("Your cart is empty");
            empty.setStyle("-fx-font-size: 16px;");
            itemsList.getChildren().add(empty);
        }

        ScrollPane scrollPane = new ScrollPane(itemsList);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(350);

        Button addMoreBtn = new Button("Add More Pizzas");
        addMoreBtn.setOnAction(e -> ScreenManager.showMenuScreen());

        Button checkoutBtn = new Button("Proceed to Checkout");
        checkoutBtn.setPrefWidth(180);
        checkoutBtn.setStyle("-fx-font-size: 14px;");
        checkoutBtn.setDisable(ScreenManager.currentCart == null);
        checkoutBtn.setOnAction(e -> ScreenManager.showCheckoutScreen());

        Button backBtn = new Button("Back to Menu");
        backBtn.setOnAction(e -> ScreenManager.showMenuScreen());

        HBox buttons = new HBox(12, backBtn, addMoreBtn, checkoutBtn);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox(20, title, scrollPane, buttons);
        layout.setPadding(new Insets(25));

        scene = new Scene(layout, 700, 550);
        try {
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        } catch (Exception ex) {
            // CSS optional
        }
    }

    public Scene getScene() {
        return scene;
    }
}