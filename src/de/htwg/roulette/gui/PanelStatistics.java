package de.htwg.roulette.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.htwg.roulette.controller.IController;
import de.htwg.roulette.model.IUser;
import de.htwg.roulette.model.bets.IBet;
import de.htwg.roulette.model.events.BetAddedEvent;
import de.htwg.roulette.model.events.BetResultEvent;
import de.htwg.roulette.model.events.GuiLogEvent;
import de.htwg.roulette.model.events.NextRoundEvent;
import de.htwg.roulette.model.events.PlayerEvent;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.IObserver;

@SuppressWarnings("serial")
public class PanelStatistics extends JPanel implements IObserver {
	private IController rController;
	private PanelChoose chooseP;

	private DefaultListModel<String> playerList = new DefaultListModel<>();
	private DefaultListModel<String> betList = new DefaultListModel<>();
	private DefaultListModel<String> logList = new DefaultListModel<>();
	private JList<String> playerJList;

	private JLabel roundNo = new JLabel();
	private JLabel lastRoll = new JLabel();

	private int defaultPara = 1000;

	public PanelStatistics(IController cont, PanelChoose choose) {
		rController = cont;
		chooseP = choose;

		this.setLayout(new GridLayout(3, 1));

		// Players
		this.add(createPanelList("Players:", playerList));

		// Lower Panerl
		JPanel cmdPanel = new JPanel();
		cmdPanel.setLayout(new GridLayout(2, 2));
		cmdPanel.setBackground(Color.BLACK);
		JButton btn = createButt("Next Round");
		cmdPanel.add(btn);
		btn.addActionListener(e -> rController.nextRound());

		btn = createButt("Please Help Me");
		btn.addActionListener(e -> callHelpBox());
		cmdPanel.add(btn);

		btn = createButt("Add Player");
		btn.addActionListener(e -> {
			String in = JOptionPane.showInputDialog("Enter Name:");
			if (in != null && !in.isEmpty())
				rController.addPlayer(in, defaultPara);
		});

		cmdPanel.add(btn);

		btn = createButt("Remove Player");
		btn.addActionListener(e -> {
			String name = getName(playerJList.getSelectedValue());
			if (name != null)
				rController.removePlayer(name);
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

		// Bets
		this.add(createPanelList("Bets:", betList));
		// Init and dummy data
		updateInfos(0);
		updatePlayers();
		updateBets();

	}

	private void callHelpBox() {
		String text = "What the fuck is going on here?\n" + "add a player, place bets and finally:\n"
				+ "KEEP ROLLIN' ROLLIN'";
		JOptionPane.showMessageDialog(null, text);
	}

	private JPanel createPanelList(String name, DefaultListModel<String> model) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setBackground(Color.BLACK);

		JLabel lbl = new JLabel(name);
		lbl.setForeground(Color.red);
		panel.add(lbl, BorderLayout.NORTH);

		JList<String> list = new JList<>(model);
		list.setBackground(Color.BLACK);
		list.setForeground(Gui.RED4);
		panel.add(new JScrollPane(list), BorderLayout.CENTER);

		if (name.startsWith("Player")) {
			playerJList = list;
			playerJList.addListSelectionListener(l -> {
				String pname = getName(playerJList.getSelectedValue());
				if (pname != null)
					chooseP.setActualPlayer(pname);
			});
		} else {
			list = new JList<>(logList);
			list.setBackground(Color.BLACK);
			list.setForeground(Gui.RED4);
			list.setVisibleRowCount(5);
			JScrollPane scroller = new JScrollPane(list);
			new SmartScroller(scroller); //scroll to the bottom
			panel.add(scroller, BorderLayout.SOUTH);
		}
		return panel;
	}

	private JButton createButt(String text) {
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
			logList.addElement(bre.toString());

			updateBets();
			updatePlayers();
		} else if (e instanceof BetAddedEvent) {
			BetAddedEvent bae = (BetAddedEvent) e;
			if (!bae.result)
				logList.addElement("Failed to add bet. Did you select a player?");
			updateBets();
			updatePlayers();

		} else if (e instanceof PlayerEvent) {
			updatePlayers();

		} else if (e instanceof NextRoundEvent) {
			NextRoundEvent ne = (NextRoundEvent) e;
			
			updateInfos(ne.getRolledNo());
			updatePlayers();
			updateBets();
		} else if (e instanceof GuiLogEvent) {
			logList.addElement(e.toString());
		}
	}

	private String getName(String cellValue) {
		if (cellValue == null)
			return null;
		
		String[] split = cellValue.split(" ");
		if (split.length == 3)
			return split[1];
		return null;
	}

	private void updatePlayers() {
		playerList.clear();
		playerList.addElement(String.format("Bank %d$", rController.getBank().getBalance()));
		for (IUser p : rController.getPlayers()) {
			playerList.addElement(String.format("Player %s %d$", p.getName(), p.getBalance()));
		}
		chooseP.setActualPlayer("");
	}

	private void updateBets() {
		betList.clear();
		for (IUser p : rController.getPlayers()) {
			for (IBet bet : p.getBets()) {
				betList.addElement(String.format("Player %s: %d$ on %s", p.getName(), bet.getStake(), bet.getName()));
			}
		}
	}

	private void updateInfos(int rolled) {
		roundNo.setText("Round: " + rController.getRound());
		lastRoll.setText("Rolled: " + rolled);
	}
}
