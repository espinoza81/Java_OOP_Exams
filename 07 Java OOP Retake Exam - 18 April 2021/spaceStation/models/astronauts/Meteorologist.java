package spaceStation.models.astronauts;

import spaceStation.common.Constants;

public class Meteorologist extends BaseAstronaut {

    public Meteorologist(String name) {
        super(name, Constants.METEOROLOGIST_OXYGEN);
    }
}