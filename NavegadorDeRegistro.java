import java.sql.*;

public class NavegadorDeRegistro {
    public static String[] registro(String db, String tbl, String[] campos, String[] valores, String botao) throws Exception {
        int qtde = 0;
        for (int i = 0; i < campos.length; i++) {
            if (campos[i] != "") { //campo 0 pode estar vazio quando for incluir registro
                qtde++;
            }
        };
        if (qtde == 0) return null;
        Connection conexao = null;
        Statement stmSqlRegistro = null;
        ResultSet rstSqlRegistro = null;
        String[] resultado = null;
        String atualizarSQL = null;

        String navegarSQL = "SELECT * FROM `" + db + "`.`" + tbl + "` ";
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
        System.out.println(botao);
System.out.println(juncao);
        switch (botao) {
            case "anterior":
            case "próximo":
                navegarSQLMeio = " WHERE ( ";
                if (qtde == 1) {
                    navegarSQLMeio += campos[0] + " " + comparar + " '" + valores[0] + "' ) ";
                } else {
                    navegarSQLMeio += campos[1] + " = '" + valores[1] + "' AND id " + comparar + " " + valores[0] + " ) OR ( " +  campos[1] + " " + comparar + " '" + valores[1] + "' ) ";
                }
                break;
            case "consultar":
                navegarSQLMeio = " WHERE ( ";
                for (Integer i = 1; i < qtde; i++) {
                    navegarSQLMeio += campos[i] + " LIKE '" + valores[i] + "%' ) ";
                    if ((qtde - i) > 1) {
                        navegarSQLMeio += " " + juncao + " ( ";
                    } 
                }
                break;
            case "irpara":
                navegarSQLMeio = " WHERE ( " + campos[0] + " = " + valores[0] + " ) ";
                break;
            case "incluir":
                atualizarSQL = "INSERT INTO `" + db + "`.`" + tbl + "` ( ";
                for (int i = 1; i < qtde; i++) {
                    atualizarSQL += campos[i];
                    if ((qtde - i) > 1 ){
                        atualizarSQL += ", ";
                    }
                };
                atualizarSQL += " ) VALUES ( ";
                for (int i = 1; i < qtde; i++) {
                    atualizarSQL += "'" + valores[i] + "'";
                    if ((qtde - i) > 1 ){
                        atualizarSQL += ", ";
                    }
                };
                atualizarSQL += " );";
                break;
            case "alterar":
                if (qtde == 0) return null;
                atualizarSQL = "UPDATE `" + db + "`.`" + tbl + "` SET ";
                for (int i = 1; i < qtde; i++) {
                    atualizarSQL += campos[i] + " = '" + valores[i] + "'";
                    if ((qtde - i) > 1 ){
                        atualizarSQL += ", ";
                    }
                };
                atualizarSQL += " WHERE " + campos[0] + " = '" + valores[0] + "';";
                break;
            case "excluir":
                if (qtde == 0) return null;
                atualizarSQL = "DELETE FROM `" + db + "`.`" + tbl + "` WHERE " + campos[0] + " = '" + valores[0] + "';";
                break;
            default:
                break;
        }
        if (qtde > 1) {
            for (Integer i = 1; i < qtde; i++){
                navegarSQLFinal += campos[i] + " " + ordenar + ", ";
            }    
        }
        navegarSQLFinal += " id " + ordenar + " LIMIT 1;";
        navegarSQL = navegarSQL + navegarSQLMeio + navegarSQLFinal;

        try {
            conexao = MySQLConnector.conectar();
            stmSqlRegistro = conexao.createStatement();
            if (botao == "incluir" || botao == "alterar" || botao == "excluir") {
                PreparedStatement ps = conexao.prepareStatement(atualizarSQL);
                int linhasAfetadas = ps.executeUpdate();
                System.out.println(atualizarSQL);
    
                if (linhasAfetadas > 0) {
                    for (int i = 1; i < qtde; i++) {
                        campos[i] = "";
                        valores[i] = "";
                    }
                    if (botao.equals("incluir")) resultado = registro(db, tbl, campos, valores, "último");
                    if (botao.equals("alterar")) resultado = registro(db, tbl, campos, valores, "irpara");
                    if (botao.equals("excluir")) resultado = null;
                } else {
                    resultado = null;
                }        
            } else {
                PreparedStatement ps = conexao.prepareStatement(navegarSQL);
                rstSqlRegistro = ps.executeQuery();
                System.out.println(navegarSQL);
    
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