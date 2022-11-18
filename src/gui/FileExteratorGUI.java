package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import logic.FileExteratorImpl;

import java.awt.event.ActionListener;

public class FileExteratorGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		createWindow();
	}

	private static void createWindow() {
		JFrame frame = new JFrame("File Extractor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createUI(frame);
		frame.setSize(560, 250);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	static String value = "";

	private static void createUI(final JFrame frame) {

		JPanel panel = new JPanel();
		LayoutManager layout = new FlowLayout();
		panel.setLayout(layout);

		// select file in storge
		JButton button = new JButton("Choose File");
		final JLabel label = new JLabel();

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int option = fileChooser.showOpenDialog(frame);
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					label.setText("File Selected: " + file.getAbsolutePath());
					System.out.println(file.getAbsolutePath());
					FileExteratorImpl fileExteratorImpl = new FileExteratorImpl();
					value = fileExteratorImpl.fileExteratorImpl(file.getAbsolutePath());
					System.out.println(value);
					try {
						FileWriter fw = new FileWriter(file.getAbsolutePath());
						fw.write(value);
						System.out.println(value);
						fw.close();
						value = " ";
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					label.setText("Open command canceled");
				}
			}
		});
		button.setForeground(new Color(56, 19, 120));
		button.setBackground(new Color(196, 192, 204));
		JLabel tempLabel = new JLabel();
		tempLabel.setText("File Exterator...                                   ");
		tempLabel.setForeground(new Color(56, 19, 120));
		panel.add(tempLabel);
		panel.add(button);
		panel.add(label);
		frame.getContentPane().add(panel, BorderLayout.BEFORE_LINE_BEGINS);
	}

}
