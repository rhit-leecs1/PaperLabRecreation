import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.*;
/**
 * Class: EvolutionComponent
 * @author kangd2 & leecs1
 * <br>Purpose: Used to graph the trends (best, average, worst, diversity) of a population as it evolves
 * <br>For example: 
 * <pre>
 *    EvolutionComponent evolutionComponent = new EvolutionComponent(population);
 * </pre>
 */
public class EvolutionComponent extends JComponent{
	
	private final static int ORIGIN_X = 200;
	private final static int ORIGIN_Y = 500;

	private final static int X_AXIS_WIDTH = 1000;
	private final static int Y_AXIS_HEIGHT = 400;
	private final static int AXES_HASHMARK_LENGTH = 10;
	private final static int DELTA_Y = Y_AXIS_HEIGHT / 100;

	private static final int KEY_X = 1250;
	private static final int KEY_Y = 400;
	private static final int KEY_TEXT_BUFFER = 20;
	private static final int KEY_COLOR_BUFFER = 10;
	
	private final static Color BEST_COLOR = Color.BLUE;
	private final static Color AVG_COLOR = Color.GREEN;
	private final static Color WORST_COLOR = Color.RED;
	private final static Color DIVERSITY_COLOR = Color.ORANGE;
	
	private final static BasicStroke LINE_STROKE = new BasicStroke(2);
	private final static BasicStroke AXES_STROKE = new BasicStroke(1);
	
	private final static int DEFAULT_GENERATIONS = 100;

	private Population population;
	private int generations;
	private int curGenerationsLeft;
	public int numTicks;
	private boolean newLinesDrawn;
	private int deltaX;

	private ArrayList<Line2D> bestLines;
	private ArrayList<Line2D> avgLines;
	private ArrayList<Line2D> worstLines;
	private ArrayList<Line2D> diversityLines;
	private HashSet<Line2D> startLines;
	
	/**
	 * ensures: stores given population and initializes the graph
	 * @param population
	 * <br>requires: population &ne; null
	 */
	public EvolutionComponent(Population population) {
		startLines = new HashSet<>();
		this.population = population;
		this.generations = DEFAULT_GENERATIONS;
		this.curGenerationsLeft = DEFAULT_GENERATIONS;
		this.newLinesDrawn = false;
		this.deltaX = X_AXIS_WIDTH / generations;
		this.bestLines = new ArrayList<Line2D>();
		this.avgLines = new ArrayList<Line2D>();
		this.worstLines = new ArrayList<Line2D>();
		this.diversityLines = new ArrayList<Line2D>();
		initializeGraph();
	} // EvolutionComponent

	/**
	 * ensures: stores given population and generations and initializes the graph
	 * @param population
	 * <br>requires: population &ne; null
	 * @param generations
	 * <br>requires: generations > 0
	 */
	public EvolutionComponent(Population population, int generations) {
		this(population);
		this.generations = generations;
		this.curGenerationsLeft = generations;
		this.deltaX = X_AXIS_WIDTH / generations;
	} // EvolutionComponent
	
	/**
	 * ensures: initializes the graph's lines (best, average, worst, diversity) and adds its first plot
	 */
	private void initializeGraph() {
		Line2D bestLine = new Line2D.Double(ORIGIN_X, ORIGIN_Y - population.getBestFitness() * DELTA_Y, ORIGIN_X, ORIGIN_Y - population.getBestFitness() * DELTA_Y);
		Line2D avgLine = new Line2D.Double(ORIGIN_X, ORIGIN_Y - population.getAverageFitness() * DELTA_Y, ORIGIN_X, ORIGIN_Y - population.getAverageFitness() * DELTA_Y);
		Line2D worstLine = new Line2D.Double(ORIGIN_X, ORIGIN_Y - population.getLeastFitness() * DELTA_Y, ORIGIN_X, ORIGIN_Y - population.getLeastFitness() * DELTA_Y);
		Line2D diversityLine = new Line2D.Double(ORIGIN_X, ORIGIN_Y - population.getAverageHammingDistance() * DELTA_Y, ORIGIN_X, ORIGIN_Y - population.getAverageHammingDistance() * DELTA_Y);
		
		bestLines.add(bestLine);
		avgLines.add(avgLine);
		worstLines.add(worstLine);
		diversityLines.add(diversityLine);
		startLines.add(bestLine);
		startLines.add(avgLine);
		startLines.add(worstLine);
		startLines.add(diversityLine);
	} // initializeGraph
	
