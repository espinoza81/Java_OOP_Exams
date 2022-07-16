package onlineShop.models.products.components;

import onlineShop.common.constants.Constant;

public class PowerSupply extends BaseComponent {
    public PowerSupply(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance * Constant.MULTIPLIER_POWER_SUPPLY, generation);
    }
}