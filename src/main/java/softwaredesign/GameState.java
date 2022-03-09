package softwaredesign;

public class GameState {

    public static Talon drawPile;
    public static DiscardPile discardPile;
    public static TurnBase turnBase;
    public static boolean gameStart = false;

    public GameState() {
        drawPile = new Talon(3);
        discardPile = new DiscardPile();
        turnBase = new TurnBase();
    }

    public static void startGame(int numPlayers) {
        PlayerStatus playerStatus = turnBase.getPlayerController();

        playerStatus.addNewHumanPlayer("You");
        playerStatus.addNewPlayers(numPlayers - 1);
        gameStart = true;
    }

    public static void nextTurn() {
        TurnBase.endTurn();
    }

    public boolean isTalonEmpty() {
        return drawPile.isEmpty();
    }

    public boolean isDiscardPileEmpty() {
        return discardPile.isEmpty();
    }

    public boolean gameIsOver() {
        PlayerStatus playerStatus = turnBase.getPlayerController();
        return playerStatus.getActivePlayers().size() <= 1;
    }
}
