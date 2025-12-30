package ru.kuznetsov.shop.shipment.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.kuznetsov.shop.represent.contract.order.OrderStatusContract;
import ru.kuznetsov.shop.represent.dto.order.OrderStatusDto;

import java.time.LocalDateTime;

import static ru.kuznetsov.shop.represent.enums.OrderStatusType.DELIVERED;
import static ru.kuznetsov.shop.represent.enums.OrderStatusType.SHIPPED;

@Service
@RequiredArgsConstructor
public class ShipmentProcessService {

    private final OrderStatusContract orderStatusService;

    Logger logger = LoggerFactory.getLogger(ShipmentProcessService.class);

    public OrderStatusDto processOrderShippedStatus(Long orderId) {
        OrderStatusDto orderStatusSaved = orderStatusService.create(new OrderStatusDto(
                SHIPPED,
                "Shipment service",
                "Order is shipped " + LocalDateTime.now(),
                orderId
        ));

        logger.info("Shipment is successfully sent for order: {}", orderId);
        return orderStatusSaved;
    }

    public OrderStatusDto processOrderDeliveredStatus(Long orderId) {
        OrderStatusDto orderStatusSaved = orderStatusService.create(new OrderStatusDto(
                DELIVERED,
                null,
                "Order is delivered " + LocalDateTime.now(),
                orderId
        ));

        logger.info("Shipment is successfully delivered for order: {}", orderId);
        return orderStatusSaved;
    }
}
