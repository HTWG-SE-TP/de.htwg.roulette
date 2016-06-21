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

import de.htwg.roulette.controller.IController;
import de.htwg.roulette.model.bets.*;
import de.htwg.roulette.model.events.BetAddedEvent;
import de.htwg.roulette.model.events.BetResultEvent;
import de.htwg.roulette.model.events.NextRoundEvent;
import de.htwg.roulette.model.events.PlayerEvent;
import de.htwg.util.observer.*;

public class Gui extends JFrame implements IObserver {
	JPanel mainPanel;
	
	PanelChoose choosePanel;
	PanelTable tablePanel;
	PanelStatistics statisticPanel;
	
	JLabel[] fields = new JLabel[37];
	JPanel[] lines = new JPanel[13];
	Color starbucks = new Color(0x006633);
	
	
	
	public Gui(IController rController, IObservable observ){
		setTitle("SS16-02-Roulette");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 3));
		
		choosePanel = new PanelChoose(rController);
		mainPanel.add(choosePanel);
	
		tablePanel = new PanelTable(rController);
		mainPanel.add(tablePanel);
	
		statisticPanel = new PanelStatistics(rController);
		mainPanel.add(statisticPanel);

		observ.addObserver(choosePanel);
		observ.addObserver(tablePanel);
		observ.addObserver(statisticPanel);
		
		this.add(mainPanel);
		pack();
		setResizable(true);
		setVisible(true);
	}

		

	@Override
	public void update(Event e) {
		//Needed here?
	}
	
	public static void main(final String[] args){
		new Gui(null, new Observable());
	}


	

}
