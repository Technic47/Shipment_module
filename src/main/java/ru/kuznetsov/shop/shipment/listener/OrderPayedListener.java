package ru.kuznetsov.shop.shipment.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
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

    @KafkaListener(topics = ORDER_STATUS_READY_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void processOrderPayedSuccess(String updateStatusDto) {
        try {
            UpdateOrderDTO dto = objectMapper.readValue(updateStatusDto, UpdateOrderDTO.class);

            shipmentContract.shipOrder(dto.getOrderId());
            shipmentProcessService.processOrderShippedStatus(dto.getOrderId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
