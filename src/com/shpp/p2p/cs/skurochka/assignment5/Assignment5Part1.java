package com.shpp.p2p.cs.skurochka.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part1 extends TextProgram {

    private static final int START_POSITION_VOWEL_LETTER = -2;

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
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    // Beautiful
    private int syllablesInWord(String word) {
        int result = 0, vowelLetterPosition = START_POSITION_VOWEL_LETTER;
        boolean singleVowel;
        char[] vowelLetters = {'a', 'e', 'i', 'o', 'u', 'y'};
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            for (char ch : vowelLetters) {
                if (word.charAt(i) == ch && !(ch == vowelLetters[1] && i == word.length() - 1)) {
                    singleVowel = vowelLetterPosition + 1 != i;
                    vowelLetterPosition = i;
                    if (singleVowel) {
                        result++;
                    }
                }
            }
        }
        return result != 0 ? result : ++result;
    }
}