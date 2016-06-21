package de.htwg.roulette.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

import de.htwg.roulette.controller.IController;
import de.htwg.roulette.model.events.BetAddedEvent;
import de.htwg.roulette.model.events.BetResultEvent;
import de.htwg.roulette.model.events.NextRoundEvent;
import de.htwg.roulette.model.events.PlayerEvent;
import de.htwg.util.observer.*;

public class PanelStatistics extends JPanel implements IObserver {

	DefaultListModel<String> playerList = new DefaultListModel<>();
	DefaultListModel<String> betList = new DefaultListModel<>();
	JLabel roundNo = new JLabel("Round: 0");
	
	public PanelStatistics(IController rController){
		this.setLayout(new GridLayout(3,1));
		
		//Players
		this.add(createPanelList("Players:", playerList));
		
		//Bets
		this.add(createPanelList("Bets:", betList));
		
		JPanel roundPanel = new JPanel();
		roundPanel.setLayout(new BorderLayout());
		roundPanel.add(roundNo, BorderLayout.NORTH);
		this.add(roundPanel);
			
		
		playerList.addElement("John Doe");
		betList.addElement("Deutschland vor noch ein Tor");
	}
	
	
	private JPanel createPanelList(String name, DefaultListModel<String> model){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(new JLabel(name), BorderLayout.NORTH);	
		panel.add(new JList<>(model), BorderLayout.CENTER);
		return panel;
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
}
