public class Constants {

    public static final String GAME_MANUAL = new StringBuilder("*************** GAME OF CARDS ***************\n\n")
            .append("How to play!\n")
            .append("=======================================\n")
            .append("--Single Card Game\n")
            .append("--------------------------------\n")
            .append("1. At least 2 players are required to play this game.\n")
            .append("2. Each player will get a card from the deck.\n")
            .append("3. Winner is decided based on the value of the card that he/she is holding.\n")
            .append("4. If players have same value cards, winner should be decided based on this priority :\n")
            .append("\t SPADES>HEART>CLUB>DIAMOND\n")
            .append("---------------------------------\n")
            .append("--Multi Card Game\n")
            .append("---------------------------------\n")
            .append("1. At least 2 players are required to play this game.\n")
            .append("2. Each player will get the selected number of cards from the deck on their turn.\n")
            .append("3. Winner is decided based on the values of the card that he/she is holding.\n")
            .append("4. The value of a card is determined by adding the card number value and suit value." +
                    "\n The suit values are as follows:\n\t DIAMOND- 0\n\t CLUB- 15\n\t HEART- 30\n\tSPADE- 45\n")
            .append("========================================\n").toString();

}
