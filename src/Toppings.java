import java.util.ArrayList;

class Toppings {
    private ArrayList<Topping> toppings;
    private float totalPrice;

    public Toppings(Topping t) {
        this.toppings = new ArrayList<>();
        this.toppings.add(t);
        calcToppingPrice();
    }

    // Return methods
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    // Mutators
    public void addTopping(Topping topping) {
        toppings.add(topping);
        calcToppingPrice();
    }

    public void removeTopping(Topping topping) {
        if (!toppings.remove(topping)) {
            System.out.println("Unable to remove topping: " + topping.getTopping());
        } else {
            calcToppingPrice();
        }
    }

    public void calcToppingPrice() {
        float total = 0;
        for (Topping t : toppings) {
            total += t.getPrice();
        }
        totalPrice = total;
    }

    @Override
    public String toString() {
        return "Toppings: " + toppings + " (total $" + totalPrice + ")";
    }
}