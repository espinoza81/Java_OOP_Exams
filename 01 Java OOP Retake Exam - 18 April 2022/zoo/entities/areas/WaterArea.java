package zoo.entities.areas;

import zoo.entities.animals.Animal;
import zoo.entities.animals.AquaticAnimal;

public class WaterArea extends BaseArea {

    public WaterArea(String name) {
        super(name, 10);
    }

    public void addAnimal(Animal animal) {
        if (animal instanceof AquaticAnimal) {
            super.addAnimal(animal);
        }
    }
}
