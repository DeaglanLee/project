package app;

public class Sentence {

    private String phrase;

    /**
     * Contructs a sentence with the phrase
     * @param phrase
     * @throws IllegalArgumentException
     */
    public Sentence(String phrase) {
        setPhrase(phrase);
    }

    /**
     * Sets the phrase. Phrase must meet validation in order to store, if it doesnt
     * meet validation requirements it throws an exception
     * 
     * @param phrase
     * @throws IllegalArgumentException
     */
    public void setPhrase(String phrase) {
        int phraseLength =  phrase.length();
        if (phraseLength < 1) {
            throw new IllegalArgumentException("Phrase has no length!");
        }

        // initialise variables
        byte quotationMarksNumber = 0;
        String sentence = "";

        // loop through each character in the String phrase
        for (int i = 0; i < phraseLength; i++) {
            String numbers = "";
            char currentCharacter = phrase.charAt(i);

            // check fullstops ("."), question marks ("?") and exclamation mark ("!")
            // location/validation
            if (i != (phraseLength - 1) && punctuation(currentCharacter)) { // middle of sentence punctuation
                throw new IllegalArgumentException(
                        "Fullstop, Question Mark or Exclamation Marks cannot be in the middle of the sentence!");
            }
            if (i == (phraseLength - 1) && !punctuation(currentCharacter)) { // end of sentence punctuation
                throw new IllegalArgumentException(
                        "Fullstop, Question Mark or Exclamation Marks must be at the end of the sentence!");
            }

            // check number validation
            if (Character.isDigit(currentCharacter)) {
                String chars = Character.toString(currentCharacter);

                // check if the next character is also a number
                if (Character.isDigit(phrase.charAt(i + 1))) {
                    chars += Character.toString(phrase.charAt(i + 1));
                    i++; // correct character increment
                }

                // if chars is between 0 and 12 (inclusive) convert the chars to letter form
                // then store it
                // if chars is above 12 then is returns the chars (number form)
                numbers = numbersToText(chars);

                // if the number is the first letters in the sentence the put the first
                // character in uppercase
                if (i <= 1 && sentence.length() < 1) {
                    numbers = numbers.substring(0, 1).toUpperCase() + numbers.substring(1, numbers.length());
                }
            }

            // check quotation marks
            if (currentCharacter == '"') {
                quotationMarksNumber++;
            }

            // check the first character is uppercase
            if (i < 1) {
                if (Character.isLowerCase(phrase.charAt(0))) {
                    throw new IllegalArgumentException("First Letter must be in Uppercase");
                }
            }

            // if the character was a digit store the result of digit validation
            // else store whatever the current character is
            if (numbers.length() != 0) {
                sentence += numbers;
            } else {
                sentence += currentCharacter;
            }
        }

        // check if the number of quotation marks is even
        if (quotationMarksNumber % 2 != 0) {
            throw new IllegalArgumentException("There is not an even number of quotation marks in this sentence");
        }

        this.phrase = sentence;
    }

    public String getPhrase() {
        return this.phrase;
    }

    /**
     * convert value in numbers to value in letters (numbers between 0 - 12
     * inclusive)
     * 
     * @param value
     * @return value or numbers 0 - 12 in letter form
     */
    private String numbersToText(String value) {
        switch (value) {
            case "0":
                return "zero";
            case "1":
                return "one";
            case "2":
                return "two";
            case "3":
                return "three";
            case "4":
                return "four";
            case "5":
                return "five";
            case "6":
                return "six";
            case "7":
                return "seven";
            case "8":
                return "eight";
            case "9":
                return "nine";
            case "10":
                return "ten";
            case "11":
                return "eleven";
            case "12":
                return "twelve";
            default:
                return value;
        }
    }

    /**
     * checks if character parameter is a ".", "!" or a "?"
     * 
     * @param character
     * @return true or false
     */
    private boolean punctuation(char character) {
        switch (character) {
            case '.':
                return true;
            case '!':
                return true;
            case '?':
                return true;
            default:
                return false;
        }
    }
}
