package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CatTest {
    private final static String FIRST_CAT_NAME = "Tommy";

    private Cat firstCat;
    @Before
    public void setUp(){
        firstCat = new Cat(FIRST_CAT_NAME);
    }

    @Test
    public void isTestCatHungry(){
        Assert.assertTrue(firstCat.isHungry());
    }
}