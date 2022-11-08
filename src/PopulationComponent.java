import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class PopulationComponent extends JComponent{
    private Population p;
    public PopulationComponent(Population p)
    {
    	this.p = p;
    }
    @Override
    public void paintComponent(Graphics g)
    {
//    	System.out.println("run");
    	Graphics2D g2d = (Graphics2D)(g);
    	p.drawOn(g2d);
    }
    public void updatePop(Population p)
    {
    	this.p = p;
    }
}
