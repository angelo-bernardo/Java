import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NovoCadastro extends JFrame {
    private final JTextField idTextField = new JTextField(10);
    private final JTextField nomeTextField = new JTextField(50);
    private final JTextField emailTextField = new JTextField(50);
    private final JPasswordField senhaPasswordField = new JPasswordField(15);
    private final JPasswordField confirmesenhaPasswordField = new JPasswordField(15);
    private final JLabel idJLabel = new JLabel("ID:");
    private final JLabel nomeJLabel = new JLabel("Digite o nome:");
    private final JLabel emailJLabel = new JLabel("Digite o email:");
    private final JLabel senhaJLabel = new JLabel("Digite a senha:");
    private final JLabel confirmesenhaJLabel = new JLabel("Confirme a senha:");
    private final JLabel notificacaoJLabel = new JLabel("");
    private final JButton incluirJButton = new JButton("Incluir");
    private final JButton cancelarJButton = new JButton("Sair");
    private String[] tabela = {"db_teste", "tbl_teste"};

    public NovoCadastro(JFrame mainWindow) {
        super("Novo Cadastro");

        setLayout(new GridLayout(6, 1, 5, 5));

        FlowLayout linha = new FlowLayout(FlowLayout.LEFT);
        JPanel linha1 = new JPanel(linha);
        JPanel linha2 = new JPanel(linha);
        JPanel linha3 = new JPanel(linha);
        JPanel linha4 = new JPanel(linha);
        JPanel linha5 = new JPanel(new GridLayout(1, 2, 5, 5));
        JPanel linha6 = new JPanel(linha);

        idTextField.setEditable(false);
        idTextField.setFocusable(false);
        habilitarCampos(true);

        incluirJButton.addActionListener(e -> {
            if (!verificarPreenchimento()) return;
            if (cancelarAcao("incluir", "Inclusão")) return;
            String[] resultado = null;
            String[][] campos = cadastro();
            try {
                resultado = NavegadorDeRegistro.registro(tabela, campos, "incluir");
                carregarCampos(resultado);
                notificacaoJLabel.setText("Registro incluído com sucesso!");
                habilitarCampos(false);
            } catch (Exception ex) {
                notificacaoJLabel.setText("Ocorreu erro ao executar o comando: " + ex);
            }
    });

        cancelarJButton.addActionListener(e -> {
            this.dispose();
            return;
        });

        senhaPasswordField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { habilitarConfirmacao();}
            public void removeUpdate(DocumentEvent e) { habilitarConfirmacao();}
            public void insertUpdate(DocumentEvent e) { habilitarConfirmacao();}
            private void habilitarConfirmacao() {
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
        linha4.add(confirmesenhaJLabel);
        linha4.add(confirmesenhaPasswordField);
        add(linha4);


        linha5.add(incluirJButton);
        linha5.add(cancelarJButton);
        add(linha5);

        linha6.add(new JLabel("Notificações:"));
        notificacaoJLabel.setText(setHtmlFormat(notificacaoJLabel.getText()));
        linha6.add(notificacaoJLabel);
        add(linha6);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                mainWindow.setVisible(true); // Torna a janela principal visível novamente
            }
        });

        setSize(700, 250);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static String setHtmlFormat (String srtText) {
        return "<html><body>" + srtText + "</body></html>";
    }

    public void carregarCampos(String[] resultado) {
        idTextField.setText(resultado[0]);
        nomeTextField.setText(resultado[1]);
        emailTextField.setText(resultado[2]);
        senhaPasswordField.setText(resultado[3]);
        confirmesenhaPasswordField.setText(resultado[3]);
    }

    public Boolean cancelarAcao(String acao, String titulo) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(null);
        Boolean retorno = false;
        Object[] botoes = {"Sim", "Não"};
        int resposta = JOptionPane.showOptionDialog(frame,
            "Tem certeza de que deseja " + acao + " o registro?",
            "Confirmar " + titulo,
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            botoes,
            botoes[1]);
        if (resposta == 1) {
            notificacaoJLabel.setText(titulo + " cancelada.");
            retorno = true;
        } else {
            retorno = false;
        }
        return retorno;
    }

    public Boolean verificarPreenchimento() {
        String nome = nomeTextField.getText();
        String email = emailTextField.getText();
        String senha = new String(senhaPasswordField.getPassword());
        String confirme_senha = new String(confirmesenhaPasswordField.getPassword());
        if (nome.trim().isEmpty()) {
            notificacaoJLabel.setText("Obrigatório o preenchimento do campo nome!");
            nomeTextField.grabFocus();
            return false;
        }
        if (email.trim().isEmpty()) {
            notificacaoJLabel.setText("Obrigatório o preenchimento do campo email!");
            emailTextField.grabFocus();
            return false;
        }
        if (!email.contains("@")) {
            notificacaoJLabel.setText("Digite um email válido!");
            emailTextField.grabFocus();
            return false;
        }
        if (senha.length() == 0) {
            notificacaoJLabel.setText("Obrigatório o preenchimento do campo senha!");
            senhaPasswordField.grabFocus();
            return false;
        }
        if (senha.length() != confirme_senha.length()) {
            notificacaoJLabel.setText("As senhas devem ser iguais!");
            confirmesenhaPasswordField.grabFocus();
            return false;
        }
        return true;
    }

    public String[][] cadastro() {
        String[][] campos = {
            {"id", "nome", "email", "senha"},
            {idTextField.getText(), nomeTextField.getText(), emailTextField.getText(), new String(senhaPasswordField.getPassword())}
        };
        return campos;
    }
    
    public void habilitarCampos(Boolean valor) {
        incluirJButton.setEnabled(valor);
        nomeTextField.setEditable(valor);
        nomeTextField.setFocusable(valor);
        emailTextField.setEditable(valor);
        emailTextField.setFocusable(valor);
        senhaPasswordField.setEditable(valor);
        senhaPasswordField.setFocusable(valor);
        confirmesenhaJLabel.setVisible(valor);
        confirmesenhaPasswordField.setEditable(valor);
        confirmesenhaPasswordField.setFocusable(valor);
        confirmesenhaPasswordField.setVisible(valor);
    }

}
