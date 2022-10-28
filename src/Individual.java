import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;
public class Individual {
    private Gene[][] genes;
    public Individual()
    {
    	genes = new Gene[10][10];
    	for(int r = 0; r < genes.length; r++)
    	{
    		for(int c = 0; c < genes[0].length; c++)
    		{
    			genes[r][c] = new Gene(r*10+c);
    		}
    	}
    }
    public Individual(int size)
    {
    	if(size == 20)
    		genes = new Gene[5][4];
    	else if(size == 100)
    		genes = new Gene[10][10];
    	else
    		System.err.println("Error: Invalid Chromosome Size");
    	for(int r = 0; r < genes.length; r++)
    	{
    		for(int c = 0; c < genes[0].length; c++)
    		{
    			genes[r][c] = new Gene(r*10+c);
    		}
    	}
    }
    public Individual(int size, String geneStr)
    {
    	if(size == 20)
    		genes = new Gene[5][4];
    	else if(size == 100)
    		genes = new Gene[10][10];
    	
    	for(int r = 0; r < genes.length; r++)
    	{
    		for(int c = 0; c < genes[0].length; c++)
    		{
//    			System.out.println(r + ","+c + ":"+geneStr.charAt(r*(genes.length-1) + c));
    			genes[r][c] = new Gene(r*10+c, geneStr.charAt(r*(genes.length-1) + c)=='0'?false:true);
    		}
    	}
    }
    public void drawOn(JPanel genePanel)
    {
    	
    	for(int r = 0; r < genes.length; r++)
    	{
    		for(int c = 0; c < genes[0].length; c++)
    		{
    			genePanel.add(genes[r][c]);
    		}
    	}
    	
    }
    public void mutate(double mRate)
    {
    	for(Gene[] row : genes)
    	{
    		for(Gene g : row)
    		{
    			if(Math.random()*100 <= (mRate*100))
    				g.flipBit();
    		}
    	}
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
    	for(Gene[] row : genes)
    	{
    		for(Gene g : row)
    		{
    			g.addActionListener(new GeneButtonListener());
    		}
    	}
    }
    public String getBinString()
    {
    	String s = "";
    	for(Gene[] row : genes)
    	{
    		for(Gene g : row)
    		{
    			s+=g.getBin();
    		}
    	}
    	return s;
    }
    public String toString()
    {
    	String s = "";
    	for(Gene[] v : genes)
    	{
    		s += Arrays.toString(v) + "\n";
    	}
    	return s;
    }
    
    
}
