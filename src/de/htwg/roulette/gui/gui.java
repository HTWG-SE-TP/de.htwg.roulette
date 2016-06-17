package de.htwg.roulette.gui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class gui extends JFrame implements ActionListener {
	JPanel mainPanel;
	JPanel tablePanel;
	JLabel[] fields = new JLabel[37];
	JPanel[] lines = new JPanel[13];
	
	
	public gui(){
		setTitle("SS16-02-Roulette");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		tablePanel = new JPanel();
		tablePanel.setLayout(new GridLayout(15, 1));
		tablePanel.setBackground(Color.GREEN);
		
		for(int i = 0; i < fields.length; i++){
			fields[i] = new JLabel(String.format("%d", i), SwingConstants.CENTER);
			fields[i].setBackground(Color.GREEN);
			fields[i].setOpaque(true);
			fields[i].setPreferredSize(new Dimension(50, 50));
		}
		
		for(int i = 0; i < lines.length; i++){
			lines[i] = new JPanel();
			tablePanel.add(lines[i]);
		}
		
		lines[0].add(fields[0]);
		for(int i = 0; i < lines.length-1; i++){
			for(int k = 1; k <= 3; k++){
				lines[i+1].add(fields[i*3+k]);
			}
		}
		
		mainPanel.add(tablePanel);
		
		this.add(mainPanel);
		pack();
		setResizable(true);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	
	}
	
	public static void main(final String[] args){
		JFrame myApp = new gui();
	}

}
