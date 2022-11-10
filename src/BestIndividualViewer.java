import java.awt.*;
import javax.swing.*;
/**
 * Class: BestIndividualViewer
 * @author kangd2 & leecs1
 * <br>Purpose: Used to create a frame to draw a BestIndividualComponent
 * <br>For example: 
 * <pre>
 *    BestIndividualViewer bestIndividualViewer = new BestIndividualViewer(best);
 * </pre>
 */
public class BestIndividualViewer {

	private static final int FRAME_WIDTH = 300;
	private static final int FRAME_HEIGHT = 300;
	private Individual best;
	private BestIndividualComponent bic;
	private JFrame frame;
	
	/**
     * ensures: initializes the best individual to best
     * @param best used to initialize the best individual object
     * <br>requires: best &ne; null
     */
	public BestIndividualViewer(Individual best)
	{
		this.best = best;
	} // BestIndividualViewer
	
	/**
	 * ensures: initializes the frame and adds a new BestIndividualComponent to the frame
	 */
    public void runBestIndividualViewer()
    {
    	// initializes frame
    	frame = new JFrame();
    	Dimension d = new Dimension(FRAME_WIDTH,FRAME_HEIGHT);
    	frame.setSize(d);
    	frame.setTitle("Best Individual Viewer");
    	
    	// initializes and adds BestIndividualComponent to frame
    	bic = new BestIndividualComponent(best);
    	frame.add(bic);
    	
    	// sets frame visibility and closing operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
    } // runBestIndividualViewer

    /**
     * ensures: that the best individual is updated to best and updates the BestIndividualComponent
     * @param best used to update the best individual
     * <br>requires: best &ne; null
     */
    public void updateBest(Individual best)
    {
    	this.best = best;
    	bic.updateBest(best);
    	bic.repaint();
    } // updateBest
    
    public void remove()
    {
    	frame.setVisible(false);
    	frame.dispose();
    }
} // BestIndividualViewer
