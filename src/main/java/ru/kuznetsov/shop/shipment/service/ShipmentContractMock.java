package ru.kuznetsov.shop.shipment.service;

import ru.kuznetsov.shop.represent.contract.order.ShipmentContract;

public class ShipmentContractMock implements ShipmentContract {

    @Override
    public Boolean shipOrder(Long orderId) {
        try {
            Thread.sleep(10000);
            return true;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
