import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.Timer;


import java.util.*;
public class SimulationMain {
    public static void main(String[] args) {
//    	System.out.println(Math.round(Math.random()));
//        Individual test1 = new Individual();
//        System.out.println(test1);
        ChromosomeViewer cv = new ChromosomeViewer();
        cv.runChromosomeViewer();
        EvolutionViewer ev = new EvolutionViewer();
        ev.runEvolutionViewer();
        
        Population p = new Population(1247892374L);
        Population p2 = new Population(1247892374L);
        p.sort();
        p2.sort();
        System.out.println(p);
        System.out.println(p2);
        
    }
 


}
