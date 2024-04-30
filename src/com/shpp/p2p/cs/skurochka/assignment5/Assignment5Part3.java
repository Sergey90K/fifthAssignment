package com.shpp.p2p.cs.skurochka.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part3 extends TextProgram {
    /* The file containing the names of the articles. */
    private static final String DICTIONARY_FILE = "en-dictionary.txt";

    private static final int NUMBER_OF_LETTERS = 3;

    @Override
    public void run() {
        ArrayList<String> dictionary = readDictionary();
        enterLettersAndFindWord(dictionary);
    }

    private void enterLettersAndFindWord(ArrayList<String> dictionary) {
        if (dictionary != null) {
            while (true) {
                String letters = readLine(" Please enter the letters: ");
                if (letters.length() == NUMBER_OF_LETTERS) {
                    letters = letters.toLowerCase();
                    boolean wordFound = checkDictionary(dictionary, letters);
                    if (!wordFound) {
                        System.out.println(" I can't find anything behind your note. ");
                    }
                } else {
                    System.out.println(" You have entered an invalid string. ");
                }
            }
        } else {
            System.out.println(" The program will not work correctly without a dictionary. ");
        }
    }

    private boolean checkDictionary(ArrayList<String> dictionary, String letters) {
        boolean wordFound = false;
        for (String word : dictionary) {
            int charPosition = 0, counter = 0;
            for (int i = 0; i < letters.length(); i++) {
                int index = word.indexOf(letters.charAt(i), charPosition + 1, word.length());
                if (index == -1) {
                    break;
                } else {
                    ++counter;
                    charPosition = index;
                }
                if (counter == NUMBER_OF_LETTERS) {
                    System.out.println(word);
                    wordFound = true;
                }
            }
        }
        return wordFound;
    }

    private ArrayList<String> readDictionary() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(DICTIONARY_FILE));
            ArrayList<String> result = new ArrayList<String>();
            while (true) {
                String word = bufferedReader.readLine();
                if (word == null) { break; }
                result.add(word);
            }
            return result;
        } catch (FileNotFoundException e) {
            System.out.println(" You have placed the dictionary file in the wrong location. ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
