package br.com.ambidextrous.algadelivery.delivery.tracking.domain.model;

import br.com.ambidextrous.algadelivery.delivery.tracking.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    @Test
    public void shouldChangeToPlaced(){

        Delivery delivery = Delivery.draft();
        delivery.editPreparationDetails(createValidPreparationDetails());
        delivery.place();

        assertEquals(DeliveryStatus.WAITING_FOR_COURIER, delivery.getStatus());
        assertNotNull(delivery.getPlacedAt());

    }

    @Test
    public void shouldNotPlace(){

        Delivery delivery = Delivery.draft();

        assertThrows(DomainException.class, () -> {
            delivery.place();
        });

        assertEquals(DeliveryStatus.DRAFT, delivery.getStatus());
        assertNull(delivery.getPlacedAt());

    }

    private Delivery.PreparationDetails createValidPreparationDetails(){

        ContactPoint sender = ContactPoint.builder()
                .zipCode("80000-000")
                .street("Rua Curitiba")
                .number("100")
                .complement("Sala 401")
                .name("João Silva")
                .phone("(41) 90000-1000")
                .build();
/// /
        ContactPoint recipient = ContactPoint.builder()
                .zipCode("80000-100")
                .street("Rua São José dos Pinhais")
                .number("500")
                .complement("")
                .name("Maria Silva")
                .phone("(41) 90000-2000")
                .build();

        return Delivery.PreparationDetails.builder()
                .sender(sender)
                .recipient(recipient)
                .distanceFee(new BigDecimal("15.00"))
                .courierPayout(new BigDecimal("5.00"))
                .expectedDeliveryTime(Duration.ofHours(5))
                .build();

    }

}