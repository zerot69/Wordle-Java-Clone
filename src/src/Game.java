/*
===========================================
- Title:  Wordle Java Clone
- Author: @zerot69
- Date:   15 Feb 2022
============================================
*/

package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Game {

    // Declaring text and background colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";

    public static void mainMenu() {
        //Menu options
        System.out.println();
        System.out.println("\u001B[32mWORDLE:\u001B[0m");
        System.out.println("Guess the WORDLE in 6 tries.");
        System.out.println("Each guess must be a valid 5 letter word. Hit the enter button to submit.");
        System.out.println("After each guess, the color of the tiles will change to show how close your guess was to the word.");
        System.out.println("\nExamples:");
        System.out.println(ANSI_GREEN_BACKGROUND + " W " + ANSI_YELLOW_BACKGROUND + " O " + ANSI_BLACK_BACKGROUND + " R " + " D " + " S " + ANSI_RESET);
        System.out.println("The letter " + ANSI_GREEN_BACKGROUND + " W " + ANSI_RESET + " is in the word and in the correct spot.");
        System.out.println("The letter " + ANSI_YELLOW_BACKGROUND + " O " + ANSI_RESET + " is in the word but in the wrong spot.");
        System.out.println("The letters " + ANSI_BLACK_BACKGROUND + " R " + ANSI_RESET + ", " + ANSI_BLACK_BACKGROUND + " D " + ANSI_RESET + " and " + ANSI_BLACK_BACKGROUND + " S " + ANSI_RESET + " are not in the word in any spot.");
        System.out.println("\nEnter 1 to play!");
    }

    public static String[] randomWord() {
        //Generate wordList - Source: https://www.bestwordlist.com/
        String path = "..//Wordle//5-letter-words-list.txt";
        String contents = null;
        try {
            contents = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert contents != null;
        return contents.split("\\r?\\n");
    }
}
