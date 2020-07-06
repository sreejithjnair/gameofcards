import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public abstract class Game {

    private Map<String, Player> players;
    protected Deck deck;
    Scanner s = new Scanner(System.in);

    public Game(Map<String, Player> players) {
        this.deck = new Deck();
        this.players = players;
    }

    public void startGame() throws InterruptedException {
        System.out.println("The battle starts now..");
        deck.shuffle();
        displayPlayers();
        deck.printTheDeck();
        for (Player player : players.values()) {
            pickCards(player);
        }
        publishWinnerAndFinishGame();
        playAnotherRound();
    }

    private void displayPlayers() {
        System.out.println("The talented players participating in the game are: ");
        for (String player : players.keySet()) {
            System.out.println("\t" + player);
        }
    }

    public abstract void getCard(Player player);

    /**
     * Pick the cards for the given player
     *
     * @param player
     */
    private void pickCards(Player player) {
        System.out.println("Hi " + player.getName() + ", Please press 1 to pick your card.");
        int input = Integer.parseInt(s.nextLine());
        if (input == 1) {
            getCard(player);
        } else {
            System.out.println("Invalid Input! Please try again!");
            pickCards(player);
        }
    }

    /**
     * Calculates points and announce the winner
     *
     * @throws InterruptedException
     */
    private void publishWinnerAndFinishGame() throws InterruptedException {
        System.out.println("\n");
        System.out.println("Calculating points.. ..");
        //Adding suspense factor
        TimeUnit.SECONDS.sleep(3);
        Player winner = publishResultAndGetWinner(players);
        System.out.println("*************The winner is " + winner.getName() + "*************\n\n");
        cleanGame();
    }

    protected abstract Player publishResultAndGetWinner(Map<String, Player> players);

    /**
     * Check whether the players interested in playing another round with the players added in the last round
     * <p>
     * If Yes, starts a new game with the already added players. Else, The game control navigates to the main menu
     *
     * @throws InterruptedException
     */
    private void playAnotherRound() throws InterruptedException {
        System.out.println("Do you want to play another round?");
        System.out.println("Press:\n\t 1 - Yes\t 2 - No");
        int input = Integer.parseInt(s.nextLine());
        if (input == 1) {
            restartGame(players);
        } else if (input == 2) {
            new GameOfCards();
        } else {
            System.out.println("Invalid Input! Please try again!");
            playAnotherRound();
        }
    }

    protected abstract void restartGame(Map<String, Player> players) throws InterruptedException;

    /**
     * resets the deck and removes the card from players
     */
    private void cleanGame() {
        deck = new Deck();
        for (Player player : players.values()) {
            player.setHand(new ArrayList<Card>());
        }
    }


}
