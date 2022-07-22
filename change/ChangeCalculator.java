package edu.miracosta.cs113.change;

import java.io.*;
import java.util.ArrayList;

/**
 * ChangeCalculator : Class containing the recursive method calculateChange, which determines and prints all
 * possible coin combinations representing a given monetary value in cents.
 *
 * Problem derived from Koffman & Wolfgang's Data Structures: Abstraction and Design Using Java (2nd ed.):
 * Ch. 5, Programming Project #7, pg. 291.
 *
 * NOTE: An additional method, printCombinationsToFile(int), has been added for the equivalent tester file to
 * verify that all given coin combinations are unique.
 */
public class ChangeCalculator {

    public static final int QUARTER = 25, DIME = 10, NICKEL = 5, PENNY = 1;
    public static ArrayList<String> combos = new ArrayList<>();


    public static void makeChange(int total, int numQ, int numD, int numN, int numP){

        if (numQ * QUARTER + numD * DIME + numN * NICKEL + numP * PENNY > total) return;

        String combination = "[" + numQ + ", " + numD + ", " + numN + ", " + numP + "]";

        if (!combos.contains(combination)) combos.add(combination);

        if (numP >= 5) makeChange(total, numQ, numD, numN + 1, numP - 5);
        if (numP >= 10) makeChange(total, numQ, numD + 1, numN, numP - 10);
        if (numP >= 25) makeChange(total, numQ + 1, numD, numN, numP - 25);
    }

    /**
     * Wrapper method for determining all possible unique combinations of quarters, dimes, nickels, and pennies that
     * equal the given monetary value in cents.
     *
     * In addition to returning the number of unique combinations, this method will print out each combination to the
     * console. The format of naming each combination is up to the user, as long as they adhere to the expectation
     * that the coins are listed in descending order of their value (quarters, dimes, nickels, then pennies). Examples
     * include "1Q 2D 3N 4P", and "[1, 2, 3, 4]".
     *
     * @param cents a monetary value in cents
     * @return the total number of unique combinations of coins of which the given value is comprised
     */
    public static int calculateChange(int cents) {
        combos.clear();
        makeChange(cents, 0, 0, 0, cents);
        for (String s : combos){
            System.out.println(s);
        }
        return combos.size();
    }

    /**
     * Calls upon calculateChange(int) to calculate and print all possible unique combinations of quarters, dimes,
     * nickels, and pennies that equal the given value in cents.
     *
     * Similar to calculateChange's function in printing each combination to the console, this method will also
     * produce a text file named "CoinCombinations.txt", writing each combination to separate lines.
     *
     * @param cents a monetary value in cents
     */
    public static void printCombinationsToFile(int cents) {
        calculateChange(cents);
        try {
            File file = new File("/Users/allisonlane/CS113/Polynomial/src/edu/HW6/src/edu/miracosta/cs113/change/CoinCombinations.txt");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (String s : combos) {
                bw.write(s);
                bw.newLine();
            }
            bw.close();
        } catch (IOException fnfe){
            fnfe.printStackTrace();
        }

    }

} // End of class ChangeCalculator