import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.*;

public class EvolutionComponent extends JComponent{
	private final static int ORIGIN_X = 200;
	private final static int ORIGIN_Y = 500;

	private final static int X_AXIS_WIDTH = 1000;
	private final static int Y_AXIS_HEIGHT = 400;
	private final static int DELTA_Y = Y_AXIS_HEIGHT / 100;
	private int deltaX;
	
	private final static int DEFAULT_GENERATIONS = 100;

	private ArrayList<Line2D> bestLines;
	private ArrayList<Line2D> avgLines;
	private ArrayList<Line2D> worstLines;
	private ArrayList<Line2D> diversityLines;

	private final static Color BEST_COLOR = Color.BLUE;
	private final static Color AVG_COLOR = Color.GREEN;
	private final static Color WORST_COLOR = Color.RED;
	private final static Color DIVERSITY_COLOR = Color.YELLOW;

	private final static BasicStroke LINE_STROKE = new BasicStroke(2);
	private final static BasicStroke AXES_STROKE = new BasicStroke(1);

	private static final int KEY_X = 1250;
	private static final int KEY_Y = 400;
	private static final int KEY_TEXT_BUFFER = 20;
	private static final int KEY_COLOR_BUFFER = 10;
	
	public int numTicks;
	private Population population;
	private int generations;
	private int curGenerationsLeft;
	private boolean newLinesDrawn;
	
	public EvolutionComponent(Population population) {
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
	}
	public EvolutionComponent(Population population, int generations) {
		this(population);
		this.generations = generations;
		this.deltaX = X_AXIS_WIDTH / generations;
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		System.out.println("paintComponent called, bestLines.size() == "+bestLines.size());
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		updateAllLines();
		drawAll(g2);
	}
	public void drawAll(Graphics2D g2) {
		drawAxes(g2);
		drawKey(g2);
		drawLines(g2);
	}
	public void updatePop(Population population)
	{
		this.population = population;
	}
	public Population getPopulation() {
		return this.population;
	}
	

	private void initializeGraph() {
		Line2D bestLine = new Line2D.Double(ORIGIN_X, ORIGIN_Y - population.getBestFitness() * DELTA_Y, ORIGIN_X, ORIGIN_Y - population.getBestFitness() * DELTA_Y);
		Line2D avgLine = new Line2D.Double(ORIGIN_X, ORIGIN_Y - population.getAverageFitness() * DELTA_Y, ORIGIN_X, ORIGIN_Y - population.getAverageFitness() * DELTA_Y);
		Line2D worstLine = new Line2D.Double(ORIGIN_X, ORIGIN_Y - population.getLeastFitness() * DELTA_Y, ORIGIN_X, ORIGIN_Y - population.getLeastFitness() * DELTA_Y);
		Line2D diversityLine = new Line2D.Double(ORIGIN_X, ORIGIN_Y - population.getAverageHammingDistance() * DELTA_Y, ORIGIN_X, ORIGIN_Y - population.getAverageHammingDistance() * DELTA_Y);
		
		bestLines.add(bestLine);
		avgLines.add(avgLine);
		worstLines.add(worstLine);
		diversityLines.add(diversityLine);
	}
	
	public void resetLines() {
		bestLines.clear();
		avgLines.clear();
		worstLines.clear();
		diversityLines.clear();
		initializeGraph();
	}
	
	public void updateAllLines() {
		if (!newLinesDrawn) {
			updateLines(bestLines);
			updateLines(avgLines);
			updateLines(worstLines);
			updateLines(diversityLines);
			this.newLinesDrawn = true;
		}
	}
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
	}

	private void updateLines(ArrayList<Line2D> lines) {
		Line2D startLine = lines.get(lines.size() - 1);
//		System.out.println("generations: "+(generations)+", curGenerationsLeft: "+curGenerationsLeft+", lines.size(): "+lines.size());

		double x1 = ORIGIN_X + ((generations-curGenerationsLeft) * deltaX);
		double y1 = startLine.getY2();
		double x2 = ORIGIN_X + ((generations-curGenerationsLeft+1) * deltaX);
		double y2;
		
		if (lines.equals(bestLines)) {
			System.out.println("best: "+this.population.getBestFitness());
			y2 = ORIGIN_Y - (this.population.getBestFitness() * DELTA_Y);

		} else if (lines.equals(avgLines)) {
			System.out.println("avg: "+this.population.getAverageFitness());
			y2 = ORIGIN_Y - this.population.getAverageFitness() * DELTA_Y;

		} else if (lines.equals(worstLines)) {
			System.out.println("worst: "+this.population.getLeastFitness());
			y2 = ORIGIN_Y - (this.population.getLeastFitness() * DELTA_Y);
		} else {
			System.out.println("diversity: "+this.population.getAverageHammingDistance());
			y2 = ORIGIN_Y - (this.population.getAverageHammingDistance() * DELTA_Y);
		}

		Line2D line = new Line2D.Double(x1, y1, x2, y2);
		lines.add(line);
	}

	public void drawLines(Graphics2D g2) {
		g2.setStroke(LINE_STROKE);
		g2.setColor(BEST_COLOR);
		for (Line2D line : this.bestLines) {
			g2.draw(line);
		}
		
		g2.setColor(AVG_COLOR);
		for (Line2D line : this.avgLines) {
			g2.draw(line);
		}

		g2.setColor(WORST_COLOR);
		for (Line2D line : this.worstLines) {
			g2.draw(line);
		}
		
		g2.setColor(DIVERSITY_COLOR);
		for (Line2D line : this.diversityLines) {
			g2.draw(line);
		}
	}

	public void drawAxes(Graphics2D g2) {
		g2.setStroke(AXES_STROKE);
		g2.setColor(Color.BLACK);
		
		g2.drawLine(ORIGIN_X, ORIGIN_Y, ORIGIN_X, ORIGIN_Y-Y_AXIS_HEIGHT);
		for(int i = 10*DELTA_Y; i <= Y_AXIS_HEIGHT; i+=10*DELTA_Y) {
			g2.drawLine(ORIGIN_X-5, ORIGIN_Y-i, ORIGIN_X+5, ORIGIN_Y-i);
		}
		
		g2.drawLine(ORIGIN_X, ORIGIN_Y, ORIGIN_X + X_AXIS_WIDTH, ORIGIN_Y);
		for(int i = 10*deltaX; i <= X_AXIS_WIDTH; i+=10*deltaX) {
			g2.drawLine(ORIGIN_X+i, ORIGIN_Y-5, ORIGIN_X+i, ORIGIN_Y+5);
		}
		g2.drawLine(ORIGIN_X+X_AXIS_WIDTH, ORIGIN_Y-5, ORIGIN_X+ X_AXIS_WIDTH, ORIGIN_Y+5);
	}
	public void updateGenerations(int generationsLeft) {
		this.curGenerationsLeft = generationsLeft;
		this.newLinesDrawn = false;
	}
}
