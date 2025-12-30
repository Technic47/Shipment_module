package ru.kuznetsov.shop.shipment.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kuznetsov.shop.kafka.service.MessageCacheService;
import ru.kuznetsov.shop.represent.contract.order.ShipmentContract;
import ru.kuznetsov.shop.shipment.service.ShipmentContractMock;

@Configuration
public class ShipmentConfig {

    @Bean
    @ConditionalOnMissingBean(ShipmentContract.class)
    public ShipmentContract shipmentContract() {
        return new ShipmentContractMock();
    }

    @Bean
    public MessageCacheService<Long> getMessageCacheService(){
        return new MessageCacheService<>();
    }
}
