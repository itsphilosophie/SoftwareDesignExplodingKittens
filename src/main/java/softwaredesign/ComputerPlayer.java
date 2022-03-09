package softwaredesign;

import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player {
//    private ComputerPlayer computerPlayer;

    Random rand = new Random();

    /**
     *  This function first generates a random number of 0 or 1
        If the number is 1, then the computer will play a card
     */
    public Card playRandomCard() {
        if (!playerHand.isEmpty() && rand.nextInt(2) == 1) {     //willekeurig getal tussen 0 en 1; 1 dan een kaart

            ArrayList<Card> hand = playerHand.getHand();
            int cardIndex = rand.nextInt(hand.size());
            Card card = hand.get(cardIndex);

            ArrayList<Player> players = PlayerStatus.getActivePlayers();
            players.remove(TurnBase.getCurrentPlayer()); //not sure if this will remove the current player from active players
            int playerIndex = rand.nextInt(players.size());
            Player toPlayer = players.get(playerIndex);
            Rules.cardAction(card, toPlayer,null,0); //do the rulezzzzzzz

            players.add(TurnBase.getCurrentPlayer()); //cheap; action worth

            playerHand.removeCard(card);
            GameState.discardPile.addCardDeck(GameState.discardPile.getDeckSize(), card);
            return (card);
        }
        endTurn();
        return null;
        //1. does the computer want to play a card?
        //2. which card do i want to play?
    }

    public void playRandomNope() {
        if (this.hasCard("Nope") && rand.nextInt(2) == 1) { //we could change the chances if we want intelligence
            playCard(new Card("Nope"), TurnBase.getCurrentPlayer(), null, 0);
        }
    }
}


