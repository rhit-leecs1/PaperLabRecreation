import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class EvolutionViewer {
	private int size = 100;
	private Population population;
	private Timer timer;
	private int generations;
	private JButton simulationButton;
	private EvolutionComponent ec;
	public EvolutionViewer()
	{
		
		population = new Population();
		ec = new EvolutionComponent(population);
		generations = 100;
	}
	public void runEvolutionViewer() {
		// initializing frame to the wanted size
		JFrame frame = new JFrame();
		Dimension frameD = new Dimension(1500, 700);
		frame.setSize(frameD);
		frame.setTitle("Evolution Viewer");
//		frame.setResizable(false);
		
		// file path label at top of frame
		JLabel graphTitle = new JLabel("Fitness Over Generations", SwingConstants.CENTER);
		graphTitle.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		frame.add(graphTitle, BorderLayout.NORTH);

		// graph
		
		frame.add(ec, BorderLayout.CENTER);
//		ec.paintComponent(g);
		
//		Dimension graphPanelD = new Dimension(300, 300);
//		graphPanel.setPreferredSize(graphPanelD);
////		graph.drawOn(graphPanel);
//		frame.add(graphPanel);
		
		
		
		
		

		// last panel with all other components
		JPanel bottomPanel = new JPanel();
		frame.add(bottomPanel, BorderLayout.SOUTH);

		// mutations
		JLabel mutationRateLabel = new JLabel("Mutation Rate (N/pop)", SwingConstants.CENTER);
		mutationRateLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		JTextField mutationRateTextField = new JTextField("1", 5);

		JLabel selectionLabel = new JLabel("Selection", SwingConstants.CENTER);
		selectionLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		String[] selections = {"Truncation"};
		JComboBox selectionList = new JComboBox(selections);
		selectionList.setSelectedIndex(0);
//		selectionList.addActionListener(selectionList);
		
		JLabel crossoverLabel = new JLabel("Crossover?", SwingConstants.CENTER);
		crossoverLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		JCheckBox crossoverCheckBox = new JCheckBox();
		crossoverCheckBox.setBounds(20,20,20,20);
		
		JLabel populationSizeLabel = new JLabel("Population Size", SwingConstants.CENTER);
		populationSizeLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		JTextField populationSizeTextField = new JTextField("100", 5);
		
		JLabel generationsLabel = new JLabel("Generations", SwingConstants.CENTER);
		generationsLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		JTextField generationsTextField = new JTextField("1", 5);

		JLabel genomeLengthLabel = new JLabel("Genome Length", SwingConstants.CENTER);
		genomeLengthLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		JTextField genomeLengthTextField = new JTextField("100", 5);
		
		JLabel elitismLabel = new JLabel("Elitism %", SwingConstants.CENTER);
		elitismLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		JTextField elitismTextField = new JTextField("0", 5);
		
		simulationButton = new JButton("Start Evolution");
		simulationButton.setFont(new Font("Times New Roman", Font.BOLD, 16));

		bottomPanel.add(mutationRateLabel);
		bottomPanel.add(mutationRateTextField);
		bottomPanel.add(selectionLabel);
		bottomPanel.add(selectionList);
		bottomPanel.add(crossoverLabel);
		bottomPanel.add(crossoverCheckBox);
		bottomPanel.add(populationSizeLabel);
		bottomPanel.add(populationSizeTextField);
		bottomPanel.add(generationsLabel);
		bottomPanel.add(generationsTextField);
		bottomPanel.add(genomeLengthLabel);
		bottomPanel.add(genomeLengthTextField);
		bottomPanel.add(elitismLabel);
		bottomPanel.add(elitismTextField);
		bottomPanel.add(simulationButton);
		
		timer = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(population.getBestFitness() == 100)
				{
					timer.stop();
					simulationButton.setEnabled(false);
				}
					
				if(generations==0)
				{
					timer.stop();
					simulationButton.setEnabled(false);
				}
				else
				{
					generations--;
					population.truncate();
//					ec.updatePop(population);
					ec.repaint();
					System.out.println(population);
				}
			}
		});
		
		

		class SimulationButtonListener implements ActionListener{
			private String state;
			public SimulationButtonListener()
			{
				state = "start";
			}
		    public void actionPerformed(ActionEvent e){
		    	System.out.println("clicked");
		    	if(state.equals("start"))
		    	{
		    		String mRateNumeratorStr = mutationRateTextField.getText();
					Action selectionAction = selectionList.getAction();
					boolean crossoverBool = crossoverCheckBox.isSelected();
					String populationSizeStr = populationSizeTextField.getText();
					String generationsStr = generationsTextField.getText();
					String genomeLengthStr = genomeLengthTextField.getText();
					String elitismStr = elitismTextField.getText();
					try
					{
						
						double mRate = Integer.parseInt(mRateNumeratorStr) / (1.0 * size);
						population.setMutationRate(mRate);
						generations = Integer.parseInt(generationsStr);
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
				
		    }
		}
		simulationButton.addActionListener(new SimulationButtonListener());
		
		// set frame visible and closing operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}



class SimulationSelectionDropDownListener implements ActionListener{
    public void actionPerformed(ActionEvent e){

    }
}