package by.training.interfaces;

;

import by.training.atm.Atm;
import by.training.atm.AtmEvents;
import by.training.client.Client;
import by.training.client.card.Card;

import java.io.IOException;

public interface AtmEventsInterface {

    public void errorAccountId(Client client, AtmEvents events) throws IOException;

    public void errorClientPin(Card card, Atm atm) throws IOException;

    public void errorAccountPin(Card card, Atm atm) throws IOException;

    public void errorGetCashId(Atm atm, Client client) throws IOException;

    public void errorGetCashMachine(Atm atm, Client client) throws IOException;


    public void errorSetCashMachine(Atm atm, Client client) throws IOException;


    public void successCardId(Client client) throws IOException;


    public void errorInputOperation(Atm atm, Client client) throws IOException;


    public void errorInputYesNo(Atm atm, Client client) throws IOException;


}
