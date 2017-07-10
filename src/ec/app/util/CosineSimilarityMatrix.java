package ec.app.util;

import librec.data.DenseMatrix;

import java.util.*;

/**
 * Describe this class and the methods exposed by it.
 */
public class CosineSimilarityMatrix {
    private DenseMatrix mMatrix;
    private Map<Integer, Integer> mIndexByItemId;

    public Map<Integer, Vector<Integer>> computeLikers(Map<Integer, Vector<Integer>> likedItemsByUser) {
        Map<Integer, Vector<Integer>> likersByItem = new HashMap<>();

        Iterator<Map.Entry<Integer, Vector<Integer>>> entriesIt = likedItemsByUser.entrySet().iterator();
        while (entriesIt.hasNext()) {
            Map.Entry<Integer, Vector<Integer>> entry = entriesIt.next();

            Integer userId = entry.getKey();
            Vector<Integer> likedItems = entry.getValue();

            for (Integer itemId : likedItems) {
                Vector<Integer> likers;
                if (likersByItem.containsKey(itemId)) {
                    likers = likersByItem.get(itemId);
                } else {
                    likers = new Vector<>();
                    likersByItem.put(itemId, likers);
                }

                likers.add(userId);
            }
        }

        return likersByItem;
    }

    private int computeIntersectionSize(Vector<Integer> a, Vector<Integer> b) {
        if (a.size() < b.size()) {
            Vector<Integer> swap = a;
            a = b;
            b = swap;
        }

        Set<Integer> bItems = new HashSet<>();
        for (Integer v : b) {
            bItems.add(v);
        }

        int intersectionSize = 0;
        for (Integer v : a) {
            if (bItems.contains(v)) {
                intersectionSize += 1;
            }
        }

        return intersectionSize;
    }

    private double cosineSimilarity(Vector<Integer> a, Vector<Integer> b) {
        int intersectionSize = computeIntersectionSize(a, b);
        double denominator = Math.sqrt(a.size()) * Math.sqrt(b.size());
        if (denominator == 0) {
            denominator = 1;
        }

        return intersectionSize / denominator;
    }

    public CosineSimilarityMatrix(Map<Integer, Vector<Integer>> likedItemsByUser) {
        Map<Integer, Vector<Integer>> likersByItem = computeLikers(likedItemsByUser);

        int numItems = likersByItem.size();
        mMatrix = new DenseMatrix(numItems, numItems);
        mIndexByItemId = new HashMap<>();

        int i = 0;
        for (Integer itemId : likersByItem.keySet()) {
            mIndexByItemId.put(itemId, i);

            // Similarity is 1 between the item and itself.
            mMatrix.add(i, i, 1.);

            i++;
        }

        for (Integer itemA : likersByItem.keySet()) {
            for (Integer itemB : likersByItem.keySet()) {

                // Avoid computing same similarities twice.
                if (itemB >= itemA) {
                    continue;
                }

                double similarity = cosineSimilarity(likersByItem.get(itemA), likersByItem.get(itemB));
                int indexA = mIndexByItemId.get(itemA);
                int indexB = mIndexByItemId.get(itemB);

                mMatrix.add(indexA, indexB, similarity);
                mMatrix.add(indexB, indexA, similarity);
            }
        }
    }

    // Direct constructor for test purposes.
    public CosineSimilarityMatrix(DenseMatrix matrix, Map<Integer, Integer> indexByItemId) {
        mMatrix = matrix;
        mIndexByItemId = indexByItemId;
    }

    public double get(int itemI, int itemJ) {
        if (!mIndexByItemId.containsKey(itemI) || !mIndexByItemId.containsKey(itemJ)) {
            return 0.;
        }

        return mMatrix.get(mIndexByItemId.get(itemI), mIndexByItemId.get(itemJ));
    }
}
