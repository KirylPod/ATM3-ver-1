package by.training.money;


import interfaces.AtmMoneyInterface;
import lombok.Getter;
import lombok.Setter;

import java.io.*;

@Getter
@Setter
public abstract class AbstractAtmMoney implements AtmMoneyInterface {

    private int money;
    private File file;

    @Override
    public Integer reedAtmMoney() throws IOException {


        file = new File("src/main/resources/atmCash");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return money = Integer.parseInt(reader.readLine());
    }

    @Override
    public void writeAtmMoney() throws IOException {
        FileWriter writer = new FileWriter(file, false);
//        writer.write(String.format("%.2f", getMoney()).replace(',', '.'));
        writer.write(String.valueOf(getMoney()));
        writer.close();

    }
}
