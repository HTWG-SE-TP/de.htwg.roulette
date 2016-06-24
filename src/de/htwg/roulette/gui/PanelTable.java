package de.htwg.roulette.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

import de.htwg.roulette.controller.IController;
import de.htwg.roulette.model.bets.Black;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.IObserver;

@SuppressWarnings("serial")
public class PanelTable extends JPanel implements IObserver {
	JLabel[] fields = new JLabel[37];
	JPanel[] lines = new JPanel[13];
	Color starbucks = new Color(0x006633);
	Color red4 = new Color(0x8b0000);
	
	
	public PanelTable(IController rController, PanelChoose choose){
		this.setLayout(new GridLayout(13, 1));
		this.setBackground(starbucks);
		
		for(int i = 0; i < fields.length; i++){
			Black bet = new Black(i);
			fields[i] = new JLabel(String.format("%d", i), SwingConstants.CENTER);
			
			if(bet.checkBet(i)){
				fields[i].setBackground(Color.BLACK);
			}else{
				fields[i].setBackground(red4);
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
			this.add(lines[i]);
		}
	}


	@Override
	public void update(Event e) {
		// TODO Auto-generated method stub
		
	}
}
