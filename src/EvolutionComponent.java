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

	private final static Color BEST_COLOR = Color.BLUE;
	private final static Color AVG_COLOR = Color.GREEN;
	private final static Color WORST_COLOR = Color.RED;

	private final static BasicStroke LINE_STROKE = new BasicStroke(2);
	private final static BasicStroke AXES_STROKE = new BasicStroke(1);

	public int numTicks;
	private Population population;
	private int generations;
<<<<<<< Upstream, based on branch 'master' of https://github.com/rhit-csse220/csse220-fall-2022-23-final-project-f23-r-1001.git
	
=======

>>>>>>> f013e44 elitism and crossover update, evolution viewer and component clean
	public EvolutionComponent(Population population) {
		this.population = population;
		this.generations = DEFAULT_GENERATIONS;
		this.deltaX = X_AXIS_WIDTH / generations;
		this.bestLines = new ArrayList<Line2D>();
		this.avgLines = new ArrayList<Line2D>();
		this.worstLines = new ArrayList<Line2D>();
		initializeGraph();
		System.out.println("deltaX: "+deltaX);
	}
<<<<<<< Upstream, based on branch 'master' of https://github.com/rhit-csse220/csse220-fall-2022-23-final-project-f23-r-1001.git
=======
	
>>>>>>> f013e44 elitism and crossover update, evolution viewer and component clean
	public EvolutionComponent(Population population, int generations) {
		this(population);
		this.generations = generations;
		this.deltaX = X_AXIS_WIDTH / generations;
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		updateAllLines();
<<<<<<< Upstream, based on branch 'master' of https://github.com/rhit-csse220/csse220-fall-2022-23-final-project-f23-r-1001.git
=======
		
>>>>>>> f013e44 elitism and crossover update, evolution viewer and component clean
		drawAll(g2);
		System.out.println("painted component");
	}
	public void drawAll(Graphics2D g2) {
		drawLines(g2);
		drawAxes(g2);
<<<<<<< Upstream, based on branch 'master' of https://github.com/rhit-csse220/csse220-fall-2022-23-final-project-f23-r-1001.git
	}
	public void updatePop(Population population)
	{
		this.population = population;
=======
>>>>>>> f013e44 elitism and crossover update, evolution viewer and component clean
	}
<<<<<<< Upstream, based on branch 'master' of https://github.com/rhit-csse220/csse220-fall-2022-23-final-project-f23-r-1001.git
=======
	public void updatePop(Population population)
	{
		this.population = population;
	}
	
>>>>>>> f013e44 elitism and crossover update, evolution viewer and component clean
	public Population getPopulation() {
		return this.population;
	}
	

	private void initializeGraph() {
		Line2D bestLine = new Line2D.Double(ORIGIN_X, ORIGIN_Y - population.getBestFitness() * DELTA_Y, ORIGIN_X, ORIGIN_Y - population.getBestFitness() * DELTA_Y);
		Line2D avgLine = new Line2D.Double(ORIGIN_X, ORIGIN_Y - population.getAverageFitness() * DELTA_Y, ORIGIN_X, ORIGIN_Y - population.getAverageFitness() * DELTA_Y);
		Line2D worstLine = new Line2D.Double(ORIGIN_X, ORIGIN_Y - population.getLeastFitness() * DELTA_Y, ORIGIN_X, ORIGIN_Y - population.getLeastFitness() * DELTA_Y);
		
		bestLines.add(bestLine);
		avgLines.add(avgLine);
		worstLines.add(worstLine);
	}
	
	public void resetLines() {
		bestLines.clear();
		avgLines.clear();
		worstLines.clear();
		initializeGraph();
	}
	
	public void updateAllLines() {
		updateLines(bestLines);
		updateLines(avgLines);
		updateLines(worstLines);
	}

	private void updateLines(ArrayList<Line2D> lines) {
		int startLineIndex = lines.size() - 1;
		int endLineIndex = lines.size();
		Line2D startLine = lines.get(startLineIndex);

		double x1 = ORIGIN_X + (startLineIndex * deltaX);
		double y1 = startLine.getY2();
<<<<<<< Upstream, based on branch 'master' of https://github.com/rhit-csse220/csse220-fall-2022-23-final-project-f23-r-1001.git
=======

>>>>>>> f013e44 elitism and crossover update, evolution viewer and component clean
		double x2 = ORIGIN_X + (endLineIndex * deltaX);
		double y2;
<<<<<<< Upstream, based on branch 'master' of https://github.com/rhit-csse220/csse220-fall-2022-23-final-project-f23-r-1001.git
		
=======

>>>>>>> f013e44 elitism and crossover update, evolution viewer and component clean
		if (lines.equals(bestLines)) {
			System.out.println("best: "+this.population.getBestFitness());
			y2 = ORIGIN_Y - (this.population.getBestFitness() * DELTA_Y);
<<<<<<< Upstream, based on branch 'master' of https://github.com/rhit-csse220/csse220-fall-2022-23-final-project-f23-r-1001.git

		} else if (lines.equals(avgLines)) {
			System.out.println("avg: "+this.population.getAverageFitness());
			y2 = ORIGIN_Y - this.population.getAverageFitness() * DELTA_Y;

=======
		}else if (lines.equals(avgLines)) {
			System.out.println("avg: "+this.population.getAverageFitness());
			y2 = ORIGIN_Y - this.population.getAverageFitness() * DELTA_Y;
>>>>>>> f013e44 elitism and crossover update, evolution viewer and component clean
		} else {
			System.out.println("worst: "+this.population.getLeastFitness());
			y2 = ORIGIN_Y - (this.population.getLeastFitness() * DELTA_Y);
		}

		Line2D line = new Line2D.Double(x1, y1, x2, y2);
		lines.add(line);
<<<<<<< Upstream, based on branch 'master' of https://github.com/rhit-csse220/csse220-fall-2022-23-final-project-f23-r-1001.git
=======

>>>>>>> f013e44 elitism and crossover update, evolution viewer and component clean
	}

	public void drawLines(Graphics2D g2) {
		g2.setStroke(LINE_STROKE);
		g2.setColor(BEST_COLOR);
		for (Line2D line : this.bestLines) {
			g2.draw(line);
		}
<<<<<<< Upstream, based on branch 'master' of https://github.com/rhit-csse220/csse220-fall-2022-23-final-project-f23-r-1001.git
		
=======
>>>>>>> f013e44 elitism and crossover update, evolution viewer and component clean
		g2.setColor(AVG_COLOR);
		for (Line2D line : this.avgLines) {
			g2.draw(line);
		}
<<<<<<< Upstream, based on branch 'master' of https://github.com/rhit-csse220/csse220-fall-2022-23-final-project-f23-r-1001.git
		
=======
>>>>>>> f013e44 elitism and crossover update, evolution viewer and component clean
		g2.setColor(WORST_COLOR);
		for (Line2D line : this.worstLines) {
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
}
