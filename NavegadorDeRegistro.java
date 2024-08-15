import java.sql.*;

public class NavegadorDeRegistro {
    public static String[]  irParaRegistro(String db, String tbl, String campo, String valor, String botao) throws Exception{
        Connection conexao = MySQLConnector.conectar();
        Statement stmSqlRegistro = conexao.createStatement();

        String strSqlRegistro = "";
        switch (botao) {
            case "primeiro":
                strSqlRegistro = "select * from `" +  db + "`.`" + tbl + "` order by " + campo + " limit 1;";
                break;
            case "anterior":
                strSqlRegistro = "select * from `" +  db + "`.`" + tbl + "` where (" + campo + " < '" + valor + "') order by " + campo + " desc limit 1;";
                break;
            case "próximo":
                strSqlRegistro = "select * from `" +  db + "`.`" + tbl + "` where (" + campo + " > '" + valor + "') order by " + campo + " asc limit 1;";
                break;
            case "último":
                strSqlRegistro = "select * from `" +  db + "`.`" + tbl + "` order by " + campo + " desc limit 1;";
                break;
            case "consulta":
                strSqlRegistro = "select * from `" + db + "`.`" + tbl + "` where (" + campo + " like '" + valor + "%') order by " + campo + " asc limit 1;";
                break;
            default:
                break;
        }

        ResultSet rstSqlRegistro = stmSqlRegistro.executeQuery(strSqlRegistro);
        rstSqlRegistro.next();
        String[] resultado = {
            rstSqlRegistro.getString("id"),
            rstSqlRegistro.getString("nome"),
            rstSqlRegistro.getString("email"),
            rstSqlRegistro.getString("senha"),
        };
    
        stmSqlRegistro.close();
        return resultado; 
    }
}