import java.sql.*;

public class MySQLConnector {
    public static Connection conectar() {        
        String mysqlHost = "127.0.0.1";
        String mysqlPort = "3306";
        String mysqlDb = "world";
        String mysqlUser = "root";
        String mysqlPassword = "123456";
        String mysqlUrl = "jdbc:mysql://" + mysqlHost + ":" + mysqlPort + "/" + mysqlDb + "?user=" + mysqlUser + "&password=" + mysqlPassword;
        Connection conn = null;
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(mysqlUrl);
        } catch (SQLException | ClassNotFoundException e) {
        }
        return conn;
    }    
}
