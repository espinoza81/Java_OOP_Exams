package onlineShop.models.products.computers;

import onlineShop.common.constants.Constant;

public class DesktopComputer extends BaseComputer {
    public DesktopComputer(int id, String manufacturer, String model, double price) {
        super(id, manufacturer, model, price, Constant.DESKTOP_COMPUTER_OVERALL_PERFORMANCE);
    }
}