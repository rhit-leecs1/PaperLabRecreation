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
    private double fitness;
    public Individual()
    {
    	fitnessType = "basic";
    	genes = new GeneButton[20];
		for(int c = 0; c < genes.length; c++)
		{
			genes[c] = new GeneButton(c);
		}
    	fitness = -1;
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

    	fitness = -1;
    }
    public Individual(int size, String geneStr)
    {
    	fitnessType = "basic";
		genes = new GeneButton[20];
		for(int c = 0; c < genes.length; c++)
		{
//    		System.out.println(r + ","+c + ":"+geneStr.charAt(r*(genes.length-1) + c));
			genes[c] = new GeneButton(c, geneStr.charAt(c));
		}

    	fitness = -1;
    }
    public Individual(int size, String geneStr, String fitnessType, String targetChromosome)
    {
    	this.targetChromosome = targetChromosome;
    	this.fitnessType = fitnessType;
		genes = new GeneButton[20];
		for(int c = 0; c < genes.length; c++)
		{
//    		System.out.println(r + ","+c + ":"+geneStr.charAt(r*(genes.length-1) + c));
			genes[c] = new GeneButton(c, geneStr.charAt(c));
		}

    	fitness = -1;
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
		for(int i = 0; i < 5; i++)
		{
			for(int c = 0; c < 4; c++)
			{
				GeneButton g = genes[i*4 + c];
				g2d.setColor(g.getColor());
//				g2d.drawRect(x+5*c, y+5*i, 5, 5);
				g2d.fillRect(2+20*c, 2+20*i, 20, 20);
				g2d.setColor(g.getColor().equals(Color.black)?Color.WHITE:Color.black);
				g2d.setFont(new Font("Times New Roman", Font.BOLD, 10));
				g2d.drawString("" + (i*5 + c), 5+20*c, 12+20*i);
			}
		}
	}
	public void drawIndividual(Graphics2D g2d, int x, int y)
	{
		for(int i = 0; i < 5; i++)
		{
			for(int c = 0; c < 4; c++)
			{
				GeneButton g = genes[i*4 + c];
				g2d.setColor(g.getColor());
//				g2d.drawRect(x+5*c, y+5*i, 5, 5);
				g2d.fillRect(x+3*c, y+3*i, 3, 3);
			}
		}
		
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
    public double calculateFitness()
    {
    	double fit = 1+( 19*getN(getBinString())/1000.0);
    	fitness = fit;
    	return 1+( 19*getN(getBinString())/1000.0);
    }
    private int getN(String geneStr)
    {
//    	System.out.println(geneStr + "," + geneStr.length());
    	int n = 1000;
    	outer: for(int days = 0; days < 1000; days++)
    	{
    		char[] phenotype = geneStr.toCharArray();
    		for(int i = 0; i < phenotype.length; i++)
    		{
    			if(phenotype[i]=='0')
    			{
//    				System.out.println("found zero");
    				return 0;
    			}
    			if(phenotype[i]=='?')
    			{
    				char bin = Math.random()<=.5?'0':'1';
    				if(bin == '0') continue outer;
    				phenotype[i]=bin;
    			}
    		}
//    		System.out.println("" + (days));
    		//locking in switches if good net
//    		System.out.println(Arrays.toString(phenotype));
    		for(int i = 0; i < 20; i++)
    		{
    			genes[i] = new GeneButton(i,phenotype[i]);
    		}
    		return n-days;
    	}
    	return n;
    }
	@Override
	public int compareTo(Object o) {
		if (((Individual)o).getFitness() < this.fitness)
			return -1;
		else if(((Individual)o).getFitness() > this.fitness)
			return 1;
		return 0;
	}

	public double getFitness() {
		if(fitness >= 0)
			return this.fitness;
		return calculateFitness();
	}

	public int countAll(char c) {
		int sum = 0;
		for (GeneButton gb: genes) {
			sum += gb.countAll(c);
		}
		return sum;
	}
    
    
}