	/**
	 * ensures: updates and draws the graph
	 */
	@Override
	protected void paintComponent(Graphics g) {
//		System.out.println("paintComponent called, bestLines.size() == "+bestLines.size());
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		updateAllLines();
		drawAll(g2);
	} // paintComponent
	
	/**
	 * ensures: draws the axes, key, and lines of the graph
	 * @param g2
	 */
	public void drawAll(Graphics2D g2) {
		drawAxes(g2);
		drawKey(g2);
		if(generations>curGenerationsLeft)
			drawLines(g2);		
	} // drawAll
	
	/**
	 * ensures: the lists of lines are updated when a new generation is produced (new lines are not drawn)
	 */
	public void updateAllLines() {
		if (!newLinesDrawn) {
			updateLines(bestLines);
			updateLines(avgLines);
			updateLines(worstLines);
			updateLines(diversityLines);
			this.newLinesDrawn = true;
		}
	} // updateAllLines
	
	/**
	 * ensures: clears the existing lists of lines (best, average, worst, diversity) and initializes the graph
	 */
	public void resetLines() {
		bestLines.clear();
		avgLines.clear();
		worstLines.clear();
		diversityLines.clear();
		initializeGraph();
	} // resetLines
	
	/**
	 * ensures: adds a new plot line to lines based on the updated population data
	 * @param lines
	 * <br>requires: lines.size() > 0
	 */
	private void updateLines(ArrayList<Line2D> lines) {
		Line2D prevLine = lines.get(lines.size() - 1);
//		System.out.println("generations: "+(generations)+", curGenerationsLeft: "+curGenerationsLeft+", lines.size(): "+lines.size());
		
		double x1 = ORIGIN_X + ((generations-curGenerationsLeft) * deltaX);
		double y1 = prevLine.getY2();
		double x2 = ORIGIN_X + ((generations-curGenerationsLeft+1) * deltaX);
		double y2;
		
		if (lines.equals(bestLines)) {
//			System.out.println("best: "+this.population.getBestFitness());
			y2 = ORIGIN_Y - (this.population.getBestFitness() * DELTA_Y);
			
		} else if (lines.equals(avgLines)) {
//			System.out.println("avg: "+this.population.getAverageFitness());
			y2 = ORIGIN_Y - this.population.getAverageFitness() * DELTA_Y;
		} else if (lines.equals(worstLines)) {
//			System.out.println("worst: "+this.population.getLeastFitness());
			y2 = ORIGIN_Y - (this.population.getLeastFitness() * DELTA_Y);
		} else {
//			System.out.println("diversity: "+this.population.getAverageHammingDistance());
			y2 = ORIGIN_Y - (this.population.getAverageHammingDistance() * DELTA_Y);
		}
		Line2D newLine = new Line2D.Double(x1, y1, x2, y2);
		if(generations>curGenerationsLeft)
		{
			newLine = new Line2D.Double(ORIGIN_X + ((generations-curGenerationsLeft-1) * deltaX), y1, ORIGIN_X + ((generations-curGenerationsLeft) * deltaX), y2);
		}
		else
		{
			startLines.add(newLine);
		}
		lines.add(newLine);
	} // updateLines
	
