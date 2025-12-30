package ru.kuznetsov.shop.shipment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.kuznetsov.shop.business.access.config.DataAccessConfig;
import ru.kuznetsov.shop.kafka.config.KafkaConfig;

@SpringBootApplication
@Import({DataAccessConfig.class, KafkaConfig.class})
public class ShipmentModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShipmentModuleApplication.class, args);
    }

}
