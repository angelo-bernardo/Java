import java.sql.*;

public class CriarBancoDeDados {
    public static Boolean criar(String[] args) throws Exception {
        try {
            Connection conexao = MySQLConnector.conectar();
            String strSqlCriarBancoDeDados = "CREATE DATABASE IF NOT EXISTS `" + args[0] + "`;";
            Statement stmSqlCriarBancoDeDados = conexao.createStatement();
            stmSqlCriarBancoDeDados.addBatch(strSqlCriarBancoDeDados);
            stmSqlCriarBancoDeDados.executeBatch();
            stmSqlCriarBancoDeDados.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
