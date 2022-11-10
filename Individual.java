import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.*;
public class Individual implements Comparable {
    private GeneButton[] genes;
    private String fitnessType;
    private String targetChromosome;
    public Individual()
    {
    	fitnessType = "basic";
    	genes = new GeneButton[100];
		for(int c = 0; c < genes.length; c++)
		{
			genes[c] = new GeneButton(c);
		}
    }
    public Individual(Random r) {
    	fitnessType = "basic";
    	genes = new GeneButton[100];
		for(int c = 0; c < genes.length; c++)
		{
			genes[c] = new GeneButton(c, r);
			
		}
	}
    
    public Individual(int size)
    {
    	fitnessType = "basic";
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
    	fitnessType = "basic";
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
    public Individual(int size, String geneStr, String fitnessType, String targetChromosome)
    {
    	this.targetChromosome = targetChromosome;
    	this.fitnessType = fitnessType;
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
	public void drawOn(Graphics2D g2d)
	{
		for(int i = 0; i < 10; i++)
		{
			for(int c = 0; c < 10; c++)
			{
				GeneButton g = genes[i*10 + c];
				g2d.setColor(g.getColor());
//				g2d.drawRect(x+5*c, y+5*i, 5, 5);
				g2d.fillRect(2+20*c, 2+20*i, 20, 20);
				g2d.setColor(g.getColor().equals(Color.green)?Color.black:Color.WHITE);
				g2d.setFont(new Font("Times New Roman", Font.BOLD, 10));
				g2d.drawString("" + (i*10 + c), 5+20*c, 12+20*i);
			}
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
				g2d.fillRect(x+3*c, y+3*i, 3, 3);
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
    	Individual newIndividual = new Individual(100, geneStr, fitnessType, targetChromosome);
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
    public void setFitnessType(String str)
    {
    	fitnessType = str;
    }
    public String getFitnessType()
    {
    	return fitnessType;
    }
    public void setTargetChromosome(String str)
    {
    	targetChromosome = str;
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
    	if(fitnessType.equals("target"))
    	{
    		int cnt = 0;
        	for(int i = 0; i < genes.length; i++)
        	{
        		if(getBinString().charAt(i) == targetChromosome.charAt(i))
        			cnt++;
        	}
        	return cnt;
    	}
    	else if(fitnessType.equals("checkered"))
    	{
    		String pattern = "0101010101101010101001010101011010101010010101010110101010100101010101101010101001010101011010101010";
    		int cnt = 0;
        	for(int i = 0; i < genes.length; i++)
        	{
        		if(getBinString().charAt(i) == pattern.charAt(i))
        			cnt++;
        	}
        	return cnt;
    	}
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
