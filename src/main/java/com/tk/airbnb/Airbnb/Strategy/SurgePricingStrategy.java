package com.tk.airbnb.Airbnb.Strategy;

import com.tk.airbnb.Airbnb.Model.Inventory;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class SurgePricingStrategy implements PricingStrategy {

    private final PricingStrategy baseStrategy;


    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = baseStrategy.calculatePrice(inventory);
        return price.multiply(inventory.getSurgeFactor());
    }
}
