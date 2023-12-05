package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import app.Sentence;

public class AppTests {

    // valid sentences

    @Test
    public void testSentenceNumberStayNumber() {
        Sentence sentenceTest1 = new Sentence("16.");

        assertEquals("16.", sentenceTest1.getPhrase());
    }

    @Test
    public void testSentenceNumberToTextPositionStart() { // convert "1" to "one", since its at the start change ""one" to "One"
        Sentence sentenceTest1 = new Sentence("1.");

        assertEquals("One.", sentenceTest1.getPhrase());
    }

    @Test
    public void testSentenceNumberToTextPositionEnd() {
        Sentence sentenceTest1 = new Sentence("Hello 1.");

        assertEquals("Hello one.", sentenceTest1.getPhrase());
    }

    @Test
    public void testSentenceNumbersToTextPositionEnd() {
        Sentence sentenceTest1 = new Sentence("Hello 12.");

        assertEquals("Hello twelve.", sentenceTest1.getPhrase());
    }

    @Test
    public void testSentenceQuestionMarkAtEnd() {
        Sentence sentenceTest1 = new Sentence("First letter?");

        assertEquals("First letter?", sentenceTest1.getPhrase());
    }

    @Test
    public void testSentenceExclamationMarkAtEnd() {
        Sentence sentenceTest1 = new Sentence("First letter!");

        assertEquals("First letter!", sentenceTest1.getPhrase());
    }

    @Test
    public void testSentenceSpeechMarksEven() {
        Sentence sentenceTest1 = new Sentence("\"First \"letter?");

        assertEquals("\"First \"letter?", sentenceTest1.getPhrase());
    }

    @Test
    public void testSentenceGivenValidExample1() {
        Sentence sentenceTest1 = new Sentence("The quick brown fox said \"hello Mr lazy dog\".");

        assertEquals("The quick brown fox said \"hello Mr lazy dog\".", sentenceTest1.getPhrase());
    }

    @Test
    public void testSentenceGivenValidExample2() {
        Sentence sentenceTest1 = new Sentence("The quick brown fox said hello Mr lazy dog.");

        assertEquals("The quick brown fox said hello Mr lazy dog.", sentenceTest1.getPhrase());
    }

    @Test
    public void testSentenceGivenValidExample3V1() {
        Sentence sentenceTest1 = new Sentence("One lazy dog is too few, 13 is too many.");

        assertEquals("One lazy dog is too few, 13 is too many.", sentenceTest1.getPhrase());
    }

    @Test
    public void testSentenceGivenValidExample3V2() {
        Sentence sentenceTest1 = new Sentence("1 lazy dog is too few, 13 is too many.");

        assertEquals("One lazy dog is too few, 13 is too many.", sentenceTest1.getPhrase());
    }

    @Test
    public void testSentenceGivenValidExample4() {
        Sentence sentenceTest1 = new Sentence("How many \"lazy dogs\" are there?");

        assertEquals("How many \"lazy dogs\" are there?", sentenceTest1.getPhrase());
    }

    // not valid sentences

    @Test
    public void testSentenceNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sentence sentenceTest1 = new Sentence("");
        });
    }

    @Test
    public void testSentenceFullStopInMiddlle() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sentence sentenceTest1 = new Sentence("First L.etter.");
        });
    }

    @Test
    public void testSentenceQuestionMarkInMiddlle() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sentence sentenceTest1 = new Sentence("First L?etter.");
        });
    }

    @Test
    public void testSentenceExclamationMarkInMiddlle() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sentence sentenceTest1 = new Sentence("First L!etter.");
        });
    }

    @Test
    public void testSentenceSpeechMarksUneven() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sentence sentenceTest1 = new Sentence("\"First letter?");
        });
    }

    @Test
    public void testSentenceGivenInvalidExample1() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sentence sentenceTest1 = new Sentence("The quick brown fox said \"hello Mr. lazy dog\".");
        });
    }

    @Test
    public void testSentenceGivenInvalidExample2() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sentence sentenceTest1 = new Sentence("the quick brown fox said \"hello Mr lazy dog\".");
        });
    }

    @Test
    public void testSentenceGivenInvalidExample3() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sentence sentenceTest1 = new Sentence("\"The quick brown fox said \"hello Mr lazy dog\"");
        });
    }

    @Test
    public void testSentenceGivenInvalidExample4() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sentence sentenceTest1 = new Sentence("One lazy dog is too few, 12 is too many");
        });
    }

    @Test
    public void testSentenceGivenInvalidExample5() { // converts numbers under 13 to text (eg. 1 becomes one)
        Sentence sentenceTest1 = new Sentence("Are there 11, 12, or 13 lazy dogs?");

        assertEquals("Are there eleven, twelve, or 13 lazy dogs?", sentenceTest1.getPhrase());
    }

    @Test
    public void testSentenceGivenInvalidExample6() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sentence sentenceTest1 = new Sentence("There is no punctuation in this sentence");
        });
    }
}
