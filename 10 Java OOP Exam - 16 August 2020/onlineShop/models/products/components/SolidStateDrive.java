package onlineShop.models.products.components;

import onlineShop.common.constants.Constant;

public class SolidStateDrive extends BaseComponent {
    public SolidStateDrive(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance * Constant.MULTIPLIER_SOLID_STATE_DRIVE, generation);
    }
}