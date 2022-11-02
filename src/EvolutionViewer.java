import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class EvolutionViewer {
	private int size = 100;
	private Population population;
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
		JPanel graphPanel = new JPanel();
		Dimension graphPanelD = new Dimension(300, 300);
		graphPanel.setPreferredSize(graphPanelD);
//		graph.drawOn(graphPanel);
		frame.add(graphPanel);

		// last panel with all other components
		JPanel bottomPanel = new JPanel();
		frame.add(bottomPanel, BorderLayout.SOUTH);

		// mutations
		JLabel mutationRateLabel = new JLabel("Mutation Rate (N/pop)", SwingConstants.CENTER);
		mutationRateLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		JTextField mutationRateTextField = new JTextField("1.0", 5);

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
		
		JButton simulationButton = new JButton("Start Evolution");
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

		// TODO: add timer

		class SimulationButtonListener implements ActionListener{
		    public void actionPerformed(ActionEvent e){
		    	System.out.println("clicked");
		    	
				String mRateNumeratorStr = mutationRateTextField.getText();
				Action selectionAction = selectionList.getAction();
				boolean crossoverBool = crossoverCheckBox.isSelected();
				String populationSizeStr = populationSizeTextField.getText();
				String generationsStr = generationsTextField.getText();
				String genomeLengthStr = genomeLengthTextField.getText();
				String elitismStr = elitismTextField.getText();
				try {
					double mRate = Integer.parseInt(mRateNumeratorStr) / (1.0 * size);
					
					
//					System.out.println("mRateNumberatorStr: "+mRateNumeratorStr);
//					System.out.println("mRate: "+mRate);
//					System.out.println("selection: "+selectionAction);
//					System.out.println("crossoverBool : "+crossoverBool);
//					System.out.println("populationSizeStr: "+populationSizeStr);
//					System.out.println("generationsStr: "+generationsStr);
//					System.out.println("genomeLengthStr: "+genomeLengthStr);
//					System.out.println("elitismStr: "+elitismStr);

				} catch (Exception error) {
					System.err.println("Invalid input");
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