package de.htwg.roulette.gui;


import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.htwg.roulette.controller.IController;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.IObservable;
import de.htwg.util.observer.IObserver;

@SuppressWarnings("serial")
public class Gui extends JFrame implements IObserver {
	private JPanel mainPanel;
	
	private PanelChoose choosePanel;
	private PanelTable tablePanel;
	private PanelStatistics statisticPanel;
	
	public static final Color STARBUCKS = new Color(0x006633);
	public static final Color GREY4 = new Color(0x5E5E5E);
	public static final Color RED4 = new Color(0x8b0000);
	
	public Gui(IController rController, IObservable observ){
		setTitle("SS16-02-Roulette");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 3));
		mainPanel.setBackground(new Color(0x006633));
		
		choosePanel = new PanelChoose(rController, observ);
		mainPanel.add(choosePanel);
	
		tablePanel = new PanelTable(choosePanel);
		mainPanel.add(tablePanel);
	
		statisticPanel = new PanelStatistics(rController, choosePanel);
		mainPanel.add(statisticPanel);

		observ.addObserver(choosePanel);
		observ.addObserver(tablePanel);
		observ.addObserver(statisticPanel);
		
		this.add(mainPanel);
		pack();
		setResizable(true);
	}

	public void show(){
		setVisible(true);
	}
		

	@Override
	public void update(Event e) {
		//Needed here?
	}
}
