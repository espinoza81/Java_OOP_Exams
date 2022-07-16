package onlineShop.models.products.components;

import onlineShop.common.constants.Constant;

public class RandomAccessMemory extends BaseComponent {
    public RandomAccessMemory(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance * Constant.MULTIPLIER_RANDOM_ACCESS_MEMORY, generation);
    }
}