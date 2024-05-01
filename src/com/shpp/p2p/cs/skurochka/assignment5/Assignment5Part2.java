package com.shpp.p2p.cs.skurochka.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part2 extends TextProgram {
    // The maximum value in one line that causes a bit of overflow.
    private static final int MAXIMUM_NUMBER_FOR_ONE_COLUMN = 10;
    // The maximum value in one line that causes a bit of overflow if there was an overflow in the previous line.
    private static final int MAXIMUM_NUMBER_FOR_ONE_OVERFLOWING = 9;

    // The method of starting the program.
    @Override
    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            // Reading the first line from the console.
            String n1 = readLine("Enter first number:  ");
            // Reading the second line from the console.
            String n2 = readLine("Enter second number: ");
            // Outputting the results of the method to the console.
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            // Missing a blank line.
            println();
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     * The method creates a stack that will contain the result.
     * After that, a variable is created that stores the value of the overflow.
     * Then both variables are equalized by the length of the string.
     * Then a loop is started that will be executed the number of times we have in the strings.
     * The next step is to add each bit of the string with the corresponding part of the other string.
     * Then the result is compared with a set of conditions depending on the overflow value,
     * and the corresponding value is written to the resulting string,
     * and the value of the overflow variable is changed accordingly,
     * depending on the input calculated value.
     * The last step is to return the calculated value to the method call point,
     * with the result adjusted accordingly depending on the value of the overflow variable.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        StringBuilder result = new StringBuilder();
        boolean columnOverflow = false;
        n1 = equalizeFirstString(n1, n2);
        n2 = equalizeSecondString(n1, n2);
        for (int i = n1.length() - 1; i >= 0; i--) {
            int midResult = (n1.charAt(i) - '0') + (n2.charAt(i) - '0');
            if (midResult == MAXIMUM_NUMBER_FOR_ONE_OVERFLOWING && columnOverflow) {
                result.insert(0, 0);
                columnOverflow = true;
            } else if (midResult > MAXIMUM_NUMBER_FOR_ONE_OVERFLOWING && columnOverflow) {
                result.insert(0, midResult - MAXIMUM_NUMBER_FOR_ONE_COLUMN + 1);
                columnOverflow = true;
            } else if (midResult < MAXIMUM_NUMBER_FOR_ONE_OVERFLOWING && columnOverflow) {
                result.insert(0, midResult + 1);
                columnOverflow = false;
            } else if (midResult == MAXIMUM_NUMBER_FOR_ONE_COLUMN) {
                result.insert(0, 0);
                columnOverflow = true;
            } else if (midResult > MAXIMUM_NUMBER_FOR_ONE_COLUMN) {
                result.insert(0, midResult - MAXIMUM_NUMBER_FOR_ONE_COLUMN);
                columnOverflow = true;
            } else {
                result.insert(0, midResult);
                columnOverflow = false;
            }
        }
        return columnOverflow ? 1 + result.toString() : result.toString();
    }

    /*
     * Method for getting a string from zeros of the corresponding value.
     * The method takes two strings as input. The resulting string is created.
     * Then a loop is started that repeats when the number of zeros is sufficient
     * to fulfill the condition of aligning the length of the string. In the loop,
     *  zeros are simply added to the result variable. Then, after the loop completes,
     * the resulting string is returned to the point of the method call.
     * */
    private String getNullString(String longerString, String shorterString) {
        StringBuilder nullString = new StringBuilder();
        for (int i = 0; i < (longerString.length() - shorterString.length()); i++) {
            nullString.append("0");
        }
        return nullString.toString();
    }

    /*
     * The method of equalizing the size of the first line.
     * The strings are compared and if the first string is smaller,
     * it is padded with zeros and returned, but if it is already larger,
     * it is simply returned.
     * */
    private String equalizeFirstString(String firstString, String secondString) {
        if (firstString.length() < secondString.length()) {
            return getNullString(secondString, firstString) + firstString;
        } else {
            return firstString;
        }
    }

    /*
     * A method for equalizing the size of the second string.
     * The strings are compared and if the second string is smaller,
     * it is padded with zeros and returned, but if it is already larger,
     * it is simply returned.
     * */
    private String equalizeSecondString(String firstString, String secondString) {
        if (firstString.length() > secondString.length()) {
            return getNullString(firstString, secondString) + secondString;
        } else {
            return secondString;
        }
    }
}
