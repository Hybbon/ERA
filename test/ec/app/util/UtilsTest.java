package ec.app.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Describe this class and the methods exposed by it.
 */
public class UtilsTest {

    @Test
    public void testComputePopularity() {
        Map<Integer, Vector<Integer>> likedItemsByUser = new HashMap<>();

        Vector<Integer> likedByUser1 = new Vector<>();
        likedByUser1.add(1);
        likedByUser1.add(2);
        likedItemsByUser.put(1, likedByUser1);

        Vector<Integer> likedByUser2 = new Vector<>();
        likedByUser1.add(1);
        likedByUser1.add(3);
        likedItemsByUser.put(2, likedByUser2);

        Map<Integer, Double> expectedPopularity = new HashMap<>();
        expectedPopularity.put(1, 1.);
        expectedPopularity.put(2, .5);
        expectedPopularity.put(3, .5);

        Map<Integer, Double> popularityByItem = Utils.compute_popularity(likedItemsByUser);

        Assert.assertEquals(expectedPopularity, popularityByItem);
    }
}
