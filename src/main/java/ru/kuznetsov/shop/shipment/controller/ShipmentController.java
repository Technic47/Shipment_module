package ru.kuznetsov.shop.shipment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kuznetsov.shop.represent.dto.order.OrderStatusDto;
import ru.kuznetsov.shop.shipment.service.ShipmentProcessService;

@RestController
@RequestMapping("/shipment")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentProcessService shipmentProcessService;

    @PostMapping("/delivered")
    public ResponseEntity<OrderStatusDto> delivered(@RequestParam Long orderId){
        return ResponseEntity.ok(shipmentProcessService.processOrderDeliveredStatus(orderId));
    }
}
