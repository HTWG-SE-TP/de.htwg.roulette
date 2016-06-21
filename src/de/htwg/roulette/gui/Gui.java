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
import de.htwg.roulette.model.events.BetAddedEvent;
import de.htwg.roulette.model.events.BetResultEvent;
import de.htwg.roulette.model.events.NextRoundEvent;
import de.htwg.roulette.model.events.PlayerEvent;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.IObserver;

public class Gui extends JFrame implements IObserver {
	JPanel mainPanel;
	JPanel tablePanel;
	JPanel choosePanel;
	JPanel statisticPanel;
	JLabel[] fields = new JLabel[37];
	JPanel[] lines = new JPanel[13];
	DefaultListModel<String> playerList = new DefaultListModel<>();
	DefaultListModel<String>betList = new DefaultListModel<>();
	JLabel roundNo = new JLabel("Round: 0");
	Color starbucks = new Color(0x006633);
	
	
	
	public Gui(){
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
	
	private JPanel createPanelList(String name, DefaultListModel model){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(new JLabel(name), BorderLayout.NORTH);	
		panel.add(new JList<>(model), BorderLayout.CENTER);
		return panel;
	}
	private void initializeStatisticPanel(){
		statisticPanel = new JPanel();
		statisticPanel.setLayout(new GridLayout(3,1));
		
		//Players
		statisticPanel.add(createPanelList("Players:", playerList));
		
		//Bets
		statisticPanel.add(createPanelList("Bets:", betList));
		
		JPanel roundPanel = new JPanel();
		roundPanel.setLayout(new BorderLayout());
		roundPanel.add(roundNo, BorderLayout.NORTH);
		statisticPanel.add(roundPanel);
		
		mainPanel.add(statisticPanel);
		
		
		playerList.addElement("John Doe");
		betList.addElement("Deutschland vor noch ein Tor");
	}
	
	@Override
	public void update(Event e) {
		String tmp;
		if (e instanceof BetResultEvent) {
			BetResultEvent bre = (BetResultEvent) e;
			if (bre.result >= 0) {
				tmp = "won";
			} else {
				tmp = "lost";
			}

			String resultInfo = String.format("%s %s %d$ with his bet on %s.", bre.user, tmp, bre.result,
					bre.bet.getName());
			
		} else if (e instanceof BetAddedEvent) {
			BetAddedEvent bae = (BetAddedEvent) e;
			if (bae.result){
				
			} else {
				
			}
		} else if (e instanceof PlayerEvent) {
			PlayerEvent pe = (PlayerEvent) e;
			tmp = pe.added ? "added" : "removed";
			if (pe.result){
				
			} else {
				
			}
				
		} else if (e instanceof NextRoundEvent) {
			NextRoundEvent ne = (NextRoundEvent) e;
			roundNo.setText("Round: " + ne.getRoundNo());
		}
		
	}
	
	public static void main(final String[] args){
		new Gui();
	}


	

}
