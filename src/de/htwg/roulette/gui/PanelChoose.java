package de.htwg.roulette.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.htwg.roulette.controller.IController;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.IObserver;
 
public class PanelChoose extends JPanel implements IObserver {
	public PanelChoose(IController rController){
		this.setLayout(new BorderLayout());
	
		JPanel fake_top = new JPanel();
		fake_top.setPreferredSize(new Dimension(150,65));
		this.add(fake_top, BorderLayout.NORTH);
		
		JPanel right = new JPanel();
		right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));
		JButton btn = new JButton("1 - 12");
		btn.addActionListener(l -> {});
		right.add(btn);
		btn = new JButton("13 - 24");
		right.add(btn);
		btn = new JButton("25 - 36");
		right.add(btn);
		
		this.add(right, BorderLayout.EAST);
	}

	@Override
	public void update(Event e) {
		// TODO Auto-generated method stub
		
	}
}
