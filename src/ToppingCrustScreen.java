import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.ArrayList;

/**
 * Choose crust size and toppings, then add pizza to cart.
 *
 * Note: This class creates Toppings and Pizza objects using your existing classes.
 */
public class ToppingCrustScreen {

    private Scene scene;
    private String baseType;

    public ToppingCrustScreen(String pizzaBase) {
        this.baseType = pizzaBase;

        Label title = new Label("Customize: " + pizzaBase);

        ComboBox<String> sizeBox = new ComboBox<>();
        sizeBox.getItems().addAll("Small", "Medium", "Large", "Extra-Large");
        sizeBox.setValue("Medium");

        ComboBox<String> crustType = new ComboBox<>();
        crustType.getItems().addAll("Regular", "Thin", "Stuffed-Crust");
        crustType.setValue("Regular");

        // toppings checkboxes
        CheckBox pepperoni = new CheckBox("Pepperoni ($1.25)");
        CheckBox sausage = new CheckBox("Sausage ($1.25)");
        CheckBox mushrooms = new CheckBox("Mushrooms ($0.95)");
        CheckBox onions = new CheckBox("Onions ($0.75)");
        CheckBox peppers = new CheckBox("Peppers ($0.85)");
        CheckBox olives = new CheckBox("Olives ($0.95)");
        CheckBox extraCheese = new CheckBox("Extra Cheese ($1.50)");
        CheckBox pineapple = new CheckBox("Pineapple ($1.00)");

        // Add button
        Button addBtn = new Button("Add Pizza to Cart");
        addBtn.setOnAction(e -> {
            // Build Crust
            Crust crust = new Crust(crustType.getValue().toLowerCase(), sizeBox.getValue().toLowerCase());

            // Build Toppings — Toppings class expects a Topping in constructor
            // We'll create Toppings with a default cheese base then add selected toppings.
            Toppings toppings = new Toppings(new Topping("Base Cheese", 0.00f));
            // add chosen toppings (prices match your Topping float)
            if (pepperoni.isSelected()) toppings.addTopping(new Topping("Pepperoni", 1.25f));
            if (sausage.isSelected()) toppings.addTopping(new Topping("Sausage", 1.25f));
            if (mushrooms.isSelected()) toppings.addTopping(new Topping("Mushrooms", 0.95f));
            if (onions.isSelected()) toppings.addTopping(new Topping("Onions", 0.75f));
            if (peppers.isSelected()) toppings.addTopping(new Topping("Peppers", 0.85f));
            if (olives.isSelected()) toppings.addTopping(new Topping("Olives", 0.95f));
            if (extraCheese.isSelected()) toppings.addTopping(new Topping("Extra Cheese", 1.50f));
            if (pineapple.isSelected()) toppings.addTopping(new Topping("Pineapple", 1.00f));

            // Map size to integer used by your Pizza class (1..4)
            int sizeInt = switch (sizeBox.getValue()) {
                case "Small" -> 1;
                case "Medium" -> 2;
                case "Large" -> 3;
                default -> 4;
            };

            // Create Pizza using your class
            Pizza pizza = new Pizza(toppings, crust, sizeInt);

            // Add to existing order/cart: if no cart -> create new Order+Cart
            if (ScreenManager.currentCart == null) {
                // create new order and cart
                Order order = new Order(ScreenManager.currentCustomer, pizza);
                ScreenManager.currentCart = new Cart(order, false);
            } else {
                // add pizza to existing order list
                Order order = ScreenManager.currentCart.getOrder();
                // Order.getOrderList() returns ArrayList<Pizza> — append to it
                order.getOrderList().add(pizza);
                // update cart total if needed (cartTotal uses order.orderTotal())
                // We can leave cartTotal as computed in Cart constructor; for safety, re-create Cart
                double isDelivery = ScreenManager.currentCart.getIsDelivery() ? 1 : 0;
                ScreenManager.currentCart = new Cart(order, ScreenManager.currentCart.getIsDelivery());
            }

            // go to cart screen
            ScreenManager.showCartScreen();
        });

        Button backBtn = new Button("Go Back");
        backBtn.setOnAction(e -> ScreenManager.showMenuScreen());

        VBox toppingsCol = new VBox(8,
                pepperoni, sausage, mushrooms, onions,
                peppers, olives, extraCheese, pineapple);
        toppingsCol.setPadding(new Insets(10));

        HBox topRow = new HBox(12,
                new VBox(8, new Label("Size:"), sizeBox),
                new VBox(8, new Label("Crust:"), crustType)
        );

        HBox buttons = new HBox(12, backBtn, addBtn);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        VBox layout = new VBox(12, title, topRow, new Separator(), new Label("Toppings:"), toppingsCol, buttons);
        layout.setPadding(new Insets(18));
        layout.setPrefWidth(520);

        scene = new Scene(layout);
    }

    public Scene getScene() {
        return scene;
    }
}
