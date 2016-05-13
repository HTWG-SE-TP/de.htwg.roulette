package de.htwg.roulette.tui;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.roulette.controller.Controller;
import de.htwg.roulette.model.*;
import de.htwg.roulette.model.bets.*;

public class TextUI {
	private Controller rController;
	private Scanner scanner;
	private static final Logger log = LogManager.getLogger(TextUI.class.getName());

	public TextUI(Controller cont) {
		rController = cont;
		scanner = new Scanner(System.in);
	}

	public void printUI() {
		log.info("Welcome to Htwg Roulette, the best roulette game ever made");
		printHelp();
	}

	private void printHelp() {
		log.info("Options during the game:");
		log.info(String.format("%-20s - this menu", "help"));
		log.info(String.format("%-20s - add a player", "add [name] [$]"));
		log.info(String.format("%-20s - remove a player", "remove [name]"));
		log.info(String.format("%-20s - place a bet menu", "bet [name] [$]"));
		log.info(String.format("%-20s - next round", "nr"));
		log.info(String.format("%-20s - ....", "quit"));
	}

	private void printRound() {
		log.info(String.format("======= Round %d =======\n", rController.getRound()));
		for (User p : rController.getPlayers()) {
			log.info(String.format("Player %s: %d$\n", p.getName(), p.getBalance()));
		}
	}

	private int parseInt(String input) {
		try {
			return Integer.parseInt(input);
		} catch (Exception ex) {
			return -1;
		}
	}

	public boolean process() {
		printRound();

		boolean exit = inputMenu();
		if (exit)
			return true;

		log.info("Round ended. Rollllllllling the thinggggggggggggggggg");
		int num = rController.nextRound();
		log.info(String.format("Picked number %d", num));

		return false;
	}

	private boolean inputMenu() {
		inputLoop: while (true) {
			String command = scanner.nextLine();
			String[] splitCmd = command.split(" ");

			// Validation
			if (splitCmd.length == 0)
				continue;

			// React
			switch (splitCmd[0]) {

			case "nr":
				break inputLoop;
			case "quit":
				log.info("Thanks for playing\n");
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
				log.info("Option not recognized. Use help to see all commands..");
			}
		}

		return false;
	}

	private void parseAddPlayer(String[] splitCmd) {
		if (splitCmd.length == 3) {
			String name = splitCmd[1];
			int dollar = parseInt(splitCmd[2]);
			if (dollar > 0) {
				if (rController.addPlayer(name, dollar)) {
					log.info(String.format("Player %s added!%n", name));
					return;
				}
			}
		}
		log.info("Add syntax invalid");
	}

	private void parseRemovePlayer(String[] splitCmd) {
		if (splitCmd.length == 2) {
			String name = splitCmd[1];
			if (rController.removePlayer(name)) {
				log.info(String.format("Player %s removed!\n", name));
				return;
			}

		}
		log.info("Remove syntax invalid");
	}

	private void parseBet(String[] splitCmd) {
		if (splitCmd.length == 3) {
			String name = splitCmd[1];
			int dollar = parseInt(splitCmd[2]);
			if (dollar > 0) {
				AbstractBet bet = new Black(dollar);

			}
		}
		log.info("Bet syntax invalid");
	}
}
