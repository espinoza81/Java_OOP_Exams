package zoo.entities.areas;

import zoo.entities.animals.Animal;
import zoo.entities.animals.TerrestrialAnimal;

public class LandArea extends BaseArea {
    public LandArea(String name) {
        super(name, 25);
    }

    public void addAnimal(Animal animal) {
        if (animal instanceof TerrestrialAnimal) {
            super.addAnimal(animal);
        }
    }
}
