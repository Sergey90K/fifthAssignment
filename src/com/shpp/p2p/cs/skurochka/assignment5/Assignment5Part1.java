package com.shpp.p2p.cs.skurochka.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part1 extends TextProgram {
    // Starting position for vowel letters.
    private static final int START_POSITION_VOWEL_LETTER = -2;
    // Array of vowel letters.
    private static final char[] VOWEL_LETTERS = {'a', 'e', 'i', 'o', 'u', 'y'};

    // The method of launching the program.
    @Override
    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesInWord(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     * The method sets a variable to save the results and a variable for the starting position of the vowel letter.
     * Then an array with all possible vowel letters is created.
     * Then the input word is converted to a word with only lowercase letters.
     * After that, a loop is started that searches through all the letters of the input word.
     * In the middle, a loop is run that searches for all vowel letters.
     * In the middle, the condition of all the rules is compared to get the number of syllables.
     * Then a checkbox is set depending on the position of the found vowel sound and
     * the position of the previous vowel sound. The value of the vowel position variable is then set.
     * Then, if the value of the flag variable is true, the result value is incremented by one.
     * At the end of all the cycles, the value is returned if the result was zero,
     * then the value will be incremented by one, if it is not zero, then this value is returned.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesInWord(String word) {
        int result = 0, vowelLetterPosition = START_POSITION_VOWEL_LETTER;
        boolean singleVowel;
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            for (char ch : VOWEL_LETTERS) {
                if (word.charAt(i) == ch && !(ch == VOWEL_LETTERS[1] && i == word.length() - 1)) {
                    singleVowel = vowelLetterPosition + 1 != i;
                    vowelLetterPosition = i;
                    if (singleVowel) { result++; }
                }
            }
        }
        return getResult(result, word);
    }

    /*
     * A method of getting the result depending on the previous result and word size.
     * If the word size is greater than zero, the previous result is checked,
     * and if it is equal to zero, the result is transferred, increased by one,
     * if the previous result was greater than zero, it will be returned unchanged.
     * If the word size is zero, then the input value of the previous result is returned.
     * */
    private int getResult(int result, String word) {
        if (word.length() != 0) {
            return result != 0 ? result : ++result;
        } else {
            return result;
        }
    }
}