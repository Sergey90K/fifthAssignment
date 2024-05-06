package com.shpp.p2p.cs.skurochka.assignment5;

import com.shpp.cs.a.console.TextProgram;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part4 extends TextProgram {
    // Regular expressions to separate strings.
    private static final String CVS_REGEX = ",|;";
    // A regular expression to check for quotes.
    private static final String CVS_REGEX_FOR_CHECK_MARKS = "\"";
    // A regular expression to separate strings with quotes.
    private static final String CVS_REGEX_FOR_STRING_WITH_QUOTE = "\",|,\"|\";|;\"";
    // Placement of the file.
    private static final String FILE_NAME = "data2.csv";
    // The index of the column to be displayed.
    private static final int COLUMN_INDEX = 3;

    // The method of launching the program.
    public void run() {
        // Checking the method for output results.
        System.out.println(extractColumn(FILE_NAME, COLUMN_INDEX));
        // Checking the method for the number of words in the answer.
        //System.out.println(extractColumn(FILE_NAME, COLUMN_INDEX).size());
    }

    /*
     * A method for retrieving a column from CVS files by column number.
     * Gets the file path and column index. In the middle,
     * a buffer reader is created that connects to the file.
     * Then an empty regex file is created.
     * Then a variable is created to store the read string.
     * A loop is started that will continuously read the lines until
     * they end in the file and assign them to the variable.
     * Then it checks whether the string contains quotes.
     * If not, the string is divided into an array of strings using a
     * regular expression and assigned to the resulting variable.
     * If the condition is not met and the string contains quotes,
     * the string is divided into an array of strings using a regular expression.
     * Then the value obtained by the method that removes unnecessary quotes
     * in the resulting array of strings is assigned to the resulting collection.
     * Then comes the error handling blocks. Then there is a null value return if an error occurred during the method.
     * */
    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            ArrayList<String> result = new ArrayList<>();
            String readinessString;
            while ((readinessString = bufferedReader.readLine()) != null) {
                if (!readinessString.contains(CVS_REGEX_FOR_CHECK_MARKS)) {
                    result.add(readinessString.split(CVS_REGEX)[columnIndex]);
                } else {
                    String[] midResult = readinessString.split(CVS_REGEX_FOR_STRING_WITH_QUOTE);
                    result.add(removeAllQuotationMarks(midResult)[columnIndex]);
                }
            }
            return result;
        } catch (FileNotFoundException e) {
            System.out.println(" The file was not found ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * A method for removing quotes from a single string.
     * First, the index of the quotes in the string is obtained.
     * Then the condition is checked, if the index is not equal to minus one,
     * a string builder is created that accepts the input string.
     * Then the character following the quote index is removed from the string.
     * Then the string with the removed quotes is assigned to the input string.
     * Then the following condition is checked, whether the string still has quotes,
     * if there are quotes, the same method is called to remove the quotes.
     * Then the resulting string is returned to the point where the method was called.
     * */
    private String removeQuotationInString(String inputString) {
        int index = inputString.indexOf(CVS_REGEX_FOR_CHECK_MARKS);
        if (index != -1) {
            StringBuilder str = new StringBuilder(inputString);
            str.deleteCharAt(index);
            inputString = str.toString();
        }
        if (inputString.indexOf(CVS_REGEX_FOR_CHECK_MARKS) != -1) {
            inputString = removeQuotationInString(inputString);
        }
        return inputString;
    }

    /*
     * Method for removing quotes in an array of strings.
     * In the middle, a loop is started that sequentially loops through the entire array of strings.
     * Then a variable is compared with the number of quotes in the string.
     * Then a loop is launched that checks each letter of the word with the corresponding quotes,
     * and if it finds quotes, it increments the counter by one. Then, at the end of this loop,
     * the counter is checked and if it is greater than zero,
     * a method is called that will remove all quotes from the string.
     * After the top-level loop completes, the corrected array is returned to the point of the method call.
     * */
    private String[] removeAllQuotationMarks(String[] midResult) {
        for (int i = 0; i < midResult.length; i++) {
            int counter = 0;
            for (int j = 0; j < midResult[i].length(); j++) {
                if (midResult[i].charAt(j) == '\"') {
                    counter++;
                }
            }
            if (counter > 0) {
                midResult[i] = removeQuotationInString(midResult[i]);
            }
        }
        return midResult;
    }
}