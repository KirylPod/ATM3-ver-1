package by.training.client;

import by.training.atm.Atm;
import by.training.atm.AtmEvents;
import by.training.client.card.Card;
import by.training.interfaces.ClientInterface;
import by.training.interfaces.Logging;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

@Getter
@Setter

public abstract class AbstractClient implements ClientInterface, Logging {

    private String clientId;
    private Card card;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public Client goToAtm(Atm atm, AtmEvents events) throws IOException {
        takeCard(atm, events);
        return (Client) this;
    }


    @Override
    public void takeCard(Atm atm, AtmEvents events) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        getLogger().info("Enter card number: ХХХХ-ХХХХ-ХХХХ-ХХХХ");
        clientId = br.readLine();
        if (Pattern.matches("^\\d{4}-\\d{4}-\\d{4}-\\d{4}$", clientId)) {
            getLogger().info(PROCESSING);
            card = new Card(clientId, "");
            atm.validId((Client) this, card, events);

        } else {
            System.out.println("Incorrect card number");
            takeCard(atm, events);
        }

    }

}
