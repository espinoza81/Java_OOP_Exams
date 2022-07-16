package onlineShop.models.products.components;

import onlineShop.common.constants.Constant;

public class CentralProcessingUnit extends BaseComponent {

    public CentralProcessingUnit(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance * Constant.MULTIPLIER_CENTRAL_PROCESSING_UNIT, generation);
    }
}