class Cart {
    private Order order;
    private double cartTotal;
    private boolean isDelivery;

    public Cart(Order order, boolean isDelivery) {
        this.order = order;
        this.cartTotal = order.orderTotal();
        this.isDelivery = isDelivery;
    }

    public double getCartTotal() {
        return cartTotal;
    }

    public boolean getIsDelivery() {
        return isDelivery;
    }

    public Order getOrder() {
        return order;
    }

    private void setCartTotal(double d) {
        cartTotal = d;
    }

    private void setIsDelivery(boolean b) {
        isDelivery = b;
    }

    @Override
    public String toString() {
        return "Order: " + order +
                "\nCart total: $" + cartTotal +
                "\nIs Delivery: " + isDelivery;
    }
}