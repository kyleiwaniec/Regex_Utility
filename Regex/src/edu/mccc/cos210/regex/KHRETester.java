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

import javax.swing.*;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.imageio.ImageIO;

import java.net.URL;
import java.io.*;

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
		setSize(734, 734);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	public static String[] sa;
	 

	public static void main(String[] sa) {
		KHRETester.sa = sa;
		new KHRETester();
	}

	private class MyJPanel extends JPanel implements ActionListener {

		private static final long serialVersionUID = 1L;
		private boolean result = false;
		private JTextField jtfRegex = new JTextField("cat(a-d)", 32);
		private JTextField jtfString = new JTextField("12cat", 32);

		private JButton jBtn_Match = new JButton("Match");
		private JButton jBtn_Find = new JButton("Find Next");
		private JButton jBtn_Reset = new JButton("Reset");
		
		private URL grumpy = getClass().getResource("/img/grumpycat.jpg");
		private URL dancing = getClass().getResource("/img/dancingCat.gif");
		private JLabel jLabel1 = new JLabel(new ImageIcon(grumpy));
		private JLabel jLabel2 = new JLabel(new ImageIcon(dancing));
		private int icon;

		public MyJPanel() {
			super();
			jBtn_Match.addActionListener(this);
			jBtn_Find.addActionListener(this);
			jBtn_Reset.addActionListener(this);

			jLabel1.setVisible(false);
			jLabel2.setVisible(false);

			add(jtfRegex);
			add(jtfString);
			add(jBtn_Match);
			add(jBtn_Find);
			add(jBtn_Reset);
			add(jLabel1);
			add(jLabel2);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == jBtn_Match){
				try{
					result = reEvaluator.match(jtfRegex.getText(), jtfString.getText());
					icon = result ? 1 : 2;
				}catch (IOException ioe){
					ioe.printStackTrace();
				}
			}else if (e.getSource() == jBtn_Find){
				try{
					result = reEvaluator.find(jtfRegex.getText(), jtfString.getText(), reEvaluator.getStart());
					icon = result ? 1 : 2;
				}catch (IOException ioe){
					ioe.printStackTrace();
				}
			}else if (e.getSource() == jBtn_Reset){
				reEvaluator.reset();
				icon = 3;
			}

			System.out.println(
				"Evaluating '" +
				jtfRegex.getText() +
				"' using '" +
				jtfString.getText() +
				"'"
			);
			switch(icon){
				case 1 : jLabel1.setVisible(false);
						 jLabel2.setVisible(true);
				break;
				case 2 : jLabel1.setVisible(true);
						 jLabel2.setVisible(false);
				break;
				case 3 : jLabel1.setVisible(false);
						 jLabel2.setVisible(false);				 
			}
			repaint();
		}


		private class MyIcon implements Icon {
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {

				// URL grumpy = getClass().getResource("/img/grumpycat.jpg");
				// URL dancing = getClass().getResource("/img/dancingCat.gif");

				// ImageIcon img;

				// 	if(result){
				// 		img = new ImageIcon(dancing);
				// 	}else{
				// 		img = new ImageIcon(grumpy);
				// 	}


				Graphics2D g2d = (Graphics2D) g.create();

				try{

					URL dancing = getClass().getResource("/img/dancingCat.gif");
					BufferedImage img2 = ImageIO.read(dancing);
					// int w = img.getWidth(null);
					// int h = img.getHeight(null);

					URL grumpy = getClass().getResource("/img/grumpycat.jpg");
	    			BufferedImage img1 = ImageIO.read(grumpy);

					if(result){
						g2d.drawImage(img2, null, 0,0);
					}else{
						g2d.drawImage(img1, null, 0,0);
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
