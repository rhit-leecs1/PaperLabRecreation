import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;
/**
 * Class: BestIndividualViewer
 * @author kangd2 & leecs1
 * <br>Purpose: Used to create and run a frame that allows the user to manipulate and save a chromosome
 * <br>For example: 
 * <pre>
 *    ChromosomeViewer chromosomeViewer = new ChromosomeViewer();
 * </pre>
 */
public class ChromosomeViewer {

	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 700;
	private static final int PANEL_WIDTH = 300;
	private static final int PANEL_HEIGHT = 300;
	private static final Font DEFAULT_FONT = new Font("Times New Roman", Font.PLAIN, 16);
	private static final int DEFAULT_CHROMOSOME_SIZE = 100;
	
	private JLabel filePath;
	private int size;
	private Individual chromosome;
	
	/**
	 * ensures: creates and runs a frame that allows the user to manipulate and save a chromosome
	 */
	public void runChromosomeViewer() {
		// initializing frame to the wanted size
		JFrame frame = new JFrame();
		Dimension frameD = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setSize(frameD);
		frame.setTitle("Chromosome Viewer");

		// file path label at top of frame
		filePath = new JLabel("Unsaved Chromosome");
		filePath.setFont(DEFAULT_FONT);
		frame.add(filePath, BorderLayout.NORTH);

		// color-coded chromosome grid
		JPanel genePanel = new JPanel();
		size = DEFAULT_CHROMOSOME_SIZE;
		chromosome = new Individual();
		genePanel.setLayout(new GridLayout(10, 10));
		Dimension genePanelD = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
		genePanel.setPreferredSize(genePanelD);
		chromosome.drawOn(genePanel);
		frame.add(genePanel);

		// last panel with all other components
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(3, 3));

		// size label
		JLabel sizeLabel = new JLabel("N = " + size, SwingConstants.CENTER);
		sizeLabel.setFont(DEFAULT_FONT);
		// empty labels for spacing
		bottomPanel.add(new JLabel(""));
		bottomPanel.add(sizeLabel);
		bottomPanel.add(new JLabel(""));

		// mutations
//    	JPanel mutationPanel = new JPanel();
		JButton mutateButton = new JButton("Mutate");
		mutateButton.setFont(DEFAULT_FONT);
		JLabel mutationRateLabel = new JLabel("Mutation Rate: __/N", SwingConstants.CENTER);
		mutationRateLabel.setFont(DEFAULT_FONT);
		JTextField mutationRateTextField = new JTextField("1", 20);
		bottomPanel.add(mutateButton, BorderLayout.WEST);
		bottomPanel.add(mutationRateLabel);
		bottomPanel.add(mutationRateTextField);

		// mutate button listener
		class MutateButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				String mRateNumeratorStr = mutationRateTextField.getText();
				try {
					double mRate = Integer.parseInt(mRateNumeratorStr) / (1.0 * size);
					chromosome.mutate(mRate);
				} catch (Exception error) {
					System.err.println("Invalid Mutation Rate");
				}
			} // actionPerformed
		} // MutateButtonListener
		mutateButton.addActionListener(new MutateButtonListener());

		// Load and Save File
		JButton loadButton = new JButton("Load");
		JButton saveButton = new JButton("Save");
		loadButton.setFont(DEFAULT_FONT);
		saveButton.setFont(DEFAULT_FONT);
		bottomPanel.add(loadButton);
		bottomPanel.add(saveButton);
		frame.add(bottomPanel, BorderLayout.SOUTH);

		//load button
		class ChromosomeLoadFileButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				JFrame parent = new JFrame();
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Load Chromosome From Text File");
				int chosen = fileChooser.showOpenDialog(parent);
				
				if(chosen == JFileChooser.APPROVE_OPTION)
				{
					File f = fileChooser.getSelectedFile();
					filePath.setText(f.getPath());
					
					try {
						Scanner s = new Scanner(f);
						String line = s.next();
						size = line.length();
						System.out.println(line + ", length:" + line.length());
						chromosome = new Individual(line.length(),line);
						
						genePanel.removeAll();
						genePanel.setLayout(new GridLayout(size==20?5:10, size==20?4:10));
						chromosome.drawOn(genePanel);
						chromosome.addListenerToGenes();
						s.close();
					} catch (Exception e1) {
						System.err.println("File Not Found or Invalid File Format (Only text (.txt) files with bit values)");
					}	
				}
			} // actionPerformed
		} // ChromosomeLoadFileButtonListener
		loadButton.addActionListener(new ChromosomeLoadFileButtonListener());

		//save button
		class ChromosomeSaveFileButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				JFrame parent = new JFrame();
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Save Chromosome as Text File");
				fc.setSelectedFile(new File("C:\\untitled.txt"));
//				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int chosen = fc.showSaveDialog(parent);
				if(chosen == JFileChooser.APPROVE_OPTION)
				{
					try {
						PrintWriter pw = new PrintWriter(fc.getSelectedFile());
						System.out.println(chromosome.getBinString());
						pw.print(chromosome.getBinString());
						filePath.setText(fc.getSelectedFile().getPath());
						pw.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			} // actionPerformed
		} // ChromosomeSaveFileButtonListener
		saveButton.addActionListener(new ChromosomeSaveFileButtonListener());
		
		// allows gene buttons to flip upon press
		chromosome.addListenerToGenes();

		// set frame visible and closing operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	} // runChromosomeViewer
	
	/**
	 * ensures: the chromosome's bin string is returned
	 * @return chromosome's bin string
	 */
	public String getTargetChromosome()
	{
		return chromosome.getBinString();
	} // getTargetChromosome
} // ChromosomeViewer
