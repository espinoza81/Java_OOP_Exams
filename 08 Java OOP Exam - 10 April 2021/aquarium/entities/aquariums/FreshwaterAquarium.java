package aquarium.entities.aquariums;

import aquarium.common.Constants;

public class FreshwaterAquarium extends BaseAquarium {

    public FreshwaterAquarium(String name) {
        super(name, Constants.FRESH_WATER_AQUARIUM_CAPACITY);
    }
}