package aquarium.entities.fish;

import aquarium.common.Constants;

public class FreshwaterFish extends BaseFish {
    //Can only live in FreshwaterAquarium!

    public FreshwaterFish(String name, String species, double price) {
        super(name, species, price);
        setSize(Constants.FRESH_WATER_FISH_SIZE);
    }

    @Override
    public void eat() {
        setSize(getSize() + 3);
    }
}