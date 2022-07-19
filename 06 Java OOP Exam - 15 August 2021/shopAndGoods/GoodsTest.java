package shopAndGoods;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GoodsTest{
    private static final String FIRST_GOOD_NAME = "Butter";
    private static final String FIRST_GOOD_CODE = "123";

    private Goods firstGood;

    @Before
    public  void setUp(){

        firstGood = new Goods(FIRST_GOOD_NAME, FIRST_GOOD_CODE);
    }

    @Test
    public void getGoodName(){
        Assert.assertEquals(FIRST_GOOD_NAME, firstGood.getName());
    }
}