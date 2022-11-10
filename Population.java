import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.*;
public class Population {
    private Individual[] chromosomes;
    private final int SIZE = 1000;
    private double mRate;
    private boolean crossoverBool;
    private int elitismNum;
    private String fitnessType;
    private String targetChromosome;
    public Population()
    {
    	fitnessType = "basic";
    	targetChromosome = "";
    	this.chromosomes = new Individual[SIZE];
    	for (int i = 0; i < SIZE; i++) {
    		this.chromosomes[i] = new Individual(20);
    	}
    	mRate = 0;
    	crossoverBool=true;
    	elitismNum = 0;
	}

    public void drawOn(Graphics2D g)
    {
    	for(int i = 0; i < 50; i++)
    	{
    		for(int c = 0; c < 20; c++)
    		{
    			chromosomes[i*20+c].drawIndividual(g, 5 + c*31, 5 + i*31);
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
	
    public double getBestFitness()
    {
    	sort();
    	return chromosomes[0].getFitness();
    }
    public Individual getBestIndividual()
    {
    	sort();
    	return chromosomes[0];
    }
    public double getAverageFitness()
    {
    	sort();
    	return chromosomes[chromosomes.length/2].getFitness();
    }
    public double getLeastFitness()
    {
    	sort();
    	return chromosomes[chromosomes.length-1].getFitness();
    }
    public int getHammingDistance(String one, String two)
    {
    	int cnt = 0;
    	for(int i = 0; i < one.length(); i++)
    	{
    		if(one.charAt(i) != two.charAt(i))
    			cnt++;
    	}
    	return cnt;
    }
    public double getAverageHammingDistance()
    {
    	double sum = 0.0;
    	for(Individual v : chromosomes)
    	{
    		for(Individual two : chromosomes)
    		{
    			sum+=getHammingDistance(v.getBinString(),two.getBinString());
    		}
    	}
    	int numPairs = chromosomes.length*(chromosomes.length-1)/2;
    	return sum/numPairs;
    }
    
    
    public Individual doOneCrossover(Individual one, Individual two)
    {
    	int point =  (int)Math.round(Math.random()*19+1);
    	Individual newOne = new Individual(20, "" + one.getBinString().substring(0,point) + two.getBinString().substring(point));
    	return newOne;
    }
    
    public void sort()
    {
    	Arrays.sort(chromosomes);
    }
    
    
    public String toString()
    {
    	String s = "";
    	int cnt = 0;
	    	for(Individual i : chromosomes)
	    	{
	    		s+=i + ", ";
	    		cnt++;
	    	}
	    	s+="\n";
    	System.out.println("Population:");
    	
    	return s;
    }
    
    public void setCrossoverBool(boolean b)
    {
    	crossoverBool=true;
    }

	public void nextGeneration() {
		
		sort();
    	double totalFitness = 0.0;
    	for(Individual v : chromosomes)
    	{
    		totalFitness+=v.getFitness();
    	}
    	ArrayList<Individual> added = new ArrayList<>();
    	outer: while(added.size() < SIZE)
    	{
    		ArrayList<Individual> pair = new ArrayList<>();
    		for(int i = 0; i < SIZE; i++)
    		{
    			if(added.size() == SIZE) break outer;
    			if(pair.size() == 2)
    			{
    				added.add( doOneCrossover( pair.get(0),pair.get(1) ) );
    				continue outer;
    			}
    			if(Math.random() <= chromosomes[i].getFitness()/totalFitness)
    			{
    				pair.add(chromosomes[i]);
    			}
    		}
    	}
    	int i = 0;
    	for(Individual v : added)
    	{
    		chromosomes[i] = v;
    		i++;
    	}
	}

	public int getOnes() {
		return countAll('1');
	}

	public int getZeroes() {
		return countAll('0');
	}

	public int getQuestions() {
		return countAll('?');
	}
	
	private int countAll(char c) {
		int sum = 0;
		for (Individual i: chromosomes) {
			sum += i.countAll(c);
		}
		return sum;
	}
	
}
