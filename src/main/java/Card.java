public class Card {

    public enum Suites {
        SPADES(45),
        HEART(30),
        CLUB(15),
        DIAMOND(0);

        private final int value;

        private Suites(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum Ranks {
        ACE(13, "A"),
        KING(12, "K"),
        QUEEN(11, "Q"),
        JACK(10, "J"),
        TEN(9, "10"),
        NINE(8, "9"),
        EIGHT(7, "8"),
        SEVEN(6, "7"),
        SIX(5, "6"),
        FIVE(4, "5"),
        FOUR(3, "4"),
        THREE(2, "3"),
        TWO(1, "2");

        private final int value;
        private final String symbol;

        private Ranks(int value, String symbol) {
            this.value = value;
            this.symbol = symbol;
        }

        public int getValue() {
            return value;
        }

        public String getSymbol() {
            return symbol;
        }
    }

    private Suites suite;
    private Ranks rank;

    public Card(Suites suite, Ranks rank) {
        this.suite = suite;
        this.rank = rank;
    }

    public Suites getSuite() {
        return suite;
    }

    public void setSuite(Suites suite) {
        this.suite = suite;
    }

    public Ranks getRank() {
        return rank;
    }

    public void setRank(Ranks rank) {
        this.rank = rank;
    }
}
