import java.awt.*;
import javax.swing.*;
public class ChromosomeComponent extends JComponent{
	private int size;
    public ChromosomeComponent(int size)
    {
    	super();
    	this.size = size;
    	
    }
    public ChromosomeComponent()
    {
    	super();
    	this.size = 1;
    	
    }
    public void paintComponent(Graphics g)
    {
    	Graphics2D g2d = (Graphics2D)g;
    	JPanel genePanel = new JPanel();
//    	JPanel genePanelFit = new JPanel();
//    	genePanelFit.setLayout(new BorderLayout());
    	Individual chromosome = new Individual();
    	if(size == 20)
    	{
    		genePanel.setLayout(new GridLayout(5,4));
    		chromosome = new Individual(20);
    	}
    	else if(size == 100)
    	{
    		genePanel.setLayout(new GridLayout(10,10));
    		chromosome = new Individual(100);
    	}
    	chromosome.drawOn(genePanel);
    	
    }
//    class GeneButton extends JButton {
//        
//		private int bin;
//        private Color color;
//        private int num;
//        public GeneButton(int num)
//        {
//        	super();
//        	this.num = num;
//        	bin = (int)Math.round(Math.random());
//        	if(bin == '0')
//        	{
//        		color = Color.black;
//        		this.setBackground(color);
//        		this.setForeground(Color.white);
//        	}
//        	if(bin == '1')
//        	{
//        		color = Color.green;
//        		this.setBackground(color);
//        		this.setForeground(Color.black);
//        	}
//        }
//        public String toString()
//        {
//        	return "Gene #" + num + ", " + bin;
//        }
//    }

}
