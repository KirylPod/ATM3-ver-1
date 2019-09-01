package by.training.client.card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractCard {

    private String cardId, cardPin;

       public AbstractCard(String cardId, String cardPin) {
        this.cardId = cardId;
        this.cardPin = cardPin;
    }

    public AbstractCard(String cardId) {
        this.cardId = cardId;
        }

    public AbstractCard() {
    }
}









