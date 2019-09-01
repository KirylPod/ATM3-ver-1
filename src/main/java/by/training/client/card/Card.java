package by.training.client.card;

public class Card extends AbstractCard {

    public Card(String cardId, String cardPin) {
        super(cardId, cardPin);
    }

    public Card(String cardId) {
        super(cardId);
    }

    public Card() {
        super();
    }
}