import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.*;
public class Individual implements Comparable {
    private Gene[] genes;
    public Individual()
    {
    	genes = new Gene[100];
		for(int c = 0; c < genes.length; c++)
		{
			genes[c] = new Gene(c);
		}
    }
    public Individual(Random r) {
    	genes = new Gene[100];
		for(int c = 0; c < genes.length; c++)
		{
			genes[c] = new Gene(c, r);
		}
	}
    
    public Individual(int size)
    {
    	if(size == 20)
    		genes = new Gene[20];
    	else if(size == 100)
    		genes = new Gene[100];
    	else
    		System.err.println("Error: Invalid Chromosome Size");
		for(int c = 0; c < genes.length; c++)
		{
			genes[c] = new Gene(c);
		}
    	
    }
    public Individual(int size, String geneStr)
    {
    	if(size == 20)
    		genes = new Gene[20];
    	else if(size == 100)
    		genes = new Gene[100];
		for(int c = 0; c < genes.length; c++)
		{
//    		System.out.println(r + ","+c + ":"+geneStr.charAt(r*(genes.length-1) + c));
			genes[c] = new Gene(c, geneStr.charAt(c)=='0'?false:true);
		}
    	
    }
	public void drawOn(JPanel genePanel)
    {
		for(int c = 0; c < genes.length; c++)
		{
			genePanel.add(genes[c]);
		}
    }
    public void mutate(double mRate)
    {
		for(Gene g : genes)
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
    			((Gene)e.getSource()).flipBit();
    		}
    	}
		for(Gene g : genes)
		{
			g.addActionListener(new GeneButtonListener());
    		}
    }
    public String getBinString()
    {
    	String s = "";
		for(Gene g : genes)
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
    	for(Gene g : genes)
    	{
    		sum+=g.getBin();
    	}
    	return sum;
    }
	@Override
	public int compareTo(Object o) {
		return ((Individual)o).etFitness() - this.getFitness();
	}
    
    
}
