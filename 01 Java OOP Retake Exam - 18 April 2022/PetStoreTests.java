package petStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PetStoreTests {

    private final static int ANIMALS_COUNT = 4;
    private final static int ANIMALS_MAX_KILOGRAMS_TO_FIND = 10;
    private final static String ANIMALS_TO_FIND_BY_SPECIE = "Dog";

    private PetStore petStore;
    private Animal firstAnimal;
    private Animal secondAnimal;
    private Animal thirdAnimal;
    private Animal fourAnimal;

    @Before
    public void setUp(){

        petStore = new PetStore();

        firstAnimal = new Animal("Dog", 68, 1000.40);
        secondAnimal = new Animal("Cat", 17, 345.40);
        thirdAnimal = new Animal("Parrot", 4, 5000.40);
        fourAnimal = new Animal("Dog", 7, 111.11);

        petStore.addAnimal(firstAnimal);
        petStore.addAnimal(secondAnimal);
        petStore.addAnimal(thirdAnimal);
        petStore.addAnimal(fourAnimal);
    }

    @Test
    public void animalCountInPetStore(){

        Assert.assertEquals(ANIMALS_COUNT, petStore.getCount());
    }

    @Test
    public void findAllAnimalsWithMaxKilograms(){

        List<Animal> expected = new ArrayList<>();
        expected.add(firstAnimal);
        expected.add(secondAnimal);

        List <Animal> actual = petStore.findAllAnimalsWithMaxKilograms(ANIMALS_MAX_KILOGRAMS_TO_FIND);

        Assert.assertEquals(expected, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addNullAnimal(){

        petStore.addAnimal(null);
    }

    @Test
    public void getTheMostExpensiveAnimal() {
        Assert.assertEquals(thirdAnimal, petStore.getTheMostExpensiveAnimal());
    }

    @Test
    public void findAllAnimalBySpecieDog() {
        List<Animal> expected = new ArrayList<>();
        expected.add(firstAnimal);
        expected.add(fourAnimal);

        List <Animal> actual = petStore.findAllAnimalBySpecie(ANIMALS_TO_FIND_BY_SPECIE);

        Assert.assertEquals(expected, actual);
    }
}