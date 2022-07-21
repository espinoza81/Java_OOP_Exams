package halfLife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTests {
    private static final String FAKE_PLAYER_NAME = "Fake player";

    private Player player;
    private Gun firstGun;
    private Gun secondGun;
    private Gun thirdGun;

    @Before
    public void setUp() {

        player = new Player("Hunter", 100);

        firstGun = new Gun("Makarov", 12);
        secondGun = new Gun("Remington", 50);
        thirdGun = new Gun("Glock", 10);

        player.addGun(firstGun);
        player.addGun(secondGun);
        player.addGun(thirdGun);
    }

    @Test(expected = NullPointerException.class)
    public void createPlayerWithNullName() {

        Player fakePlayer = new Player(null, 30);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createPlayerWithNegativeHealth() {

        Player fakePlayer = new Player(FAKE_PLAYER_NAME, -30);
    }

    @Test(expected = IllegalStateException.class)
    public void takeDamageOnDeadPlayer() {

        Player fakePlayer = new Player(FAKE_PLAYER_NAME, 0);
        fakePlayer.takeDamage(30);
    }

    @Test
    public void takeDamageOnLivePlayer() {

        player.takeDamage(30);

        Assert.assertEquals(70, player.getHealth());
    }

    @Test(expected = NullPointerException.class)
    public void addNullGunToPlayer() {

        player.addGun(null);
    }

    @Test
    public void removeGunFromPlayer() {

        Assert.assertTrue(player.removeGun(firstGun));
    }

    @Test
    public void getGunFromPlayer() {

        Assert.assertEquals(secondGun, player.getGun("Remington"));
    }
}