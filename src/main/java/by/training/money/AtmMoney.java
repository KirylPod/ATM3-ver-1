package by.training.money;



import java.io.File;
import java.io.IOException;


public class AtmMoney extends AbstractAtmMoney {

    @Override
    public int getMoney() {
        return super.getMoney();
    }

    @Override
    public File getFile() {
        return super.getFile();
    }

    @Override
    public void setMoney(int money) {
        super.setMoney(money);
    }

    @Override
    public void setFile(File file) {
        super.setFile(file);
    }

    @Override
    public Integer reedAtmMoney() throws IOException {
        return super.reedAtmMoney();
    }

    @Override
    public void writeAtmMoney() throws IOException {
        super.writeAtmMoney();
    }
}
