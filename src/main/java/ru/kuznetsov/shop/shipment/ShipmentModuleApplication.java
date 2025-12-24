package ru.kuznetsov.shop.shipment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.kuznetsov.shop.business.access.config.DataAccessConfig;

@SpringBootApplication
@Import({DataAccessConfig.class})
public class ShipmentModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShipmentModuleApplication.class, args);
    }

}
