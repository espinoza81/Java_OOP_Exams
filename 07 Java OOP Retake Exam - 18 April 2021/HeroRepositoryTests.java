package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeroRepositoryTests {
    private static final String FIRST_HERO_NAME = "Ivan";
    private static final int FIRST_HERO_LEVEL = 4;
    private static final String SECOND_HERO_NAME = "Dragan";
    private static final int SECOND_HERO_LEVEL = 5;
    private static final int HERO_REPOSITORY_COUNT = 2;

    private HeroRepository heroRepository;
    private Hero firstHero;
    private Hero secondHero;

    @Before
    public void setUp() {

        heroRepository = new HeroRepository();

        firstHero = new Hero(FIRST_HERO_NAME, FIRST_HERO_LEVEL);
        secondHero = new Hero(SECOND_HERO_NAME, SECOND_HERO_LEVEL);

        heroRepository.create(firstHero);
        heroRepository.create(secondHero);
    }

    @Test
    public void getHeroRepositoryCount() {

        Assert.assertEquals(HERO_REPOSITORY_COUNT, heroRepository.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void createNullHero() {

        heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createHeroWithExistingName() {

        heroRepository.create(firstHero);
    }

    @Test(expected = NullPointerException.class)
    public void removeHeroWithNullName() {

        heroRepository.remove(null);
    }

    @Test
    public void removeExistingHero() {

        Assert.assertTrue(heroRepository.remove("Ivan"));
    }

    @Test
    public void getHeroWithHighestLevel() {

        Assert.assertEquals(secondHero, heroRepository.getHeroWithHighestLevel());
    }

    @Test
    public void getHeroByName() {

        Assert.assertEquals(firstHero, heroRepository.getHero("Ivan"));
    }
}