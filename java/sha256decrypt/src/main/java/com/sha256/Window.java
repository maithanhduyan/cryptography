package com.sha256;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.Neuron;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1021048020697523225L;
	private JPanel contentPane;
	private JTextField txtServerSeedHash;
	private JTextField txtServerSeed;

	/**
	 * Creates Variable
	 */
	private NeuralNetwork<BackPropagation> ann;
	private DataSet ds;
	private double[] networkOutputOne;
	private static final Logger log = LoggerFactory.getLogger(Window.class);
	private static final String FILE_PATH = "data\\training_sha256.csv";
	private String file_path = "";
	private transient Thread learningThread;
	BackPropagation backPropagation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Window() {
		setResizable(false);
		setTitle("SHA256 Decrypt");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 233);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblServerSeedHash = new JLabel("Server Seed Hash");
		lblServerSeedHash.setBounds(10, 13, 115, 14);
		contentPane.add(lblServerSeedHash);

		txtServerSeedHash = new JTextField();
		txtServerSeedHash.setText("0a392fe66536a23f3cea0ec0b5f6e10d456ea2bcf61c2460b716bc301a7b482a");
		txtServerSeedHash.setBounds(135, 10, 427, 20);
		contentPane.add(txtServerSeedHash);
		txtServerSeedHash.setColumns(10);

		lblServerSeed = new JLabel("Server Seed");
		lblServerSeed.setBounds(10, 53, 115, 14);
		contentPane.add(lblServerSeed);

		txtServerSeed = new JTextField();
		txtServerSeed.setColumns(10);
		txtServerSeed.setBounds(135, 50, 427, 20);
		contentPane.add(txtServerSeed);

		btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnDecryptActionPerformed(evt);
			}
		});
		btnDecrypt.setBounds(106, 97, 89, 23);
		contentPane.add(btnDecrypt);

		btnClear = new JButton("Clear");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnClearClicked(e);
			}
		});
		btnClear.setBounds(444, 97, 89, 23);
		contentPane.add(btnClear);

		lblCurrentIteration = new JLabel("CurrentIteration: 0");
		lblCurrentIteration.setBounds(10, 155, 115, 14);
		contentPane.add(lblCurrentIteration);

		lblMaxError = new JLabel("MaxError: 0.01");
		lblMaxError.setBounds(10, 179, 115, 14);
		contentPane.add(lblMaxError);

		lblSha256Result = new JLabel("4c45e424ed2b247804e799df21b85c4bacf966a1fa94a7f46b09556e0ff1e694");
		lblSha256Result.setBounds(135, 74, 427, 13);
		contentPane.add(lblSha256Result);

		init();

	}

	private void init() {
		try {
			// Properties properties = new Properties();
			// properties.load(this.getClass().getClassLoader().getResourceAsStream("data.properties"));
			// file_path = properties.getProperty("dataFilePath");
			// log.info("File Path " + file_path + " is loading...");
			initNeuronNetwork();
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
	}

	/**
	 * Initial Neuron Network
	 */
	void initNeuronNetwork() {
		log.info("Initialize Neuron Network...");
		// Let's first define our input layer:
		Layer inputLayer = new Layer();
		for (int i = 0; i < 512; i++) {
			inputLayer.addNeuron(new Neuron());
		}

		// hidden layer 1:
		Layer hiddenLayer1 = new Layer();
		for (int i = 0; i < 1024; i++) {
			hiddenLayer1.addNeuron(new Neuron());
		}

		// hidden layer 2:
		Layer hiddenLayer2 = new Layer();
		for (int i = 0; i < 1024; i++) {
			hiddenLayer2.addNeuron(new Neuron());
		}

		// hidden layer 3:
		Layer hiddenLayer3 = new Layer();
		for (int i = 0; i < 1024; i++) {
			hiddenLayer3.addNeuron(new Neuron());
		}

		// hidden layer 4:
		Layer hiddenLayer4 = new Layer();
		for (int i = 0; i < 1024; i++) {
			hiddenLayer4.addNeuron(new Neuron());
		}

		// hidden layer 5:
		Layer hiddenLayer5 = new Layer();
		for (int i = 0; i < 1024; i++) {
			hiddenLayer5.addNeuron(new Neuron());
		}

		// hidden layer 6:
		Layer hiddenLayer6 = new Layer();
		for (int i = 0; i < 1024; i++) {
			hiddenLayer6.addNeuron(new Neuron());
		}
		// hidden layer 7:
		Layer hiddenLayer7 = new Layer();
		for (int i = 0; i < 1024; i++) {
			hiddenLayer7.addNeuron(new Neuron());
		}
		// hidden layer 8:
		Layer hiddenLayer8 = new Layer();
		for (int i = 0; i < 1024; i++) {
			hiddenLayer8.addNeuron(new Neuron());
		}

		// hidden layer 9:
		Layer hiddenLayer9 = new Layer();
		for (int i = 0; i < 1024; i++) {
			hiddenLayer9.addNeuron(new Neuron());
		}

		// hidden layer 10:
		Layer hiddenLayer10 = new Layer();
		for (int i = 0; i < 1024; i++) {
			hiddenLayer10.addNeuron(new Neuron());
		}

		// hidden layer 11:
		Layer hiddenLayer11 = new Layer();
		for (int i = 0; i < 1024; i++) {
			hiddenLayer11.addNeuron(new Neuron());
		}

		// hidden layer 12:
		Layer hiddenLayer12 = new Layer();
		for (int i = 0; i < 1024; i++) {
			hiddenLayer12.addNeuron(new Neuron());
		}

		// hidden layer 13:
		Layer hiddenLayer13 = new Layer();
		for (int i = 0; i < 1024; i++) {
			hiddenLayer13.addNeuron(new Neuron());
		}

		// hidden layer 14:
		Layer hiddenLayer14 = new Layer();
		for (int i = 0; i < 1024; i++) {
			hiddenLayer14.addNeuron(new Neuron());
		}

		// hidden layer 15:
		Layer hiddenLayer15 = new Layer();
		for (int i = 0; i < 1024; i++) {
			hiddenLayer15.addNeuron(new Neuron());
		}

		// hidden layer 16:
		Layer hiddenLayer16 = new Layer();
		for (int i = 0; i < 1024; i++) {
			hiddenLayer16.addNeuron(new Neuron());
		}

		// define our output layer:
		Layer outputLayer = new Layer();
		for (int i = 0; i < 512; i++) {
			outputLayer.addNeuron(new Neuron());
		}

		// put them together into a NeuralNetwork:
		ann = new NeuralNetwork<BackPropagation>();
		ann.addLayer(0, inputLayer);
		ann.addLayer(1, hiddenLayer1);
		ann.addLayer(2, hiddenLayer2);
		ann.addLayer(3, hiddenLayer3);
		ann.addLayer(4, hiddenLayer4);
		ann.addLayer(5, hiddenLayer5);
		ann.addLayer(6, hiddenLayer6);
		ann.addLayer(7, hiddenLayer7);
		ann.addLayer(8, hiddenLayer8);
		ann.addLayer(9, hiddenLayer9);
		ann.addLayer(10, hiddenLayer10);
		ann.addLayer(11, hiddenLayer11);
		ann.addLayer(12, hiddenLayer12);
		ann.addLayer(13, hiddenLayer13);
		ann.addLayer(14, hiddenLayer14);
		ann.addLayer(15, hiddenLayer15);
		ann.addLayer(16, hiddenLayer16);
		ann.addLayer(17, outputLayer);
		ConnectionFactory.fullConnect(ann.getLayerAt(0), ann.getLayerAt(1));
		ConnectionFactory.fullConnect(ann.getLayerAt(1), ann.getLayerAt(2));
		ConnectionFactory.fullConnect(ann.getLayerAt(2), ann.getLayerAt(3));
		ConnectionFactory.fullConnect(ann.getLayerAt(3), ann.getLayerAt(4));
		ConnectionFactory.fullConnect(ann.getLayerAt(4), ann.getLayerAt(5));
		ConnectionFactory.fullConnect(ann.getLayerAt(5), ann.getLayerAt(6));
		ConnectionFactory.fullConnect(ann.getLayerAt(6), ann.getLayerAt(7));
		ConnectionFactory.fullConnect(ann.getLayerAt(7), ann.getLayerAt(8));
		ConnectionFactory.fullConnect(ann.getLayerAt(8), ann.getLayerAt(9));
		ConnectionFactory.fullConnect(ann.getLayerAt(9), ann.getLayerAt(10));
		ConnectionFactory.fullConnect(ann.getLayerAt(10), ann.getLayerAt(11));
		ConnectionFactory.fullConnect(ann.getLayerAt(11), ann.getLayerAt(12));
		ConnectionFactory.fullConnect(ann.getLayerAt(12), ann.getLayerAt(13));
		ConnectionFactory.fullConnect(ann.getLayerAt(13), ann.getLayerAt(14));
		ConnectionFactory.fullConnect(ann.getLayerAt(14), ann.getLayerAt(15));
		ConnectionFactory.fullConnect(ann.getLayerAt(15), ann.getLayerAt(16));
		ConnectionFactory.fullConnect(ann.getLayerAt(16), ann.getLayerAt(17));
		ConnectionFactory.fullConnect(ann.getLayerAt(0), ann.getLayerAt(ann.getLayersCount() - 1), false);

		ann.setInputNeurons(inputLayer.getNeurons());
		ann.setOutputNeurons(outputLayer.getNeurons());
		log.info("Neuron Network Add Layer: " + ann.getLayersCount());

		// Training
		// For training purposes, let's put together a DataSet by specifying the size of
		// both the input and resulting output vector:
		int inputSize = 512;
		int outputSize = 512;

		// DataSet adhering to the input and output
		log.info("Create DataSet...");

		int rows = 10;
		ds = createNewDataSet(inputSize, outputSize, rows);

		log.info("Save DataSet as File...");
		try {
			ds.saveAsTxt(file_path, ",");
		} catch (Exception ex) {
			log.error(file_path + " Not Found or Denied!");
		}
		// let's train our NeuralNetwork with the built in BackPropogation LearningRule:
		log.info("BackPropagation...");
		backPropagation = new BackPropagation();
		// backPropagation.setMaxIterations(1000);
		backPropagation.setMaxError(0.00);
		backPropagation.addListener(new LearningEventListener() {
			@Override
			public void handleLearningEvent(LearningEvent event) {
				displayLearningEvent(event);
			}
		});

		log.info("Learn from DataSet...");
		// ann.learn(ds, backPropagation);
		// ann.learn(ds, backPropagation);
		learningThread = new Thread() {
			@Override
			public void run() {
				ann.learn(ds, backPropagation);
				log.info("ANN Learn Welldone!");
			}
		};
		learningThread.setName("NeurophLearningThread");
		learningThread.setPriority(Thread.MAX_PRIORITY);
		learningThread.start();

		log.info("Neuron Netwok Ready to Calculate.");
		// ann.save(FILE_PATH);

	}

	DataSet createNewDataSet(int inputSize, int ouputSize, int rows) {
		DataSet ds = new DataSet(inputSize, ouputSize);
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				String serverSeed = DataGenerate.randomServerSeed();
				String serverSeedHash = DataGenerate.hash(serverSeed);
				double[] input = DataGenerate.hexToDoubleArr(serverSeedHash);
				double[] output = DataGenerate.hexToDoubleArr(serverSeed);
				ds.addRow(new DataSetRow(input, output));
			}
		}
		return ds;
	}

	public void btnClearClicked(MouseEvent e) {
		txtServerSeedHash.setText("");
		txtServerSeed.setText("");
	}

	public void displayLearningEvent(LearningEvent event) {
		lblCurrentIteration.setText("CurrentIteration: " + backPropagation.getCurrentIteration());
		log.info("CurrentIteration: " + backPropagation.getCurrentIteration());
		lblMaxError.setText("Set MaxError: " + backPropagation.getMaxError());
		log.info("Set MaxError: " + backPropagation.getMaxError());
		log.info("LearningRate: " + backPropagation.getLearningRate());
		log.info("MinErrorChange: " + backPropagation.getMinErrorChange());
		log.info("MinErrorChangeIterationsLimit: " + backPropagation.getMinErrorChangeIterationsLimit());
		// Server Seed:
		// 4c45e424ed2b247804e799df21b85c4bacf966a1fa94a7f46b09556e0ff1e694
		// Server Seed Hash:
		// 0a392fe66536a23f3cea0ec0b5f6e10d456ea2bcf61c2460b716bc301a7b482a
		if (nnCalculate(txtServerSeedHash.getText().trim()).equalsIgnoreCase(lblSha256Result.getText().trim())) {
			ann.stopLearning();
		}
	}

	public void btnDecryptActionPerformed(java.awt.event.ActionEvent evt) {
		btnDecrypt.setEnabled(false);
		String inputString = txtServerSeedHash.getText().trim();
		if (!inputString.equalsIgnoreCase("")) {
			String outputString = nnCalculate(inputString);
			txtServerSeed.setText(outputString);
		}
		btnDecrypt.setEnabled(true);
	}

	String nnCalculate(String inputString) {
		double[] input = DataGenerate.hexToDoubleArr(inputString.trim());
		ann.setInput(input);
		ann.calculate();
		networkOutputOne = ann.getOutput();

		// Display
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < networkOutputOne.length; i++) {
			sb.append((int) networkOutputOne[i]);
		}
		log.info(sb.toString());

		String binaryStr = sb.toString();
		int length = binaryStr.length();
		StringBuilder hexoutput = new StringBuilder();
		for (int i = 0; i < length; i += 8) {
			String oneByte = binaryStr.substring(i, i + 8);
			hexoutput.append(DataGenerate.binaryToHex(oneByte).trim());
		}
		log.info(hexoutput.toString());
		return hexoutput.toString();
	}

	/**
	 * Components Variable
	 */
	JButton btnDecrypt;
	JButton btnClear;
	JLabel lblServerSeedHash;
	JLabel lblServerSeed;
	JLabel lblCurrentIteration;
	JLabel lblMaxError;
	private JLabel lblSha256Result;
}
