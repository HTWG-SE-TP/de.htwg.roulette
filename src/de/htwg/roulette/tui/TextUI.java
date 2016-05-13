package de.htwg.roulette.tui;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.roulette.controller.Controller;
import de.htwg.roulette.model.*;
import de.htwg.roulette.model.bets.*;

public class TextUI  {
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
		LOGGER.info("%-20s - this menu", "help");
		LOGGER.info("%-20s - add a player", "add [name] [$]");
		LOGGER.info("%-20s - remove a player", "remove [name]");
		LOGGER.info("%-20s - place a bet menu", "bet [name] [$]");
		LOGGER.info("%-20s - next round", "nr");
		LOGGER.info("%-20s - ....", "quit");
	}

	private void printRound() {
		LOGGER.info("======= Round %d =======\n", rController.getRound());
		for (User p : rController.getPlayers()) {
			LOGGER.info("Player %s: %d$\n", p.getName(), p.getBalance());
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

		String command = "";
		String[] splitCmd;
		
		inputLoop: 
		while (true) {
			command = scanner.nextLine();
			splitCmd = command.split(" ");

			// Validation
			if (splitCmd.length == 0)
				continue;

			// React
			switch (splitCmd[0]) {
			case "nr":
				break inputLoop;
			case "quit":
				LOGGER.info("Thanks for playing\n");
				return true;

			// ============================
			case "help":
				printHelp();
				break;
			case "add":
				if (splitCmd.length == 3) {
					String name = splitCmd[1];
					int dollar = parseInt(splitCmd[2]);
					if (dollar > 0) {
						if (rController.addPlayer(name, dollar)) {
							LOGGER.info("Player %s added!\n", name);
							break;
						}
					}
				}
				LOGGER.info("Add syntax invalid");
				break;

			case "remove":
				if (splitCmd.length == 2) {
					String name = splitCmd[1];
					if (rController.removePlayer(name)) {
						LOGGER.info("Player %s removed!\n", name);
						break;
					}

				}
				LOGGER.info("Remove syntax invalid");
				break;
				
			case "bet":
				if (splitCmd.length == 3) {
					String name = splitCmd[1];
					int dollar = parseInt(splitCmd[2]);
					if (dollar > 0) {
						AbstractBet bet = new Black(dollar);
						
					}
				}
				LOGGER.info("Add syntax invalid");
				break;
			default:
				LOGGER.info("Option not recognized. Use help to see all commands..");
			}
		}

		LOGGER.info("Round ended. Rollllllllling the thinggggggggggggggggg");
		int num = rController.nextRound();
		LOGGER.info("Picked number %d", num);
		
		return false;
	}
}
