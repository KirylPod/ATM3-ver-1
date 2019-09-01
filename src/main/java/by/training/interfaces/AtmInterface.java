package by.training.interfaces;

import by.training.atm.AtmEvents;
import by.training.client.Client;
import by.training.client.card.Card;

import java.io.IOException;

public interface AtmInterface {

    public void validId(Client client, Card card, AtmEvents events) throws IOException;

    public void inputPin(Client client, Card card, AtmEvents events) throws IOException;

    public void validPin(Client client, Card card, AtmEvents events) throws IOException;

    public void getAtmMoney(Client client) throws IOException;

    public void operation(Client client, AtmEvents events) throws IOException;

    public void yesNo(Client client, AtmEvents events) throws IOException;

    public String checkBalance(String value);

    public String getCash(String value) throws IOException;

    public String setCash(String value) throws IOException;

    public Integer stringToInt(String str);

}
