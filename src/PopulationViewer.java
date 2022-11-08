import java.awt.*;
import javax.swing.*;
public class PopulationViewer {
	private Population p;
	private int tick;
	private PopulationComponent pc;
	public PopulationViewer(Population p)
	{
		this.tick = 0;
		this.p=p;
	}
    public void runPopulationViewer()
    {
    	//initializing frame
    	JFrame frame = new JFrame();
    	Dimension d = new Dimension(400,400);
    	frame.setSize(d);
    	frame.setTitle("Population Viewer");

    	//drawing population
    	pc = new PopulationComponent(p);
    	frame.add(pc);
    	
    	// set frame visible and closing operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
    	
    }
    public void updatePop(Population p)
    {
    	this.p = p;
    	pc.updatePop(p);
    	pc.repaint();
    }
    
}