	/**
	 * ensures: draws the key (legend) of the graph
	 * @param g2
	 */
	private void drawKey(Graphics2D g2) {
        g2.drawString("Best Line", KEY_X, KEY_Y);
		g2.drawString("Worst Line", KEY_X, KEY_Y + KEY_TEXT_BUFFER);
		g2.drawString("Average Line", KEY_X, KEY_Y + 2*KEY_TEXT_BUFFER);
		g2.drawString("Diversity", KEY_X, KEY_Y + 3*KEY_TEXT_BUFFER);

		Rectangle best = new Rectangle(KEY_X - KEY_COLOR_BUFFER, KEY_Y - KEY_COLOR_BUFFER, KEY_COLOR_BUFFER, KEY_COLOR_BUFFER);
		g2.setColor(BEST_COLOR);
		g2.fill(best);
		Rectangle least = new Rectangle(KEY_X - KEY_COLOR_BUFFER, KEY_Y + KEY_TEXT_BUFFER - KEY_COLOR_BUFFER, KEY_COLOR_BUFFER, KEY_COLOR_BUFFER);
		g2.setColor(WORST_COLOR);
		g2.fill(least);
		Rectangle average = new Rectangle(KEY_X - KEY_COLOR_BUFFER, KEY_Y + 2*KEY_TEXT_BUFFER - KEY_COLOR_BUFFER, KEY_COLOR_BUFFER, KEY_COLOR_BUFFER);
		g2.setColor(AVG_COLOR);
		g2.fill(average);
		Rectangle diversity = new Rectangle(KEY_X - KEY_COLOR_BUFFER, KEY_Y + 3*KEY_TEXT_BUFFER - KEY_COLOR_BUFFER, KEY_COLOR_BUFFER, KEY_COLOR_BUFFER);
		g2.setColor(DIVERSITY_COLOR);
		g2.fill(diversity);
	} // drawKey

	/**
	 * ensures: draws the graph based on data stored in the lists of lines
	 * @param g2
	 */
	public void drawLines(Graphics2D g2) {
		g2.setStroke(LINE_STROKE);
		g2.setColor(BEST_COLOR);
		
		for (Line2D line : this.bestLines) {
			if(!startLines.contains(line))
				g2.draw(line);
		}
		
		g2.setColor(AVG_COLOR);
		for (Line2D line : this.avgLines) {
			if(!startLines.contains(line))
				g2.draw(line);
		}

		g2.setColor(WORST_COLOR);
		for (Line2D line : this.worstLines) {
			if(!startLines.contains(line))
				g2.draw(line);
		}
		
		g2.setColor(DIVERSITY_COLOR);
		for (Line2D line : this.diversityLines) {
			if(!startLines.contains(line))
				g2.draw(line);
		}
	} // drawLines

	/**
	 * ensures: draws the axes of the graph with hashmarks
	 * @param g2
	 */
	public void drawAxes(Graphics2D g2) {
		g2.setStroke(AXES_STROKE);
		g2.setColor(Color.BLACK);
		
		g2.drawLine(ORIGIN_X, ORIGIN_Y, ORIGIN_X, ORIGIN_Y-Y_AXIS_HEIGHT);
		g2.drawLine(ORIGIN_X, ORIGIN_Y, ORIGIN_X + X_AXIS_WIDTH, ORIGIN_Y);
		g2.drawLine(ORIGIN_X+X_AXIS_WIDTH, ORIGIN_Y-AXES_HASHMARK_LENGTH/2, ORIGIN_X+ X_AXIS_WIDTH, ORIGIN_Y+AXES_HASHMARK_LENGTH/2);
		
		for (int i = 10*deltaX; i <= X_AXIS_WIDTH; i+=10*deltaX) {
			g2.drawLine(ORIGIN_X+i, ORIGIN_Y-AXES_HASHMARK_LENGTH/2, ORIGIN_X+i, ORIGIN_Y+AXES_HASHMARK_LENGTH/2);
		}
		for (int i = 10*DELTA_Y; i <= Y_AXIS_HEIGHT; i+=10*DELTA_Y) {
			g2.drawLine(ORIGIN_X-AXES_HASHMARK_LENGTH/2, ORIGIN_Y-i, ORIGIN_X+AXES_HASHMARK_LENGTH/2, ORIGIN_Y-i);
		}
	} // drawAxes
	
	/**
	 * ensures: updates the population to population
	 * @param population
	 * <br>requires: population &ne; null
	 */
	public void updatePop(Population population)
	{
		this.population = population;
	} // updatePop
	
	/**
	 * ensures: updates the number of generations left in the evolution and signals new lines need to be drawn for the new generation
	 * @param generationsLeft
	 */
	public void updateGenerations(int generationsLeft) {
		this.curGenerationsLeft = generationsLeft;
		this.newLinesDrawn = false;
	} // updateGenerations
} // EvolutionComponent
