package softwaredesign;

public abstract class Player {
    protected String name;
    protected HandCards playerHand;

    protected Player(HandCards playerHand) {
        this.playerHand = playerHand;
    }

    protected Player() {
        this.name = "default";
        playerHand = new HandCards();
    }

    public String getName() {
        return name;
    }

    public void setName(String newPlayerName) {
        this.name = newPlayerName;
    }

    public void setPlayerHand(HandCards newHand) {
        this.playerHand = newHand;
    }

    public HandCards getPlayerHand() {
        return playerHand;
    }

    public void addToPlayersHand(Card card) {
        playerHand.addCardHand(card);
    }

    public void removePlayedCard(Card card) {
        playerHand.removeCard(card);
    }

    public boolean hasCard(Card card) {
        return playerHand.hasCard(card);
    }

    public boolean hasCard(String cardType) {
        return playerHand.hasCard(new Card(cardType));
    }

    public void drawCard() {
        playerHand.drawCard();
    }

    public void endTurn() {
        Card card = GameState.drawPile.drawTopCard();
        if (card.getCardType().equals("ExplodingKitten")) {
            if (this.hasCard("Defuse")) {
                GameState.drawPile.hideKitten(card, 0);
            } else {
                PlayerStatus.addInactivePlayers();
            }
        } else {
            playerHand.addCardHand(card);
            //if no kitten drawn, then add the drawn card to your hand
        }
        GameState.nextTurn();
    }

    // This function allows the human player to play a card
    public Card playCard(Card card, Player toPlayer, Card getCard, int index) {
        playerHand.removeCard(card);
        GameState.discardPile.addCardDeck(GameState.discardPile.getDeckSize(), card);
            Rules.cardAction(card, toPlayer, getCard, index);
        return(card);
    }

}



