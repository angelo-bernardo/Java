import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class FontJPanel extends JPanel
{
   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g); 

      g.fillRect(20, 40, 100, 50);
      g.setColor(Color.BLUE);

      g.setFont(new Font("Arial", Font.ITALIC, 22));
      g.drawString("Senac", 22, 70);
   } 
}

