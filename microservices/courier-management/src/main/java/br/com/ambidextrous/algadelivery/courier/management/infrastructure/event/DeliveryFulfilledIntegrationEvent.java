package br.com.ambidextrous.algadelivery.courier.management.infrastructure.event;

import java.time.OffsetDateTime;
import java.util.UUID;

public class DeliveryFulfilledIntegrationEvent {

    private OffsetDateTime occuredAt;
    private UUID deliveryId;

}
