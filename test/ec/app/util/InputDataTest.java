package ec.app.util;

import ec.app.data.InputData;
import ec.app.exceptions.MalformedRatingLineException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Vector;

public class InputDataTest {

    @Test
    public void testReadRatingsFile() throws IOException, MalformedRatingLineException {
        StringReader fileContents = new StringReader("1\t2\t10\n1\t23\t4444\n2\t2\t2\n");
        Map<Integer, Vector<Integer>> likedItemsByUser = InputData.readRatingsFile(fileContents);

        Assert.assertTrue(likedItemsByUser.containsKey(1));
        Assert.assertTrue(likedItemsByUser.containsKey(2));

        Vector<Integer> likedByUser1 = likedItemsByUser.get(1);
        Vector<Integer> expectedLikesForUser1 = new Vector<>();
        expectedLikesForUser1.add(2);
        expectedLikesForUser1.add(23);
        Assert.assertEquals(expectedLikesForUser1, likedByUser1);

        Vector<Integer> likedByUser2 = likedItemsByUser.get(2);
        Vector<Integer> expectedLikesForUser2 = new Vector<>();
        expectedLikesForUser2.add(2);
        Assert.assertEquals(expectedLikesForUser2, likedByUser2);
    }

    @Test(expected = MalformedRatingLineException.class)
    public void testReadRatingsThrowsExceptionOnLineWithFourIntegers()
        throws IOException, MalformedRatingLineException {
        StringReader fileContents = new StringReader("1\t23\t44\t4444\n");
        InputData.readRatingsFile(fileContents);
    }

    @Test(expected = MalformedRatingLineException.class)
    public void testReadRatingsThrowsExceptionOnLineWithFloat()
        throws IOException, MalformedRatingLineException {
        StringReader fileContents = new StringReader("1\t23.7\t4444\n");
        InputData.readRatingsFile(fileContents);
    }

    @Test(expected = MalformedRatingLineException.class)
    public void testReadRatingsThrowsExceptionOnLineWithSpaces()
        throws IOException, MalformedRatingLineException {
        StringReader fileContents = new StringReader("1 23.7 4444\n");
        InputData.readRatingsFile(fileContents);
    }
}
