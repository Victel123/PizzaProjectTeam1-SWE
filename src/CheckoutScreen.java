import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class CheckoutScreen {

    private Scene scene;

    public CheckoutScreen() {
        Label title = new Label("Checkout");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Display customer info
        Customer customer = ScreenManager.currentCustomer;

        TextArea receiptArea = new TextArea();
        receiptArea.setEditable(false);
        receiptArea.setPrefHeight(300);
        receiptArea.setWrapText(true);

        if (ScreenManager.currentCart != null) {
            Checkout checkout = new Checkout(ScreenManager.currentCart);

            StringBuilder receipt = new StringBuilder();
            receipt.append("=== PIZZA PALACE RECEIPT ===\n\n");
            receipt.append("Customer: ").append(customer.getName()).append("\n");
            receipt.append("Phone: ").append(customer.getPhoneNumber()).append("\n");
            receipt.append("Email: ").append(customer.getEmail()).append("\n");
            receipt.append("Address: ").append(customer.getAddress().getFullAddress()).append("\n");
            receipt.append("Payment: ").append(customer.getPaymentMethod().toString()).append("\n\n");

            receipt.append("=== ORDER DETAILS ===\n");
            Order order = ScreenManager.currentCart.getOrder();
            int pizzaNum = 1;
            for (Pizza p : order.getOrderList()) {
                receipt.append(pizzaNum++).append(". ").append(p.toString()).append("\n");
            }

            receipt.append("\n=== TOTALS ===\n");
            double subtotal = ScreenManager.currentCart.getCartTotal();
            double tax = subtotal * 0.07;
            double total = subtotal + tax;

            receipt.append(String.format("Subtotal: $%.2f\n", subtotal));
            receipt.append(String.format("Tax (7%%): $%.2f\n", tax));
            receipt.append(String.format("TOTAL: $%.2f\n", total));
            receipt.append("\nDelivery: ").append(ScreenManager.currentCart.getIsDelivery() ? "Yes" : "No (Pickup)");

            receiptArea.setText(receipt.toString());
        }

        Button confirmBtn = new Button("Confirm & Place Order");
        confirmBtn.setPrefWidth(200);
        confirmBtn.setPrefHeight(40);
        confirmBtn.setStyle("-fx-font-size: 16px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        confirmBtn.setOnAction(e -> {
            // Save to database
            Checkout checkout = new Checkout(ScreenManager.currentCart);
            checkout.sendToDatabase();

            // Go to receipt screen
            ScreenManager.showReceiptScreen();
        });

        Button backBtn = new Button("Back to Cart");
        backBtn.setOnAction(e -> ScreenManager.showCartScreen());

        HBox buttons = new HBox(15, backBtn, confirmBtn);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox(20, title, receiptArea, buttons);
        layout.setPadding(new Insets(25));

        scene = new Scene(layout, 650, 500);
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