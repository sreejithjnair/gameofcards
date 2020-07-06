import java.util.List;
import java.util.Map;

public class MultiCardGame extends Game {

    private int noCards;

    public MultiCardGame(Map<String, Player> players) {
        super(players);
        System.out.print("Enter the number of cards to pick(Should not exceed " + (int) 52 / players.size() + "): ");
        this.noCards = Integer.parseInt(s.nextLine());
    }

    @Override
    public void getCard(Player player) {
        List<Card> currentHand = player.getHand();
        List<Card> pickedCards = deck.getCards(noCards);
        //Adding picked cards to players list
        currentHand.addAll(pickedCards);
    }

    @Override
    protected void restartGame(Map<String, Player> players) throws InterruptedException {
        //restarts the game with the existing players
        new MultiCardGame(players).startGame();
    }

    @Override
    protected Player publishResultAndGetWinner(Map<String, Player> players) {
        Player winner = null;
        int winnerPoints = 0;
        System.out.println("Results:");
        for (Player player : players.values()) {
            //Get the total value of the cards which player holds
            int playerPoints = player.getHand().stream()
                    .mapToInt(card ->
                            (card.getRank().getValue() + card.getSuite().getValue()))
                    .sum();
            //Checks whether current player's points greater than the winner's
            if (winner == null ||
                    playerPoints >= winnerPoints) {
                winner = player;
                winnerPoints = playerPoints;
            }
            StringBuilder spades = new StringBuilder(),
                    club = new StringBuilder(),
                    heart = new StringBuilder(),
                    diamond = new StringBuilder();
            int points = 0;
            System.out.print(player.getName());
            for (Card card : player.getHand()) {
                switch (card.getSuite()) {
                    case SPADES:
                        spades = spades.append(card.getRank().getSymbol()).append(" ");
                        break;
                    case CLUB:
                        club = club.append(card.getRank().getSymbol()).append(" ");
                        break;
                    case HEART:
                        heart = heart.append(card.getRank().getSymbol()).append(" ");
                        break;
                    case DIAMOND:
                        diamond = diamond.append(card.getRank().getSymbol()).append(" ");
                        break;
                }
                points += card.getRank().getValue() + card.getSuite().getValue();
            }
            System.out.println(new StringBuilder("\t\t Points: ")
                    .append(points)
                    .append("\n\t\t")
                    .append("SPADES:\t\t")
                    .append(spades.toString())
                    .append("\n\t\t")
                    .append("CLUB:\t\t")
                    .append(club.toString())
                    .append("\n\t\t")
                    .append("HEART:\t\t")
                    .append(heart.toString())
                    .append("\n\t\t")
                    .append("DIAMOND:\t\t")
                    .append(diamond).toString());
        }
        return winner;
    }
}
