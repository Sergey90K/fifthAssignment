package com.shpp.p2p.cs.skurochka.assignment5;

import com.shpp.cs.a.console.TextProgram;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part3 extends TextProgram {
    // The file containing the names of the articles.
    private static final String DICTIONARY_FILE = "en-dictionary.txt";
    // Number of letters to enter and search for.
    private static final int NUMBER_OF_LETTERS = 3;

    // The method of launching the program.
    @Override
    public void run() {
        // Getting a dictionary from a file.
        ArrayList<String> dictionary = readDictionary();
        // Run the method to find matches in the dictionary.
        enterLettersAndFindWord(dictionary);
    }

    /*
     * A method of entering letters and searching for them in a dictionary.
     * The method takes a dictionary as input.
     * First, it checks if the dictionary is not null.
     * If the condition is met, a message is displayed in the console informing that you need to enter data,
     * and reads it from the console and saves it in a variable.
     * Then it checks whether the variable entered by the user is of the required size.
     * If so, the letters are re-checked in lower case.
     * Then a method is called that will look for a match in the
     * dictionary and return the result if there was a match.
     * Next, the matching condition is checked, if no matches are found,
     * a special message is displayed in the console.
     * The final step is to fulfill the conditions if the size of
     * the entered word does not meet the required conditions,
     * and a corresponding message is displayed.
     * Then the conditions are fulfilled if the dictionary is zero,
     * and a corresponding message is also displayed.
     * */
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

    /*
     * A method for checking matches in a dictionary.
     * Takes a dictionary as input and a string of letters.
     * First, a boolean variable is created to store the result,
     * whether any matches were found at all.
     * Then a loop is started that searches through all the words in the dictionary.
     * In the middle of the loop,
     * variables are created to store the position of the found character and the number of characters found.
     * Then the loop is started to search all the characters for verification.
     * The next step is to search for the character in the word from the dictionary,
     * and if found, the value is stored in the index variable.
     * Then the index is checked and if it is minus one, the internal loop is interrupted.
     * If the condition is not met, the count of found liters is incremented by one.
     * The index of the found element is stored in a special variable with the position of the character.
     * Then it is checked whether the count of the found characters is equal to the required one,
     * if so, the result is displayed in the console and the value of the word variable is set to true.
     * At the end of all cycles, the value of the character variable is returned to the point of the method call.
     * */
    private boolean checkDictionary(ArrayList<String> dictionary, String letters) {
        boolean wordFound = false;
        for (String word : dictionary) {
            int charPosition = -1, counter = 0;
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

    /*
     * A method for reading a dictionary from a file.
     * The method is executed in an area with the ability to catch and handle the corresponding errors.
     * First, a buffer object is created with the path to the file.
     * Then a collection is created to store the result.
     * Then an infinite loop of reading data is started.
     * In the middle, the given string is read and assigned to a variable,
     * which is then checked for null, and if it is null, the loop is interrupted.
     * If not, the data is written to the collection. After the loop is completed, the buffer object is closed.
     * Then the results are passed to the method call point.
     * Here are two methods that catch certain types of parcels and process them accordingly.
     * And in the end, the value null is returned if the method did not work correctly and received an error.
     * */
    private ArrayList<String> readDictionary() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(DICTIONARY_FILE));
            ArrayList<String> result = new ArrayList<>();
            while (true) {
                String word = bufferedReader.readLine();
                if (word == null) { break; }
                result.add(word);
            }
            bufferedReader.close();
            return result;
        } catch (FileNotFoundException e) {
            System.out.println(" You have placed the dictionary file in the wrong location. ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}