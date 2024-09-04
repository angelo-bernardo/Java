import java.awt.*;
import javax.swing.*;

public class TeladeLogin extends JFrame {
    
    private final JLabel loginJLabel = new JLabel("Usuário", SwingConstants.CENTER);
    private final JTextField loginTextField = new JTextField();
    private final JLabel senhaJLabel = new JLabel("Digite a senha:", SwingConstants.CENTER);
    private final JPasswordField senhaPasswordField = new JPasswordField();
    private final JButton entrarJButton = new JButton("Entrar");
    private final JButton incluirJButton = new JButton("Incluir");
    private final JCheckBox termosButton = new JCheckBox("Aceito os termos"); //Aceito os termos
    private final JLabel notificacaoJLabel = new JLabel("Bem vindo, faço o login ou cadastre-se");
    private String[] tabela = {"db_teste", "tbl_teste"};

    public TeladeLogin() {
        super("Tela de Login");
        setLayout(new GridLayout(11, 1, 5, 5));
        GridLayout linha = new GridLayout(1, 1, 5, 5);
        JPanel linha0 = new JPanel(linha); // espaço em branco
        JPanel linha1 = new JPanel(linha); // loginJLabel
        JPanel linha2 = new JPanel(linha); // loginTextField
        JPanel linha3 = new JPanel(linha); // espaço em branco
        JPanel linha4 = new JPanel(linha); // senhaJLabel
        JPanel linha5 = new JPanel(linha); // senhaPasswordField
        JPanel linha6 = new JPanel(linha); // espaço em branco
        JPanel linha7 = new JPanel(new GridLayout(1, 2, 5, 5)); // botões
        JPanel linha8 = new JPanel(linha); // espaço em branco
        JPanel linha9 = new JPanel(linha); // checkbox termosButton
        JPanel linha10 = new JPanel(new FlowLayout(FlowLayout.LEFT)); //notificações

        entrarJButton.addActionListener(e -> {
            if (!termosButton.isSelected()) {
                notificacaoJLabel.setText("Aceite os termos.");
                return;
            }
            notificacaoJLabel.setText("");
            String nome = loginTextField.getText();
            if (nome.trim().isEmpty()) {
                notificacaoJLabel.setText("Preencha os campos de nome e senha.");
                loginTextField.grabFocus();
                return;
            }
            if (new String(senhaPasswordField.getPassword()).isEmpty()) {
                notificacaoJLabel.setText("Preencha os campos de nome e senha.");
                senhaPasswordField.grabFocus();
                return;
            }
            String[] resultado = null;
            String[][] campos = {{"id", "nome"}, {"0", nome}};
            try {
                resultado = NavegadorDeRegistro.registro(tabela, campos, "consultar_ou");
            } catch (Exception ex) {
                notificacaoJLabel.setText("Ocorreu erro ao executar o comando: " + ex);
                return;
            }
            if (resultado == null) {
                notificacaoJLabel.setText("Usuário não encontrado!");
                return;
            }
            notificacaoJLabel.setText("Acesso negado!");
            if (resultado[1].equals(nome)) {
                if (resultado[3].equals(new String(senhaPasswordField.getPassword()))) {
                    notificacaoJLabel.setText("Acesso com sucesso!");
                    new EditarCadastro();
                    this.dispose();
                }
            }
        });

        incluirJButton.addActionListener(e -> {
            if (!termosButton.isSelected()) {
                notificacaoJLabel.setText("Aceite os termos.");
                return;
            }
            this.setVisible(false);
            NovoCadastro novoCadastro = new NovoCadastro(this); // Passa a janela principal para a nova janela
            novoCadastro.setVisible(true);
    
            // Torna a janela principal não visível
            this.setVisible(false);
        });

        linha0.add(new JLabel());
        add(linha0);

        linha1.add(loginJLabel);
        add(linha1);

        linha2.add(loginTextField);
        add(linha2);

        linha3.add(new JLabel());
        add(linha3);

        linha4.add(senhaJLabel);
        add(linha4);

        linha5.add(senhaPasswordField);
        add(linha5);

        linha6.add(new JLabel());
        add(linha6);

        linha7.add(entrarJButton);
        linha7.add(incluirJButton);
        add(linha7);
        
        linha8.add(new JLabel());
        add(linha8);
        
        linha9.add(termosButton);
        add(linha9);

        linha10.add(new JLabel("Notificações:"));
        notificacaoJLabel.setText(setHtmlFormat(notificacaoJLabel.getText()));
        linha10.add(notificacaoJLabel);
        add(linha10);

        setSize(350, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static String setHtmlFormat (String srtText) {
        return "<html><body>" + srtText + "</body></html>";
    }

}
