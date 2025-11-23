class Checkout {
    private Cart cart;
    private String receipt;

    public Checkout(Cart cart) {
        this.cart = cart;
        this.receipt = cart.toString();
    }

    public String printReceipt() {
        return receipt;
    }

    public Cart getCart() {
        return cart;
    }

    @Override
    public String toString() {
        return receipt;
    }

    // New: send everything to the SQLite database
    public void sendToDatabase() {
        Database.saveOrder(cart);
    }
}