package com.tk.airbnb.Airbnb.Strategy;

import com.tk.airbnb.Airbnb.Model.Inventory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PricingServiceImpl implements PricingService {

    public BigDecimal calculateDynamicPricing(Inventory inventory) {
        PricingStrategy pricingStrategy = new BasePricingStrategy();

        // apply the additional strategies
        pricingStrategy = new SurgePricingStrategy(pricingStrategy);
        pricingStrategy = new OccupancyPricingStrategy(pricingStrategy);
        pricingStrategy = new UrgencyPricingStrategy(pricingStrategy);
        pricingStrategy = new HolidayPricingStrategy(pricingStrategy);

        return pricingStrategy.calculatePrice(inventory);
    }

    @Override
    public BigDecimal calculateTotalPrice(List<Inventory> inventoryList) {
        return inventoryList.stream().map(this::calculateDynamicPricing).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
