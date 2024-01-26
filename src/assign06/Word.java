package assign06;

/**
 * Represents a word with utility methods.
 * 
 * @author Shawn
 * @version Oct 20, 2023
 */
public class Word {
    private char[] letters;

    /**
     * Initializes the word.
     * 
     * @param word Input word.
     * @throws IllegalArgumentException for non-alphabetic characters.
     */
    public Word(String word) {
        if (word == null || !isValidWord(word, 0)) {
            throw new IllegalArgumentException("Invalid word.");
        }
        this.letters = new char[word.length()];
        populateLetters(word, 0);
    }

    private boolean isValidWord(String word, int index) {
        if (index == word.length()) return true;
        char c = word.charAt(index);
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            return isValidWord(word, index + 1);
        }
        return false;
    }

    private void populateLetters(String word, int index) {
        if (index == word.length()) return;
        letters[index] = word.charAt(index);
        populateLetters(word, index + 1);
    }

    /**
     * @return Word as a string.
     */
    public String toString() {
        return toString(0);
    }

    private String toString(int startIndex) {
        if (startIndex == letters.length) return "";
        return letters[startIndex] + toString(startIndex + 1);
    }

    /**
     * Checks if the word is a palindrome.
     * 
     * @return true if palindrome, otherwise false.
     */
    public boolean isPalindrome() {
        return isPalindrome(0, letters.length - 1);
    }

    private boolean isPalindrome(int startIndex, int endIndex) {
        if (startIndex >= endIndex) return true;
        return (letters[startIndex] == letters[endIndex]) && isPalindrome(startIndex + 1, endIndex - 1);
    }

    /**
     * Replaces a letter with another in the word.
     * 
     * @param letter Target letter.
     * @param replacement Replacement letter.
     * @return New Word after replacement.
     * @throws IllegalArgumentException for non alphabetic input.
     */
    public Word replaceLetter(char letter, char replacement) {
        if (!isValidCharacter(letter) || !isValidCharacter(replacement)) {
            throw new IllegalArgumentException("Invalid letter.");
        }
        return new Word(replaceLetter(letter, replacement, 0));
    }

    private boolean isValidCharacter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private String replaceLetter(char letter, char replacement, int startIndex) {
        if (startIndex == letters.length) return "";
        char current = (letters[startIndex] == letter) ? replacement : letters[startIndex];
        return current + replaceLetter(letter, replacement, startIndex + 1);
    }

    /** Reverses the word in place. */
    public void reverse() {
        reverse(0, letters.length - 1);
    }

    private void reverse(int startIndex, int endIndex) {
        if (startIndex >= endIndex) return;
        char temp = letters[startIndex];
        letters[startIndex] = letters[endIndex];
        letters[endIndex] = temp;
        reverse(startIndex + 1, endIndex - 1);
    }
}
