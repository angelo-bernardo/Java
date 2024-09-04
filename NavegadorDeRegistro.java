import java.sql.*;

public class NavegadorDeRegistro {
    public static String[] registro(String[] tabela, String[][] campos, String botao) throws Exception {
        int qtde = 0;
        for (int i = 0; i < campos[1].length; i++) {
            if (campos[1][i] != "") {
                qtde++;
            }
        };
        if (qtde == 0) return null;

        Connection conexao = null;
        Statement stmSqlRegistro = null;
        ResultSet rstSqlRegistro = null;
        String[] resultado = null;
        String atualizarSQL = null;

        String navegarSQL = "SELECT * FROM `" + tabela[0] + "`.`" + tabela[1] + "` ";
        String navegarSQLMeio = "";
        String navegarSQLFinal = " ORDER BY ";
        String comparar = "";
        String ordenar = "";
        String juncao = "";
        
        if (botao.equals("anterior") || botao.equals("último")) {
            comparar = "<";
            ordenar = "DESC";
        } else {
            comparar = ">";
            ordenar = "ASC";
        }
        if (botao.equals("consultar_e")) {
            botao = "consultar";
            juncao = "AND";
        }
        if (botao.equals("consultar_ou")) {
            botao = "consultar";
            juncao = "OR";
        }
        switch (botao) {
            case "anterior":
            case "próximo":
                navegarSQLMeio = " WHERE ( ";
                if (qtde == 1) {
                    navegarSQLMeio += campos[0][0] + " " + comparar + " '" + campos[1][0] + "' ) ";
                } else {
                    navegarSQLMeio += campos[0][1] + " = '" + campos[1][1] + "' AND id " + comparar + " " + campos[1][0] + " ) OR ( " +  campos[0][1] + " " + comparar + " '" + campos[1][1] + "' ) ";
                }
                break;
            case "consultar":
                navegarSQLMeio = " WHERE ( ";
                for (Integer i = 1; i < qtde; i++) {
                    navegarSQLMeio += campos[0][i] + " LIKE '" + campos[1][i] + "%' ) ";
                    if ((qtde - i) > 1) {
                        navegarSQLMeio += " " + juncao + " ( ";
                    } 
                }
                break;
            case "irpara":
                navegarSQLMeio = " WHERE ( " + campos[0][0] + " = " + campos[1][0] + " ) ";
                break;
            case "incluir":
                atualizarSQL = "INSERT INTO `" + tabela[0] + "`.`" + tabela[1] + "` ( ";
                for (int i = 1; i < qtde; i++) {
                    atualizarSQL += campos[0][i];
                    if ((qtde - i) > 1 ){
                        atualizarSQL += ", ";
                    }
                };
                atualizarSQL += " ) VALUES ( ";
                for (int i = 1; i < qtde; i++) {
                    atualizarSQL += "'" + campos[1][i] + "'";
                    if ((qtde - i) > 1 ){
                        atualizarSQL += ", ";
                    }
                };
                atualizarSQL += " );";
                break;
            case "alterar":
                atualizarSQL = "UPDATE `" + tabela[0] + "`.`" + tabela[1] + "` SET ";
                for (int i = 1; i < qtde; i++) {
                    atualizarSQL += campos[0][i] + " = '" + campos[1][i] + "'";
                    if ((qtde - i) > 1 ){
                        atualizarSQL += ", ";
                    }
                };
                atualizarSQL += " WHERE " + campos[0][0] + " = '" + campos[1][0] + "';";
                break;
            case "excluir":
                atualizarSQL = "DELETE FROM `" + tabela[0] + "`.`" + tabela[1] + "` WHERE " + campos[0][0] + " = '" + campos[1][0] + "';";
                break;
            default:
                break;
        }
        if (qtde > 1) {
            for (Integer i = 1; i < qtde; i++){
                navegarSQLFinal += campos[0][i] + " " + ordenar + ", ";
            }    
        }
        navegarSQLFinal += " id " + ordenar + " LIMIT 1;";
        navegarSQL = navegarSQL + navegarSQLMeio + navegarSQLFinal;

        try {
            conexao = MySQLConnector.conectar();
            stmSqlRegistro = conexao.createStatement();
            if (botao.equals("incluir") || botao.equals("alterar") || botao.equals("excluir")) {
                System.out.println(atualizarSQL);
                PreparedStatement ps = conexao.prepareStatement(atualizarSQL);
                int linhasAfetadas = ps.executeUpdate();    
                if (linhasAfetadas > 0) {
                    for (int i = 1; i < qtde; i++) {
                        campos[0][i] = "";
                        campos[1][i] = "";
                    }
                    if (botao.equals("incluir")) resultado = registro(tabela, campos, "último");
                    if (botao.equals("alterar")) resultado = registro(tabela, campos, "irpara");
                    if (botao.equals("excluir")) resultado = null;
                } else {
                    resultado = null;
                }        
            } else {
                System.out.println(navegarSQL);
                PreparedStatement ps = conexao.prepareStatement(navegarSQL);
                rstSqlRegistro = ps.executeQuery();    
                if (rstSqlRegistro.next()) {
                    resultado = new String[] {
                        rstSqlRegistro.getString("id"),
                        rstSqlRegistro.getString("nome"),
                        rstSqlRegistro.getString("email"),
                        rstSqlRegistro.getString("senha")
                    };
                }    
            }
        } finally {
            if (rstSqlRegistro != null) {
                rstSqlRegistro.close();
            }
            if (stmSqlRegistro != null) {
                stmSqlRegistro.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        }
        return resultado;
    }
}