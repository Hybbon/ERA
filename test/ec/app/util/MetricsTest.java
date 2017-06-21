package ec.app.util;

import librec.data.DenseMatrix;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Describe this class and the methods exposed by it.
 */
public class MetricsTest {

    @Test
    public void testEpc() {
        Vector<Integer> ranking = new Vector<>();
        ranking.add(1);
        ranking.add(2);
        ranking.add(3);

        Map<Integer, Double> popularityByItem = new HashMap<>();
        popularityByItem.put(1, .5);
        popularityByItem.put(2, .3);
        popularityByItem.put(3, .1);

        double expectedEpc = (0.5 + 0.7 * 0.85 + 0.9 * 0.85 * 0.85) / (1 + 0.85 + 0.85 * 0.85);
        double epc = Metrics.epc(ranking, popularityByItem, 3);
        Assert.assertEquals(expectedEpc, epc, 0.00001);
    }

    @Test
    public void testEild() {
        Vector<Integer> ranking = new Vector<>();
        ranking.add(1);
        ranking.add(2);
        ranking.add(3);

        // Setup for the similarity matrix
        Map<Integer, Integer> indexByItemId = new HashMap<>();
        indexByItemId.put(1, 0);
        indexByItemId.put(2, 1);
        indexByItemId.put(3, 2);

        DenseMatrix matrix = new DenseMatrix(3, 3);
        matrix.add(0, 0, 1);
        matrix.add(0, 1, 2 / Math.sqrt(6));
        matrix.add(0, 2, 1 / Math.sqrt(3));

        matrix.add(1, 0, 2 / Math.sqrt(6));
        matrix.add(1, 1, 1);
        matrix.add(1, 2, 1 / Math.sqrt(2));

        matrix.add(2, 0, 1 / Math.sqrt(3));
        matrix.add(2, 1, 1 / Math.sqrt(2));
        matrix.add(2, 2, 1);

        // Matrix is initialized directly, to avoid having to specify the likers one by one.
        CosineSimilarityMatrix similarityMatrix = new CosineSimilarityMatrix(matrix, indexByItemId);

        double eildK0 = (2 / Math.sqrt(6) + 0.85 / Math.sqrt(3)) / 1.85;
        double eildK1 = (2 / Math.sqrt(6) + 1 / Math.sqrt(2)) / 2;
        double eildK2 = (1 / Math.sqrt(3) + 1 / Math.sqrt(2)) / 2;

        double expectedEild = (eildK0 + eildK1 * 0.85 + eildK2 * Math.pow(0.85, 2)) / (1 + 0.85 + Math.pow(0.85, 2));
        double eild = Metrics.eild(ranking, similarityMatrix, 3);
        Assert.assertEquals(expectedEild, eild, 0.000001);
    }
}
