package com.sda.hangman;

import com.sda.hangman.domain.HangmanGameService;
import com.sda.hangman.domain.model.GameStatus;
import com.sda.hangman.domain.port.PhraseRepository;
import com.sda.hangman.infrastructure.memory.InMemoryPhraseRepository;

import java.util.Scanner;

public class Application {

    private static Scanner scanner;
    private static PhraseRepository phraseRepository;
    private static HangmanGameService hangmanGameService;

    public static void main(String[] args) {

        hangmanGameService = new HangmanGameService();
        phraseRepository = new InMemoryPhraseRepository();
        scanner = new Scanner(System.in);
        boolean menuFlag = true;
        do {
            System.out.println(
                    "1. Start\n" +
                            "2. Result\n" +
                            "Other. End\n");
            int menu = scanner.nextInt();

            switch (menu) {
                case 1:
                    System.out.println("Start Logic");
                    break;
                case 2:
                    System.out.println("Results Logic");
                    break;
                default:
                    System.out.println("End");
                    menuFlag = false;

            }
        } while (menuFlag);


    }

    public static void startTheGame() {
        System.out.println("Insert your name:");
        String name = scanner.nextLine();
        System.out.println("Hit enter to play the game");
        String phrase = phraseRepository.getPhrase();
        GameStatus gameStatus = hangmanGameService.createGame(name, phrase);

        while (true) {

            System.out.println("The phrase will be here, (5 attempts left)");
            System.out.println("Please guess a character");
            char letter = scanner.nextLine().charAt(0);
            hangmanGameService.processNextLetter(letter, gameStatus);


        }
    }
}
