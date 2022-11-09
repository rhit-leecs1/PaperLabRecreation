import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
/**
 * Class: EvolutionViewer
 * @author kangd2 & leecs1
 * <br>Purpose: Used to create a frame to draw a BestIndividualComponent and run the population and best individual viewers
 * <br>For example: 
 * <pre>
 *    EvolutionViewer evolutionViewer = new EvolutionViewer();
 * </pre>
 */
public class EvolutionViewer {
	
	private final static int DEFAULT_FRAME_X = 1700;
	private final static int DEFAULT_FRAME_Y = 700;

	private final static int TERMINATE_AT_FITNESS = 90;
	private final static int DEFAULT_GENERATIONS = 100;
	private final static Font DEFAULT_FONT = new Font("Times New Roman", Font.BOLD, 16);
	
	private final static int DEFAULT_SIZE = 100;

	private boolean terminateOn;
	private boolean crossover;
	private int generations;
	private int elitismNum;
	
	private Population population;
	private Timer timer;
	private JButton simulationButton;
	private EvolutionComponent ec;
	private JLabel genCntLabel;
	
	/**
	 * ensures: intitializes the population, EvolutionComponent, and other settings (terminate, crossover, elitism, generation)
	 */
	public EvolutionViewer()
	{
		
		population = new Population();
		ec = new EvolutionComponent(population);
		generations = DEFAULT_GENERATIONS;
		terminateOn = false;
		crossover = false;
		elitismNum = 0;
		genCntLabel = new JLabel("Generation 0     ", SwingConstants.CENTER);
	} // EvolutionViewer
	
