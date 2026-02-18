package br.com.ambidextrous.algadelivery.delivery.tracking.domain.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface CourierPayoutCalculationService {

    BigDecimal calculatePayout(Double distanceInKm);

}
