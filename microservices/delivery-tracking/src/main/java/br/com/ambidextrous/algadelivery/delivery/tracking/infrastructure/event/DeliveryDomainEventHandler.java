package br.com.ambidextrous.algadelivery.delivery.tracking.infrastructure.event;

import br.com.ambidextrous.algadelivery.delivery.tracking.domain.event.DeliveryFulfilledEvent;
import br.com.ambidextrous.algadelivery.delivery.tracking.domain.event.DeliveryPickUpEvent;
import br.com.ambidextrous.algadelivery.delivery.tracking.domain.event.DeliveryPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import static br.com.ambidextrous.algadelivery.delivery.tracking.infrastructure.kafka.KafkaTopicConfig.DELIVERY_EVENTS_TOPIC_NAME;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeliveryDomainEventHandler {

    private final IntegrationEventPublisher integrationEventPublisher;

//    @EventListener - Somente o EventListener não funciona, pois o Spring não consegue criar o KafkaTemplate,
//    ele precisa esperar o repositório ser salvo pra depois criar o bean
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(DeliveryPlacedEvent event){
        log.info("EVENT RECEIVED {}", event);
        log.info(event.toString());
        integrationEventPublisher.publish(event, event.getDeliveryId().toString(), DELIVERY_EVENTS_TOPIC_NAME);
    }

//    @EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(DeliveryPickUpEvent event){
        log.info(event.toString());
        integrationEventPublisher.publish(event, event.getDeliveryId().toString(), DELIVERY_EVENTS_TOPIC_NAME);
    }

//    @EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(DeliveryFulfilledEvent event){
        log.info(event.toString());
        integrationEventPublisher.publish(event, event.getDeliveryId().toString(), DELIVERY_EVENTS_TOPIC_NAME);
    }

}
