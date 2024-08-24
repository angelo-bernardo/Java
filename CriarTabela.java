import java.sql.*;

public class CriarTabela {
    public static Boolean criar(String[] args) throws Exception {
        try {
            Connection conexao = MySQLConnector.conectar();
            String strSqlCriarTabela = "CREATE TABLE IF NOT EXISTS `" + args[0] + "`.`" + args[1] + "` (`id` INT NOT NULL AUTO_INCREMENT, `nome` VARCHAR(255) NOT NULL, `email` varchar(255) NOT NULL, `senha` VARCHAR(255) NOT NULL, PRIMARY KEY (`id`));";
            Statement stmSqlCriarTabela = conexao.createStatement();
            stmSqlCriarTabela.addBatch(strSqlCriarTabela);
            stmSqlCriarTabela.executeBatch();
            stmSqlCriarTabela.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
