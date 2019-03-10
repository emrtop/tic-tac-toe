package de.emrtop.tictactoe.ui;

import java.util.Scanner;

import de.emrtop.tictactoe.game.Game;
import de.emrtop.tictactoe.game.Phase;
import de.emrtop.tictactoe.storage.Player;

/**
 * Handles the interaction with the players through the CUI (character user interface).
 *
 * @author emrtop
 */
public class CUI {

	private Game game;
	private Scanner input;

	/**
	 * Stores the current game instance and initializes the {@link java.util.Scanner} that is being used for retrieving the players input
	 *
	 * @param game instance of the current game
	 */
	public CUI(Game game) {
		setGame(game);
		input = new Scanner(System.in);
	}

	/**
	 * Responsible for handling the current game.
	 *
	 * Asking for the players data, assining their {@link de.emrtop.tictactoe.storage.Player#symbol} accordingly, handling the players turn and announcing the result of the game.
	 * The data is further processed within {@link de.emrtop.tictactoe.storage.Player} and {@link de.emrtop.tictactoe.game.Game} respectively.
	 *
	 * @see CUI
	 * @see de.emrtop.tictactoe.game.Phase
	 */
	public void run() {
		boolean isRunning = true;
		while (isRunning) {
			switch (game.getPhase()) {
				case CREATE_PLAYER:
					System.out.println("Spieler 1, bitte gib deinen Namen ein:");
					String name = readStringInput();

					System.out.println("Bitte wähle ein Zeichen ('X' oder 'O'):");
					char symbol = readStringInput().toUpperCase().charAt(0);

					if (symbol == 'X' || symbol == 'O') {
						System.out.println("Du wirst als '" + symbol + "' spielen.");
						game.createFirstPlayer(name, symbol);
					} else {
						System.out.println("Du hast kein gültiges Zeichen gewählt.");
						System.out.println("Dir wird 'X' zugewiesen.");
						game.createFirstPlayer(name, 'X');
					}

					System.out.println("Spieler 2, bitte gib deinen Namen ein:");
					name = readStringInput();
					game.createSecondPlayer(name);

					System.out.println("Dir wurde folgendes Zeichen zugewiesen: " + game.getSecondPlayer().getSymbol());
					System.out.println("Das Spiel beginnt...");
					break;

				case TURN:
					printGame();
					Player player = game.getPlayerInAction();
					System.out.println(player.getName() + " ist am Zug.");

					boolean turnLegit = false;
					while(!turnLegit) {
						System.out.println("Zeile eingeben:");
						int row = -1;

						boolean rowLegit = false;
						while(!rowLegit) {
							row = readIntInput(); // method returns -1 when the input does not equal to 1, 2 or 3

							if(row != -1) {
								rowLegit = true;
							} else {
								System.out.println("Bitte gebe eine Zahl zwischen 1 und 3 ein.");
							}
						}

						System.out.println("Spalte eingeben:");
						int column = -1;

						boolean columnLegit = false;
						while(!columnLegit) {
							column = readIntInput(); // method returns -1 when the input does not equal to 1, 2 or 3

							if(column != -1) {
								columnLegit = true;
							} else {
								System.out.println("Bitte gebe eine Zahl zwischen 1 und 3 ein.");
							}
						}

						if(game.isTurnLegit(column, row)) {
							turnLegit = true;
						} else {
							printGame();
							System.out.println("Das Feld {Z" + row + ", S" + column + "} ist bereits belegt.");
							System.out.println("Bitte suche dir ein anderes Feld aus.");
						}
					}
					break;

				// Merge cases to prevent redundancies
				case GAME_TIED:
				case GAME_WON:
					printGame();
					if(game.getPhase().equals(Phase.GAME_TIED)) {
						System.out.println("Ihr habt beide gewonnen - Unentschieden.");
					} else {
						System.out.println(game.getPlayerInAction().getName() + " hat gewonnen!");
					}

					isRunning = false;
					break;
			}
		}
	}

	/**
	 * Returns a String that the user provides.
	 *
	 * @see java.util.Scanner#next()
	 * @return the user-provided string
	 */
	private String readStringInput() {
		return input.next();
	}

	/**
	 * Returns an integer that the user provides.
	 *
	 * @see java.util.Scanner#nextInt()
	 * @return the user-provided integer, {@code -1} when unable to retrieve any data
	 */
	private int readIntInput() {
		int i = this.input.nextInt();
		if(i <= 3 && i >= 1) {
			return i;
		}

		return  -1;
	}

	/**
	 * Prints the current state of the playground.
	 */
	private void printGame() {
		char[][] pg = game.getPlayground();

		System.out.println("");
		System.out.println(" " + pg[0][0] + " | " + pg[0][1] + " | " + pg[0][2] + " ");
		System.out.println("---|---|---");
		System.out.println(" " + pg[1][0] + " | " + pg[1][1] + " | " + pg[1][2] + " ");
		System.out.println("---|---|---");
		System.out.println(" " + pg[2][0] + " | " + pg[2][1] + " | " + pg[2][2] + " ");
	}

	/**
	 * Sets this classes game variable.
	 *
	 * @param game instance of {@link de.emrtop.tictactoe.game.Game}
	 */
	private void setGame(Game game) {
		this.game = game;
	}

}
