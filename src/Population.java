import java.awt.*;
import java.util.Arrays;
import java.util.Random;

import javax.swing.*;
public class Population {
    private Individual[] chromosomes;
    private final int SIZE = 100;
    private double mRate;
    public Population() 
    {
    	this.chromosomes = new Individual[SIZE];
    	for (int i = 0; i < SIZE; i++) {
    		this.chromosomes[i] = new Individual();
    	}
    	mRate = .01;
	}
    public int getBestFitness()
    {
    	sort();
    	return chromosomes[0].getFitness();
    }
    public int getAverageFitness()
    {
    	sort();
    	return chromosomes[chromosomes.length/2].getFitness();
    }
    public int getLeastFitness()
    {
    	sort();
    	return chromosomes[chromosomes.length-1].getFitness();
    }
    public Population(long seed) 
    {
    	this.chromosomes = new Individual[SIZE];
    	
		Random r = new Random(seed);

    	for (int i = 0; i < SIZE; i++) {
    		this.chromosomes[i] = new Individual(r);
    	}
    	mRate = .01;
	}
    public Population(double mRate) 
    {
    	this.chromosomes = new Individual[SIZE];
    	for (int i = 0; i < SIZE; i++) {
    		this.chromosomes[i] = new Individual();
    	}
    	this.mRate = mRate;
	}

    public Population(long seed, double mRate) 
    {
    	this.chromosomes = new Individual[SIZE];
    	
		Random r = new Random(seed);

    	for (int i = 0; i < SIZE; i++) {
    		this.chromosomes[i] = new Individual(r);
    	}
    	this.mRate = mRate;
	}




    public void drawOn(Graphics2D g)
    {
    	for(int i = 0; i < 10; i++)
    	{
    		for(int c = 0; c < 10; c++)
    		{
    			chromosomes[i*10+c].drawIndividual(g, 50 + c*52, 50 + i*52);
    		}
    	}
    }
    
    /*
     * Evolutionary Loop
	 * Generates a random population (#1) and then sort the population by fitness (#2)
	 * 
	 * Sort (use Java’s built-in Sort)
	 * Testing: Print out the sorted collection of chromosomes, 
	 *      verify they have the correct order!
	 *      
	 * Parent (truncation) selection: Write code in which each generation: “kills” 
	 * 		the bottom 50% of the population, then using the (50%) survivors creates new 
	 * 		mutant chromosomes (two of each) to produce the next generation (all new 
	 * 		chromosomes). Be careful to create NEW chromosomes.
	 * Testing: Print out the population before and after truncation and after creating the
	 * next generation. BE SURE you are NOT making shallow copies! Ask if you are confused!
	 * Terminate after a predetermined number of generations
     */
	

    
    public void truncate()
    {
    	this.sort();
    	for (int i = 0; i < SIZE/2; i++) {
    		Individual cur = this.chromosomes[i];
//    		System.out.println(chromosomes[i]);
//    		System.out.println(cur.getBinString())
    	    this.chromosomes[i] = new Individual(100,cur.getBinString());
    	    this.chromosomes[i+SIZE/2] = new Individual(100,cur.getBinString());
    	    chromosomes[i].mutate(mRate);
    	    chromosomes[i+SIZE/2].mutate(mRate);
    	}
    	this.sort();
    }
    public void sort() 
    {
//    	System.out.println(this.chromosomes);
    	Arrays.sort(this.chromosomes);
//    	System.out.println(this.chromosomes);
    }
    
    // individual1.getFitness()
    public String toString()
    {
    	String s = "";
//    	int cnt = 0;
//    	for(int r = 0; r < 100; r+=(chromosomes.length==100?10:4))
//    	{
//    		s+="[ ";
//	    	for(int i = r+0; i < chromosomes.length; i++)
//	    	{
//	    		s+="(" + cnt + ", ";
//	    		s += chromosomes[i] + ") ";
//	    		cnt++;
//	    	}
//	    	s+="]\n";
//    	}
    	
    	int cnt = 0;
	    	for(Individual i : chromosomes)
	    	{
	    		s+=i + ", ";
	    		cnt++;
	    	}
	    	s+="\n";
    	System.out.println("(cnt: " + cnt + ")");
    	
    	return s;
    }
    public void setMutationRate(double mRate)
    {
    	this.mRate=mRate;
    }
}
