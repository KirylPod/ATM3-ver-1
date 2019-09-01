package by.training.atm;


import by.training.bank.Bank;
import by.training.bank.BankAccounts;
import by.training.client.Client;
import by.training.client.card.Card;
import by.training.enums.Menu;
import by.training.interfaces.AtmInterface;
import by.training.interfaces.Logging;
import by.training.money.AtmMoney;
import by.training.server.HibernateUtil;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

@Getter
@Setter

public abstract class AbstractAtm implements AtmInterface, Logging {

    private AtmMoney atmMoney = new AtmMoney();
    private Bank bank = new Bank();
    private Client client = new Client();

    private BankAccounts account;
    private int cash;

    AtmEvents events = new AtmEvents((Atm) this, client);

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    protected AbstractAtm() {
    }

    public void go() throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        validId(client.goToAtm((Atm) this, events), client.getCard(), events);
    }

    @Override
    public void validId(Client client, Card card, AtmEvents events) throws IOException {
        account = bank.getAccount(card.getCardId(), client, events);
        if (account != null) {
            inputPin(client, card, events);
        } else {
            events.errorAccountId(client, events);
        }
    }

    @Override
    public void inputPin(Client client, Card card, AtmEvents events) throws IOException {

        System.out.println("Enter PIN: ХХХХ");
        card.setCardPin(br.readLine());
        if (Pattern.matches("^\\d{4}", card.getCardPin())) {
            getLogger().info(PROCESSING);
            validPin(client, card, events);
        } else {
            events.errorClientPin(card, (Atm) this);
        }
    }

    @Override
    public void validPin(Client client, Card card, AtmEvents events) throws IOException {
        if (card.getCardPin().equals(account.getAccountPin())) {
            card.setCardPin(account.getAccountPin());
            getAtmMoney(client);
        } else {
            events.errorAccountPin(card, (Atm) this);
        }
    }

    @Override
    public void getAtmMoney(Client client) throws IOException {
        atmMoney.reedAtmMoney();
        operation(client, events);
    }

    @Override
    public void operation(Client client, AtmEvents events) throws IOException {

        getLogger().info(Menu.SELECT.getType());
        getLogger().info(Menu.CHECK_BALANCE.getType());
        getLogger().info(Menu.GET_CASH.getType());
        getLogger().info(Menu.SET_CASH.getType());
        getLogger().info(Menu.RETURN_CARD.getType());

        String num = br.readLine();
        if (Pattern.matches("^[1-4]{1}", num)) {
            switch (num) {
                case "1":
                    account.setAccountCash(checkBalance(account.getAccountCash()));
                    yesNo(client, events);
                    break;
                case "2":
                    account.setAccountCash(getCash(account.getAccountCash()));
                    yesNo(client, events);
                    break;
                case "3":
                    account.setAccountCash(setCash(account.getAccountCash()));
                    yesNo(client, events);
                    break;
                case "4":
                    getLogger().info(COMPLETE);
                    bank.setAccount(account.getAccountId(), account.getAccountCash());
                    atmMoney.writeAtmMoney();
                    events.successCardId(client);
                    break;
            }
        } else {
            events.errorInputOperation((Atm) this, client);
        }
    }

    @Override
    public void yesNo(Client client, AtmEvents events) throws IOException {

        getLogger().info(Menu.CONTINUE.getType());
        getLogger().info(Menu.YES.getType());
        getLogger().info(Menu.NO.getType());

        String num = br.readLine();

        if (Pattern.matches("^[1-2]{1}", num)) {
            switch (num) {
                case "1":
                    operation(client, events);
                    break;
                case "2":
                    getLogger().info(COMPLETE);
                    bank.setAccount(account.getAccountId(), account.getAccountCash());
                    atmMoney.setMoney(atmMoney.getMoney());
                    atmMoney.writeAtmMoney();
                    events.successCardId(client);
                    break;
            }
        } else {
            events.errorInputYesNo((Atm) this, client);
        }
        br.close();
    }

    @Override
    public String checkBalance(String value) {
        System.out.println("Оn the screen " + account.getAccountCash());
        return account.getAccountCash();
    }

    @Override
    public String getCash(String value) throws IOException {

        System.out.println("Other sum");
        cash = Integer.parseInt(br.readLine());

        if (cash > atmMoney.getMoney()) {
            events.errorGetCashId((Atm) this, client);
        }
        if (cash >= atmMoney.getMoney()) {
            events.errorGetCashMachine((Atm) this, client);
        }
        getLogger().info(REQUEST);
        System.out.println("Take your money: " + cash);

        atmMoney.setMoney(atmMoney.getMoney() - cash);

        return value = String.valueOf(stringToInt(account.getAccountCash()) - cash);

    }

    @Override
    public String setCash(String value) throws IOException {

        System.out.println("Other sum");
        cash = Integer.parseInt(br.readLine());
        if (cash >= 1000000) {
            events.errorSetCashMachine((Atm) this, client);
        }
        getLogger().info(REQUEST);
        System.out.println("Transfer complete: " + cash);

        atmMoney.setMoney(atmMoney.getMoney() + cash);

        return value = String.valueOf(stringToInt(account.getAccountCash()) + cash);
    }

    @Override
    public Integer stringToInt(String str) {
        return Integer.parseInt(str);
    }


}





