package softwaredesign;

import java.util.ArrayList;
import java.util.*;


public class PlayerStatus {
    private static ArrayList<Player> activePlayers;
    private static ArrayList<Player> inactivePlayers;

    public PlayerStatus() {
        activePlayers = new ArrayList<Player>();
        inactivePlayers = new ArrayList<Player>();
    }

    public void addNewPlayers(int numPlayers) {

        if (!GameState.gameStart) {
            for (int i = 0; i < numPlayers; i++) {
                addNewComputerPlayer();
            }
        }
    }

    private void addNewComputerPlayer() {
        Player newPlayer = new ComputerPlayer();
        activePlayers.add(newPlayer);
    }

    public void addNewHumanPlayer(String name) {
        Player newPlayer = new HumanPlayer(name);
        activePlayers.add(newPlayer);
    }

    public static ArrayList<Player> getActivePlayers() {

        return activePlayers;
    }

    public static boolean checkDisjoint(Collection<Player> activePlayers, Collection<Player> inactivePlayers) {
        return Collections.disjoint(activePlayers, inactivePlayers);
    }

    public static void addInactivePlayers() {
//        Player currentPlayer = TurnBase.getCurrentPlayer();
//        for (Card card : currentPlayer.getPlayerHand()){
//            // add card in discardPile
//        }
        activePlayers.remove(TurnBase.getCurrentPlayer());
        if (checkDisjoint(activePlayers, inactivePlayers)) {
            inactivePlayers.add(TurnBase.getCurrentPlayer());
        }
    }

}
