import Model.Review;
import Translater.ListDivider;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


/**
 * Created by admin on 14.06.2017.
 */
public class TestListDivider {
    @Test
    public void testSizeBucket() {
        Assert.assertEquals("bucketSize must be equals 50", 50000, ListDivider.getBasketSize());
        Assert.assertEquals("calculateBucketSize must be equals 50", 142116, ListDivider.calculateBucketSize(4,568455));

    }
    @Test
    public void testArrayDivide() {
        ArrayList<Review> reviewList = new ArrayList<>();
        reviewList.add(new Review("As a big fan of sweet potato fries", "not translated"));
        reviewList.add(new Review("I *love* the Multigrain flavor of these chips/crackers!", "not translated"));
        reviewList.add(new Review("I am a label reader - I count fat grams", "not translated"));
        reviewList.add(new Review("big fan of sweet", "not translated"));
        reviewList.add(new Review("I *love* the Multigrain", "not translated"));
        reviewList.add(new Review("I count fat grams", "not translated"));
        reviewList.add(new Review("I count fat grams", "not translated"));

        Assert.assertEquals("testArrayDivide must be equals 50", 1, ListDivider.arrayDivide(reviewList).size());

    }
}
