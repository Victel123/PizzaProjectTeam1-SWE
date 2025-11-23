public class Pizza {
    Toppings toppings;
    Crust crust;
    double price;
    int size; // 1-small, 2-medium, 3-large, 4-extra large

    public Pizza(Toppings toppings, Crust crust, int size) {
        this.toppings = toppings;
        this.crust = crust;
        this.size = size;
        this.price = toppings.getTotalPrice() + crust.getCrustPrice();
    }

    public double getPrice() {
        return price;
    }

    public Toppings getToppings() {
        return toppings;
    }

    public Crust getCrust() {
        return crust;
    }

    @Override
    public String toString() {
        return "Pizza {" +
                "crust=" + crust +
                ", toppings=" + toppings +
                ", price=$" + price +
                '}';
    }
}