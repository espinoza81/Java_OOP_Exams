package computers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ComputerManagerTests {
    private final static String FIRST_COMPUTER_MANUFACTURER = "HP";
    private final static String FIRST_COMPUTER_MODEL = "Pavilion";

    private ComputerManager computerManager;
    private Computer firstComputer;
    private Computer secondComputer;
    private Computer thirdComputer;


    @Before
    public void setUp(){
        computerManager = new ComputerManager();

        firstComputer = new Computer(FIRST_COMPUTER_MANUFACTURER, FIRST_COMPUTER_MODEL, 1234.56);
        secondComputer = new Computer(FIRST_COMPUTER_MANUFACTURER, "Rog", 234.56);
        thirdComputer = new Computer("Lenovo", "V34", 456.78);

        computerManager.addComputer(firstComputer);
        computerManager.addComputer(secondComputer);
        computerManager.addComputer(thirdComputer);
    }

    @Test
    public void getCountOfComputersInManager(){

        Assert.assertEquals(3, computerManager.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullComputerToManager(){

        computerManager.addComputer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addExistingComputerToManager(){

        computerManager.addComputer(firstComputer);
    }

    @Test
    public void removeComputerToManager(){

        Computer removedComputer = computerManager.removeComputer(FIRST_COMPUTER_MANUFACTURER, FIRST_COMPUTER_MODEL);
        Assert.assertEquals(firstComputer ,removedComputer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getComputerWithNullManufacturer() {

        Computer findComputer = computerManager.getComputer(null, FIRST_COMPUTER_MODEL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getComputerWithNullModel() {

        Computer findComputer = computerManager.getComputer(FIRST_COMPUTER_MANUFACTURER, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getNonExistingComputer() {

        Computer findComputer = computerManager.getComputer("No Such Manufacturer", "No Such Model");
    }

    @Test
    public void getExistingComputer() {

        Computer findComputer = computerManager.getComputer(FIRST_COMPUTER_MANUFACTURER, FIRST_COMPUTER_MODEL);
        Assert.assertEquals(firstComputer, findComputer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getComputersByNullManufacturer() {

        List<Computer> actualComputers = computerManager.getComputersByManufacturer(null);
    }

    @Test
    public void getComputersByManufacturer() {

        List<Computer> expectedComputers = new ArrayList<>();
        expectedComputers.add(firstComputer);
        expectedComputers.add(secondComputer);


        List<Computer> actualComputers = computerManager.getComputersByManufacturer(FIRST_COMPUTER_MANUFACTURER);

        Assert.assertEquals(expectedComputers, actualComputers);
    }
}