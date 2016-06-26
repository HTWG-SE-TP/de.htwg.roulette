package de.htwg.roulette.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

import de.htwg.roulette.controller.IController;
import de.htwg.roulette.model.IUser;
import de.htwg.roulette.model.bets.*;
import de.htwg.roulette.model.events.GuiLogEvent;
import de.htwg.roulette.model.events.NextRoundEvent;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.IObservable;
import de.htwg.util.observer.IObserver;
 
@SuppressWarnings("serial")
public class PanelChoose extends JPanel implements IObserver {
	private int minMoney = 0;
	private int maxMoney = 500;
	
	private IController rController;
	private IObservable observer;
	
	private IUser actualPlayer;
	private JSlider setMoney;
	private boolean isEnabled = false;
	private int toSelect = 0;
	private List<Integer> selectedNums;
	
	
	public PanelChoose(IController cont, Gui g, IObservable observ){
		rController = cont;
		observer = observ;
		
		selectedNums = new LinkedList<>();
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
		btn.addActionListener(e->
			rController.placeBet(getActualPlayer(), new Dozen(setMoney.getValue(), Dozen.Flag.Premier)));
		fillButt(btn);
		btn.addActionListener(l -> {});
		thirds.add(btn);
		btn = new JButton("13 - 24");
		btn.addActionListener(e->
			rController.placeBet(getActualPlayer(), new Dozen(setMoney.getValue(), Dozen.Flag.Milieu)));
		fillButt(btn);
		thirds.add(btn);
		btn = new JButton("25 - 36");
		btn.addActionListener(e->
			rController.placeBet(getActualPlayer(), new Dozen(setMoney.getValue(), Dozen.Flag.Dernier)));
		fillButt(btn);
		thirds.add(btn);
		buttons.add(thirds);
		
		JPanel colors = new JPanel();
		setPanelColor(colors);
		btn = new JButton("Black");
		fillButt(btn);
		btn.addActionListener(
				e->rController.placeBet(getActualPlayer(), new Black(setMoney.getValue())));
		colors.add(btn);
		btn = new JButton("Red");
		btn.addActionListener(
				e->rController.placeBet(getActualPlayer(), new Black(setMoney.getValue())));
		fillButt(btn);
		colors.add(btn);
		buttons.add(colors);
		
		JPanel corner = new JPanel();
		setPanelColor(corner);
		btn = new JButton("Corner");
		btn.addActionListener(e->enableTable(4));
		fillButt(btn);
		corner.add(btn);
		buttons.add(corner);
		
		JPanel modulo = new JPanel();
		setPanelColor(modulo);
		btn = new JButton("Even");
		fillButt(btn);
		btn.addActionListener(
				e->rController.placeBet(getActualPlayer(), new Even(setMoney.getValue())));
		modulo.add(btn);
		btn = new JButton("Odd");
		fillButt(btn);
		btn.addActionListener(
				e->rController.placeBet(getActualPlayer(), new Odd(setMoney.getValue())));
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
		btn.addActionListener(
				e->rController.placeBet(getActualPlayer(), new LowerHalf(setMoney.getValue())));
		fillButt(btn);
		halfs.add(btn);
		btn = new JButton("UpperHalf");
		fillButt(btn);
		btn.addActionListener(
				e->rController.placeBet(getActualPlayer(), new UpperHalf(setMoney.getValue())));
		halfs.add(btn);
		buttons.add(halfs);
		
		JPanel sn = new JPanel();
		setPanelColor(sn);
		btn = new JButton("SingleNum");
		btn.addActionListener(e-> enableTable(1));
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

		JPanel slider = new JPanel();
		setMoney = new JSlider(JSlider.HORIZONTAL, minMoney, maxMoney, minMoney);
		setMoney.setBackground(Gui.STARBUCKS);
		setMoney.setForeground(Color.WHITE);
		setMoney.setPaintLabels(true);
		setMoney.setMinorTickSpacing(0);
		setMoney.setMajorTickSpacing(100);
		updateSlider();
		slider.add(setMoney);
		buttons.add(setMoney);
		
		this.add(buttons, BorderLayout.EAST);
	}

	
	private void fillButt(JButton bt){
		bt.setPreferredSize(new Dimension(110, 50));
		bt.setFocusPainted(false);
		bt.setContentAreaFilled(false);
		bt.setForeground(Color.WHITE);
		bt.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
	}
	private void setPanelColor(JPanel pan){
		pan.setBackground(Gui.STARBUCKS);
	}
	

	public void enableTable(int toSelect){
		resetSelect();
		isEnabled = true;
		this.toSelect = toSelect;
		
		observer.notifyObservers(new GuiLogEvent(String.format("Please select %d numbers for this bet", toSelect)));
	}
	
	public boolean isTableEnabled(){
		return isEnabled;
	}
	
	public boolean selectNum(int num){
		selectedNums.add(num);
		if(selectedNums.size() == toSelect) {
			return false;
		}
		return true;
	}
	
	public void resetSelect(){
		isEnabled = false;
		selectedNums.clear();
	}
	

	public String getActualPlayer() {
		return actualPlayer.getName();
	}

	public void setActualPlayer(String playerName) {
		for (IUser u : rController.getPlayers()){
			if (u.getName().equals(playerName)){
					actualPlayer = u;
					maxMoney = actualPlayer.getBalance();
					updateSlider();
			}
		}
	}
	
	private void updateSlider(){
		setMoney.setMinimum(minMoney);
		setMoney.setMaximum(maxMoney);
		setMoney.createStandardLabels((maxMoney - minMoney) / 5);
		setMoney.invalidate();
	}


	@Override
	public void update(Event e) {
		if (e instanceof NextRoundEvent){
			resetSelect();
		}
	}
}
