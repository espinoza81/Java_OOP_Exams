package onlineShop.models.products.computers;

import onlineShop.common.constants.Constant;

public class Laptop extends BaseComputer {
    public Laptop(int id, String manufacturer, String model, double price) {
        super(id, manufacturer, model, price, Constant.LAPTOP_OVERALL_PERFORMANCE);
    }
}