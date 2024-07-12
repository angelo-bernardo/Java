// Programa Java para criar um JComboBox simples
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

class solve extends JFrame implements ItemListener {

	// frame
	static JFrame f;

	// rótulos
	static JLabel l;

	// combobox
	static JComboBox c;

	// main class
	public static void main(String[] args)
	{
		// cria uma nova frame
		f = new JFrame("JComboBox");

		// cria um objeto
		solve s = new solve();

		// determina um layout de frame
		f.setLayout(new FlowLayout());

		// array tipo string contendo os nomes dos alunos
		String alunos[] = { "Gustavo", "Gabriel", "Lucas", "Matheus", "Daniel", "Julio", "Angelo", "Enrique", "Germano", "Farias", "Douglas", "Rafael", "Felipe" };

		// cria o ComboBox
		c = new JComboBox(alunos);

		// adiciona um ItemListener
		c.addItemListener(s);

		// cria um rótulo
		l = new JLabel("Selecione o Aluno ");

		// cria um novo painel
		JPanel p = new JPanel();

		// adiciona o rótulo ao painel
        p.add(l);

		// adiciona o combobox ao painel
		p.add(c);

        // adiciona o painel ao frame
		f.add(p);

		// determina o tamanho do frame
		f.setSize(400, 300);

        // mostra o frame criado
		f.show();
	}

    // monitora evento de mudança de item
	public void itemStateChanged(ItemEvent e)
	{
		// se houver mudança na seleção, indica o objeto onde o evento ocorreu
		if (e.getSource() == c) {
            // Para o futuro...
            // Poderia mostrar o aluno, de acordo com o item selecionado
            // então, poderia criar um novo rótulo l1:
            //    static JLabel l1;
            //    l1 = new JLabel("Gustavo selecionado"); // Gustavo porque é o primeiro da lista...
            // adicionar ao painel:
            //    p.add(l);
            // e, então, ir atualizando por aqui:
            //    l1.setText(c.getSelectedItem() + " selecionado");
		}
	}
}
