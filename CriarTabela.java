import java.sql.*;

public class CriarTabela {
    public static Boolean criar(String[] args) throws Exception {
        Connection conexao = null;
        Statement stmSqlCriarTabela = null;
        Boolean retorno = true;
        try {
            conexao = MySQLConnector.conectar();
            String strSqlCriarTabela = "CREATE TABLE IF NOT EXISTS `" + args[0] + "`.`" + args[1] + "` (`id` INT NOT NULL AUTO_INCREMENT, `nome` VARCHAR(255) NOT NULL, `email` varchar(255) NOT NULL, `senha` VARCHAR(255) NOT NULL, PRIMARY KEY (`id`));";
            stmSqlCriarTabela = conexao.createStatement();
            stmSqlCriarTabela.addBatch(strSqlCriarTabela);
            stmSqlCriarTabela.executeBatch();
            return true;
        } catch (Exception e) {
            retorno = false;
        } finally {
            if (stmSqlCriarTabela != null) {
                stmSqlCriarTabela.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        }
        return retorno;
    }
}