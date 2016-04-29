package de.htwg.roulette.tui;

import java.util.*;

import de.htwg.roulette.controller.Controller;
import de.htwg.roulette.model.*;
import de.htwg.roulette.model.bets.*;

public class TextUI {
	private Controller rController;
	private Scanner scanner;

	public TextUI(Controller cont) {
		rController = cont;
		scanner = new Scanner(System.in);
	}

	public void printUI() {
		System.out.println("Welcome to Htwg Roulette, the best roulette game ever made");
		printHelp();
	}

	private void printHelp() {
		System.out.println("Options during the game:");
		System.out.printf("%-20s - this menu\n", "help");
		System.out.printf("%-20s - add a player\n", "add [name] [$]");
		System.out.printf("%-20s - remove a player\n", "remove [name]");
		System.out.printf("%-20s - place a bet menu\n", "bet [name] [$]");
		System.out.printf("%-20s - next round\n", "nr");
		System.out.printf("%-20s - ....\n", "quit");
	}

	private void printRound() {
		System.out.printf("======= Round %d =======\n", rController.getRound());
		for (User p : rController.getPlayers()) {
			System.out.printf("Player %s: %d$\n", p.getName(), p.getBalance());
		}
	}

	private int validateDollars(String input) {
		try {
			return Integer.parseInt(input);
		} catch (Exception ex) {
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
				System.out.println("Thanks for playing");
				return true;

			// ============================
			case "help":
				printHelp();
				break;
			case "add":
				if (splitCmd.length == 3) {
					String name = splitCmd[1];
					int dollar = validateDollars(splitCmd[2]);
					if (dollar > 0) {
						if (rController.addPlayer(name, dollar)) {
							System.out.printf("Player %s added!\n", name);
							break;
						}
					}
				}
				System.out.println("Add syntax invalid");
				break;

			case "remove":
				if (splitCmd.length == 2) {
					String name = splitCmd[1];
					if (rController.removePlayer(name)) {
						System.out.printf("Player %s removed!\n", name);
						break;
					}

				}
				System.out.println("Remove syntax invalid");
				break;
				
			case "bet":
				if (splitCmd.length == 3) {
					String name = splitCmd[1];
					int dollar = validateDollars(splitCmd[2]);
					if (dollar > 0) {
						AbstractBet bet = new Black(dollar);
						
					}
				}
				System.out.println("Add syntax invalid");
				break;
			default:
				System.out.println("Option not recognized. Use help to see all commands..");
			}
		}

		System.out.println("Round ended. Rollllllllling the thinggggggggggggggggg");
		int num = rController.nextRound();
		System.out.printf("Picked number %d\n", num);

		return false;
	}
}
