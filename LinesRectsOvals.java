import java.awt.Color;
import javax.swing.JFrame;

public class LinesRectsOvals
{
   // execute application
   public static void main(String[] args)
   {
      // create frame for LinesRectsOvalsJPanel
      JFrame frame = 
         new JFrame("Formas e textos");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
      LinesRectsOvalsJPanel linesRectsOvalsJPanel = 
         new LinesRectsOvalsJPanel(); 
      linesRectsOvalsJPanel.setBackground(Color.WHITE); 
      frame.add(linesRectsOvalsJPanel); 
      frame.setSize(400, 210);
      frame.setVisible(true);
   } 
}