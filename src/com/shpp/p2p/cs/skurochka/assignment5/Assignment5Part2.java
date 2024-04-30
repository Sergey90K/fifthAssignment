package com.shpp.p2p.cs.skurochka.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part2 extends TextProgram {

    private static final int MAXIMUM_NUMBER_FOR_ONE_COLUMN = 10;
    private static final int MAXIMUM_NUMBER_FOR_ONE_OVERFLOWING = 9;

    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number:  ");
            String n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            println();
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
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
        if (n1.length() == n2.length()) {
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
        }
        return columnOverflow ? 1 + result.toString() : result.toString();
    }

    private String getNullString(String longerString, String shorterString) {
        StringBuilder nullString = new StringBuilder();
        for (int i = 0; i < (longerString.length() - shorterString.length()); i++) {
            nullString.append("0");
        }
        return nullString.toString();
    }

    private String equalizeFirstString(String firstString, String secondString) {
        if (firstString.length() < secondString.length()) {
            return getNullString(secondString, firstString) + firstString;
        } else {
            return firstString;
        }
    }

    private String equalizeSecondString(String firstString, String secondString) {
        if (firstString.length() > secondString.length()) {
            return getNullString(firstString, secondString) + secondString;
        } else {
            return secondString;
        }
    }
}
