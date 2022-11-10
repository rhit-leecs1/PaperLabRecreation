import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

/**
 * Class: SimulationMain
 * @author kangd2 & leecs1
 * <br>Purpose: Used to start the simulation by running the ChromosomeViewer and EvolutionViewer
 */
public class SimulationMain {

	/**
	 * ensures: initializes and runs the chromosome and evolution viewers
	 */
    public static void main(String[] args) {
        
        EvolutionViewer ev = new EvolutionViewer();
        ev.runEvolutionViewer();
//            	
//    	Individual newI = new Individual(20,"111?11?1111?1111?111");
//    	System.out.println(newI.getFitness());
//    	
    	
    } // main
} // SimulationMain
