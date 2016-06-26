package de.htwg.roulette.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

import de.htwg.roulette.controller.IController;
import de.htwg.roulette.model.User;
import de.htwg.roulette.model.bets.*;
import de.htwg.roulette.model.events.BetAddedEvent;
import de.htwg.roulette.model.events.BetResultEvent;
import de.htwg.roulette.model.events.NextRoundEvent;
import de.htwg.roulette.model.events.PlayerEvent;
import de.htwg.util.observer.*;

@SuppressWarnings("serial")
public class Gui extends JFrame implements IObserver {
	private JPanel mainPanel;
	
	private PanelChoose choosePanel;
	private PanelTable tablePanel;
	private PanelStatistics statisticPanel;
	
	private JLabel[] fields = new JLabel[37];
	private JPanel[] lines = new JPanel[13];
	
	public static final Color STARBUCKS = new Color(0x006633);
	public static final Color GREY4 = new Color(0x5E5E5E);
	public static final Color RED4 = new Color(0x8b0000);
	
	public Gui(IController rController, IObservable observ){
		setTitle("SS16-02-Roulette");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 3));
		mainPanel.setBackground(new Color(0x006633));
		
		choosePanel = new PanelChoose(rController, this, observ);
		mainPanel.add(choosePanel);
	
		tablePanel = new PanelTable(rController, choosePanel);
		mainPanel.add(tablePanel);
	
		statisticPanel = new PanelStatistics(rController, choosePanel);
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
}
