package aquarium.entities.fish;

import aquarium.common.Constants;

public class SaltwaterFish extends BaseFish{
    //Can only live in SaltwaterAquarium!

    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
        setSize(Constants.SALT_WATER_FISH_SIZE);
    }

    @Override
    public void eat() {
        setSize(getSize() + 2);
    }
}