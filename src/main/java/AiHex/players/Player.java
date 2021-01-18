package AiHex.players;

import java.util.ArrayList;

import AiHex.hexBoards.Board;
import AiHex.gameMechanics.Move;

public interface Player {

	public static final int CLICK_PLAYER = 3;
	public static final int autoplayer = 5;
	public static final String CLICK_DEFAULT_ARGS = "n/a";
	public static final String CLICK_DEFAULT_ARGS1 = "n/a";

	public static final String[] playerList = {"Human Player", "AutoPlayer"};
	public static final int[] playerIndex = {  CLICK_PLAYER, autoplayer  };

	public static final String[] argsList = { CLICK_DEFAULT_ARGS, CLICK_DEFAULT_ARGS1 };
	public Move getMove();

	public ArrayList<Board> getAuxBoards();
}