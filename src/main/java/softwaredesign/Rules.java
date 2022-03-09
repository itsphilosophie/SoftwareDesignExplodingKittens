package softwaredesign;

public class Rules {

    private static void playSkip(Player activePlayer) {
        if (activePlayer.playerHand.hasCard("Skip")) {
            activePlayer.drawCard();
            TurnBase.endTurn();
        } else {
            throw new IllegalArgumentException("You can't play this since you don't have the needed card.\n");
        }
    }

//    private static void playNope() {
//
//    }

    private static void playTacoCat(Player activePlayer) {
        if (activePlayer.playerHand.hasCard("TacoCat")) {
            activePlayer.drawCard();
            TurnBase.endTurn();
        } else {
            throw new IllegalArgumentException("You can't play this since you don't have the needed card.\n");
        }
    }

    private static void playCaterMelon(Player activePlayer) {
        if (activePlayer.playerHand.hasCard("Cattermelon")) {
            activePlayer.drawCard();
            TurnBase.endTurn();
        } else {
            throw new IllegalArgumentException("You can't play this since you don't have the needed card.\n");
        }
    }

    private static void playBeardCat(Player activePlayer) {
        if (activePlayer.playerHand.hasCard("BeardCat")) {
            activePlayer.drawCard();
            TurnBase.endTurn();
        } else {
            throw new IllegalArgumentException("You can't play this since you don't have the needed card.\n");
        }
    }

    private static void playRainbowRalphingCat(Player activePlayer) {
        if (activePlayer.playerHand.hasCard("RainbowRalphingCat")) {
            activePlayer.drawCard();
            TurnBase.endTurn();
        } else {
            throw new IllegalArgumentException("You can't play this since you don't have the needed card.\n");
        }
    }

    private static void playHairyPotatoCat(Player activePlayer) {
        if (activePlayer.playerHand.hasCard("HairyPotatoCat")) {
            activePlayer.drawCard();
            TurnBase.endTurn();
        } else {
            throw new IllegalArgumentException("You can't play this since you don't have the needed card.\n");
        }
    }

    private static void playSeeFuture(Player activePlayer) {
        if (activePlayer.playerHand.hasCard("SeeFuture")) {
            Talon.seeTop3Cards();
            activePlayer.drawCard();
            TurnBase.endTurn();
        } else {
            throw new IllegalArgumentException("You can't play this since you don't have the needed card.\n");
        }
    }

    private static void playShuffle(Player activePlayer) {
        if (activePlayer.playerHand.hasCard("Shuffle")) {
            GameState.drawPile.shuffleDeck();
            activePlayer.drawCard();
            TurnBase.endTurn();
        } else {
            throw new IllegalArgumentException("You can't play this since you don't have the needed card.\n");
        }
    }

    private static void playFavor(Player activePlayer, Card getCard, Player fromPlayer) {
        HandCards playerHand = activePlayer.getPlayerHand();
        HandCards otherPlayerHand = fromPlayer.getPlayerHand();
        if (playerHand.hasCard("Favor")) {
            if (otherPlayerHand.hasCard(getCard)) {
                playerHand.addCardHand(getCard);
                otherPlayerHand.removeCard(getCard);
                activePlayer.drawCard();
                TurnBase.endTurn();
            } else {
                activePlayer.drawCard();
                TurnBase.endTurn();
                throw new IllegalArgumentException("The specified player doesn't have said card.\n");
            }
        } else {
            throw new IllegalArgumentException("You can't play this since you don't have the needed card.\n");
        }
    }


    private static void playAttack(Player activePlayer) {
        if (activePlayer.playerHand.hasCard("Attack")) {
            TurnBase.endTurn();
        } else {
            throw new IllegalArgumentException("You can't play this since you don't have the needed card.\n");
        }
    }

    private static void playDefuse(Card card, int index, Player activePlayer) {
        if (activePlayer.playerHand.hasCard("Defuse")) {
            GameState.drawPile.hideKitten(card, index);
            activePlayer.drawCard();
            TurnBase.endTurn();
        } else {
            throw new IllegalArgumentException("You can't play this since you don't have the needed card.\n");
        }
    }

    private static void playExplodingKitten(Player activePlayer) {
        if (!activePlayer.playerHand.hasCard("Defuse")) {
            PlayerStatus.addInactivePlayers();
        } else {
            throw new IllegalArgumentException("You can't play this since you don't have the needed card.\n");
        }
    }


    public static void cardAction(Card card, Player toPlayer, Card getCard, int index) {
        switch (card.getCardType()) {
            case "TacoCat":
                playTacoCat(TurnBase.getCurrentPlayer());
                break;
            case "CaterMelon":
                playCaterMelon(TurnBase.getCurrentPlayer());
                break;
            case "BeardCat":
                playBeardCat(TurnBase.getCurrentPlayer());
                break;
            case "RainbowRalphingCat":
                playRainbowRalphingCat(TurnBase.getCurrentPlayer());
                break;
            case "HairyPotatoCat":
                playHairyPotatoCat(TurnBase.getCurrentPlayer());
                break;
            case "SeeFuture":
                playSeeFuture(TurnBase.getCurrentPlayer());
                break;
            case "Shuffle":
                playShuffle(TurnBase.getCurrentPlayer());
                break;
            case "Favor":
                playFavor(TurnBase.getCurrentPlayer(), getCard, toPlayer);
                break;
            case "Skip":
                playSkip(TurnBase.getCurrentPlayer());
                break;
            case "Attack":
                playAttack(TurnBase.getCurrentPlayer());
                break;
            /*  case "Nope":
                playNope();
                break; */
            case "Defuse":
                playDefuse(card, index, TurnBase.getCurrentPlayer());
                break;
            case "ExplodingKitten":
                playExplodingKitten(TurnBase.getCurrentPlayer());
                break;
            default:
                break;
        }
    }
}


