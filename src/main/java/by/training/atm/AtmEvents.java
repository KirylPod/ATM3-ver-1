package by.training.atm;


import by.training.client.Client;
import by.training.client.card.Card;
import by.training.interfaces.AtmEventsInterface;
import by.training.interfaces.Logging;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class AtmEvents implements AtmEventsInterface, Logging {

    private Atm atm;
    private Client client;


    public AtmEvents(Atm atm, Client client) {
        this.atm = atm;
        this.client = client;
    }

    @Override
    public void errorAccountId(Client client, AtmEvents events) throws IOException {
        System.out.println("Incorrect credit card");
        System.out.println("Contact the bank where you are served");
        atm.go();
    }

    @Override
    public void errorClientPin(Card card, Atm atm) throws IOException {
        System.out.println("Incorrect PIN");
        getLogger().info(REPEAT);
        atm.inputPin(client, card, this);
    }

    @Override
    public void errorAccountPin(Card card, Atm atm) throws IOException {
        System.out.println("Wrong PIN");
        getLogger().info(REPEAT);
        atm.validPin(client, card, this);
    }

    @Override
    public void errorGetCashId(Atm atm, Client client) throws IOException {
        System.out.println("Insufficient funds. Limit current account");
        atm.yesNo(client, this);
    }

    @Override
    public void errorGetCashMachine(Atm atm, Client client) throws IOException {
        System.out.println("Insufficient funds. Limit ATM");
        atm.yesNo(client, this);
    }

    @Override
    public void errorSetCashMachine(Atm atm, Client client) throws IOException {
        System.out.println("Insufficient funds. Limit 1 000 000");
        atm.yesNo(client, this);
    }

    @Override
    public void successCardId(Client client) throws IOException {
        System.out.println("Have a nice day!");
        System.out.println(" ");
        atm.go();
    }

    @Override
    public void errorInputOperation(Atm atm, Client client) throws IOException {
        System.out.println("Incorrect transaction");
        atm.operation(client, this);
    }

    @Override
    public void errorInputYesNo(Atm atm, Client client) throws IOException {
        System.out.println("Incorrect transaction");
        atm.yesNo(client, this);
    }


}
