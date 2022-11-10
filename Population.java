import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.*;
public class Population {
    private Individual[] chromosomes;
    private final int SIZE = 100;
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
    		this.chromosomes[i] = new Individual();
    	}
    	mRate = .01;
    	crossoverBool=false;
    	elitismNum = 0;
	}
    public Population(long seed)
    {
    	fitnessType = "basic";
    	targetChromosome = "";
    	this.chromosomes = new Individual[SIZE];
    	
		Random r = new Random(seed);

    	for (int i = 0; i < SIZE; i++) {
    		this.chromosomes[i] = new Individual(r);
    	}
    	crossoverBool=false;
    	mRate = .01;
    	elitismNum = 0;
	}
    public Population(double mRate)
    {
    	fitnessType = "basic";
    	targetChromosome = "";
    	this.chromosomes = new Individual[SIZE];
    	for (int i = 0; i < SIZE; i++) {
    		this.chromosomes[i] = new Individual();
    	}
    	this.mRate = mRate;
    	crossoverBool=false;
    	elitismNum = 0;
	}

    public Population(long seed, double mRate) 
    {
    	fitnessType = "basic";
    	targetChromosome = "";
    	this.chromosomes = new Individual[SIZE];
    	
		Random r = new Random(seed);

    	for (int i = 0; i < SIZE; i++) {
    		this.chromosomes[i] = new Individual(r);
    	}
    	this.mRate = mRate;
    	crossoverBool=false;
    	elitismNum = 0;
	}
    public void drawOn(Graphics2D g)
    {
    	for(int i = 0; i < 10; i++)
    	{
    		for(int c = 0; c < 10; c++)
    		{
    			chromosomes[i*10+c].drawIndividual(g, 5 + c*31, 5 + i*31);
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
	
    public int getBestFitness()
    {
    	sort();
    	return chromosomes[0].getFitness();
    }
    public Individual getBestIndividual()
    {
    	sort();
    	return chromosomes[0];
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
    public void setFitnessType(String str)
    {
    	this.fitnessType=str;
    	for(Individual v : chromosomes)
    	{
    		v.setFitnessType(str);
    	}
    }
    public void setTargetChromosome(String str)
    {
    	this.targetChromosome = str;
    	for(Individual v : chromosomes)
    	{
    		v.setTargetChromosome(str);
    	}
    }
    public void rankSelection()
    {
    	double weightTotal = 0.0;
    	for(int i = 0; i < SIZE; i++)
    	{
    		weightTotal+=(100-i);
    	}
    	ArrayList<Individual> added = new ArrayList<>();
    	outer: while(added.size() < SIZE-elitismNum)
    	{
    		for(int i = 0; i < SIZE; i++)
    		{
    			if(added.size() == SIZE-elitismNum) break outer;
    			double proportion = (100-i)/weightTotal;
    			if(Math.random() <= proportion)
    				added.add(new Individual(100,chromosomes[i].getBinString(),fitnessType, targetChromosome));
    		}
    	}
    	int i = elitismNum;
    	for(Individual v : added)
    	{
			chromosomes[i] = v;
			chromosomes[i].mutate(mRate);    			
    		i++;
    	}
    }
    public void rouletteWheelSelection()
    {
    	sort();
    	double totalFitness = 0.0;
    	for(Individual v : chromosomes)
    	{
    		totalFitness+=v.getFitness();
    	}
    	ArrayList<Individual> added = new ArrayList<>();
    	outer: while(added.size() < SIZE-elitismNum)
    	{
    		for(int i = 0; i < SIZE; i++)
    		{
    			if(added.size() == SIZE-elitismNum) break outer;
    			if(Math.random() <= chromosomes[i].getFitness()/totalFitness)
    				added.add(new Individual(100,chromosomes[i].getBinString(), fitnessType, targetChromosome));
    		}
    	}
    	int i = elitismNum;
    	for(Individual v : added)
    	{
    		chromosomes[i] = v;
			chromosomes[i].mutate(mRate);   
    		i++;
    	}
    }
    public void truncate()
    {
    	this.sort();
    	for (int i = elitismNum; i < SIZE/2; i++) {
    		Individual cur = this.chromosomes[i];
    	    this.chromosomes[i] = new Individual(100,cur.getBinString(), fitnessType, targetChromosome);
    	    this.chromosomes[i+SIZE/2] = new Individual(100,cur.getBinString(), fitnessType, targetChromosome);
    	    if(crossoverBool)
    	    {
    	    	doCrossover();
    	    }
    	    chromosomes[i].mutate(mRate);
    	    chromosomes[i+SIZE/2].mutate(mRate);
    	}
    	this.sort();
    }
    
    public Individual[] doOneCrossover(Individual one, Individual two)
    {
    	Individual newOne = new Individual(100, "" + one.getBinString().substring(0,50) + two.getBinString().substring(50), fitnessType, targetChromosome);
    	Individual newTwo = new Individual(100, "" + two.getBinString().substring(0,50) + one.getBinString().substring(50), fitnessType, targetChromosome);
    	Individual[] offspring = {one, two};
    	return offspring;
    }
    public void doCrossover()
    {
    	for(int i = elitismNum; i < SIZE; i+=2)
    	{
    		Individual[] offspring = doOneCrossover(chromosomes[i], chromosomes[i+1]);
			chromosomes[i] = offspring[0];
			chromosomes[i+1] = offspring[1];
    	}
    }
    public void setElitismNum(int elitismNum)
    {
    	this.elitismNum=elitismNum;
    }
    public void sort()
    {
    	Arrays.sort(this.chromosomes);
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
    public void setMutationRate(double mRate)
    {
    	this.mRate=mRate;
    }
    public void setCrossoverBool(boolean b)
    {
    	crossoverBool=b;
    }
}
