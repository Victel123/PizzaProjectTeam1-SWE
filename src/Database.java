import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Database {

    // SQLite DB file in your project folder
    private static final String URL = "jdbc:sqlite:pizza.db";

    // Static initializer: runs once, creates tables if they don't exist
    static {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement st = conn.createStatement()) {

            // Make sure foreign keys are enforced
            st.execute("PRAGMA foreign_keys = ON");

            // CUSTOMER TABLE
            st.execute("""
                CREATE TABLE IF NOT EXISTS Customer (
                    CustomerID   INTEGER PRIMARY KEY AUTOINCREMENT,
                    FirstName    TEXT NOT NULL,
                    LastName     TEXT NOT NULL,
                    Phone        TEXT,
                    Email        TEXT,
                    Street       TEXT,
                    City         TEXT,
                    State        TEXT,
                    Zip          TEXT
                );
            """);

            // ORDER TABLE
            st.execute("""
                CREATE TABLE IF NOT EXISTS "Order" (
                    OrderID      INTEGER PRIMARY KEY AUTOINCREMENT,
                    CustomerID   INTEGER NOT NULL,
                    OrderDate    TEXT NOT NULL,
                    Tax          REAL NOT NULL,
                    Total        REAL NOT NULL,
                    IsCart       INTEGER NOT NULL,
                    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
                );
            """);

            // ORDER ITEM TABLE (one row per pizza)
            st.execute("""
                CREATE TABLE IF NOT EXISTS OrderItem (
                    OrderItemID  INTEGER PRIMARY KEY AUTOINCREMENT,
                    OrderID      INTEGER NOT NULL,
                    Description  TEXT NOT NULL,
                    Quantity     INTEGER NOT NULL,
                    ItemTotal    REAL NOT NULL,
                    FOREIGN KEY (OrderID) REFERENCES "Order"(OrderID)
                );
            """);

            // PAYMENT TABLE
            st.execute("""
                CREATE TABLE IF NOT EXISTS Payment (
                    PaymentID     INTEGER PRIMARY KEY AUTOINCREMENT,
                    OrderID       INTEGER NOT NULL,
                    PaymentDate   TEXT NOT NULL,
                    Amount        REAL NOT NULL,
                    BillingStreet TEXT,
                    BillingCity   TEXT,
                    BillingState  TEXT,
                    BillingZip    TEXT,
                    FOREIGN KEY (OrderID) REFERENCES "Order"(OrderID)
                );
            """);

            System.out.println("Database initialized successfully.");

        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // ---------- INSERT HELPERS ----------

    // Inserts a Customer row based on your Customer + Address class
    public static int insertCustomer(Customer customer) throws SQLException {
        String fullName = customer.getName() == null ? "" : customer.getName().trim();
        String[] parts = fullName.split("\\s+", 2);
        String first = parts.length > 0 ? parts[0] : "";
        String last = parts.length > 1 ? parts[1] : "";

        Address addr = customer.getAddress();

        String sql = """
            INSERT INTO Customer (FirstName, LastName, Phone, Email, Street, City, State, Zip)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, first);
            ps.setString(2, last);
            ps.setString(3, customer.getPhoneNumber());
            ps.setString(4, customer.getEmail());
            ps.setString(5, addr != null ? addr.getStreetAddress() : null);
            ps.setString(6, addr != null ? addr.getCity() : null);
            ps.setString(7, addr != null ? addr.getState() : null);
            ps.setString(8, addr != null ? addr.getZip() : null);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Inserted Customer with ID " + id);
                    return id;
                }
            }
        }
        return -1;
    }

    // Inserts an Order row for this Cart
    public static int insertOrder(int customerId, Cart cart) throws SQLException {
        String sql = """
            INSERT INTO "Order" (CustomerID, OrderDate, Tax, Total, IsCart)
            VALUES (?, ?, ?, ?, ?)
            """;

        String now = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        double subtotal = cart.getCartTotal();
        double tax = subtotal * 0.07;     // You can change the tax rate if needed
        double total = subtotal + tax;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, customerId);
            ps.setString(2, now);
            ps.setDouble(3, tax);
            ps.setDouble(4, total);
            ps.setInt(5, 0); // 0 = not just a cart, completed order

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Inserted Order with ID " + id);
                    return id;
                }
            }
        }
        return -1;
    }

    // Inserts one row per Pizza in the Order
    public static void insertOrderItems(int orderId, Order order) throws SQLException {
        String sql = """
            INSERT INTO OrderItem (OrderID, Description, Quantity, ItemTotal)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (Pizza p : order.getOrderList()) {
                String desc = p.toString();  // contains crust, toppings, price
                int qty = 1;                 // 1 pizza per line
                double itemTotal = p.getPrice();

                ps.setInt(1, orderId);
                ps.setString(2, desc);
                ps.setInt(3, qty);
                ps.setDouble(4, itemTotal);
                ps.addBatch();
            }

            ps.executeBatch();
            System.out.println("Inserted " + order.getOrderList().size() + " OrderItem rows.");
        }
    }

    // Inserts a Payment row tied to the Order
    public static void insertPayment(int orderId, Customer customer, Cart cart) throws SQLException {
        String sql = """
            INSERT INTO Payment (OrderID, PaymentDate, Amount, BillingStreet, BillingCity, BillingState, BillingZip)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        String now = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Address addr = customer.getAddress();

        // Depending on how you define "Amount", you might want tax+delivery here instead
        double amount = cart.getCartTotal();

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ps.setString(2, now);
            ps.setDouble(3, amount);
            ps.setString(4, addr != null ? addr.getStreetAddress() : null);
            ps.setString(5, addr != null ? addr.getCity() : null);
            ps.setString(6, addr != null ? addr.getState() : null);
            ps.setString(7, addr != null ? addr.getZip() : null);

            ps.executeUpdate();
            System.out.println("Inserted Payment for OrderID " + orderId);
        }
    }

    // High-level helper that saves everything based on a Cart
    public static void saveOrder(Cart cart) {
        try {
            Order order = cart.getOrder();
            Customer customer = order.getCustomer();

            int customerId = insertCustomer(customer);
            if (customerId == -1) {
                throw new SQLException("Failed to insert customer.");
            }

            int orderId = insertOrder(customerId, cart);
            if (orderId == -1) {
                throw new SQLException("Failed to insert order.");
            }

            insertOrderItems(orderId, order);
            insertPayment(orderId, customer, cart);

            System.out.println("Order successfully saved to database. OrderID = " + orderId);

        } catch (SQLException e) {
            System.err.println("Error saving order to database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}