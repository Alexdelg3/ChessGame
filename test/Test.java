package test;

import game.*;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author Alex Delgado
 * Skeleton code taken from Dr. Boorojeni
 */
public class Test {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome! Please enter a command.");
        while (true) {
            String firstCommand = keyboard.nextLine();
            String[] firstTokens = firstCommand.split(" ");
            if (firstCommand.startsWith("new game")) {
                if (firstTokens.length != 4) {
                    System.out.println("Invalid input. Please enter exactly 2 names.");
                    continue;
                }
                Game game = new Game(firstTokens[2], firstTokens[3]);
                System.out.println("A new game has been made with " + game.getPlayer1() + " being Player 1 (White) and " +
                        game.getPlayer2() + " being Player 2 (Black).");
                //Subsequent commands after starting a game
                while (true) {
                    if (game.isWhiteTurn()) {
                        System.out.println(game.getPlayer1() + "'s turn, please input a valid command: ");
                    } else {
                        System.out.println(game.getPlayer2() + "'s turn, please input a valid command: ");
                    }
                    String command = keyboard.nextLine();
                    String[] tokens = command.split(" ");
                    if (command.startsWith("mv")) {
                        if (tokens.length != 2) {
                            System.out.println("Invalid input. Please enter the moves with no spaces.");
                            continue;
                        }
                        //Handles the command by turning it into a character array
                        char[] check = tokens[1].toCharArray();
                        if (!((check[0] >= 97 && check[0] <= 104) && (check[2] >= 97 && check[2] <= 104))) {//Checks the letters
                            System.out.println("Please input correct letters.");
                            continue;
                        }
                        if (!((check[1] >= 49 && check[1] <= 57) && (check[3] >= 49 && check[3] <= 57))) {//Checks the numbers
                            System.out.println("Please input correct numbers.");
                            continue;
                        }
                        //Move Legality Check using move & isLegal
                        if (game.move(new Move(tokens[1]))) {//If the move is legal
                            if (game.isWhiteTurn()) {
                                System.out.println(game.getPlayer2() + " moves: " + tokens[1]);//Turn is odd
                            } else {
                                System.out.println(game.getPlayer1() + " moves: " + tokens[1]);//Turn is even
                            }
                            game.showBoard(System.out);
                        } else {
                            System.out.println("Illegal Move.");
                        }
                    }
                    if (command.startsWith("cp")) {
                        if (tokens.length != 3) {
                            System.out.println("Invalid input. Please enter the capture with a space between the moves.");
                            continue;
                        }
                        String[] moveCommand = {tokens[1] + tokens[2]};
                        char[] check = moveCommand[0].toCharArray();
                        if (!((check[0] >= 97 && check[0] <= 104) && (check[2] >= 97 && check[2] <= 104))) {//Checks the letters
                            System.out.println("Please input correct letters.");
                            continue;
                        }
                        if (!((check[1] >= 49 && check[1] <= 57) && (check[3] >= 49 && check[3] <= 57))) {//Checks the numbers
                            System.out.println("Please input correct numbers.");
                            continue;
                        }
                        //Capture Legality Check using isLegal
                        if(game.capture(new Move(moveCommand[0]))){
                            if (game.isWhiteTurn()) {
                                System.out.println(game.getPlayer2() + " captures: " + tokens[1]);//Turn is odd
                            } else {
                                System.out.println(game.getPlayer1() + " captures: " + tokens[1]);//Turn is even
                            }
                            game.showBoard(System.out);
                        } else {
                            System.out.println("Illegal Capture.");
                        }
                    }
                    if (command.equals("undo")) {
                        game = game.subtractMove(game);
                        System.out.println("Undo successful. The board is currently:");
                        game.showBoard(System.out);
                    }
                    if (command.equals("print status")) {
                        game.showBoard(System.out);
                        System.out.println("List of moves: " + game.getMoves());
                    }
                    if(command.equals("quit")){
                        System.out.println("Thank you for playing!");
                        break;
                    }
                }
            } else {
                System.out.println("Invalid command. A new game must be started to use the other commands.");
            }
        }
    }
}
