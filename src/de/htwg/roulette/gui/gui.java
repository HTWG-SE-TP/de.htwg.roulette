package de.htwg.roulette.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

import de.htwg.roulette.model.bets.*;

public class gui extends JFrame {
	JPanel mainPanel;
	JPanel tablePanel;
	JPanel choosePanel;
	JPanel statisticPanel;
	JLabel[] fields = new JLabel[37];
	JPanel[] lines = new JPanel[13];
	Color starbucks = new Color(0x006633);
	
	
	public gui(){
		setTitle("SS16-02-Roulette");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 3));
		
		initializeChoosePanel();
		initializeTable();
		initializeStatisticPanel();
		
		this.add(mainPanel);
		pack();
		setResizable(true);
		setVisible(true);
	}

	
	private void initializeTable(){
		tablePanel = new JPanel();
		tablePanel.setLayout(new GridLayout(13, 1));
		
		for(int i = 0; i < fields.length; i++){
			Black bet = new Black(i);
			fields[i] = new JLabel(String.format("%d", i), SwingConstants.CENTER);
			
			if(bet.checkBet(i)){
				fields[i].setBackground(Color.BLACK);
			}else{
				fields[i].setBackground(Color.RED);
			}
			fields[i].setForeground(Color.WHITE);
			fields[i].setOpaque(true);
			fields[i].setPreferredSize(new Dimension(50, 50));
		}
		
		lines[0] = new JPanel();
		lines[0].setBackground(starbucks); //starbucks-green
		lines[0].add(fields[0]);
		for(int i = 0; i < lines.length-1; i++){
			lines[i+1] = new JPanel();
			lines[i+1].setBackground(starbucks); //starbucks-green
			for(int k = 1; k <= 3; k++){
				lines[i+1].add(fields[i*3+k]);
			}
		}
		
		for(int i = 0; i < lines.length; i++){
			tablePanel.add(lines[i]);
		}
		
		mainPanel.add(tablePanel);
	}
	
	private void initializeChoosePanel(){
		JButton btn;
		choosePanel = new JPanel();
		choosePanel.setLayout(new BorderLayout());
	
		JPanel fake_top = new JPanel();
		fake_top.setPreferredSize(new Dimension(150,65));
		choosePanel.add(fake_top, BorderLayout.NORTH);
		
		JPanel right = new JPanel();
		right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));
		btn = new JButton("1 - 12");
		btn.addActionListener(l -> {});
		right.add(btn);
		btn = new JButton("13 - 24");
		right.add(btn);
		btn = new JButton("25 - 36");
		right.add(btn);
		
		choosePanel.add(right, BorderLayout.EAST);
		
		mainPanel.add(choosePanel);
	}
	
	private void initializeStatisticPanel(){
		statisticPanel = new JPanel();
		
		
		mainPanel.add(statisticPanel);
	}
	
	public static void main(final String[] args){
		JFrame myApp = new gui();
	}

}
