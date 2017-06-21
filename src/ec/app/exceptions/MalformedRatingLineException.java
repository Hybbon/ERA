package ec.app.exceptions;

/**
 * Exception thrown when reading a ratings file with a line that's not in the right format.
 */
public class MalformedRatingLineException extends Exception {
    private String mLine;

    public MalformedRatingLineException(String line) {
        mLine = line;
    }

    public String getLine() {
        return mLine;
    }
}
