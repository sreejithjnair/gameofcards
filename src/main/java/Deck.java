import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private List<Card> cards;

    public Deck() {
        reset();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * To get a single card from the deck and remove it from the deck
     *
     * @return picked card
     */
    public Card get() {
        Card card = null;
        if (cards.size() > 0) {
            card = cards.get(cards.size() - 1);
            cards.remove(card);
        }
        return card;
    }

    /**
     * get the number of cards specified in the parameter 1 and remove them from the deck
     *
     * @param noCards
     * @return picked cards
     */
    public List<Card> getCards(int noCards) {
        List<Card> pickedCards = new ArrayList<Card>();
        while (noCards-- > 0) {
            if (cards.size() > 0) {
                Card pickedCard = cards.get(cards.size() - 1);
                cards.remove(pickedCard);
                pickedCards.add(pickedCard);
            } else {
                System.out.println("Failed! No cards to pick!");
            }
        }
        return pickedCards;
    }

    /**
     * Add a single card to the deck
     *
     * @param card
     */
    public void put(Card card) {
        cards.add(card);
    }

    /**
     * To reset the deck of cards
     */
    public void reset() {
        cards = new ArrayList<Card>();
        for (Card.Suites suite : Card.Suites.values()) {
            for (Card.Ranks rank : Card.Ranks.values()) {
                cards.add(new Card(suite, rank));
            }
        }
    }

    /**
     * Prints all the cards in the deck in a sorted order
     */
    public void printTheDeck() {
        System.out.println("Available cards in the Deck are:");
        //Sort cards based on their value
        List<Card> sortedCards = cards.stream()
                .sorted(Comparator.comparing(Card::getSuite)
                        .thenComparing(card -> card.getRank().getValue()))
                .collect(Collectors.toList());
        //Assign first suite in the list as the current selected suite
        Card.Suites currentSuite = sortedCards.get(0).getSuite();
        System.out.print("\t" + currentSuite + ": ");
        for (Card card : sortedCards) {
            if (!card.getSuite().equals(currentSuite)) {
                currentSuite = card.getSuite();
                System.out.print("\n\t" + currentSuite + ": ");
            }
            System.out.print(card.getRank().getSymbol() + " ");
        }
        System.out.println();
    }
}
