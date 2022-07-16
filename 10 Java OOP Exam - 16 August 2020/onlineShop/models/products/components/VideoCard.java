package onlineShop.models.products.components;

import onlineShop.common.constants.Constant;

public class VideoCard extends BaseComponent {
    public VideoCard(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance * Constant.MULTIPLIER_VIDEO_CARD, generation);
    }
}