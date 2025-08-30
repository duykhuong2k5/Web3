package vn.iostart.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Thông tin kết nối MySQL
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mvc_demo?useSSL=false&serverTimezone=UTC";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "Khuowng205@#";

    // Load driver MySQL khi class được nạp
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: MySQL JDBC Driver not found!");
            e.printStackTrace();
        }
    }

    /**
     * Lấy kết nối tới MySQL
     * @return Connection object
     * @throws SQLException nếu kết nối thất bại
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        System.out.println("Kết nối MySQL thành công!");
        return connection;
    }

    /**
     * Test kết nối database
     */
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Test kết nối thành công, database mvc_demo sẵn sàng!");
            } else {
                System.out.println("Test kết nối thất bại!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi kết nối tới MySQL:");
            e.printStackTrace();
        }
    }
}
