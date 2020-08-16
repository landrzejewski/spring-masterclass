package pl.training.shop.legacy;

import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.FastMoney;
import org.springframework.http.ResponseEntity;
import org.springframework.jndi.JndiTemplate;
import org.springframework.web.bind.annotation.*;

import javax.naming.NamingException;

@RequestMapping("${apiPrefix}")
@RequiredArgsConstructor
@RestController
public class LegacyTestController {

    private final JmsSender jmsSender;
    private final JndiTemplate jndiTemplate = new JndiTemplate();

    @GetMapping("exchange-rate")
    public double getExchangeRate(@RequestParam String value) throws NamingException {
        ExchangeRate exchangeRate = jndiTemplate.lookup("java:global/shop-0.0.1-SNAPSHOT/FakeExchangeRate", ExchangeRate.class);
        return exchangeRate.get(FastMoney.parse(value));
    }

    @PostMapping("messages")
    public ResponseEntity<Void> sendMessage(@RequestBody String text) {
        jmsSender.send(text);
        return ResponseEntity.accepted().build();
    }

}
