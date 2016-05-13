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
		log.info("%-20s - this menu", "help");
		log.info("%-20s - add a player", "add [name] [$]");
		log.info("%-20s - remove a player", "remove [name]");
		log.info("%-20s - place a bet menu", "bet [name] [$]");
		log.info("%-20s - next round", "nr");
		log.info("%-20s - ....", "quit");
	}

	private void printRound() {
		log.info("======= Round %d =======\n", rController.getRound());
		for (User p : rController.getPlayers()) {
			log.info("Player %s: %d$\n", p.getName(), p.getBalance());
		}
	}

	private int parseInt(String input) {
		try {
			return Integer.parseInt(input);
		} catch (Exception ex) {
			log.debug(ex);
			return -1;
		}
	}

	public boolean process() {
		printRound();

		String command = "";
		String[] splitCmd;
		inputLoop: while (true) {
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
				log.info("Thanks for playing\n");
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
							log.info("Player %s added!\n", name);
							break;
						}
					}
				}
				log.info("Add syntax invalid");
				break;

			case "remove":
				if (splitCmd.length == 2) {
					String name = splitCmd[1];
					if (rController.removePlayer(name)) {
						log.info("Player %s removed!\n", name);
						break;
					}

				}
				log.info("Remove syntax invalid");
				break;
				
			case "bet":
				if (splitCmd.length == 3) {
					String name = splitCmd[1];
					int dollar = parseInt(splitCmd[2]);
					if (dollar > 0) {
						AbstractBet bet = new Black(dollar);
						
					}
				}
				log.info("Add syntax invalid");
				break;
			default:
				log.info("Option not recognized. Use help to see all commands..");
			}
		}

		log.info("Round ended. Rollllllllling the thinggggggggggggggggg");
		int num = rController.nextRound();
		log.info("Picked number %d", num);
		
		return false;
	}
}
