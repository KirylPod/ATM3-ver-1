package by.training.interfaces;


import by.training.atm.AtmEvents;
import by.training.bank.BankAccounts;
import by.training.client.Client;

import java.io.IOException;
import java.util.List;

public interface BankInterface {

    public List<BankAccounts> getAccountValue() throws IOException;

    public BankAccounts getAccount(String clientCardId, Client client, AtmEvents events) throws IOException;

    public void setAccount(String id, String newValue) throws IOException;

}
