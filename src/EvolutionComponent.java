import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class EvolutionComponent extends JComponent{
    public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
        Graphics2D g2=(Graphics2D) g;
        g2.setPaint(Color.BLACK);
        Rectangle2D rect=new Rectangle2D.Double(50,50,200,200);
        g2.draw(rect);
    }
}
