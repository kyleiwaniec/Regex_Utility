//package edu.mccc.cos210.khre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class KHRETester extends JFrame {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Object reEvaluator = new Object();					// your regex evaluator

	public KHRETester() {
		super("Regular Expression Tester");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(
			new MyJPanel(),
			BorderLayout.CENTER
		);
		setSize(512, 256);
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

			// result = reEvaluator.evaluate(jtfRegex, jtfString);
			result = !result;	// fake code

			repaint();
		}

		private class MyIcon implements Icon {

			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.setColor(result ? Color.GREEN : Color.RED);
				g2d.fillRect(0, 0, getIconWidth(), getIconHeight());
				g2d.dispose();
			}

			@Override
			public int getIconWidth() {
				return 128;
			}

			@Override
			public int getIconHeight() {
				return 64;
			}

		}
	}

}
