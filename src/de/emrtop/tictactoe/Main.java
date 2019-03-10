package de.emrtop.tictactoe;

import de.emrtop.tictactoe.game.Game;
import de.emrtop.tictactoe.ui.CUI;

/**
 * @author emrtop
 */
public class Main {

	/**
	 * Initializes the CUI (character user interface) and passes a Game instance on.
	 */
	public static void main(String args[]) {
		CUI cui = new CUI(new Game());
		cui.run();
	}

}
