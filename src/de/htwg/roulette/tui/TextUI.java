package de.htwg.roulette.tui;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.roulette.controller.BetResultEvent;
import de.htwg.roulette.controller.Controller;
import de.htwg.roulette.model.*;
import de.htwg.roulette.model.bets.*;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.IObserver;

public class TextUI implements IObserver {
	private Controller rController;
	private Scanner scanner;
	private static final Logger LOGGER = LogManager.getLogger(TextUI.class.getName());

	public TextUI(Controller cont) {
		rController = cont;
		scanner = new Scanner(System.in);
	}

	public void printUI() {
		LOGGER.info("Welcome to Htwg Roulette, the best roulette game ever made");
		printHelp();
	}

	private void printHelp() {
		LOGGER.info("Options during the game:");
		LOGGER.info(String.format("%-20s - this menu", "help"));
		LOGGER.info(String.format("%-20s - add a player", "add [name] [$]"));
		LOGGER.info(String.format("%-20s - remove a player", "remove [name]"));
		LOGGER.info(String.format("%-20s - place a bet menu", "bet [name] [$]"));
		LOGGER.info(String.format("%-20s - next round", "nr"));
		LOGGER.info(String.format("%-20s - ....", "quit"));
	}

	private void printRound() {
		LOGGER.info(String.format("======= Round %d =======", rController.getRound()));
		for (User p : rController.getPlayers()) {
			LOGGER.info(String.format("Player %s: %d$", p.getName(), p.getBalance()));
		}
	}

	private int parseInt(String input) {
		try {
			return Integer.parseInt(input);
		} catch (Exception ex) {
			LOGGER.debug(ex);
			return -1;
		}
	}

	public boolean process() {
		printRound();

		boolean exit = inputMenu();
		if (exit)
			return true;

		LOGGER.info("Round ended. Rollllllllling the thinggggggggggggggggg");
		int num = rController.nextRound();
		LOGGER.info(String.format("Picked number %d", num));

		return false;
	}

	private boolean inputMenu() {
		while (true) {
			String command = scanner.nextLine();
			String[] splitCmd = command.split(" ");
			
			// Validation
			if (command.isEmpty() || splitCmd.length == 0)
				continue;

			// React
			switch (splitCmd[0]) {

			case "nr":
				return false;
			case "quit":
				LOGGER.info("Thanks for playing");
				return true;

			// ============================
			case "help":
				printHelp();
				break;
			case "add":
				parseAddPlayer(splitCmd);
				break;

			case "remove":
				parseRemovePlayer(splitCmd);
				break;

			case "bet":
				parseBet(splitCmd);
				break;
			default:
				LOGGER.debug(command);
				LOGGER.info("Option not recognized. Use help to see all commands..");
			}
		}
	}

	private void parseAddPlayer(String[] splitCmd) {
		if (splitCmd.length == 3) {
			String name = splitCmd[1];
			int dollar = parseInt(splitCmd[2]);
			if (dollar > 0 && rController.addPlayer(name, dollar)) {
				LOGGER.info(String.format("Player %s added!%n", name));
				return;

			}
		}
		LOGGER.info("Add syntax invalid");
	}

	private void parseRemovePlayer(String[] splitCmd) {
		if (splitCmd.length == 2) {
			String name = splitCmd[1];
			if (rController.removePlayer(name)) {
				LOGGER.info(String.format("Player %s removed!", name));
				return;
			}

		}
		LOGGER.info("Remove syntax invalid");
	}

	private void parseBet(String[] splitCmd) {
		if (splitCmd.length == 3) {
			String name = splitCmd[1];
			int dollar = parseInt(splitCmd[2]);
			if (dollar > 0) {
				printBetMenu();
				AbstractBet bet = parseBetOptions(dollar);

				if (rController.placeBet(name, bet)) {
					LOGGER.info("Bet added!");
					return;
				}

			}
		}
		LOGGER.info("Bet syntax invalid/Bet not recognized");
	}

	private void printBetMenu() {
		LOGGER.info("Select your bet!");
		LOGGER.info("Bets: num - Single Number, red - Red Numbers, black - Black Numbers");
	}

	private AbstractBet parseBetOptions(int dollar) {
		AbstractBet bet = null;
		String betName = scanner.nextLine();

		switch (betName) {
		case "num":
			LOGGER.info("Wich number?");
			int num = scanner.nextInt();
			bet = new SingleNumber(dollar, num);
			break;
		case "red":
			bet = new Red(dollar);
			break;
		case "black":
			bet = new Black(dollar);
			break;
		default:
			LOGGER.info("Unknown bet option " + betName);
		}

		return bet;
	}

	@Override
	public void update(Event e) {
		if (e instanceof BetResultEvent){
			LOGGER.info(e);
		}
	}
}
