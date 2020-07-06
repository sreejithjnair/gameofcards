import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameOfCards {

    private Map<String, Player> players;

    Scanner s = new Scanner(System.in);

    public GameOfCards() {
        this.start();
    }

    public static void main(String[] args) {
        new GameOfCards();
    }

    public void start() {
        System.out.println(Constants.GAME_MANUAL);
        players = new HashMap<String, Player>();
        while (true) {
            System.out.println("Press\n\t" +
                    "1 for Add Player\n\t" +
                    "2 for Remove Player\n\t" +
                    "3 for Continue\n\t" +
                    "4 for Exit");
            System.out.println("=============================");
            int input = Integer.parseInt(s.nextLine());
            System.out.println(input);
            if (input == 1) {
                if (players.size() > 26) {
                    System.out.println("Sorry! Maximum player limit reached!!");
                    continue;
                }
                System.out.print("Enter player name: ");
                addPlayer(s.nextLine());
            } else if (input == 2) {
                System.out.print("Enter player name: ");
                removePlayer(s.nextLine());
            } else if (input == 3) {
                if (players.size() < 2) {
                    System.out.println("Failed! At least 2 players are needed to play the game. Please add players.");
                } else {
                    Game game = null;
                    while (game == null) {
                        game = selectGameMode();
                    }
                    try {
                        game.startGame();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            } else if (input == 4) {
                try {
                    quitGame();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Invalid Input. Please try again!");
            }
        }
    }

    private Game selectGameMode() {
        System.out.println("Select Game Mode");
        System.out.println("1 - Single Card\t\t 2 - Multi Card");
        int input = Integer.parseInt(s.nextLine());
        Game game;
        if (input == 1) {
            game = new SingleCardGame(players);
        } else if (input == 2) {
            game = new MultiCardGame(players);
        } else {
            System.out.println("Invalid Input. Please try again!");
            game = null;
        }
        return game;
    }

    public void addPlayer(String name) {
        //checks for player name uniqueness
        if (players.size() > 0 && players.containsKey(name)) {
            System.out.println("Failed! A player with the given name already exists in game. Please give a new name.");
        } else {
            players.put(name, new Player(name));
            System.out.println("Success! Player has been enrolled to the game.");
        }
    }

    public void removePlayer(String name) {
        //checks whether player exists
        if (players.size() <= 0 || !players.containsKey(name)) {
            System.out.println("Failed! Player does not exists in game.");
        } else {
            players.remove(name);
            System.out.println("Success! Player has been removed from the game.");
        }
    }

    public void quitGame() throws InterruptedException {
        System.out.println("Thanks for playing Game of Cards!");
        TimeUnit.SECONDS.sleep(2);
        System.exit(0);
    }
}
