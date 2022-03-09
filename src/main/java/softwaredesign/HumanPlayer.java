package softwaredesign;

public class HumanPlayer extends Player {
//    private HumanPlayer humanPlayer;

    protected HumanPlayer(String name) {
        this.name = name;
    }

    // This method allows the player to play the nope card on the current player's turn.
    public void playNope(Card card) {
        if (card.getCardType().equals("Nope")) {
            playCard(card, TurnBase.getCurrentPlayer(), null, 0);
        }
    }

}
