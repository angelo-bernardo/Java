import java.sql.*;

public class GerirRegistro {
   public static String atualizar(String db, String tbl, String id, String nome, String email, String senha, String acao) {
      String retorno = "";
      try {
         String strSqlRegistro = "";
         switch (acao) {
            case "incluído":
               strSqlRegistro = "insert into `" + db + "`.`" + tbl + "` (`nome`, `email`, `senha`) values ('" + nome + "', '" + email + "', '" + senha + "');";
               break;
            case "atualizado":
               strSqlRegistro = "update `" + db + "`.`" + tbl + "` set `nome` = '" + nome + "', `email` = '" + email + "', `senha` = '" + senha + "' where id = " + id + ";";
               break;
            case "excluído":
               strSqlRegistro = "delete from `" + db + "`.`" + tbl + "` where (id = '" + id + "');";
               break;
            default:
               break;
         }
         Connection conexao = MySQLConnector.conectar();
         Statement stmSqlRegistro = conexao.createStatement();
         stmSqlRegistro.addBatch(strSqlRegistro);
         stmSqlRegistro.executeBatch();
         stmSqlRegistro.close();
         retorno = "Notificação: Registro " + acao + " com sucesso!";

      } catch (Exception e) {
         retorno = "Ocorreu erro na atualização: " + e;
      }
      return retorno;
   }
}