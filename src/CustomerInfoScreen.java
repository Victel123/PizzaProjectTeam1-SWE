import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class CustomerInfoScreen {

    private Scene scene;

    public CustomerInfoScreen() {
        Label title = new Label("Customer Information");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Customer fields
        TextField nameField = new TextField();
        nameField.setPromptText("Full Name");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");

        // Address fields
        TextField streetField = new TextField();
        streetField.setPromptText("Street Address");

        TextField cityField = new TextField();
        cityField.setPromptText("City");

        TextField stateField = new TextField();
        stateField.setPromptText("State");

        TextField zipField = new TextField();
        zipField.setPromptText("ZIP Code");

        // Payment info
        ComboBox<String> paymentType = new ComboBox<>();
        paymentType.getItems().addAll("Cash", "Credit Card", "Debit Card");
        paymentType.setValue("Cash");

        TextField cardField = new TextField();
        cardField.setPromptText("Card Number (if applicable)");
        cardField.setDisable(true);

        paymentType.setOnAction(e -> {
            cardField.setDisable(paymentType.getValue().equals("Cash"));
        });

        // Delivery option
        CheckBox deliveryCheck = new CheckBox("Delivery (otherwise pickup)");

        Button nextBtn = new Button("Continue to Menu");
        nextBtn.setPrefWidth(150);
        nextBtn.setOnAction(e -> {
            // Validate
            if (nameField.getText().trim().isEmpty()) {
                showAlert("Please enter your name");
                return;
            }

            // Create Address
            Address addr = new Address(
                    streetField.getText().trim(),
                    cityField.getText().trim(),
                    stateField.getText().trim(),
                    zipField.getText().trim()
            );

            // Create Payment
            Payment payment;
            if (paymentType.getValue().equals("Cash")) {
                payment = new Payment("Cash");
            } else {
                payment = new Payment(paymentType.getValue(), cardField.getText().trim());
            }

            // Create Customer
            ScreenManager.currentCustomer = new Customer(
                    nameField.getText().trim(),
                    emailField.getText().trim(),
                    phoneField.getText().trim(),
                    addr,
                    payment
            );

            // Initialize cart as null (will be created when first pizza added)
            ScreenManager.currentCart = null;

            ScreenManager.showMenuScreen();
        });

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> ScreenManager.showWelcomeScreen());

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(12);
        form.setPadding(new Insets(20));

        int row = 0;
        form.add(new Label("Name:"), 0, row);
        form.add(nameField, 1, row++);

        form.add(new Label("Email:"), 0, row);
        form.add(emailField, 1, row++);

        form.add(new Label("Phone:"), 0, row);
        form.add(phoneField, 1, row++);

        form.add(new Separator(), 0, row++, 2, 1);

        form.add(new Label("Street:"), 0, row);
        form.add(streetField, 1, row++);

        form.add(new Label("City:"), 0, row);
        form.add(cityField, 1, row++);

        form.add(new Label("State:"), 0, row);
        form.add(stateField, 1, row++);

        form.add(new Label("ZIP:"), 0, row);
        form.add(zipField, 1, row++);

        form.add(new Separator(), 0, row++, 2, 1);

        form.add(new Label("Payment:"), 0, row);
        form.add(paymentType, 1, row++);

        form.add(new Label("Card #:"), 0, row);
        form.add(cardField, 1, row++);

        HBox buttons = new HBox(12, backBtn, nextBtn);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        VBox layout = new VBox(20, title, form, buttons);
        layout.setPadding(new Insets(25));

        scene = new Scene(layout, 650, 650);
        try {
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        } catch (Exception ex) {
            // CSS optional
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Required");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public Scene getScene() {
        return scene;
    }
}