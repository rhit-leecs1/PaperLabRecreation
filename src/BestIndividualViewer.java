import java.awt.*;
import javax.swing.*;
public class BestIndividualViewer {
	private Individual best;
	private BestIndividualComponent bic;
	public BestIndividualViewer(Individual b)
	{
		best = b;
	}
    public void runBestIndividualViewer()
    {
    	//initializing frame
    	JFrame frame = new JFrame();
    	Dimension d = new Dimension(300,300);
    	frame.setSize(d);
    	frame.setTitle("Best Individual Viewer");
    	
    	bic = new BestIndividualComponent(best);
    	frame.add(bic);
    	
    	
    	// set frame visible and closing operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
    }
    public void updateBest(Individual best)
    {
    	this.best = best;
    	bic.updateBest(best);
    	bic.repaint();
    }
    
}
