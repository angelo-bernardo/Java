import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class LinesRectsOvalsJPanel extends JPanel 
{
   // display various lines, rectangles and ovals
   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g); 
      this.setBackground(Color.WHITE);

      g.setColor(Color.RED);
      g.drawLine(5, 30, 380, 30);

      g.setColor(Color.BLUE);
      g.drawRect(5, 40, 90, 55);
      g.fillRect(100, 40, 90, 55);

      g.setColor(Color.BLACK);
      g.fillRoundRect(195, 40, 90, 55, 50, 50);
      g.drawRoundRect(290, 40, 90, 55, 20, 20);

      g.setColor(Color.GREEN);   
      g.draw3DRect(5, 100, 90, 55, true);
      g.fill3DRect(100, 100, 90, 55, false);

      g.setColor(Color.MAGENTA);
      g.drawOval(195, 100, 90, 55);
      g.fillOval(290, 100, 90, 55);

      g.setColor(Color.RED);
      g.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 18));
      g.drawString("Senac", 20, 70);

      g.setColor(Color.YELLOW);
      g.setFont(new Font("Monospaced", Font.BOLD, 24));
      g.drawString("Senac", 105, 70);

      g.setColor(Color.WHITE);
      g.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 18));
      g.drawString("Senac", 215, 70);

      g.setColor(Color.BLACK);
      g.setFont(new Font("Serif", Font.BOLD, 16));
      g.drawString("Senac", 310, 70);

      g.setColor(Color.BLACK);
      g.setFont(new Font("Monospaced", Font.ITALIC + Font.BOLD, 24));
      g.drawString("Senac", 10, 130);

      g.setColor(Color.YELLOW);
      g.setFont(new Font("SansSerif", Font.PLAIN, 14));
      g.drawString("Senac", 120, 130);

      g.setColor(Color.GREEN);
      g.setFont(new Font("Serif", Font.BOLD, 18));
      g.drawString("Senac", 215, 130);

      g.setColor(Color.BLUE);
      g.setFont(new Font("SansSerif", Font.PLAIN, 14));
      g.drawString("Senac", 310, 130);
   } 
}

