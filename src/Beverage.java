import java.util.Objects;

class Beverage {
    private String type;
    private double price;

    Beverage(String type){
        this.type = type;

        if(Objects.equals(type, "soda"))
            price = 2.50;
        if(Objects.equals(type, "water"))
            price = 1.50;
    }
}