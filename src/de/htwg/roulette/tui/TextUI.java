package de.htwg.roulette.tui;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.roulette.controller.Controller;
import de.htwg.roulette.model.*;
import de.htwg.roulette.model.bets.*;
import de.htwg.roulette.model.events.BetAddedEvent;
import de.htwg.roulette.model.events.BetResultEvent;
import de.htwg.roulette.model.events.NextRoundEvent;
import de.htwg.roulette.model.events.PlayerEvent;
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

	public void printInitalUI() {
		LOGGER.info("Welcome to Htwg Roulette, the best roulette game ever made");
		printHelp();
		printRound();
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
		String command = scanner.nextLine();
		return inputMenu(command);
	}

	private boolean inputMenu(String command) {
		String[] splitCmd = command.split(" ");

		// Validation
		if (isLineInvalid(command, splitCmd))
			return false;

		// React
		switch (splitCmd[0]) {
		case "nr":
			parseNextRount();
			break;

		case "quit":
			LOGGER.info("Thanks for playing");
			return true;

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

		return false;
	}

	private boolean isLineInvalid(String command, String[] splitCmd) {
		return command.isEmpty() || splitCmd.length == 0;
	}

	private void parseNextRount() {
		LOGGER.info("Round ended. Rollllllllling the thinggggggggggggggggg");
		rController.nextRound();
		// Print new Round
		printRound();
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
				IBet bet = parseBetOptions(dollar);

				// Response over Observer
				rController.placeBet(name, bet);
			}
		}
		LOGGER.info("Bet syntax invalid/Bet not recognized");
	}

	private void printBetMenu() {
		LOGGER.info("Select your bet!");
		LOGGER.info("Bets: num - Single Number, red, black, even, odd, lower - Lower half,\n"
				+ " upper - Upper Half, column, corner, dozen, \n" + "first - First Four, split, street\n");
	}

	private IBet parseBetOptions(int dollar) {
		IBet bet = null;
		String betName = scanner.nextLine().toLowerCase();
		int num;

		switch (betName) {
		case "num":
			LOGGER.info("Wich number?");
			num = scanner.nextInt();
			bet = new SingleNumber(dollar, num);
			break;
		case "red":
			bet = new Red(dollar);
			break;
		case "black":
			bet = new Black(dollar);
			break;
		case "even":
			bet = new Even(dollar);
			break;
		case "odd":
			bet = new Odd(dollar);
			break;
		case "lower":
			bet = new LowerHalf(dollar);
			break;
		case "upper":
			bet = new UpperHalf(dollar);
			break;
		case "column":
			LOGGER.info("Which column?");
			num = scanner.nextInt();
			bet = new Column(dollar, num);
			break;
		case "corner":
			LOGGER.info("Enter four adjacent numbers");
			List<Integer> l = new LinkedList<>();
			for (int i = 0; i < 4; i++) {
				l.add(scanner.nextInt());
			}
			bet = new Corner(dollar, l);
			break;
		case "dozen":
			LOGGER.info("Enter Premier, Milieu or Dernier");
			Dozen.Flag option = Dozen.Flag.valueOf(scanner.nextLine());
			bet = new Dozen(dollar, option);
			break;
		case "first":
			bet = new FirstFour(dollar);
			break;
		case "split":
			LOGGER.info("Enter two numbers");
			int firstnum = scanner.nextInt();
			int secondnum = scanner.nextInt();
			bet = new Split(dollar, firstnum, secondnum);
			break;
		case "street":
			LOGGER.info("Enter beginning number of a street.");
			num = scanner.nextInt();
			bet = new Street(dollar, num);
			break;
		default:
			LOGGER.info("Unknown bet option " + betName);
		}

		return bet;
	}

	@Override
	public void update(Event e) {
		String tmp;
		if (e instanceof BetResultEvent) {
			BetResultEvent bre = (BetResultEvent) e;
			if (bre.result >= 0) {
				tmp = "won";
			} else {
				tmp = "lost";
			}

			String resultInfo = String.format("%s %s %d$ with his bet on %s.", bre.user, tmp, bre.result,
					bre.bet.getName());
			LOGGER.info(resultInfo);
		} else if (e instanceof BetAddedEvent) {
			BetAddedEvent bae = (BetAddedEvent) e;
			if (bae.result)
				LOGGER.info("Bet added");
			else
				LOGGER.info("Fail: Add bet");
		} else if (e instanceof  PlayerEvent) {
			PlayerEvent pe = (PlayerEvent) e;
			tmp = pe.added ? "added" : "removed";
			if (pe.result)
				LOGGER.info("Player %s %s!", pe.user, tmp);
			else
				LOGGER.info("Fail: Player %s %s", pe.user, tmp);
		} else if (e instanceof NextRoundEvent) {
			LOGGER.info(String.format("Picked number %d", ((NextRoundEvent) e).getResult()));
		}
	}
}
