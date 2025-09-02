package vn.oistart.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String SERVER_NAME = "localhost";
    private static final String DB_NAME = "ServletCRUDMVC";
    private static final String PORT_NUMBER = "3306";
    private static final String USER = "root";
    private static final String PASSWORD = "Khuowng205@#";

    private static final String URL =
            "jdbc:mysql://" + SERVER_NAME + ":" + PORT_NUMBER + "/" + DB_NAME
            + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // nạp driver 1 lần
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
