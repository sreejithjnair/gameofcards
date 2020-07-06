import java.util.List;
import java.util.Map;

public class SingleCardGame extends Game {
    public SingleCardGame(Map<String, Player> players) {
        super(players);
    }

    @Override
    public void getCard(Player player) {
        List<Card> currentHand = player.getHand();
        Card pickedCard = deck.get();
        //Adding picked card to players list
        currentHand.add(pickedCard);
        System.out.println("You have got " + pickedCard.getRank().getSymbol() + " of " + pickedCard.getSuite());
    }

    @Override
    protected void restartGame(Map<String, Player> players) throws InterruptedException {
        //restarts the game with the existing players
        new SingleCardGame(players).startGame();
    }

    @Override
    protected Player publishResultAndGetWinner(Map<String, Player> players) {
        Player winner = null;
        System.out.println("Results:");
        System.out.println("player\t\t\t\t SUITE\t\t RANK");
        for (Player player : players.values()) {
            String suite, rank;
            //Get the rank of the card which player holds
            int currPlayerRankValue = player.getHand().get(0).getRank().getValue();
            //Checks whether current player's card greater than the winner's
            if (winner == null ||
                    currPlayerRankValue >= winner.getHand().get(0).getRank().getValue()) {
                //Checks whether current player's suite greater than winner's
                if (winner == null ||
                        player.getHand().get(0).getSuite().getValue() > winner.getHand().get(0).getSuite().getValue()) {
                    winner = player;
                }
            }
            System.out.print(player.getName());
            suite = String.valueOf(player.getHand().get(0).getSuite());
            rank = player.getHand().get(0).getRank().getSymbol();
            System.out.println(new StringBuilder("\t\t\t\t ")
                    .append(suite)
                    .append("\t\t")
                    .append(rank)
                    .toString());
        }
        return winner;
    }

}
