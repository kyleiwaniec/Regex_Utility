package edu.mccc.cos210.regex;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.imageio.ImageIO;

import java.net.URL;

import java.io.*;
// import com.sun.image.codec.jpeg.*;

public class KHRETester extends JFrame {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Regex reEvaluator = new Regex();					// your regex evaluator

	public KHRETester() {
		super("Regular Expression Tester");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(
			new MyJPanel(),
			BorderLayout.CENTER
		);
		setSize(512, 512);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] sa) {
		new KHRETester();
	}

	private class MyJPanel extends JPanel implements ActionListener {

		private static final long serialVersionUID = 1L;
		private boolean result = false;
		private JTextField jtfRegex = new JTextField("RegularExpression", 32);
		private JTextField jtfString = new JTextField("TestString", 32);
		private JButton jButton = new JButton("Match");
		private JLabel jLabel = new JLabel(new MyIcon());
		public MyJPanel() {
			super();
			jButton.addActionListener(this);
			add(jtfRegex);
			add(jtfString);
			add(jLabel);
			add(jButton);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(
				"Evaluating '" +
				jtfRegex.getText() +
				"' using '" +
				jtfString.getText() +
				"'"
			);

			//result = reEvaluator.evaluate(jtfRegex, jtfString);
			try{
				reEvaluator.evaluate(jtfRegex.getText(), jtfString.getText());
			}catch (IOException ioe){
				ioe.printStackTrace();
			}
			
			result = !result;	// fake code

			repaint();
		}

		private class MyIcon implements Icon {

			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2d = (Graphics2D) g.create();

				try{

					// URL url = getClass().getResource("grumpycat.jpg");
					// File file = new File(url.getPath());

					File f = new File("/Volumes/Victoria/Rosemary/COS210/Regex_Utility/Regex/src/edu/mccc/cos210/regex/grumpycat.jpg");
					BufferedImage img = ImageIO.read(f);
					// int w = img.getWidth(null);
					// int h = img.getHeight(null);

					if(!result){
						g2d.setColor(Color.BLUE);
						g2d.fillRect(0, 0, getIconWidth(), getIconHeight());
						//g2d.fillRect(0, 0, w, h);
					}else{
						g2d.drawImage(img, null, 0,0);
					}
		
				}catch(IOException e){
					e.printStackTrace();
				}
				g2d.dispose();
			}

			@Override
			public int getIconWidth() {
				return 284;
			}

			@Override
			public int getIconHeight() {
				return 426;
			}

		}
	}

}
