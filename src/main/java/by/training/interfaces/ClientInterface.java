package by.training.interfaces;

import by.training.atm.Atm;
import by.training.atm.AtmEvents;
import by.training.client.Client;

import java.io.IOException;

public interface ClientInterface {

    public Client goToAtm(Atm atm, AtmEvents events) throws IOException;

    public void takeCard(Atm atm, AtmEvents events) throws IOException;

}
