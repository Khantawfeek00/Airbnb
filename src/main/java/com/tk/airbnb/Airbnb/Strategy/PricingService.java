package com.tk.airbnb.Airbnb.Strategy;

import com.tk.airbnb.Airbnb.Model.Inventory;

import java.math.BigDecimal;
import java.util.List;

public interface PricingService {
    BigDecimal calculateTotalPrice(List<Inventory> inventoryList);
}
