import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        ScreenManager.init(primaryStage);
        ScreenManager.showLoginScreen();

        primaryStage.setTitle("Pizza Ordering System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
















/*
import javax.xml.transform.Source;
import java.sql.SQLOutput;
import java.time.OffsetDateTime;
import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;



class dailyRecords{

    public dailyRecords(String date, int numberOfOrders, double sales, double grossProfit, int pizzasSold){
        this.Date = date;
        this.numberOfOrders = numberOfOrders;
        this.sales = sales;
        this.grossProfit = grossProfit;
        this.pizzasSold = pizzasSold;
    }

    String Date;
    int numberOfOrders;
    double sales;
    double grossProfit;
    int pizzasSold;

    public void printStats(){
        System.out.println("Date: " + Date);
        System.out.println("Orders completed: " + numberOfOrders);
        System.out.println("Pizzas sold: " + pizzasSold);
        System.out.println("Sales: $" + sales);
        System.out.println("Gross Profit: $" + grossProfit);


    }
}

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        ScreenManager.initialize(stage);
        ScreenManager.showLoginScreen();
    }

    public static void main(String[] args) {
        launch();
        TerminalUI ui = new TerminalUI();
        ui.start();
    }
}


*/

