package ec.app.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Describe this class and the methods exposed by it.
 */
public class testCosineSimilarityMatrix {

    @Test
    public void testConstructAndGet() {
        Map<Integer, Vector<Integer>> likedItemsByUser = new HashMap<>();

        Vector<Integer> likedByUser1 = new Vector<>();
        likedByUser1.add(2);
        likedItemsByUser.put(1, likedByUser1);

        Vector<Integer> likedByUser2 = new Vector<>();
        likedByUser2.add(2);
        likedByUser2.add(3);
        likedItemsByUser.put(2, likedByUser2);

        Vector<Integer> likedByUser3 = new Vector<>();
        likedByUser3.add(3);
        likedItemsByUser.put(3, likedByUser3);

        CosineSimilarityMatrix m = new CosineSimilarityMatrix(likedItemsByUser);

        Assert.assertEquals(1., m.get(2, 2), 0.000001);
        Assert.assertEquals(1., m.get(3, 3), 0.000001);
        Assert.assertEquals(.5, m.get(2, 3), 0.000001);
        Assert.assertEquals(.5, m.get(3, 2), 0.000001);

        Assert.assertEquals(0., m.get(1, 2), 0.000001);
    }
}
