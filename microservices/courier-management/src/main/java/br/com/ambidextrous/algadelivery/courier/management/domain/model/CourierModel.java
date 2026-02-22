package br.com.ambidextrous.algadelivery.courier.management.domain.model;

import java.util.UUID;

public record CourierModel(
        UUID id,
        String name,
        String phone
) {
}
