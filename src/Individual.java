import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.*;
public class Individual implements Comparable {
    private GeneButton[] genes;
    public Individual()
    {
    	genes = new GeneButton[100];
		for(int c = 0; c < genes.length; c++)
		{
			genes[c] = new GeneButton(c);
		}
    }
    public Individual(Random r) {
    	genes = new GeneButton[100];
		for(int c = 0; c < genes.length; c++)
		{
			genes[c] = new GeneButton(c, r);
			
		}
	}
    
    public Individual(int size)
    {
    	if(size == 20)
    		genes = new GeneButton[20];
    	else if(size == 100)
    		genes = new GeneButton[100];
    	else
    		System.err.println("Error: Invalid Chromosome Size");
		for(int c = 0; c < genes.length; c++)
		{
			genes[c] = new GeneButton(c);
		}
    	
    }
    public Individual(int size, String geneStr)
    {
    	if(size == 20)
    		genes = new GeneButton[20];
    	else if(size == 100)
    		genes = new GeneButton[100];
		for(int c = 0; c < genes.length; c++)
		{
//    		System.out.println(r + ","+c + ":"+geneStr.charAt(r*(genes.length-1) + c));
			genes[c] = new GeneButton(c, geneStr.charAt(c)=='0'?false:true);
		}
    	
    }
	public void drawOn(JPanel genePanel)
    {
		for(int c = 0; c < genes.length; c++)
		{
			genePanel.add(genes[c]);
		}
    }
	public void drawIndividual(Graphics2D g2d, int x, int y)
	{
		for(int i = 0; i < 10; i++)
		{
			for(int c = 0; c < 10; c++)
			{
				GeneButton g = genes[i*10 + c];
				g2d.setColor(g.getColor());
//				g2d.drawRect(x+5*c, y+5*i, 5, 5);
				g2d.fillRect(x+5*c, y+5*i, 5, 5);
			}
		}
		
	}
    public void mutate(double mRate)
    {
		for(GeneButton g : genes)
		{
			if(Math.random()*100 <= (mRate*100))
				g.flipBit();
			
		}
    }
    public Individual mutateIndividual(double mRate)
    {
    	String geneStr = this.getBinString();
    	Individual newIndividual = new Individual(100, geneStr);
    	newIndividual.mutate(mRate);
    	return newIndividual;
    }
    public void addListenerToGenes()
    {
    	class GeneButtonListener implements ActionListener
    	{
    		public void actionPerformed(ActionEvent e)
    		{
    			((GeneButton)e.getSource()).flipBit();
    		}
    	}
		for(GeneButton g : genes)
		{
			g.addActionListener(new GeneButtonListener());
    		}
    }
    public String getBinString()
    {
    	String s = "";
		for(GeneButton g : genes)
		{
			s+=g.getBin();
		}
    	return s;
    }
    public String toString()
    {
    	String s = "";
//    	for(int r = 0; r < 100; r+=(genes.length==100?10:4))
//    	{
//	    	for(int i = r+0; i < genes.length; i++)
//	    	{
//	    		s += genes[i] + " ";
//	    	}
//	    	s+="\n";
//    	}
    	return s + this.getFitness();
    }
    public int getFitness()
    {
    	int sum = 0;
    	for(GeneButton g : genes)
    	{
    		sum+=g.getBin();
    	}
    	return sum;
    }
	@Override
	public int compareTo(Object o) {
		return ((Individual)o).getFitness() - this.getFitness();
	}
    
    
}
