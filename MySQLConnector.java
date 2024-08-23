import java.sql.*;

public class MySQLConnector {
    public static Connection conectar() {
        // String status = "";
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
            // status = "Conexão realizada com sucesso!";
        } catch (SQLException | ClassNotFoundException e) {
            // status = "Ocorreu erro de conexão com o servidor Sql. Mensagem: " + e;
        }
        // System.out.println(status);
        return conn;
    }    
}
