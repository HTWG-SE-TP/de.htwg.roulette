package de.htwg.roulette.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.roulette.controller.IController;
import de.htwg.roulette.model.bets.Black;
import de.htwg.roulette.model.events.BetAddedEvent;
import de.htwg.roulette.model.events.NextRoundEvent;
import de.htwg.roulette.tui.TextUI;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.IObserver;

@SuppressWarnings("serial")
public class PanelTable extends JPanel implements IObserver {
	private static final Logger LOGGER = LogManager.getLogger(PanelTable.class.getName());
	private JLabel[] fields = new JLabel[37];
	private JPanel[] lines = new JPanel[13];
	
	private IController rController;
	private PanelChoose chooseP;
	
	public PanelTable(IController cont, PanelChoose choose){
		rController = cont;
		chooseP = choose;
		
		this.setLayout(new GridLayout(13, 1));
		this.setBackground(Gui.STARBUCKS);
		
		for(int i = 0; i < fields.length; i++){
			fields[i] = new JLabel(String.format("%d", i), SwingConstants.CENTER);
			
			fields[i].setForeground(Color.WHITE);
			fields[i].setOpaque(true);
			fields[i].setPreferredSize(new Dimension(50, 50));
			fields[i].addMouseListener(new MouseAdapter() {
				@Override
			    public void mouseReleased(MouseEvent e) {
					selectNum(((JLabel) e.getSource()).getText());
			    }
			});
		}
		
		resetColors();
		
		lines[0] = new JPanel();
		lines[0].setBackground(Gui.STARBUCKS); //starbucks-green
		lines[0].add(fields[0]);
		for(int i = 0; i < lines.length-1; i++){
			lines[i+1] = new JPanel();
			lines[i+1].setBackground(Gui.STARBUCKS); //starbucks-green
			for(int k = 1; k <= 3; k++){
				lines[i+1].add(fields[i*3+k]);
			}
		}
		
		for(int i = 0; i < lines.length; i++){
			this.add(lines[i]);
		}
	}

	private void resetColors(){
		for(int i = 0; i < fields.length; i++){
			Black bet = new Black(i);
			
			if(bet.checkBet(i)){
				fields[i].setBackground(Color.BLACK);
			}else{
				fields[i].setBackground(Gui.RED4);
			}
			
		}
	}
	
	private void selectNum(String text){
		try{
			int num = Integer.parseInt(text);
			
			if (chooseP.isTableEnabled()) {
				if (chooseP.selectNum(num)) {
					fields[num].setBackground(Color.BLUE);
				} else {
					 resetColors();
				}
			}
		} catch (Exception ex){
			LOGGER.fatal(ex);
		}
	}

	@Override
	public void update(Event e) {
		if (e instanceof NextRoundEvent || e instanceof BetAddedEvent){
			resetColors(); 
		}
	}
}
