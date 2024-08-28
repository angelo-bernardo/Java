import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EditarCadastro extends JFrame {
    
    private final JTextField idTextField = new JTextField(10);
    private final JTextField nomeTextField = new JTextField(50);
    private final JTextField emailTextField = new JTextField(50);
    private final JPasswordField senhaPasswordField = new JPasswordField(15);
    private final JPasswordField confirmesenhaPasswordField = new JPasswordField(15);
    private final JLabel idJLabel = new JLabel("ID:");
    private final JLabel ordemJLabel = new JLabel("Navegação ordenada por:");
    private final JLabel juncaoJLabel = new JLabel("Procurar usando:");
    private final JLabel nomeJLabel = new JLabel("Digite o nome:");
    private final JLabel emailJLabel = new JLabel("Digite o email:");
    private final JLabel senhaJLabel = new JLabel("Digite a senha:");
    private final JLabel confirmesenhaJLabel = new JLabel("Confirme a senha:");
    private final JLabel notificacoesJLabel = new JLabel("Notificações:");
    private final JLabel notificacaoJLabel = new JLabel("");
    private final JButton primeiroRegistroJButton = new JButton("<<");
    private final JButton registroAnteriorJButton = new JButton("<");
    private final JButton proximoRegistroJButton = new JButton(">");
    private final JButton ultimoRegistroJButton = new JButton(">>");
    private final JButton incluirJButton = new JButton("Incluir");
    private final JButton consultarJButton = new JButton("Consultar");
    private final JButton alterarJButton = new JButton("Alterar");
    private final JButton excluirJButton = new JButton("Excluir");
    private final JRadioButton idButton;
    private final JRadioButton nomeButton;
    private final JRadioButton emailButton;
    private final ButtonGroup ordenarGroup;
    private final JRadioButton eButton;
    private final JRadioButton ouButton;
    private final ButtonGroup juncaoGroup;
    private String campo = null;
    private String juncao = null;
    private String[] tabela = {"db_teste", "tbl_teste"};

    public EditarCadastro() {
        super("Editar Cadastro");

        setLayout(new GridLayout(8, 1, 5, 5));

        FlowLayout linha = new FlowLayout(FlowLayout.LEFT);
        JPanel linha0 = new JPanel(linha);
        JPanel linha1 = new JPanel(linha);
        JPanel linha2 = new JPanel(linha);
        JPanel linha3 = new JPanel(linha);
        JPanel linha4 = new JPanel(linha);
        JPanel linha5 = new JPanel(new GridLayout(1, 4, 5, 5));
        JPanel linha6 = new JPanel(new GridLayout(1, 4, 5, 5));
        JPanel linha7 = new JPanel(linha);

        idButton = new JRadioButton("ID");
        nomeButton = new JRadioButton("Nome");
        emailButton = new JRadioButton("E-mail");
        idButton.setSelected(true);
        campo = "id";

        ordenarGroup = new ButtonGroup();
        ordenarGroup.add(idButton);
        ordenarGroup.add(nomeButton);
        ordenarGroup.add(emailButton);

        idButton.addActionListener(e -> campoPerformed(e));
        nomeButton.addActionListener(e -> campoPerformed(e));
        emailButton.addActionListener(e -> campoPerformed(e));

        eButton = new JRadioButton("nome E email");
        ouButton = new JRadioButton("nome OU email");
        eButton.setSelected(true);
        juncao = "e";

        juncaoGroup = new ButtonGroup();
        juncaoGroup.add(eButton);
        juncaoGroup.add(ouButton);

        eButton.addActionListener(e -> juncaoPerformed(e));
        ouButton.addActionListener(e -> juncaoPerformed(e));

        idTextField.setEditable(false);
        idTextField.setFocusable(false);
        mostrarJuncao(false);
        habilitarCampos(false,false,false);
        habilitarNavegacao(true, false, false, true);
        habilitarCadastro(true, true, true, true);

        ActionListener primeiroRegistro = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] resultado = null;
                String[][] campos = parametrosNavegacao();
                try {
                    resultado = NavegadorDeRegistro.registro(tabela, campos, "primeiro");
                    notificacaoJLabel.setText("Primeiro registro posicionado com sucesso!");
                    carregarCampos(resultado);
                } catch (Exception ex) {
                    notificacaoJLabel.setText("Ocorreu erro ao executar o comando: " + ex);
                }
                habilitarNavegacao(false, false, true, true);
            }
        };

        ActionListener registroAnterior = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] resultado = null;
                String[][] campos = parametrosNavegacao();
                try {
                    resultado = NavegadorDeRegistro.registro(tabela, campos, "anterior");
                    notificacaoJLabel.setText("Registro anterior posicionado com sucesso!");
                    habilitarNavegacao(true, true, true, true);
                    carregarCampos(resultado);
                } catch (Exception ex) {
                    habilitarNavegacao(false, false, true, true);
                    notificacaoJLabel.setText("Primeiro registro posicionado com sucesso!");
                }
            }
        };

        ActionListener proximoRegistro = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] resultado = null;
                String[][] campos = parametrosNavegacao();
                try {
                    resultado = NavegadorDeRegistro.registro(tabela, campos, "próximo");
                    notificacaoJLabel.setText("Próximo registro posicionado com sucesso!");
                    habilitarNavegacao(true, true, true, true);
                    carregarCampos(resultado);
                } catch (Exception ex) {
                    habilitarNavegacao(true, true, false, false);
                    notificacaoJLabel.setText("Último registro posicionado com sucesso!");
                }
            }
        };

        ActionListener ultimoRegistro = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] resultado = null;
                String[][] campos = parametrosNavegacao();
                try {
                    resultado = NavegadorDeRegistro.registro(tabela, campos, "último");
                    notificacaoJLabel.setText("Último registro posicionado com sucesso!");
                    carregarCampos(resultado);
                } catch (Exception ex) {
                    notificacaoJLabel.setText("Ocorreu erro ao executar o comando: " + ex);
                }
                habilitarNavegacao(true, true, false, false);
            }
        };

        primeiroRegistroJButton.addActionListener(primeiroRegistro);
        registroAnteriorJButton.addActionListener(registroAnterior);
        proximoRegistroJButton.addActionListener(proximoRegistro);
        ultimoRegistroJButton.addActionListener(ultimoRegistro);

        incluirJButton.addActionListener(e -> {
            if (incluirJButton.getText().equals("Incluir")) {
                habilitarCampos(true,true,true);
                habilitarNavegacao(false, false, false, false);
                habilitarCadastro(true, false, false, true);
                notificacaoJLabel.setText("Preencha os campos para incluir o novo registro.");
                limparCampos();
                nomeTextField.grabFocus();
                incluirJButton.setText("Gravar");
                excluirJButton.setText("Cancelar");
                return;
            }
            if (incluirJButton.getText().equals("Gravar")) {
                if (!verificarPreenchimento()) return;
                if (cancelarAcao("incluir", "Inclusão")) return;

                String[] resultado = null;
                String[][] campos = cadastro();
                try {
                    resultado = NavegadorDeRegistro.registro(tabela, campos, "incluir");
                    carregarCampos(resultado);
                    habilitarCampos(false,false,false);
                    habilitarNavegacao(true, true, true, true);
                    habilitarCadastro(true, true, true, true);
                } catch (Exception ex) {
                    notificacaoJLabel.setText("Ocorreu erro ao executar o comando: " + ex);
                }
            }
        });

        consultarJButton.addActionListener(e -> {
            if (consultarJButton.getText().equals("Consultar")) {
                mostrarJuncao(true);
                habilitarCampos(true,true,false);
                habilitarNavegacao(false, false, false, false);
                habilitarCadastro(false, true, false, true);
                notificacaoJLabel.setText("Preencha o campo nome e/ou o campo email para consultar.");
                limparCampos();
                nomeTextField.grabFocus();
                consultarJButton.setText("Procurar");
                excluirJButton.setText("Cancelar");
                return;
            }
            if (consultarJButton.getText().equals("Procurar")) {
                String[] resultado = null;
                String[][] campos = null;
                String nome = nomeTextField.getText();
                String email = emailTextField.getText();
                if (!nome.trim().isEmpty()) {
                    nomeButton.setSelected(true);
                    campo = "nome";
                    campos = parametrosNavegacao();
                }
                if (!email.trim().isEmpty()) {
                    if (campos == null) {
                        emailButton.setSelected(true);
                        campo = "email";
                        campos = parametrosNavegacao();
                    } else {
                        campos[0][2] = "email";
                        campos[1][2] = emailTextField.getText();
                    }
                }
                if (campos == null) {
                    campos = parametrosNavegacao();
                    campos[0][1] = "nome";
                    campos[1][1] = nomeTextField.getText();
                }
                try {
                    resultado = NavegadorDeRegistro.registro(tabela, campos, "consultar_"+juncao);
                    notificacaoJLabel.setText("Registro posicionado com sucesso!");
                    carregarCampos(resultado);
                    mostrarJuncao(false);
                    habilitarCampos(false,false,false);
                    habilitarNavegacao(true, true, true, true);
                    habilitarCadastro(true, true, true, true);
                } catch (Exception ex) {
                    notificacaoJLabel.setText("Ocorreu erro ao executar o comando: " + ex);
                }
                if (idTextField.getText().isEmpty()) {
                    notificacaoJLabel.setText("Registro não encontrado!");
                }
            }
        });

        alterarJButton.addActionListener(e -> {
            if (alterarJButton.getText().equals("Alterar")) {
                if (idTextField.getText().isEmpty()) {
                    notificacaoJLabel.setText("Selecione um registro para ser alterado.");
                    return;
                }
                habilitarCampos(true,true,true);
                habilitarNavegacao(false, false, false, false);
                habilitarCadastro(false, false, true, true);
                notificacaoJLabel.setText("Preencha os campos para alterar o registro.");
                nomeTextField.grabFocus();
                alterarJButton.setText("Gravar");
                excluirJButton.setText("Cancelar");
                return;
            }
            if (alterarJButton.getText().equals("Gravar")) {
                if (!verificarPreenchimento()) return;
                if (cancelarAcao("alterar", "Alteração")) return;

                String[] resultado = null;
                String[][] campos = cadastro();
                try {
                    resultado = NavegadorDeRegistro.registro(tabela, campos, "alterar");
                    carregarCampos(resultado);
                    habilitarCampos(false,false,false);
                    habilitarNavegacao(true, true, true, true);
                    habilitarCadastro(true, true, true, true);
                } catch (Exception ex) {
                    notificacaoJLabel.setText("Ocorreu erro ao executar o comando: " + ex);
                }
            }
        });

        excluirJButton.addActionListener(e -> {
            if (excluirJButton.getText().equals("Cancelar")) {
                mostrarJuncao(false);
                habilitarCampos(false,false,false);
                habilitarNavegacao(true, true, true, true);
                if (alterarJButton.getText().equals("Gravar")) {
                    String[] resultado = null;
                    String[][] campos = cadastro();
                    try {
                        resultado = NavegadorDeRegistro.registro(tabela, campos, "irpara");
                    } catch (Exception ex) {
                    }
                    if (resultado == null) {
                        limparCampos();
                    } else {
                        carregarCampos(resultado);
                    }
                } else {
                    limparCampos();
                    habilitarNavegacao(true, false, false, true);
                }
                habilitarCadastro(true, true, true, true);
                return;
            }
            if (excluirJButton.getText().equals("Excluir")) {
                if (idTextField.getText().isEmpty()) {
                    notificacaoJLabel.setText("Selecione um registro para ser excluído.");
                    return;
                }

                if (cancelarAcao("excluir", "Exclusão")) return;

                String[][] campos = parametrosNavegacao();
                try {
                    NavegadorDeRegistro.registro(tabela, campos, "excluir");
                } catch (Exception ex) {
                    notificacaoJLabel.setText("Ocorreu erro ao executar o comando: " + ex);
                    return;
                }
                registroAnterior.actionPerformed(null);
                proximoRegistro.actionPerformed(null);
            }
        });

        // nomeTextField.getDocument().addDocumentListener(new DocumentListener() {
        //     public void changedUpdate(DocumentEvent e) { desabilitarEmail();}
        //     public void removeUpdate(DocumentEvent e) { desabilitarEmail();}
        //     public void insertUpdate(DocumentEvent e) { desabilitarEmail();}
        //     private void desabilitarEmail() {
        //         if (consultarJButton.getText().equals("Procurar")){
        //             habilitarEmail(false);
        //         }
        //     }
        // });

        // emailTextField.getDocument().addDocumentListener(new DocumentListener() {
        //     public void changedUpdate(DocumentEvent e) { desabilitarNome();}
        //     public void removeUpdate(DocumentEvent e) { desabilitarNome();}
        //     public void insertUpdate(DocumentEvent e) { desabilitarNome();}
        //     private void desabilitarNome() {
        //         if (consultarJButton.getText().equals("Procurar")){
        //             habilitarNome(false);
        //         }
        //     }
        // });

        senhaPasswordField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { habilitarConfirmacao();}
            public void removeUpdate(DocumentEvent e) { habilitarConfirmacao();}
            public void insertUpdate(DocumentEvent e) { habilitarConfirmacao();}
            private void habilitarConfirmacao() {
                habilitarConfirmeSenha(true);
            }
        });

        linha0.add(ordemJLabel);
        linha0.add(idButton);
        linha0.add(nomeButton);
        linha0.add(emailButton);
        add(linha0);

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
        linha4.add(juncaoJLabel);
        linha4.add(eButton);
        linha4.add(ouButton);
        add(linha4);

        linha5.add(primeiroRegistroJButton);
        linha5.add(registroAnteriorJButton);
        linha5.add(proximoRegistroJButton);
        linha5.add(ultimoRegistroJButton);
        add(linha5);

        linha6.add(incluirJButton);
        linha6.add(consultarJButton);
        linha6.add(alterarJButton);
        linha6.add(excluirJButton);
        add(linha6);

        linha7.add(notificacoesJLabel);
        linha7.add(notificacaoJLabel);
        add(linha7);

        setSize(700, 300);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void campoPerformed(ActionEvent e) {
        if (e.getSource() == idButton) {
            campo = "id";
        } else if (e.getSource() == nomeButton) {
            campo = "nome";
        } else if (e.getSource() == emailButton) {
            campo = "email";
        }
        if (idTextField.getText().isEmpty()) {
            habilitarNavegacao(true, false, false, true);
        } else {
            habilitarNavegacao(true, true, true, true);
        }
        notificacaoJLabel.setText("Ordenação por " + campo + " selecionada.");
    }

    private void juncaoPerformed(ActionEvent e) {
        if (e.getSource() == eButton) {
            juncao = "e";
        } else if (e.getSource() == ouButton) {
            juncao = "ou";
        }
        notificacaoJLabel.setText("Busca " + juncao.toUpperCase() + " selecionada.");
    }

    public void limparCampos() {
        idTextField.setText("");
        nomeTextField.setText("");
        emailTextField.setText("");
        senhaPasswordField.setText("");
        confirmesenhaPasswordField.setText("");
        habilitarConfirmeSenha(false);
    }

    public void carregarCampos(String[] resultado) {
        idTextField.setText(resultado[0]);
        nomeTextField.setText(resultado[1]);
        emailTextField.setText(resultado[2]);
        senhaPasswordField.setText(resultado[3]);
        confirmesenhaPasswordField.setText(resultado[3]);
        habilitarConfirmeSenha(false);
    }

    public void habilitarNavegacao(Boolean primeiro, Boolean anterior, Boolean proximo, Boolean ultimo) {
        primeiroRegistroJButton.setEnabled(primeiro);
        registroAnteriorJButton.setEnabled(anterior);
        proximoRegistroJButton.setEnabled(proximo);
        ultimoRegistroJButton.setEnabled(ultimo);
    }

    public void habilitarCadastro(Boolean incluir, Boolean consultar, Boolean alterar, Boolean excluir) {
        incluirJButton.setEnabled(incluir);
        consultarJButton.setEnabled(consultar);
        alterarJButton.setEnabled(alterar);
        excluirJButton.setEnabled(excluir);
        if (incluir && consultar && alterar && excluir) {
            incluirJButton.setText("Incluir");
            consultarJButton.setText("Consultar");
            alterarJButton.setText("Alterar");
            excluirJButton.setText("Excluir");
        }
    }

    public void habilitarCampos(Boolean nome, Boolean email, Boolean senha) {
        habilitarNome(nome);
        habilitarEmail(email);
        habilitarSenha(senha);
        habilitarConfirmeSenha(false);
    }

    public void mostrarJuncao(Boolean valor) {
        juncaoJLabel.setVisible(valor);
        eButton.setVisible(valor);
        ouButton.setVisible(valor);
        if (valor) {
            valor = false;
        } else {
            valor = true;
        }
        senhaJLabel.setVisible(valor);
        senhaPasswordField.setVisible(valor);
    }

    public void habilitarNome(Boolean valor) {
        nomeTextField.setEditable(valor);
        nomeTextField.setFocusable(valor);
    }

    public void habilitarEmail(Boolean valor) {
        emailTextField.setEditable(valor);
        emailTextField.setFocusable(valor);
    }

    public void habilitarSenha(Boolean valor) {
        senhaPasswordField.setEditable(valor);
        senhaPasswordField.setFocusable(valor);
    }

    public void habilitarConfirmeSenha(Boolean valor) {
        confirmesenhaJLabel.setVisible(valor);
        confirmesenhaPasswordField.setEditable(valor);
        confirmesenhaPasswordField.setFocusable(valor);
        confirmesenhaPasswordField.setVisible(valor);
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

    public String[][] parametrosNavegacao() {
        String[][] campos = {{"", "", "", ""}, {"", "", "", ""}};
        campos[0][0] = "id";
        campos[1][0] = idTextField.getText();
        if (!campos[0][0].equals(campo)) {
            campos[0][1] = campo;
            campos[1][1] = campoPesquisa();
        }
        return campos;
    }

    public String[][] cadastro() {
        String[][] campos = {
            {"id", "nome", "email", "senha"},
            {idTextField.getText(), nomeTextField.getText(), emailTextField.getText(), new String(senhaPasswordField.getPassword())}
        };
        return campos;
    }

    public String campoPesquisa() {
        String resultado = null;
        switch (campo) {
            case "id":
                resultado = idTextField.getText();
                break;
            case "nome":
                resultado = nomeTextField.getText();
                break;
            case "email":
                resultado = emailTextField.getText();
                break;
            default:
                break;
        }
        return resultado;
    }

    public static void main(String[] args) {
        new EditarCadastro();
    }
}
