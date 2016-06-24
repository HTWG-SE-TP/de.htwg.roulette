package de.htwg.roulette.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.htwg.roulette.controller.IController;
import de.htwg.roulette.model.bets.Black;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.IObserver;
 
@SuppressWarnings("serial")
public class PanelChoose extends JPanel implements IObserver {
	
	IController rController;
	String actualPlayer;
	
	public PanelChoose(IController rController, Gui g){
		this.rController = rController;
		
		this.setLayout(new BorderLayout());
	
		JPanel fake_top = new JPanel();
		setPanelColor(fake_top);
		fake_top.setPreferredSize(new Dimension(150,65));
		this.add(fake_top, BorderLayout.NORTH);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(12, 1));
		setPanelColor(buttons);
		
		JPanel thirds = new JPanel();
		setPanelColor(thirds);
		JButton btn = new JButton("1 - 12");
		fillButt(btn);
		btn.addActionListener(l -> {});
		thirds.add(btn);
		btn = new JButton("13 - 24");
		fillButt(btn);
		thirds.add(btn);
		btn = new JButton("25 - 36");
		fillButt(btn);
		thirds.add(btn);
		buttons.add(thirds);
		
		JPanel colors = new JPanel();
		setPanelColor(colors);
		btn = new JButton("Black");
		fillButt(btn);
		btn.addActionListener(e-> rController.placeBet(actualPlayer, new Black(1)));
		colors.add(btn);
		btn = new JButton("Red");
		fillButt(btn);
		colors.add(btn);
		buttons.add(colors);
		
		JPanel corner = new JPanel();
		setPanelColor(corner);
		btn = new JButton("Corner");
		fillButt(btn);
		corner.add(btn);
		buttons.add(corner);
		
		JPanel dozen = new JPanel();
		setPanelColor(dozen);
		btn = new JButton("Dozen");
		fillButt(btn);
		dozen.add(btn);
		buttons.add(dozen);
		
		JPanel modulo = new JPanel();
		setPanelColor(modulo);
		btn = new JButton("Even");
		fillButt(btn);
		modulo.add(btn);
		btn = new JButton("Odd");
		fillButt(btn);
		modulo.add(btn);
		buttons.add(modulo);
		
		JPanel ff = new JPanel();
		setPanelColor(ff);
		btn = new JButton("FirstFour");
		fillButt(btn);
		ff.add(btn);
		buttons.add(ff);
		
		JPanel halfs = new JPanel();
		setPanelColor(halfs);
		btn = new JButton("LowerHalf");
		fillButt(btn);
		halfs.add(btn);
		btn = new JButton("UpperHalf");
		fillButt(btn);
		halfs.add(btn);
		buttons.add(halfs);
		
		JPanel sn = new JPanel();
		setPanelColor(sn);
		btn = new JButton("SingleNum");
		fillButt(btn);
		sn.add(btn);
		buttons.add(sn);
		
		
		JPanel split = new JPanel();
		setPanelColor(split);
		btn = new JButton("Split");
		fillButt(btn);
		split.add(btn);
		buttons.add(split);
		
		JPanel street = new JPanel();
		setPanelColor(street);
		btn = new JButton("Street");
		fillButt(btn);
		street.add(btn);
		buttons.add(street);
		
		JPanel tw = new JPanel();
		setPanelColor(tw);
		btn = new JButton("Two Rows");
		fillButt(btn);
		tw.add(btn);
		buttons.add(tw);

		
		this.add(buttons, BorderLayout.EAST);
	}

	@Override
	public void update(Event e) {
		// TODO Auto-generated method stub
		
	}
	
	private void fillButt(JButton bt){
		bt.setPreferredSize(new Dimension(110, 50));
		bt.setFocusPainted(false);
		bt.setContentAreaFilled(false);
		bt.setForeground(Color.WHITE);
		bt.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
	}
	private void setPanelColor(JPanel pan){
		pan.setBackground(new Color(0x006633));
	}
	

	public void enableTable(int toSelect){
		
	}
	
	public boolean isTableEnabled(){
		return false;
	}
	
	public boolean selectNum(int num){
		return false;
	}
	
	public void resetSelect(){
		
	}
	

	public String getActualPlayer() {
		return actualPlayer;
	}

	public void setActualPlayer(String playerName) {
		this.actualPlayer = playerName;
	}
}
