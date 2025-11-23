import java.util.ArrayList;

class Order {
    private Customer customer;
    ArrayList<Pizza> orderList;

    public Order(Customer customer, Pizza pizza) {
        this.customer = customer;
        this.orderList = new ArrayList<>();
        this.orderList.add(pizza);
    }

    public Order(Customer customer, ArrayList<Pizza> orderList) {
        this.customer = customer;
        this.orderList = orderList;
    }

    private void addPizza(Pizza p) {
        orderList.add(p);
    }

    public double orderTotal() {
        double total = 0;
        for (Pizza p : orderList) {
            total += p.getPrice();
        }
        return total;
    }

    public ArrayList<Pizza> getOrderList() {
        return orderList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void printOrders() {
        for (Pizza p : orderList) {
            System.out.println(p);
        }
    }

    @Override
    public String toString() {
        return "Order for " + customer.getName() +
                " (" + orderList.size() + " pizzas, total $" + orderTotal() + ")";
    }
}