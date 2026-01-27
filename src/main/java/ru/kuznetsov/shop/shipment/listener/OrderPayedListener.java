package ru.kuznetsov.shop.shipment.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.kuznetsov.shop.kafka.service.MessageCacheService;
import ru.kuznetsov.shop.represent.contract.order.ShipmentContract;
import ru.kuznetsov.shop.represent.dto.order.UpdateOrderDTO;
import ru.kuznetsov.shop.shipment.service.ShipmentProcessService;

import static ru.kuznetsov.shop.represent.common.KafkaConst.ORDER_STATUS_READY_TOPIC;

@Component
@RequiredArgsConstructor
public class OrderPayedListener {

    private final ObjectMapper objectMapper;
    private final ShipmentContract shipmentContract;
    private final ShipmentProcessService shipmentProcessService;
    private final MessageCacheService<Long> messageCache;

    Logger logger = LoggerFactory.getLogger(OrderPayedListener.class);

    @KafkaListener(topics = ORDER_STATUS_READY_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void processOrderPayedSuccess(String updateStatusDto) {
        try {
            UpdateOrderDTO dto = objectMapper.readValue(updateStatusDto, UpdateOrderDTO.class);
            Long orderId = dto.getOrderId();
            logger.info("Order payed successfully with orderId={}. Preparing shipment", orderId);

            if (!messageCache.exists(orderId)) {
                messageCache.put(orderId);

                shipmentContract.shipOrder(orderId);
                shipmentProcessService.processOrderShippedStatus(orderId);

                logger.info("OrderId: {} successfully shipped", orderId);
            } else {
                logger.info("Duplicate message for order id {}", orderId);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
