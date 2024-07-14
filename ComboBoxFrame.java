import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JComboBox;

public class ComboBoxFrame extends JFrame 
{
   private final JComboBox<String> alunosJComboBox; // contém os nomes dos alunos

   private static final String[] names = 
      {"Gustavo", "Gabriel", "Lucas", "Matheus", "Daniel", "Julio", "Angelo", "Enrique", "Germano", "Farias", "Douglas", "Rafael", "Felipe"}; //nomes dos alunos


   // construtor ComboBoxFrame adiciona o JComboBox ao JFrame
   public ComboBoxFrame()
   {
      super("Teste de JComboBox");
      setLayout(new FlowLayout()); // determina o layout da frame

      alunosJComboBox = new JComboBox<String>(names); // programa o JComboBox
      // alunosJComboBox.setMaximumRowCount(3); // mostra somente três linhas

      add(alunosJComboBox);
   } // fim do construtor do ComboBoxFrame
} // fim da classe ComboBoxFrame
