package pl.training.shop.legacy;

import org.javamoney.moneta.FastMoney;

import javax.ejb.Stateless;
import java.util.Random;

@Stateless
public class FakeExchangeRate implements ExchangeRate {

    private final Random random = new Random();

    @Override
    public double get(FastMoney value) {
        return random.nextInt(10) / (double) 10;
    }

}
