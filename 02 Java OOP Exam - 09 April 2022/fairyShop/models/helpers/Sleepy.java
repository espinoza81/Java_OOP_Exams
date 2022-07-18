package fairyShop.models.helpers;

import fairyShop.common.Constants;

public class Sleepy extends BaseHelper{
    public Sleepy(String name) {
        super(name, Constants.SLEEPY_ENERGY);
        setWorkEnergy(Constants.SLEEPY_WORK_ENERGY);
    }
}