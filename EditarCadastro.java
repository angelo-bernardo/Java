import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EditarCadastro extends JFrame {
   private final JTextField idJTextField = new JTextField();
   private final JTextField nomeJTextField = new JTextField();
   private final JTextField emailJTextField = new JTextField();
   private final JTextField senhaJTextField = new JTextField();
   private final JButton atualizarJButton = new JButton("✓");
   private final JButton primeiroJButton = new JButton("<<");
   private final JButton anteriorJButton = new JButton("<");
   private final JButton proximoJButton = new JButton(">");
   private final JButton ultimoJButton = new JButton(">>");
   private final JLabel idJLabel = new JLabel("ID:");
   private final JLabel nomeJLabel = new JLabel("Digite o Nome:");
   private final JLabel emailJLabel = new JLabel("Digite o Email:");
   private final JLabel senhaJLabel = new JLabel("Digite a Senha:");
   private final JLabel notificacaoJLabel = new JLabel("Notificações...");

   // constructor
   public EditarCadastro()
   {
      super("Editar Cadastro");
      setLayout(new GridLayout(6, 4, 5, 5));
      
      atualizarJButton.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               String dbString = "db_teste";
               String tblString = "tbl_teste";
               String campoString = "nome";
               String nome;
               try
               {
                  nome = nomeJTextField.getText();
                  //InserirRegistro.cadastrar(dbString, tblString, campoString, nome);
               } 
               catch(NumberFormatException ex)
               {
                  //System.out.println("Digite alguma coisa");
                  return;
               } 
            } 
         } 
      );

      add(idJLabel);
      add(idJTextField);
      add(atualizarJButton);
      add(new JLabel());
      atualizarJButton.setToolTipText("Atualizar Cadastro");

      add(nomeJLabel);
      add(nomeJTextField);
      add(new JLabel());
      add(new JLabel());

      add(emailJLabel);
      add(emailJTextField);
      add(new JLabel());
      add(new JLabel());

      add(senhaJLabel);
      add(senhaJTextField);
      add(new JLabel());
      add(new JLabel());

      add(primeiroJButton);
      add(anteriorJButton);
      add(proximoJButton);
      add(ultimoJButton);
      primeiroJButton.setToolTipText("Primeiro Registro");
      anteriorJButton.setToolTipText("Registro Anterior");
      proximoJButton.setToolTipText("Próximo Registro");
      ultimoJButton.setToolTipText("Último Registro");

      add(new JLabel());
      add(notificacaoJLabel);

      idJTextField.setEditable(false);
      atualizarJButton.setEnabled(false);
      primeiroJButton.setEnabled(false);
      anteriorJButton.setEnabled(false);

      setSize(400, 200);
      setVisible(true);
   }

   public static void main(String[] args)
   {
      EditarCadastro application = new EditarCadastro();
      application.setDefaultCloseOperation(EXIT_ON_CLOSE);
   } 
}