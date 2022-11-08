import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class BestIndividualComponent extends JComponent{
    private Individual best;
    public BestIndividualComponent(Individual best)
    {
    	this.best = best;
    }
    @Override
    public void paintComponent(Graphics g)
    {
//    	System.out.println("run");
    	Graphics2D g2d = (Graphics2D)(g);
    	best.drawOn(g2d);
    }
    //update each time timer goes
    public void updateBest(Individual best)
    {
    	this.best = best;
    }
}
