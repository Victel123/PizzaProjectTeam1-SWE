import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class WelcomeScreen {

    private Scene scene;

    public WelcomeScreen() {
        Label title = new Label("Welcome to Luigi's Pizza");
        title.setFont(Font.font("Arial", 32));
        title.setStyle("-fx-font-weight: bold;");

        Label subtitle = new Label("Fresh pizzas made to order");
        subtitle.setFont(Font.font("Arial", 16));

        Button startBtn = new Button("Start Your Order");
        startBtn.setPrefWidth(200);
        startBtn.setPrefHeight(50);
        startBtn.setStyle("-fx-font-size: 16px;");
        startBtn.setOnAction(e -> ScreenManager.showCustomerInfoScreen());

        VBox layout = new VBox(30, title, subtitle, startBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(50));
        layout.setStyle("-fx-background-color: #f5f5f5;");

        scene = new Scene(layout, 600, 400);
        try {
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        } catch (Exception ex) {
            // CSS file optional
        }
    }

    public Scene getScene() {
        return scene;
    }
}