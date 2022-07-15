package aquarium.entities.aquariums;

import aquarium.common.Constants;

public class SaltwaterAquarium extends BaseAquarium {

    public SaltwaterAquarium(String name) {
        super(name, Constants.SALT_WATER_AQUARIUM_CAPACITY);
    }
}