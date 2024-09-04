import java.sql.*;

public class CriarBancoDeDados {
    public static Boolean criar(String[] args) throws Exception {
        Connection conexao = null;
        Statement stmSqlCriarBancoDeDados = null;
        Boolean retorno = true;
        try {
            conexao = MySQLConnector.conectar();
            String strSqlCriarBancoDeDados = "CREATE DATABASE IF NOT EXISTS `" + args[0] + "`;";
            stmSqlCriarBancoDeDados = conexao.createStatement();
            stmSqlCriarBancoDeDados.addBatch(strSqlCriarBancoDeDados);
            stmSqlCriarBancoDeDados.executeBatch();
            return true;
        } catch (Exception e) {
            retorno = false;
        } finally {
            if (stmSqlCriarBancoDeDados != null) {
                stmSqlCriarBancoDeDados.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        }
        return retorno;
    }
}