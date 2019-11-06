package Chess;

import Model.*;

import java.util.Scanner;

/**
 * Chess.java - Main class that runs main function to take in inputs until game is finished
 *
 * @author Zarir Hamza and Fares Easa
 */

public class Chess {

    /**
     * main - takes in user input until game is finished
     *
     * @param args - not used
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // IMPLEMENT DRAW FUNCTION AND RESIGN

        //System.out.println("this is updated");
        Scanner in = new Scanner(System.in);
        Board board = new Board();
        int gameCounter = 0;
        boolean first = true;
        boolean draw = false;

        while (board.gameState == 0) {
            String color = (gameCounter % 2 == 0) ? "White" : "Black";
            String opponent = (gameCounter % 2 == 0) ? "Black" : "White";
            if (board.gameCounter != gameCounter || first) {
                if(first)
                    first = false;
                board.gameCounter = gameCounter;
                if (new Check().amIinCheckMate(board)) {
                    if (new Check().amIinCheck(board)) {
                        board.printBoard();

                        System.out.println("checkmate");
                        System.out.println(opponent + " wins");
                        board.gameState = 1;
                        break;
                    } else {
                        board.printBoard();
                        System.out.println("stalemate");
                        System.out.println("draw");
                        board.gameState = 2;
                        break;
                    }
                } else if (new Check().amIinCheck(board))
                    System.out.println("Check");

                board.printBoard();
            }
            System.out.print(color + "'s move: ");
            String input = in.nextLine();

            if (input.equals("resign")) {
                board.printBoard();
                System.out.println(opponent + " wins");
                board.gameState = 1;
                break;
            } else if (draw) {
                if (input.equals("draw")) {
                    board.printBoard();
                    System.out.println("Draw");
                    board.gameState = 1;
                    break;
                } else
                    draw = false;
            } else if (input.contains("draw?")) {
                draw = true;
            }
            if (board.doMove(input)) {
                gameCounter++;
            }
        }
        //output winner based on state
    }
}
