import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.*;

public class EvolutionComponent extends JComponent{
	private int originX = 200;
	private int originY = 500;
	
	private int YAxisLength = 400;

	private ArrayList<Line2D> avgLines;
	private ArrayList<Line2D> worstLines;
	private ArrayList<Line2D> bestLines;

	private int XAxisVal = 1000;
	private int rangeMultiplier = 4;

	public int numTicks;
	Population population;
	private int generationLength;
	
	
	//------
	private int domainMultiplier = 10;
	//------


	public EvolutionComponent(Population population) {
		this.population = population;
		this.generationLength = 100;
		linesetUp();
	}
	public EvolutionComponent(Population population, int generationLength) {
		this.population = population;
		this.generationLength = generationLength;
		linesetUp();
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
//		System.out.println("paintComponent");
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;


//		resetLines();
		updateAllLines();
		
		updateAll(g2);
	}
	public void updatePop(Population p)
	{
		population = p;
	}
	public void updateAll(Graphics2D g2)
	{
		g2.setStroke(new BasicStroke(2));
		drawBestLines(g2);
		drawWorstLines(g2);
		drawAvgLines(g2);
		
		g2.setStroke(new BasicStroke(1));
		drawBoundary(g2);
	}
	
	public Population getPopulation() {
		return population;
	}
	

	private void linesetUp() {
		//System.out.println("line set up called--------------------------------");
		Line2D initialAvgLine = new Line2D.Double(originX,
				originY - population.getAverageFitness() * rangeMultiplier, originX,
				originY - population.getAverageFitness() * rangeMultiplier);
		System.out.println("initial line Y cordinate for AVERAGE is " + (originY - population.getAverageFitness() * rangeMultiplier));
		System.out.println("very intial fitness: " + population.getAverageFitness());
		Line2D initialBestLine = new Line2D.Double(originX,
				originY - population.getBestFitness() * rangeMultiplier, originX,
				originY - population.getBestFitness() * rangeMultiplier);
//		System.out.println("initial line Y cordinate for BEST is " + (originY - generation.getLatestBestFitnees()));
		Line2D initialWorstLine = new Line2D.Double(originX,
				originY - population.getLeastFitness() * rangeMultiplier, originX,
				originY - population.getLeastFitness() * rangeMultiplier);
//		System.out.println("initial line Y cordinate for WORST is " + (originY - generation.getLatestWorstFitnees()));
		
		this.avgLines = new ArrayList<Line2D>();
		this.worstLines = new ArrayList<Line2D>();
		this.bestLines = new ArrayList<Line2D>();
		avgLines.add(initialAvgLine);
		bestLines.add(initialBestLine);
		worstLines.add(initialWorstLine);
	}
	
	public void resetLines() {
		avgLines.clear();
		bestLines.clear();
		worstLines.clear();
		linesetUp();
	}
	
	public void updateAllLines() {
		updateLine(bestLines);
		updateLine(avgLines);
		updateLine(worstLines);
	}

	private void updateLine(ArrayList<Line2D> lineToUpdate) {
		int startLineIndex = lineToUpdate.size() - 1;
		int endLineIndex = lineToUpdate.size();
		Line2D startLine = lineToUpdate.get(startLineIndex);

		double x1 = this.originX + (startLineIndex * domainMultiplier);
		double y1 = startLine.getY2();

		double x2 = this.originX + (endLineIndex * domainMultiplier);
		double y2;

		if (lineToUpdate.equals(avgLines)) {
			//System.out.println("latest Average fitness:" + this.generation.getLatestAverageFitnees());
			System.out.println("avg: "+this.population.getAverageFitness());
			y2 = this.originY - this.population.getAverageFitness() * rangeMultiplier;

		} else if (lineToUpdate.equals(bestLines)) {
			System.out.println("best: "+this.population.getBestFitness());
			y2 = this.originY - (this.population.getBestFitness() * rangeMultiplier);

		} else {
			System.out.println("least: "+this.population.getLeastFitness());
			y2 = this.originY - (this.population.getLeastFitness() * rangeMultiplier);
		}

		Line2D line = new Line2D.Double(x1, y1, x2, y2);
		//System.out.println("line created, Y: " + startingY +" to " + endY);
//		if(startingX != endX && startingY!= endY) {
			lineToUpdate.add(line);
//		}

	}

	public void drawBestLines(Graphics2D g2) {
		g2.setColor(Color.BLUE);
		for (Line2D line : this.bestLines) {
			g2.draw(line);
		}
	}

	public void drawAvgLines(Graphics2D g2) {
		g2.setColor(Color.GREEN);
		for (Line2D line : this.avgLines) {
			g2.draw(line);
		}
	}

	public void drawWorstLines(Graphics2D g2) {
		g2.setColor(Color.RED);
		for (Line2D line : this.worstLines) {
			g2.draw(line);
		}
	}

	public void drawBoundary(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		
		g2.drawLine(originX, originY, originX, originY-YAxisLength);
		for(int i = 40; i <= YAxisLength; i+=40) {
			g2.drawLine(originX-5, originY-i, originX+5, originY-i);
		}
		
		g2.drawLine(originX, originY, originX + XAxisVal, originY);
		for(int i = 100; i <= this.XAxisVal; i+=100) {
			g2.drawLine(originX+i, originY-5, originX+i, originY+5);
		}
		g2.drawLine(originX+this.XAxisVal, originY-5, originX+ this.XAxisVal, originY+5);
		
	}

	public void drawAll(Graphics2D g2) {
		g2.setStroke(new BasicStroke(2));
		drawBestLines(g2);
		drawWorstLines(g2);
		drawAvgLines(g2);
		g2.setStroke(new BasicStroke(1));
		drawBoundary(g2);

	}


	public void setNewGraphWithGenerationLength(int generationLength) {
//		population.resetGenerarions();
//		System.out.println("new Graph created with new generation length: "+ generationLength);
		this.repaint();
		// TODO Auto-generated method stub
	}
	
	
//	domainMuliplier = XAxisVal / 100; // 100 is default generationLength

	
	
	
//	public void setDomainMultiplier(int generationLength) {
//		//System.out.println("setDomainMultiplier called with generation length of " + generationLength);
//		int generationLengthLimit = 1000;
//		if (1 <= generationLength && generationLength <= generationLengthLimit) {
////			System.out.println("X is within range");
//
//			if (((double) generationLengthLimit
//					/ (double) generationLength) == (int) (generationLengthLimit / generationLength)) {
//				//System.out.println("1000/" + generationLength + "is integer");
////				domainMuliplier = generationLengthLimit / generationLength;
//
//			} else {
//				//System.out.println("1000/" + generationLength + " is not an integer");
////				domainMuliplier = generationLengthLimit / generationLength;
//				this.XAxisVal = this.XAxisVal - (generationLengthLimit % generationLength);
//				//System.out.println("domainMuliplier = " + generationLengthLimit / generationLength);
//				//System.out.println("this.XAxisVal = " + (this.XAxisVal - (generationLengthLimit % generationLength)));
//			}
//		}
//		//System.out.println("XAxis length is " + this.XAxisVal);
//	}

	
}
