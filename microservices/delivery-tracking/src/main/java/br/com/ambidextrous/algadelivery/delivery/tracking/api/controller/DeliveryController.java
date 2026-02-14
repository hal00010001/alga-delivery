package br.com.ambidextrous.algadelivery.delivery.tracking.api.controller;

import br.com.ambidextrous.algadelivery.delivery.tracking.domain.model.Delivery;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/deliveries")
public class DeliveryController {

    @PostMapping
    public Delivery draft(@RequestBody @Valid DeliveryInput input){
        return null;
    }

    @PutMapping("/{deliveryId}")
    public Delivery edit(@PathVariable UUID deliveryId, @RequestBody @Valid DeliveryInput input){
        return null;
    }

}
