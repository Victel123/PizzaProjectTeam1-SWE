import javafx.stage.Stage;


public class ScreenManager {

    private static Stage primaryStage;

    // Global state
    public static Customer currentCustomer;
    public static Cart currentCart;

    public static void init(Stage stage) {
        primaryStage = stage;
    }

    public static void showWelcomeScreen() {
        WelcomeScreen screen = new WelcomeScreen();
        primaryStage.setScene(screen.getScene());
    }

    public static void showCustomerInfoScreen() {
        CustomerInfoScreen screen = new CustomerInfoScreen();
        primaryStage.setScene(screen.getScene());
    }

    public static void showMenuScreen() {
        MenuScreen screen = new MenuScreen();
        primaryStage.setScene(screen.getScene());
    }

    public static void showToppingScreen(String pizzaBase) {
        ToppingCrustScreen screen = new ToppingCrustScreen(pizzaBase);
        primaryStage.setScene(screen.getScene());
    }

    public static void showCartScreen() {
        CartScreen screen = new CartScreen();
        primaryStage.setScene(screen.getScene());
    }

    public static void showCheckoutScreen() {
        CheckoutScreen screen = new CheckoutScreen();
        primaryStage.setScene(screen.getScene());
    }

    public static void showLoginScreen() {
        LoginScreen screen = new LoginScreen();
        primaryStage.setScene(screen.getScene());
    }

    public static void showReceiptScreen() {
        ReceiptScreen screen = new ReceiptScreen();
        primaryStage.setScene(screen.getScene());
    }

    public static void resetOrder() {
        currentCustomer = null;
        currentCart = null;
    }
}