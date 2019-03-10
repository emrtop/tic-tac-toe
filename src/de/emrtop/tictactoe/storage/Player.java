package de.emrtop.tictactoe.storage;

/**
 * Stores information about the players participating in the game.
 *
 * @author emrtop
 */
public class Player {

	private String name;
	private char symbol;
	private boolean isInAction;

	/**
	 * Creates a new Player
	 *
	 * @param name the name
	 * @param symbol the symbol to assign
	 */
	public Player(String name, char symbol) {
		this.name = name;
		this.symbol = symbol;
	}

	/**
	 * Returns the players name.
	 *
	 * @return player name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the players symbol.
	 *
	 * @return player symbol
	 */
	public char getSymbol() {
		return symbol;
	}

	/**
	 * Returns the symbol that the players opponent is playing with.
	 *
	 * @return symbol of the players opponent
	 */
	public char getOppositeSymbol() {
		if(symbol == 'X') {
			return 'O';
		} else {
			return 'X';
		}
	}

	/**
	 * Tells if a player is currently playing.
	 *
	 * @return {@code true} when the player is currently playing
	 */
	public boolean isInAction() {
		return isInAction;
	}

	/**
	 * Sets whether the player is currently playing or not.
	 *
	 * @param isInAction the role the player should take on
	 */
	public void setInAction(boolean isInAction) {
		this.isInAction = isInAction;
	}

}
