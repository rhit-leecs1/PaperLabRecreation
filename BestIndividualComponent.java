import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * Class: BestIndividualComponent
 * @author kangd2 & leecs1
 * <br>Purpose: Used to store and paint the best performing individual
 * <br>Restrictions: Must pass in an Individual object
 * <br>For example: 
 * <pre>
 *    BestIndividualComponent bestIndividualComponent = new BestIndividualComponent(bestIndividual);
 * </pre>
 */
public class BestIndividualComponent extends JComponent{
	
    private Individual best;
    
    /**
     * ensures: initializes the best individual to best
     * @param best used to initialize the best individual object
     * <br>requires: best &ne; null
     */
    public BestIndividualComponent(Individual best)
    {
    	this.best = best;
    } // BestIndividualComponent
    
    /**
     * ensures: overrides JComponent's paintComponent method to draw the best individual
     */
    @Override
    public void paintComponent(Graphics g)
    {
    	Graphics2D g2d = (Graphics2D)(g);
    	best.drawOn(g2d);
    } // paintComponent
    
    /**
     * ensures: that the best individual is updated to best
     * @param best used to update the best individual
     * <br>requires: best &ne; null
     */
    public void updateBest(Individual best)
    {
    	this.best = best;
    } // updateBest
} // BestIndividualComponent
