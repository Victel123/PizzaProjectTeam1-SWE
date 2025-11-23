import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LoginScreen {

    private Scene scene;

    public LoginScreen() {
        Label title = new Label("Luigi's Pizza");
        title.setFont(Font.font("Arial", 28));
        title.setStyle("-fx-font-weight: bold;");

        Label subtitle = new Label("Welcome!");
        subtitle.setFont(Font.font("Arial", 14));

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(300);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(300);

        Button loginBtn = new Button("Login");
        loginBtn.setPrefWidth(150);
        loginBtn.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (!username.isEmpty() && !password.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Success");
                alert.setHeaderText(null);
                alert.setContentText("Welcome, " + username + "!");
                alert.showAndWait();

                // Go to welcome screen or admin dashboard
                ScreenManager.showWelcomeScreen();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText(null);
                alert.setContentText("Please enter username and password");
                alert.showAndWait();
            }
        });

        Button guestBtn = new Button("Continue as Guest");
        guestBtn.setOnAction(e -> ScreenManager.showWelcomeScreen());

        VBox layout = new VBox(20, title, subtitle, usernameField, passwordField, loginBtn, guestBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(50));
        layout.setStyle("-fx-background-color: #f5f5f5;");

        scene = new Scene(layout, 600, 450);
    }

    public Scene getScene() {
        return scene;
    }
}