	/**
	 * ensures: initializes and runs the frame and timer for the evolution component, as well as run the population and best individual viewer
	 */
	public void runEvolutionViewer() {
		// run chromosome viewer
		ChromosomeViewer cv = new ChromosomeViewer();
        cv.runChromosomeViewer();
		
		// initialize frame
		JFrame frame = new JFrame();
		Dimension frameD = new Dimension(DEFAULT_FRAME_X, DEFAULT_FRAME_Y);
		frame.setSize(frameD);
		frame.setTitle("Evolution Viewer");
		frame.setResizable(false);
		
		// file path label at top of frame
		JLabel graphTitle = new JLabel("Fitness Over Generations", SwingConstants.CENTER);
		graphTitle.setFont(DEFAULT_FONT);
		frame.add(graphTitle, BorderLayout.NORTH);
		frame.add(genCntLabel, BorderLayout.EAST);
		
		// graph (evolution component)
		frame.add(ec, BorderLayout.CENTER);
		
		// population viewer
		PopulationViewer pv = new PopulationViewer(population);
        pv.runPopulationViewer();
		
        // best individual viewer
        BestIndividualViewer biv = new BestIndividualViewer(population.getBestIndividual());
        biv.runBestIndividualViewer();
		
        // timer
  		timer = new Timer(100, new ActionListener() {
  			public void actionPerformed(ActionEvent e)
  			{
  				if((generations == 0) || (terminateOn && population.getBestFitness() == TERMINATE_AT_FITNESS))
  				{
  					timer.stop();
  					simulationButton.setEnabled(false);
  				}
  				else
  				{
  					generations--;
  					population.truncate();
      				ec.updatePop(population);
      				ec.updateGenerations(generations);
  					ec.repaint();
  					System.out.println("ec repaint");
  					pv.updatePop(population);
  					biv.updateBest(population.getBestIndividual());
  					genCntLabel.setText("Generation " + (100-generations) + "     ");
  					System.out.println("Generation " + (100-generations));
  					System.out.println("diversity: " + population.getAverageHammingDistance());
  					System.out.println(population);
  				}
  			} // actionPerformed
  		});
        
		// last panel with all other components
		JPanel bottomPanel = new JPanel();
//		JPanel topButtonPanel = new JPanel();
//		JPanel bottomButtonPanel = new JPanel();
//		bottomPanel.add(topButtonPanel,BorderLayout.NORTH);
//		bottomPanel.add(bottomButtonPanel,BorderLayout.SOUTH);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		
		// mutations
		JLabel mutationRateLabel = new JLabel("Mutation Rate (N/pop)", SwingConstants.CENTER);
		mutationRateLabel.setFont(DEFAULT_FONT);
		JTextField mutationRateTextField = new JTextField("1", 5);

		JLabel selectionLabel = new JLabel("Selection", SwingConstants.CENTER);
		selectionLabel.setFont(DEFAULT_FONT);
		String[] selections = {"Truncation"};
		JComboBox selectionList = new JComboBox(selections);
		selectionList.setSelectedIndex(0);
//		selectionList.addActionListener(selectionList);
		
		JLabel crossoverLabel = new JLabel("Crossover?", SwingConstants.CENTER);
		crossoverLabel.setFont(DEFAULT_FONT);
		JCheckBox crossoverCheckBox = new JCheckBox();
		crossoverCheckBox.setBounds(20,20,20,20);
		
		JLabel populationSizeLabel = new JLabel("Population Size", SwingConstants.CENTER);
		populationSizeLabel.setFont(DEFAULT_FONT);
		JTextField populationSizeTextField = new JTextField("100", 5);
		
		JLabel generationsLabel = new JLabel("Generations", SwingConstants.CENTER);
		generationsLabel.setFont(DEFAULT_FONT);
		JTextField generationsTextField = new JTextField("100", 5);

		JLabel terminateLabel = new JLabel("Terminal Fitness 90?", SwingConstants.CENTER);
		terminateLabel.setFont(DEFAULT_FONT);
		JCheckBox terminateCheckBox = new JCheckBox();
		terminateCheckBox.setBounds(20,20,20,20);
		
		JLabel genomeLengthLabel = new JLabel("Genome Length", SwingConstants.CENTER);
		genomeLengthLabel.setFont(DEFAULT_FONT);
		JTextField genomeLengthTextField = new JTextField("100", 5);
		
		JLabel elitismLabel = new JLabel("Elitism % (N/pop)", SwingConstants.CENTER);
		elitismLabel.setFont(DEFAULT_FONT);
		JTextField elitismTextField = new JTextField("0", 5);
		
		simulationButton = new JButton("Start Evolution");
		simulationButton.setFont(DEFAULT_FONT);

//		bottomButtonPanel.add(mutationRateLabel);
//		bottomButtonPanel.add(mutationRateTextField);
//		bottomButtonPanel.add(selectionLabel);
//		bottomButtonPanel.add(selectionList);
//		bottomButtonPanel.add(crossoverLabel);
//		bottomButtonPanel.add(crossoverCheckBox);
//		bottomButtonPanel.add(populationSizeLabel);
//		bottomButtonPanel.add(populationSizeTextField);
//		bottomButtonPanel.add(generationsLabel);
//		bottomButtonPanel.add(generationsTextField);
//		bottomButtonPanel.add(terminateLabel);
//		bottomButtonPanel.add(terminateCheckBox);		
//		bottomButtonPanel.add(genomeLengthLabel);
//		bottomButtonPanel.add(genomeLengthTextField);
//		bottomButtonPanel.add(elitismLabel);
//		bottomButtonPanel.add(elitismTextField);
//		bottomButtonPanel.add(simulationButton);

		JLabel fitnessTypeLabel = new JLabel("Selection", SwingConstants.CENTER);
		fitnessTypeLabel.setFont(DEFAULT_FONT);
		String[] fitnessTypes = {"basic","target","1010pattern"};
		JComboBox fitnessTypesList = new JComboBox(fitnessTypes);
		fitnessTypesList.setSelectedIndex(0);
		
		bottomPanel.add(mutationRateLabel);
		bottomPanel.add(mutationRateTextField);
		
		bottomPanel.add(fitnessTypeLabel);
		bottomPanel.add(fitnessTypesList);
		
		bottomPanel.add(selectionLabel);
		bottomPanel.add(selectionList);
		bottomPanel.add(crossoverLabel);
		bottomPanel.add(crossoverCheckBox);
		bottomPanel.add(populationSizeLabel);
		bottomPanel.add(populationSizeTextField);
		bottomPanel.add(generationsLabel);
		bottomPanel.add(generationsTextField);
		bottomPanel.add(terminateLabel);
		bottomPanel.add(terminateCheckBox);		
		bottomPanel.add(genomeLengthLabel);
		bottomPanel.add(genomeLengthTextField);
		bottomPanel.add(elitismLabel);
		bottomPanel.add(elitismTextField);
		bottomPanel.add(simulationButton);
		
//		JLabel fitnessTypeLabel = new JLabel("Selection", SwingConstants.CENTER);
//		fitnessTypeLabel.setFont(DEFAULT_FONT);
//		String[] fitnessTypes = {"basic","target","1010pattern"};
//		JComboBox fitnessTypesList = new JComboBox(fitnessTypes);
//		fitnessTypesList.setSelectedIndex(0);
//		
//		topButtonPanel.add(fitnessTypeLabel);
//		topButtonPanel.add(fitnessTypesList);
		
		
		
		
		
		/**
		 * Class: SimulationButtonListener
		 * @author kangd2 & leecs1
		 * <br>Purpose: Used to clicks on the simulation button and run the timer accordingly
		 * <br>For example: 
		 * <pre>
		 *    SimulationButtonListener listender = new SimulationButtonListener();
		 * </pre>
		 */
		class SimulationButtonListener implements ActionListener{
			
			private String state;
			
			/**
			 * ensures: initializes the state to "start"
			 */
			public SimulationButtonListener()
			{
				state = "start";
			} // SimulationButtonListener
			
			/**
			 * ensures: listens to clicks made on the button and adjusts variables according to user input
			 */
		    public void actionPerformed(ActionEvent e){
		    	System.out.println("clicked");
		    	if(state.equals("start"))
		    	{
		    		String mRateNumeratorStr = mutationRateTextField.getText();
					Action selectionAction = selectionList.getAction();
					boolean crossoverBool = crossoverCheckBox.isSelected();
					boolean terminateBool = terminateCheckBox.isSelected();
					String populationSizeStr = populationSizeTextField.getText();
					String generationsStr = generationsTextField.getText();
					String genomeLengthStr = genomeLengthTextField.getText();
					String elitismStr = elitismTextField.getText();
					Action fitnessTypeAction = fitnessTypesList.getAction();
					try
					{
						double mRate = Integer.parseInt(mRateNumeratorStr) / (1.0 * DEFAULT_SIZE);
						generations = Integer.parseInt(generationsStr);
						elitismNum = Integer.parseInt(elitismStr);
						terminateOn = terminateBool;
						crossover = crossoverBool;
						
						population.setFitnessType(""+fitnessTypeAction);
						population.setTargetChromosome(cv.getTargetChromosome());
						population.setMutationRate(mRate);
						population.setElitismNum(elitismNum);
						population.setCrossoverBool(crossoverBool);
						state = "started";
						simulationButton.setText("Pause");
						timer.start();
//						System.out.println("mRateNumberatorStr: "+mRateNumeratorStr);
//						System.out.println("mRate: "+mRate);
//						System.out.println("selection: "+selectionAction);
//						System.out.println("crossoverBool : "+crossoverBool);
//						System.out.println("populationSizeStr: "+populationSizeStr);
//						System.out.println("generationsStr: "+generationsStr);
//						System.out.println("genomeLengthStr: "+genomeLengthStr);
//						System.out.println("elitismStr: "+elitismStr);
					}
					catch (Exception error)
					{
						System.err.println("Invalid input");
					}
		    	}
		    	else if(state.equals("started") || state.equals("resumed"))
		    	{
		    		state = "paused";
		    		timer.stop();
		    		simulationButton.setText("Resume");
		    	}
		    	else
		    	{
		    		state = "resumed";
		    		timer.start();
		    		simulationButton.setText("Pause");
		    	}
		    } // actionPerformed
		} // SimulationButtonListener
		simulationButton.addActionListener(new SimulationButtonListener());
		
		// set frame visible and closing operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	} // runEvolutionViewer
} // EvolutionViewer

//class SimulationSelectionDropDownListener implements ActionListener{
//    public void actionPerformed(ActionEvent e){
//
//    }
//}