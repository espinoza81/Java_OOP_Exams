package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {
    private static final int SPACESHIP_CAPACITY = 2;
    private static final int SPACESHIP_NEGATIVE_CAPACITY = -3;
    private static final String SPACESHIP_NAME = "Bleik";
    private static final String FIRST_ASTRONAUT_NAME = "Laika";
    private static final double FIRST_ASTRONAUT_OXYGEN = 5.5;
    private static final String SECOND_ASTRONAUT_NAME = "Georgi";
    private static final double SECOND_ASTRONAUT_OXYGEN = 9.5;
    private static final String THIRD_ASTRONAUT_NAME = "Pesho";
    private static final double THIRD_ASTRONAUT_OXYGEN = 15.5;

    private Spaceship spaceship;
    private Astronaut firstAstronaut;
    private Astronaut secondAstronaut;
    private Astronaut thirdAstronaut;

    @Before
    public void setUp() {
        spaceship = new Spaceship(SPACESHIP_NAME,SPACESHIP_CAPACITY);

        firstAstronaut = new Astronaut(FIRST_ASTRONAUT_NAME, FIRST_ASTRONAUT_OXYGEN);
        secondAstronaut = new Astronaut(SECOND_ASTRONAUT_NAME, SECOND_ASTRONAUT_OXYGEN);
        thirdAstronaut = new Astronaut(THIRD_ASTRONAUT_NAME, THIRD_ASTRONAUT_OXYGEN);

        spaceship.add(firstAstronaut);
        spaceship.add(secondAstronaut);
    }

    @Test(expected = NullPointerException.class)
    public void createSpaceshipWhitNullName() {

        Spaceship fakeSpaceship = new Spaceship(null, SPACESHIP_CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createSpaceshipWhitNegativeCapacity() {

        Spaceship fakeSpaceship = new Spaceship(SPACESHIP_NAME, SPACESHIP_NEGATIVE_CAPACITY);
    }

    @Test
    public void getAstronautsCountInSpaceship(){
        Assert.assertEquals(SPACESHIP_CAPACITY, spaceship.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAstronautInFullSpaceship() {

        spaceship.add(thirdAstronaut);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addExistingAstronautInSpaceship() {

        spaceship.remove(FIRST_ASTRONAUT_NAME);
        spaceship.add(secondAstronaut);
    }

    @Test
    public void removeExistingAstronautInSpaceship() {

        Assert.assertTrue(spaceship.remove(FIRST_ASTRONAUT_NAME));
    }
}