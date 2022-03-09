package softwaredesign;

import java.util.ArrayList;

public class TurnBase {
    private PlayerStatus playerStatus;
    private static ArrayList<Player> turnsList;

    public TurnBase() {
        playerStatus = new PlayerStatus();
        turnsList = new ArrayList<Player>();
    }

    public PlayerStatus getPlayerController() {
        return playerStatus;
    }

    public static Player getCurrentPlayer() {
        return turnsList.get(0);
    }

    public static void endTurn() {
        Player currentPlayer = getCurrentPlayer();
        turnsList.remove(0);
        turnsList.add(currentPlayer);
    }
}
