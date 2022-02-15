/*
===========================================
- Title:  Wordle Java Clone
- Author: @zerot69
- Date:   15 Feb 2022
============================================
*/

package src;

import java.util.*;

import static src.Game.*;

public class Main {

    public static void main(String[] args) {

        src.Game.mainMenu();

        Scanner scanner = new Scanner(System.in);
        String scannedInput = scanner.nextLine().trim();

        while (scannedInput.equals("1")) {

            String typedWord;

            String[] wordArray = src.Game.randomWord();
            List<String> wordList = Arrays.asList(wordArray);
            Random random = new Random();
            String randomWord = wordList.get(random.nextInt(wordList.size()));
            char[] arrayRandomWord = randomWord.toCharArray();
            System.out.print("\nLet's play!\nType your first guess: ");

            int tries = 1;
            while (tries <= 6) {
                typedWord = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
                while ((typedWord.length() != 5) || (!wordList.contains(typedWord))){
                    System.out.print("Invalid guess. Try again: ");
                    typedWord = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
                }
                char[] arrayTypedWord = typedWord.toCharArray();

                System.out.print(tries + ". guess: ");
                if (Arrays.equals(arrayTypedWord, arrayRandomWord)){
                    for (int i=0; i<5; i++){
                        System.out.print(ANSI_GREEN_BACKGROUND + " " + arrayTypedWord[i] + " " + ANSI_RESET);
                    }
                    System.out.println("\n\nCongrats! You solved the WORDLE in \u001B[32m" + tries + "\u001B[0m tries.");
                    tries = 6;
                } else {
                    for (int i=0; i<5; i++){

                        int countCharTyped = typedWord.length() - typedWord.replaceAll(String.valueOf(arrayTypedWord[i]),"").length();
                        int countCharRandom = randomWord.length() - randomWord.replaceAll(String.valueOf(arrayTypedWord[i]),"").length();
                        int colorTiles = countCharRandom;

                        if (randomWord.contains(String.valueOf(arrayTypedWord[i]))){
                            if (arrayTypedWord[i]==arrayRandomWord[i]){
                                System.out.print(ANSI_GREEN_BACKGROUND + " " + arrayTypedWord[i] + " " + ANSI_RESET);
                            } else {
                                if (countCharTyped <= countCharRandom){
                                    System.out.print(ANSI_YELLOW_BACKGROUND + " " + arrayTypedWord[i] + " " + ANSI_RESET);
                                } else {
                                    for (int j=0; j<=i; j++){
                                        if (arrayTypedWord[j]==arrayTypedWord[i]) colorTiles--;
                                    }
                                    for (int k=0; k<5; k++){
                                        if (arrayTypedWord[k]==arrayRandomWord[i]) colorTiles--;
                                    }
                                    if (colorTiles>0) {
                                        System.out.print(ANSI_YELLOW_BACKGROUND + " " + arrayTypedWord[i] + " " + ANSI_RESET);
                                    } else {
                                        if (countCharRandom>0){
                                            int checkAppearPrevious = 0;
                                            int countGreenTiles = 0;
                                            for (int j=0; j<i; j++){
                                                if (arrayTypedWord[j]==arrayTypedWord[i]){
                                                    checkAppearPrevious++;
                                                }
                                            }
                                            for (int k=0; k<5; k++){
                                                if (arrayTypedWord[k]==arrayRandomWord[k] && arrayTypedWord[i]==arrayTypedWord[k]) countGreenTiles++;
                                            }
                                            if (checkAppearPrevious==0 && countGreenTiles==0) System.out.print(ANSI_YELLOW_BACKGROUND + " " + arrayTypedWord[i] + " " + ANSI_RESET);
                                            else System.out.print(ANSI_BLACK_BACKGROUND + " " + arrayTypedWord[i] + " " + ANSI_RESET);
                                        } else {
                                            System.out.print(ANSI_BLACK_BACKGROUND + " " + arrayTypedWord[i] + " " + ANSI_RESET);
                                        }
                                    }
                                }
                            }
                        } else {
                            System.out.print(ANSI_BLACK_BACKGROUND + " " + arrayTypedWord[i] + " " + ANSI_RESET);
                        }
                    }
                    System.out.println();
                }

                if (tries == 6 && !Arrays.equals(arrayTypedWord, arrayRandomWord)) {
                    System.out.println("\nLooks like you didn't win this time.");
                }
                tries++;
            }

            System.out.print("The word is: ");
            for (int i=0; i<5; i++){
                System.out.print(ANSI_GREEN_BACKGROUND + " " + arrayRandomWord[i] + " " + ANSI_RESET);
            }

            System.out.println("\n\nEnter 1 to continue.\nEnter 0 to exit.");
            scannedInput = scanner.nextLine().trim();

        }

        System.out.println("Invalid input. Exiting program!");
    }
}
