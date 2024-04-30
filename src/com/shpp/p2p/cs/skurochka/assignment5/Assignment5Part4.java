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
    // Regular expressions to separate strings with quotation marks.
    private static final String CVS_REGEX_WITH_MARKS = ",\"\"|;\"\"";
    // Regular expressions for removing quotes.
    private static final String CVS_REGEX_FOR_DELETE_MARKS = "\"\"|\"";
    // A regular expression to check for quotes.
    private static final String CVS_REGEX_FOR_CHECK_MARKS = "\"";
    // Placement of the file.
    private static final String FILE_NAME = "data2.csv";
    // The index of the column to be displayed.
    private static final int COLUMN_INDEX = 0;

    // The method of launching the program.
    public void run() {
        // Checking the method for output results.
        System.out.println(extractColumn(FILE_NAME, COLUMN_INDEX));
        // Checking the method for the number of words in the answer.
        System.out.println(extractColumn(FILE_NAME, COLUMN_INDEX).size());
    }

    /*
     * A method for retrieving a column from CVS files by column number.
     * Gets the file path and column index. In the middle,
     * a buffer reader is created that connects to the file.
     * Then an empty regex file is created.
     * Then a variable is created to store the read string.
     * A loop is started that will continuously read the lines until
     * they end in the file and assign them to the variable.
     * Then it checks whether the string has quotes at the beginning of the string.
     * If there are, the first pair of quotes is removed.
     * Then the remaining quotes are removed again and the result is written to the collection.
     * If the condition is not met, the string is checked for quotes at all,
     * and if there are no quotes, the string is split using a regular expression and the
     * necessary part is obtained and put into the collection.
     * If there are quotes in the string, then the data is obtained by the following regular expression.
     * Then the reader buffer is opened. The value of the collection is returned at the point of the method call.
     * Then comes the error handling blocks. Then there is a null value return if an error occurred during the method.
     * */
    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            ArrayList<String> result = new ArrayList<>();
            String readinessString;
            while ((readinessString = bufferedReader.readLine()) != null) {
                if (readinessString.indexOf(CVS_REGEX_FOR_CHECK_MARKS) == 0) {
                    readinessString = (readinessString.split(CVS_REGEX_WITH_MARKS)[columnIndex]);
                    result.add(readinessString.split(CVS_REGEX_FOR_DELETE_MARKS)[countIndex(readinessString)]);
                } else {
                    if (!readinessString.contains(CVS_REGEX_FOR_CHECK_MARKS)) {
                        result.add(readinessString.split(CVS_REGEX)[columnIndex]);
                    } else {
                        result.add(readinessString.split(CVS_REGEX_FOR_CHECK_MARKS)[columnIndex]);
                    }
                }
            }
            bufferedReader.close();
            return result;
        } catch (FileNotFoundException e) {
            System.out.println(" The file was not found ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * A method for calculating the index of a substring that receives a string.
     * Returns the index value depending on the position of the regular expression in the substring.
     * */
    private int countIndex(String inputString) {
        return inputString.indexOf(CVS_REGEX_FOR_CHECK_MARKS) == 0 ? 1 : 0;
    }
}