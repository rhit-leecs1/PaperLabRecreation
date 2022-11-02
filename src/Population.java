import java.awt.*;
import java.util.Arrays;
import java.util.Random;

import javax.swing.*;
public class Population {
    private Individual[] chromosomes;
    private final int SIZE = 100;

    public Population() 
    {
    	this.chromosomes = new Individual[SIZE];
    	for (int i = 0; i < SIZE; i++) {
    		this.chromosomes[i] = new Individual();
    	}
	}

    public Population(long seed) 
    {
    	this.chromosomes = new Individual[SIZE];
    	
		Random r = new Random(seed);

    	for (int i = 0; i < SIZE; i++) {
    		this.chromosomes[i] = new Individual(r);
    	}
	}




	public void drawOn(Graphics2D g)
    {
    	
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
	

    
    public void truncate(double mutationRate)
    {
    	this.sort();
    	for (int i = 0; i < SIZE/2; i++) {
    		Individual cur = this.chromosomes[i];
    	    this.chromosomes[i] = cur.mutateIndividual(mutationRate);
    	    this.chromosomes[i+(SIZE/2)] = cur.mutateIndividual(mutationRate);
    	}
    }
    public void sort() 
    {
//    	System.out.println(this.chromosomes);
    	Arrays.sort(this.chromosomes);
//    	System.out.println(this.chromosomes);
    }
    
    // individual1.getFitness()
}
