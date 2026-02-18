package br.com.ambidextrous.algadelivery.delivery.tracking.domain.service;

import br.com.ambidextrous.algadelivery.delivery.tracking.domain.model.ContactPoint;

public interface DeliveryTimeEstimationService {

    DeliveryEstimate estimate(ContactPoint sender, ContactPoint receiver);

}
