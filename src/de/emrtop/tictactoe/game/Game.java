package de.emrtop.tictactoe.game;

import de.emrtop.tictactoe.storage.Player;

/**
 * Describes how the game and its actions work.
 *
 * @author emrtop
 */
public class Game {

	private char[][] playground = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}}; // Alternative: for-Schleife
	private Player firstPlayer;
	private Player secondPlayer;
	private Phase phase;

	/**
	 * Sets the game into {@link de.emrtop.tictactoe.game.Phase#CREATE_PLAYER} upon construction
	 */
	public Game() {
		setPhase(Phase.CREATE_PLAYER);
	}

	/**
	 * Returns the playground
	 *
	 * @return the playground
	 */
	public char[][] getPlayground() {
		return playground;
	}

	/**
	 * Sets the next player.
	 */
	private void changeActionPlayer() {
		if(firstPlayer.isInAction()) {
			firstPlayer.setInAction(false);
			secondPlayer.setInAction(true);
		} else {
			secondPlayer.setInAction(false);
			firstPlayer.setInAction(true);
		}
	}

	/**
	 * Checks if the selected field is empty.
	 * The turn is being entered after the check succeeds.
	 *
	 * @param column the selected column
	 * @param row the selected row
	 * @return Returns {@code true} when the field is empty
	 */
	public boolean isTurnLegit(int column, int row) {
		if(playground[--row][--column] == ' ') {
			enterTurn(column, row, getPlayerInAction().getSymbol());
			return true;
		}

		return false;
	}

	/**
	 * Enters the turn that the player chose.
	 * A previous check has been performed within {@link #isTurnLegit(int, int)}.
	 *
	 * @param column the selected column
	 * @param row the selected row
	 * @param symbol the players symbol
	 */
	private void enterTurn(int column, int row, char symbol) {
		playground[--row][--column] = symbol;

		if(hasWon(symbol)) {
			setPhase(Phase.GAME_WON);
		} else if(!hasEmptyField()) {
			setPhase(Phase.GAME_TIED);
		} else {
			changeActionPlayer();
		}
	}

	/**
	 * Checks for empty fields in the playground.
	 *
	 * @return {@code true} when an empty field exists
	 */
	private boolean hasEmptyField() {
		for(int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(playground[i][j] == ' ') {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Checks whether the player that is associated with the specified symbol has won.
	 *
	 * @param symbol the symbol to check
	 * @return {@code true} when the player won
	 */
	private boolean hasWon(char symbol) {
		/*
			A list of all cases where the game is expected to end.

			horizontal: 00 01 02, 10 11 12, 20 21 22
			vertical: 00 10 20, 01 11 21, 02 12 22
			diagonal: 00 11 22, 02 11 20
		 */

		if((playground[0][0] == symbol && playground[0][1] == symbol && playground[0][2] == symbol) ||
				(playground[1][0] == symbol && playground[1][1] == symbol && playground[1][2] == symbol) ||
				(playground[2][0] == symbol && playground[2][1] == symbol && playground[2][2] == symbol) ||
				(playground[0][0] == symbol && playground[1][0] == symbol && playground[2][0] == symbol) ||
				(playground[0][1] == symbol && playground[1][1] == symbol && playground[2][1] == symbol) ||
				(playground[0][2] == symbol && playground[1][2] == symbol && playground[2][2] == symbol) ||
				(playground[0][0] == symbol && playground[1][1] == symbol && playground[2][2] == symbol) ||
				(playground[0][2] == symbol && playground[1][1] == symbol && playground[2][0] == symbol)) {
			return true;
		}

		return false;
	}

	/**
	 * Create the first player
	 *
	 * @param name the name
	 * @param symbol assigned symbol
	 */
	public void createFirstPlayer(String name, char symbol) {
		firstPlayer = new Player(name, symbol);
	}

	/**
	 * Create the second player. The symbol is being determined by {@link de.emrtop.tictactoe.storage.Player#getOppositeSymbol()}.
	 * {@link de.emrtop.tictactoe.game.Phase#CREATE_PLAYER} ends, {@link de.emrtop.tictactoe.game.Phase#TURN} starts.
	 *
	 * @param name the name
	 */
	public void createSecondPlayer(String name) {
		secondPlayer = new Player(name, firstPlayer.getOppositeSymbol());
		setPhase(Phase.TURN);
		changeActionPlayer();
	}

	/**
	 * Returns the symbol of the specified player.
	 *
	 * @param player the player
	 * @return the players symbol
	 */
	public char getSymbol(Player player) {
		return player.getSymbol();
	}

	/**
	 * Returns the the first player.
	 *
	 * @return Object of {@link #firstPlayer}
	 */
	public Player getFirstPlayer() {
		return firstPlayer;
	}

	/**
	 * Returns the second player.
	 *
	 * @return Object of {@link #secondPlayer}
	 */
	public Player getSecondPlayer() {
		return secondPlayer;
	}

	/**
	 * Returns the player that is currently playing.
	 *
	 * @return the player in action
	 */
	public Player getPlayerInAction() {
		if(firstPlayer.isInAction()) {
			return firstPlayer;
		}

		return secondPlayer;
	}

	/**
	 * Sets the games current {@link de.emrtop.tictactoe.game.Phase}.
	 *
	 * @see de.emrtop.tictactoe.game.Phase
	 * @param phase Phase to change to
	 */
	private void setPhase(Phase phase) {
		this.phase = phase;
	}

	/**
	 * Returns the current {@link de.emrtop.tictactoe.game.Phase} of the game.
	 *
	 * @see de.emrtop.tictactoe.game.Phase
	 * @return current Phase
	 */
	public Phase getPhase() {
		return phase;
	}

}
