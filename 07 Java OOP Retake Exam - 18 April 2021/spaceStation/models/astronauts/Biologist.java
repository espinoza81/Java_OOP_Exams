package spaceStation.models.astronauts;

import spaceStation.common.Constants;

public class Biologist extends BaseAstronaut {

    public Biologist(String name) {
        super(name, Constants.BIOLOGIST_OXYGEN);
    }

    @Override
    public void breath() {
        double oxygen = getOxygen() >= 5 ? getOxygen()-5 : 0;
        setOxygen(oxygen);
    }
}