package de.htwg.roulette.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

import de.htwg.roulette.controller.IController;
import de.htwg.roulette.model.IUser;
import de.htwg.roulette.model.bets.IBet;
import de.htwg.roulette.model.events.BetAddedEvent;
import de.htwg.roulette.model.events.BetResultEvent;
import de.htwg.roulette.model.events.NextRoundEvent;
import de.htwg.roulette.model.events.PlayerEvent;
import de.htwg.util.observer.*;

@SuppressWarnings("serial")
public class PanelStatistics extends JPanel implements IObserver {

	DefaultListModel<String> playerList = new DefaultListModel<>();
	DefaultListModel<String> betList = new DefaultListModel<>();
	JLabel roundNo = new JLabel();
	JLabel lastRoll = new JLabel();
	IController rController;
	static Color grey4 = new Color(0x5E5E5E);
	Color red4 = new Color(0x8b0000);
	private int defaultPara = 1000;
	
	public PanelStatistics(IController cont){
		rController = cont;
		
		this.setLayout(new GridLayout(3,1));
		
		//Players
		this.add(createPanelList("Players:", playerList));
		

		
		//Lower Panerl
		JPanel cmdPanel = new JPanel();
		cmdPanel.setLayout(new GridLayout(2, 2));
		cmdPanel.setBackground(Color.BLACK);
		JButton btn = createButt("Next Round");
		cmdPanel.add(btn);
		btn.addActionListener(e -> cont.nextRound());
		
		btn = createButt("Please Help Me");
		btn.addActionListener(e -> callHelpBox());
		cmdPanel.add(btn);
		
				
		btn = createButt("Add Player");
		btn.addActionListener(e -> {
			String in = JOptionPane.showInputDialog("Enter Name:");
			if(in != null)
				cont.addPlayer(in, defaultPara);
		});
		
		cmdPanel.add(btn);
		
		btn = createButt("Remove Player");
		btn.addActionListener(e -> {
			
		});
		cmdPanel.add(btn);
		

		
		JPanel roundPanel = new JPanel();
		roundPanel.setLayout(new BorderLayout());
		roundPanel.setBackground(Color.BLACK);
		
		roundNo.setForeground(Color.red);
		lastRoll.setForeground(Color.red);
		
		roundPanel.add(roundNo, BorderLayout.NORTH);
		roundPanel.add(lastRoll, BorderLayout.SOUTH);
		roundPanel.add(cmdPanel, BorderLayout.CENTER);
		this.add(roundPanel);
		
		//Bets
		this.add(createPanelList("Bets:", betList));
		//Init and dummy data
		updateInfos(0);
		updatePlayers();
		updateBets();
		
	}
	
	
	private void callHelpBox() {
		String text = "What the fuck is going on here?\n"
				+ "add a player, place bets and finally:\n"
				+ "KEEP ROLLIN' ROLLIN'";
		JOptionPane.showMessageDialog(null, text);
	}


	private JPanel createPanelList(String name, DefaultListModel<String> model){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setBackground(Color.BLACK);
		
		JLabel lbl = new JLabel(name);
		lbl.setForeground(Color.red);
		panel.add(lbl, BorderLayout.NORTH);	
		
		JList<String> list = new JList<>(model);
		list.setBackground(Color.BLACK);
		list.setForeground(red4);
		panel.add(list, BorderLayout.CENTER);
		return panel;
	}
	
	private JButton createButt(String text){
		JButton btn = new JButton(text);
		btn.setForeground(Color.WHITE);
		btn.setContentAreaFilled(false);
		btn.setBorder(BorderFactory.createLineBorder(Color.white));
		btn.setFocusPainted(false);
		return btn;
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
			
			updateBets();
			updatePlayers();
		} else if (e instanceof BetAddedEvent) {
			updateBets();
			
		} else if (e instanceof PlayerEvent) {
			updatePlayers();
				
		} else if (e instanceof NextRoundEvent) {
			NextRoundEvent ne = (NextRoundEvent) e;
			updateInfos(ne.getRolledNo());
		}
	}
	
	
	
	private void updatePlayers(){
		playerList.clear();
		playerList.addElement(String.format("Bank: %d$", rController.getBank().getBalance()));
		for (IUser p : rController.getPlayers()) {
			playerList.addElement(String.format("Player %s: %d$", p.getName(), p.getBalance()));
		}
	}
	
	private void updateBets(){
		betList.clear();
		for (IUser p : rController.getPlayers()) {
			for(IBet bet : p.getBets()){
				betList.addElement(String.format("Player %s: %d$ on %s", p.getName(), bet.getStake(), bet.getName()));
			}
		}
	}
	
	private void updateInfos(int rolled){
		roundNo.setText("Round: " + rController.getRound());
		lastRoll.setText("Rolled: " + rolled);
	}
}
