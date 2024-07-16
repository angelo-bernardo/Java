import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MenuFrame extends JFrame 
{
   public MenuFrame()
   {
      super("Menus");     

      JMenu arquivoMenu = new JMenu("Arquivo");
      arquivoMenu.setMnemonic('A');

      JMenu editarMenu = new JMenu("Editar");
      editarMenu.setMnemonic('E');

      JMenuBar bar = new JMenuBar();
      setJMenuBar(bar);
      bar.add(arquivoMenu);
      bar.add(editarMenu);
   }
}