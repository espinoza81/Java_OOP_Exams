package football;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FootballTeamTests {
    private FootballTeam footballTeam;
    private Footballer first;

    @Before
    public void setUp(){
        footballTeam = new FootballTeam("Test", 2);
        first = new Footballer("Pesho");
        footballTeam.addFootballer(first);
    }

    @Test (expected = NullPointerException.class)
    public void createTeamWithEmptyName(){
        FootballTeam footballTeam = new FootballTeam(null, 3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void createTeamWithNegativeVacantPosition(){
        FootballTeam footballTeam = new FootballTeam("Test", -3);
    }

    @Test
    public void getVacantPositions(){
        int actual = footballTeam.getVacantPositions();

        Assert.assertEquals(2, actual);
    }

    @Test
    public void getCount(){
        int actual = footballTeam.getCount();

        Assert.assertEquals(1, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addInFullTeam(){
        Footballer second = new Footballer("Ivan");
        Footballer third = new Footballer("Dragan");

        footballTeam.addFootballer(second);
        footballTeam.addFootballer(third);

    }

    @Test
    public void removeExistingFootballer(){
        footballTeam.removeFootballer("Pesho");

        int actual = footballTeam.getCount();

        Assert.assertEquals(0, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNonExistingFootballer(){
        footballTeam.removeFootballer("Ivan");
    }

    @Test
    public void saleExistingFootballer(){
        Footballer footballer = footballTeam.footballerForSale("Pesho");

        Assert.assertEquals("Pesho", footballer.getName());
        Assert.assertFalse(footballer.isActive());
    }

    @Test(expected = IllegalArgumentException.class)
    public void saleNonExistingFootballer(){
        Footballer footballer = footballTeam.footballerForSale("Ivan");
    }

    @Test
    public void getStatistic(){
        String expected = "The footballer Pesho is in the team Test.";
        String actual = footballTeam.getStatistics();

        Assert.assertEquals(expected, actual);
    }
}
