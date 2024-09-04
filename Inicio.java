public class Inicio {
    public static void main(String[] args) {
        String[] tabela = {"db_teste", "tbl_teste"};
        try{
            CriarBancoDeDados.criar(tabela);
        } catch (Exception e) {return;};
        try {
            CriarTabela.criar(tabela);
        } catch (Exception e) {return;};
        new TeladeLogin();
    }
}
