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
        ChromosomeViewer cv = new ChromosomeViewer();
        cv.runChromosomeViewer();
        EvolutionViewer ev = new EvolutionViewer();
        ev.runEvolutionViewer();
    } // main
} // SimulationMain
