package fairyShop.models.helpers;

import fairyShop.common.Constants;

public class Happy extends BaseHelper {
    public Happy(String name) {
        super(name, Constants.HAPPY_ENERGY);
        setWorkEnergy(Constants.HAPPY_WORK_ENERGY);
    }
}