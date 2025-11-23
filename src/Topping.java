class Topping {
    private String topping;
    private float price;

    public Topping(String topping, float price) {
        this.topping = topping;
        this.price = price;
    }

    // Return methods
    public String getTopping() {
        return topping;
    }

    public float getPrice() {
        return price;
    }

    // Mutators
    public void setTopping(String topping) {
        this.topping = topping;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return topping + " ($" + price + ")";
    }
}