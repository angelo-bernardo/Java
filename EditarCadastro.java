import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class EditarCadastro extends JFrame {

    private final JTextField idTextField = new JTextField();
    private final JTextField nomeTextField = new JTextField();
    private final JTextField emailTextField = new JTextField();
    private final JTextField senhaPasswordField = new JPasswordField();
    private final JLabel idJLabel = new JLabel("ID:");
    private final JLabel nomeJLabel = new JLabel("Digite o nome:");
    private final JLabel emailJLabel = new JLabel("Digite o email:");
    private final JLabel senhaJLabel = new JLabel("Digite a senha:");
    private final JLabel notificacaoJLabel = new JLabel("Notificações:");
    private final JButton consultarJButton = new JButton("Consultar");
    private final JButton incluirJButton = new JButton("Incluir");
    private final JButton atualizarJButton = new JButton("Atualizar");
    private final JButton excluirJButton = new JButton("Excluir");
    private final JButton primeiroRegistroJButton = new JButton("<<");
    private final JButton registroAnteriorJButton = new JButton("<");
    private final JButton proximoRegistroJButton = new JButton(">");
    private final JButton ultimoRegistroJButton = new JButton(">>");
    public String campo = "id";

    public EditarCadastro() {

        super("Editar Cadastro");
        setLayout(new GridLayout(7, 1, 5, 5));

        JPanel linha1 = new JPanel(new GridLayout(1, 2, 5, 5));

        JPanel linha2 = new JPanel(new GridLayout(1, 2, 5, 5));

        JPanel linha3 = new JPanel(new GridLayout(1, 2, 5, 5));

        JPanel linha4 = new JPanel(new GridLayout(1, 2, 5, 5));

        JPanel linha5 = new JPanel(new GridLayout(1, 4, 5, 5));

        JPanel linha6 = new JPanel(new GridLayout(1, 4, 5, 5));

        JPanel linha7 = new JPanel(new GridLayout(1, 1, 5, 5));


        idTextField.setEditable(false);
        idTextField.setToolTipText("Não pode ser mudado!");
        consultarJButton.setToolTipText("Consultar cadastro");
        incluirJButton.setToolTipText("Incluir cadastro");
        atualizarJButton.setToolTipText("Atualizar cadastro");
        excluirJButton.setToolTipText("Excluir cadastro");
        primeiroRegistroJButton.setToolTipText("Primeiro registro");
        registroAnteriorJButton.setToolTipText("Registro antetior");
        proximoRegistroJButton.setToolTipText("Proximo registro");
        ultimoRegistroJButton.setToolTipText("Ultimo registro");
        
        consultarJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                String[] resultado;
                String valor;
                try{
                    String reg_anterior = idTextField.getText();
                    if (!reg_anterior.equals("")) {
                        idTextField.setText("");
                        nomeTextField.setText("");
                        emailTextField.setText("");
                        senhaPasswordField.setText("");
                        notificacaoJLabel.setText("Notificações: Preencha o campo nome ou email e clique novamente em Consultar");
                        primeiroRegistroJButton.setEnabled(true);
                        registroAnteriorJButton.setEnabled(true);
                        proximoRegistroJButton.setEnabled(true);
                        ultimoRegistroJButton.setEnabled(true);    
                        return;
                    }
                    valor = nomeTextField.getText();
                    campo = "id";
                    if (!valor.equals("")) {
                        campo = "nome";
                    } else {
                        valor = emailTextField.getText();
                        if (!valor.equals("")) {
                            campo = "email";
                        }
                    }
                    if (campo == "id") {
                        notificacaoJLabel.setText("Notificações: Preencha o campo nome ou email e clique novamente em Consultar");
                        return;
                    }
                    resultado = NavegadorDeRegistro.irParaRegistro("db_teste", "tbl_teste", campo, valor, "consulta");
                    notificacaoJLabel.setText("Notificações: Registro posicionado com sucesso, ordenado por " + campo);
                    idTextField.setText(resultado[0]);
                    nomeTextField.setText(resultado[1]);
                    emailTextField.setText(resultado[2]);
                    senhaPasswordField.setText(resultado[3]);
                    primeiroRegistroJButton.setEnabled(true);
                    registroAnteriorJButton.setEnabled(true);
                    proximoRegistroJButton.setEnabled(true);
                    ultimoRegistroJButton.setEnabled(true);
                } catch(Exception e) {
                    System.out.println("Ocorreu erro ao executar o comando: " + e);
                    return;
                }
            }
        }); 

        incluirJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                String[] resultado;
                String aux = campo;
                try{
                    String reg_anterior = idTextField.getText();
                    if (!reg_anterior.equals("")) {
                        idTextField.setText("");
                        nomeTextField.setText("");
                        emailTextField.setText("");
                        senhaPasswordField.setText("");
                        notificacaoJLabel.setText("Notificações: Preencha os campos para incluir o novo registro");
                        primeiroRegistroJButton.setEnabled(true);
                        registroAnteriorJButton.setEnabled(true);
                        proximoRegistroJButton.setEnabled(true);
                        ultimoRegistroJButton.setEnabled(true);    
                        return;
                    }
                    String nome = nomeTextField.getText();
                    String email = emailTextField.getText();
                    String senha = senhaPasswordField.getText();
                    if (nome.equals("")) {
                        notificacaoJLabel.setText("Notificações: Obrigatório o preenchimento do campo nome!");
                        return;
                    }
                    notificacaoJLabel.setText(GerirRegistro.atualizar("db_teste", "tbl_teste", idTextField.getText(), nome, email, senha, "incluído"));
                    campo = "id";
                    resultado = NavegadorDeRegistro.irParaRegistro("db_teste", "tbl_teste", campo, idTextField.getText(), "último");
                    campo = aux;
                    idTextField.setText(resultado[0]);
                    nomeTextField.setText(resultado[1]);
                    emailTextField.setText(resultado[2]);
                    senhaPasswordField.setText(resultado[3]);
                    primeiroRegistroJButton.setEnabled(true);
                    registroAnteriorJButton.setEnabled(true);
                    proximoRegistroJButton.setEnabled(true);
                    ultimoRegistroJButton.setEnabled(true);
                } catch(Exception e){
                    System.out.println("Ocorreu erro ao executar o comando: " + e);
                    return;
                } 
            }
        }); 

        atualizarJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                String nome, email, senha;
                try{
                    nome = nomeTextField.getText();
                    email = emailTextField.getText();
                    senha = senhaPasswordField.getText();
                    // aqui vamos incluir a ação de cadastrar no bando de dados
                    notificacaoJLabel.setText(GerirRegistro.atualizar("db_teste", "tbl_teste", idTextField.getText(), nome, email, senha, "atualizado"));
                } catch(Exception e){
                    System.out.println("Ocorreu erro ao executar o comando: " + e);
                    return;
                } 
            }
        }); 

        excluirJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                String[] resultado;
                String aux;

                aux = campo;
                campo = "id";
                String reg_anterior = idTextField.getText();

                if (reg_anterior.equals("")) {
                    notificacaoJLabel.setText("Notificações: Selecione um registro a ser excluído");
                    return;
                }
                try{
                    notificacaoJLabel.setText(GerirRegistro.atualizar("db_teste", "tbl_teste", idTextField.getText(), "", "", "", "excluído"));
                } catch(Exception e) {
                    System.out.println("Ocorreu erro ao executar o comando: " + e);
                    return;
                }
                campo = aux;
                switch (campo) {
                    case "id":
                        reg_anterior = idTextField.getText();
                        break;
                
                    case "nome":
                        reg_anterior = nomeTextField.getText();
                        break;
                
                    case "email":
                        reg_anterior = emailTextField.getText();
                        break;

                    default:
                        break;
                }
                idTextField.setText("");
                nomeTextField.setText("");
                emailTextField.setText("");
                senhaPasswordField.setText("");
                try{
                    resultado = NavegadorDeRegistro.irParaRegistro("db_teste", "tbl_teste", campo, reg_anterior, "anterior");
                    idTextField.setText(resultado[0]);
                    nomeTextField.setText(resultado[1]);
                    emailTextField.setText(resultado[2]);
                    senhaPasswordField.setText(resultado[3]);    
                } catch(Exception e) {
                }
                try{
                    resultado = NavegadorDeRegistro.irParaRegistro("db_teste", "tbl_teste", campo, reg_anterior, "próximo");
                    idTextField.setText(resultado[0]);
                    nomeTextField.setText(resultado[1]);
                    emailTextField.setText(resultado[2]);
                    senhaPasswordField.setText(resultado[3]);
                } catch(Exception e) {
                }
            }
        }); 

        primeiroRegistroJButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                String[] resultado;
                try{
                    resultado = NavegadorDeRegistro.irParaRegistro("db_teste", "tbl_teste", campo, "0", "primeiro");
                    notificacaoJLabel.setText("Notificações: Primeiro registro posicionado com sucesso, ordenado por " + campo);
                    idTextField.setText(resultado[0]);
                    nomeTextField.setText(resultado[1]);
                    emailTextField.setText(resultado[2]);
                    senhaPasswordField.setText(resultado[3]);
                    primeiroRegistroJButton.setEnabled(false);
                    registroAnteriorJButton.setEnabled(false);
                    proximoRegistroJButton.setEnabled(true);
                    ultimoRegistroJButton.setEnabled(true);
                } catch(Exception e) {
                    System.out.println("Ocorreu erro ao executar o comando: " + e);
                    return;
                }
            }
        });
        
        registroAnteriorJButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                String[] resultado;
                String reg_anterior = idTextField.getText();
                switch (campo) {
                    case "nome":
                        reg_anterior = nomeTextField.getText();
                        break;
                
                    case "email":
                        reg_anterior = emailTextField.getText();
                        break;

                    default:
                        break;
                }
                try{
                    if (reg_anterior.equals("")) {
                        resultado = NavegadorDeRegistro.irParaRegistro("db_teste", "tbl_teste", campo, "0", "primeiro");
                    } else {
                        resultado = NavegadorDeRegistro.irParaRegistro("db_teste", "tbl_teste", campo, reg_anterior, "anterior");
                    }
                    notificacaoJLabel.setText("Notificações: Registro anterior posicionado com sucesso, ordenado por " + campo);
                    idTextField.setText(resultado[0]);
                    nomeTextField.setText(resultado[1]);
                    emailTextField.setText(resultado[2]);
                    senhaPasswordField.setText(resultado[3]);
                    primeiroRegistroJButton.setEnabled(true);
                    registroAnteriorJButton.setEnabled(true);    
                    proximoRegistroJButton.setEnabled(true);
                    ultimoRegistroJButton.setEnabled(true);
                } catch(Exception e) {
                    primeiroRegistroJButton.setEnabled(false);
                    registroAnteriorJButton.setEnabled(false);
                    notificacaoJLabel.setText("Notificações: Primeiro registro posicionado com sucesso, ordenado por " + campo);
                    return;
                }
            }
        });
        
        proximoRegistroJButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                String[] resultado;
                String reg_anterior = idTextField.getText();
                switch (campo) {
                    case "nome":
                        reg_anterior = nomeTextField.getText();
                        break;
                
                    case "email":
                        reg_anterior = emailTextField.getText();
                        break;

                    default:
                        break;
                }
                try{
                    if (reg_anterior.equals("")) {
                        resultado = NavegadorDeRegistro.irParaRegistro("db_teste", "tbl_teste", campo, "0", "primeiro");
                    } else {
                        resultado = NavegadorDeRegistro.irParaRegistro("db_teste", "tbl_teste", campo, reg_anterior, "próximo");
                    }
                    notificacaoJLabel.setText("Notificações: Próximo registro posicionado com sucesso, ordenado por " + campo);
                    idTextField.setText(resultado[0]);
                    nomeTextField.setText(resultado[1]);
                    emailTextField.setText(resultado[2]);
                    senhaPasswordField.setText(resultado[3]);
                    primeiroRegistroJButton.setEnabled(true);
                    registroAnteriorJButton.setEnabled(true);
                    proximoRegistroJButton.setEnabled(true);
                    ultimoRegistroJButton.setEnabled(true);
                } catch(Exception e) {
                    proximoRegistroJButton.setEnabled(false);
                    ultimoRegistroJButton.setEnabled(false);
                    notificacaoJLabel.setText("Notificações: Ultimo registro posicionado com sucesso, ordenado por " + campo);
                    return;
                }
            }
        });
        
        ultimoRegistroJButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                String[] resultado;
                try{
                    resultado = NavegadorDeRegistro.irParaRegistro("db_teste", "tbl_teste", campo, "0", "último");
                    notificacaoJLabel.setText("Notificações: Ultimo registro posicionado com sucesso, ordenado por " + campo);
                    idTextField.setText(resultado[0]);
                    nomeTextField.setText(resultado[1]);
                    emailTextField.setText(resultado[2]);
                    senhaPasswordField.setText(resultado[3]);
                    primeiroRegistroJButton.setEnabled(true);
                    registroAnteriorJButton.setEnabled(true);
                    proximoRegistroJButton.setEnabled(false);
                    ultimoRegistroJButton.setEnabled(false);
                } catch(Exception e) {
                    System.out.println("Ocorreu erro ao executar o comando: " + e);
                    return;
                }
            }
        });

        
        linha1.add(idJLabel);
        linha1.add(idTextField);
        add(linha1);

        linha2.add(nomeJLabel);
        linha2.add(nomeTextField);
        add(linha2);

        linha3.add(emailJLabel);
        linha3.add(emailTextField);
        add(linha3);

        linha4.add(senhaJLabel);
        linha4.add(senhaPasswordField);
        add(linha4);
        
        linha5.add(primeiroRegistroJButton);
        linha5.add(registroAnteriorJButton);
        linha5.add(proximoRegistroJButton);
        linha5.add(ultimoRegistroJButton);
        add(linha5);

        linha6.add(consultarJButton);
        linha6.add(incluirJButton);
        linha6.add(atualizarJButton);
        linha6.add(excluirJButton);
        add(linha6);


        linha7.add(notificacaoJLabel);
        add(linha7);

        setSize(600, 250);
        setVisible(true);

    }


    public static void main(String[] args) {
        EditarCadastro application = new EditarCadastro();
        application.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
