package pl.training.cloud.trips;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Log
@RefreshScope
@Component
public class TripChargeConfig {

    @Getter
    private long baseCharge;

    @Value("${baseCharge}")
    public void setBaseCharge(long baseCharge) {
        log.info("Updating base charge to: " + baseCharge);
        this.baseCharge = baseCharge;
    }

}
