import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ReceiptScreen {

    private Scene scene;

    public ReceiptScreen() {
        Label title = new Label("Order Receipt");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextArea receiptArea = new TextArea();
        receiptArea.setEditable(false);
        receiptArea.setPrefHeight(350);
        receiptArea.setWrapText(true);

        if (ScreenManager.currentCart != null && ScreenManager.currentCustomer != null) {
            Customer customer = ScreenManager.currentCustomer;

            StringBuilder receipt = new StringBuilder();
            receipt.append("=== PIZZA PALACE ===\n");
            receipt.append("Thank You For Your Order!\n\n");
            receipt.append("Customer: ").append(customer.getName()).append("\n");
            receipt.append("Phone: ").append(customer.getPhoneNumber()).append("\n\n");

            Order order = ScreenManager.currentCart.getOrder();
            receipt.append("=== ORDER ITEMS ===\n");
            int pizzaNum = 1;
            for (Pizza p : order.getOrderList()) {
                receipt.append(pizzaNum++).append(". ").append(p.toString()).append("\n");
            }

            double subtotal = ScreenManager.currentCart.getCartTotal();
            double tax = subtotal * 0.07;
            double total = subtotal + tax;

            receipt.append("\n=== TOTALS ===\n");
            receipt.append(String.format("Subtotal: $%.2f\n", subtotal));
            receipt.append(String.format("Tax: $%.2f\n", tax));
            receipt.append(String.format("TOTAL: $%.2f\n", total));

            receiptArea.setText(receipt.toString());
        } else {
            receiptArea.setText("No order information available.");
        }

        Button printBtn = new Button("Print Receipt");
        printBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Print");
            alert.setHeaderText(null);
            alert.setContentText("Receipt sent to printer!");
            alert.showAndWait();
        });

        Button newOrderBtn = new Button("New Order");
        newOrderBtn.setOnAction(e -> {
            ScreenManager.resetOrder();
            ScreenManager.showWelcomeScreen();
        });

        Button loginBtn = new Button("Back to Login");
        loginBtn.setOnAction(e -> ScreenManager.showLoginScreen());

        HBox buttons = new HBox(12, printBtn, newOrderBtn, loginBtn);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox(20, title, receiptArea, buttons);
        layout.setPadding(new Insets(25));

        scene = new Scene(layout, 600, 500);
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