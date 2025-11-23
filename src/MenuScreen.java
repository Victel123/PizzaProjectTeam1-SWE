import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class MenuScreen {

    private Scene scene;

    public MenuScreen() {
        Label title = new Label("Select Your Pizza Base");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Pizza options
        Button margheritaBtn = createPizzaButton("Margherita", "Tomato, mozzarella and basil");
        Button pepperoniBtn = createPizzaButton("Pepperoni", "Cheese, tomato and pepperoni");
        Button supremeBtn = createPizzaButton("Supreme", "Loaded with all the toppings");
        Button vegetarianBtn = createPizzaButton("Vegetarian", "Pepper, olives & onions");
        Button customBtn = createPizzaButton("Custom", "Build your own");

        margheritaBtn.setOnAction(e -> ScreenManager.showToppingScreen("Margherita"));
        pepperoniBtn.setOnAction(e -> ScreenManager.showToppingScreen("Pepperoni"));
        supremeBtn.setOnAction(e -> ScreenManager.showToppingScreen("Supreme"));
        vegetarianBtn.setOnAction(e -> ScreenManager.showToppingScreen("Vegetarian"));
        customBtn.setOnAction(e -> ScreenManager.showToppingScreen("Custom"));

        VBox pizzaList = new VBox(15, margheritaBtn, pepperoniBtn, supremeBtn, vegetarianBtn, customBtn);
        pizzaList.setAlignment(Pos.CENTER);

        Button cartBtn = new Button("View Cart");
        cartBtn.setPrefWidth(150);
        cartBtn.setOnAction(e -> {
            if (ScreenManager.currentCart == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Empty Cart");
                alert.setHeaderText(null);
                alert.setContentText("Your cart is empty. Add some pizzas first!");
                alert.showAndWait();
            } else {
                ScreenManager.showCartScreen();
            }
        });

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> ScreenManager.showCustomerInfoScreen());

        HBox buttons = new HBox(12, backBtn, cartBtn);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox(25, title, pizzaList, buttons);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        scene = new Scene(layout, 600, 550);
        try {
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        } catch (Exception ex) {
            // CSS optional
        }
    }

    private Button createPizzaButton(String name, String description) {
        Button btn = new Button(name + "\n" + description);
        btn.setPrefWidth(350);
        btn.setPrefHeight(60);
        btn.setStyle("-fx-font-size: 14px;");
        return btn;
    }

    public Scene getScene() {
        return scene;
    }
}