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
    // A regular expression for separating strings with double quotes.
    private static final String CVS_REGEX_FOR_DUAL_QUOTE = "\"\"";
    // A mark to indicate the presence of double quotes.
    private static final char CVS_REGEX_FOR_RESTORATION_QUOTE = '!';
    // Double quotation marks to restore the final line.
    private static final char CVS_REGEX_QUOTE = '"';
    // Comma to restore the final line.
    private static final char CVS_REGEX_COMMA = ',';
    // Placement of the file.
    private static final String FILE_NAME = "data2.csv";
    // The index of the column to be displayed.
    private static final int COLUMN_INDEX = 1;

    // The method of launching the program.
    public void run() {
        // Checking the method for output results.
        System.out.println(extractColumn(FILE_NAME, COLUMN_INDEX));
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
                    readinessString = modifyLineForSeparation(replaceQuote(readinessString));
                    String[] midResult = readinessString.split(CVS_REGEX_FOR_STRING_WITH_QUOTE);
                    result.add(removeAllQuotationMarks(midResult)[columnIndex]);
                }
            }
            return result;
        } catch (FileNotFoundException e) {
            System.out.println(" The file was not found ");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(" Invalid column index ");
        }
        return null;
    }

    /*
     * A method for modifying a string to split it correctly.
     * First, a flag variable is created that will allow you to add upper quotes.
     * Then the resulting string is created using a string builder.
     * Then a loop is started to go through each letter of the string.
     * First, the conditions for changing the flag are checked, and they are changed if the conditions are met.
     * Then each letter is checked for commas and flag permission to add a character,
     * if the conditions are met, a punctuation mark is added at the specified index.
     * Then, after all operations are completed, the resulting string is returned to the method call point.
     * */
    private String modifyLineForSeparation(String string) {
        boolean flagAllow = true;
        StringBuilder resultString = new StringBuilder(string);
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == CVS_REGEX_QUOTE && string.length() > i + 1 &&
                    string.charAt(i + 1) != CVS_REGEX_FOR_RESTORATION_QUOTE) {
                flagAllow = !flagAllow;
            }
            if (string.charAt(i) == CVS_REGEX_COMMA && flagAllow) {
                resultString.insert(i + 1, CVS_REGEX_QUOTE);
            }
        }
        return resultString.toString();
    }

    /*
     * A method for replacing a special character with double quotes.
     * First, the index of the first occurrence of the special character is obtained.
     * If the special character is found, then the special character is
     * removed at this index and then double quotes are added to this position.
     * Then once again there is a check whether there are any more special characters,
     * and if so, they are called using recursion to call the same method.
     * At the end of the method, the changed string is returned to the point of the method call.
     * */
    private String restorationQuote(String inputString) {
        int index = inputString.indexOf(CVS_REGEX_FOR_RESTORATION_QUOTE);
        if (index > -1) {
            StringBuilder str = new StringBuilder(inputString);
            str.deleteCharAt(index);
            inputString = str.insert(index, CVS_REGEX_QUOTE).toString();
            if (inputString.indexOf(CVS_REGEX_FOR_RESTORATION_QUOTE) != -1) {
                inputString = restorationQuote(inputString);
            }
        }
        return inputString;
    }

    /*
     * Method for adding a special character for two double quotes.
     * First, the index of the first appearance of the double quotes is obtained.
     * A new string is created. If the quotes are found,
     * the special character is added immediately after the incremented index to the new string.
     * Then it is checked again whether there are any more double quotes,
     * and if so, the same method is called using recursion.
     * At the end of the method, the changed string is returned to the point of the method call.
     * */
    private String replaceQuote(String inputString) {
        int index = inputString.indexOf(CVS_REGEX_FOR_DUAL_QUOTE);
        if (index > -1) {
            StringBuilder str = new StringBuilder(inputString);
            inputString = str.insert(++index, CVS_REGEX_FOR_RESTORATION_QUOTE).toString();
            if (inputString.contains(CVS_REGEX_FOR_DUAL_QUOTE)) {
                inputString = replaceQuote(inputString);
            }
        }
        return inputString;
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
        if (inputString.contains(CVS_REGEX_FOR_CHECK_MARKS)) {
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
                if (midResult[i].charAt(j) == CVS_REGEX_QUOTE) {
                    counter++;
                }
            }
            if (counter > 0) {
                midResult[i] = restorationQuote(removeQuotationInString(midResult[i]));
            }
        }
        return midResult;
    }
}