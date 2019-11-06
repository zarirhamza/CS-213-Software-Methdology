package Model;

import java.util.ArrayList;

/**
 * Class.java - utility class with functions related to checking game state
 *
 * @author Zarir Hamza and Fares Easa
 */
public class Check {
    /**
     * array of boolean values to see if corresponding spot in the board can be reached
     * colors of the board being checked
     */

    public boolean[][] checkArray = new boolean[8][8];

    /**
     * color of the current turn
     */
    public String color;

    /**
     * Constructor to create updated array depending on boardstate
     *
     * @param color - color of pieces to be checked
     * @param board - board of pieces
     */
    public Check(String color, Board board) {
        this.color = color;
        for (int a = 0; a < 8; a++) {
            for (int b = 0; b < 8; b++) {
                checkArray[a][b] = false;
            }
        }
        this.setCheckArray(this.color, board);
    }

    /**
     * utility constructor
     */
    public Check() {
        ;
    }

    /**
     * sets array of boolean values depending on board and color
     *
     * @param color - color of pieces being checked
     * @param B     - board of pieces
     */
    public void setCheckArray(String color, Board B) {
        checkArray = new boolean[8][8];
        B.updatePieces();
        for (int a = 0; a < 8; a++) {
            for (int b = 0; b < 8; b++) {
                if (B.boardArray[a][b] != null && B.boardArray[a][b].getColor().equals(color)) {
                    checkArray[a][b] = true;
                    for (Move m : B.boardArray[a][b].legalMoves) {
                        checkArray[m.getX2()][m.getY2()] = true;
                    }
                }
            }
        }
        /*System.out.println(color);
        this.printArray(checkArray);*/
    }

    /***
     * Checks to see if the current color is in check
     * @param board - board of pieces
     * @return true/false if color is in check or not
     */
    public boolean amIinCheck(Board board) {
        String color = (board.gameCounter % 2 == 0) ? "white" : "black";
        //System.out.println(color);

        if (color.equals("white")) {
            boolean[][] blackArray = new Check("black", board).getCheckArray();
            int wkx = 0, wky = 0;
            for (int a = 0; a < 8; a++) {
                for (int b = 0; b < 8; b++) {
                    if (board.boardArray[a][b] != null && board.boardArray[a][b].getName().equals("King") && board.boardArray[a][b].getColor().equals("white")) {
                        wkx = a;
                        wky = b;
                    }
                }
            }

            //  this.printArray(blackArray);
            if (blackArray[wkx][wky])
                return true;
        } else if (color.equals("black")) {

            boolean[][] whiteArray = new Check("white", board).getCheckArray();
            int bkx = 0, bky = 0;
            for (int a = 0; a < 8; a++) {
                for (int b = 0; b < 8; b++) {
                    if (board.boardArray[a][b] != null && board.boardArray[a][b].getName().equals("King") && board.boardArray[a][b].getColor().equals("black")) {
                        bkx = a;
                        bky = b;
                    }
                }
            }
            // this.printArray(whiteArray);
            if (whiteArray[bkx][bky])
                return true;
        }
        return false;
    }

    /**
     * Checks if piece of color can reach destination on board
     *
     * @param color - color of piece moving
     * @param x1    - ending x point
     * @param y1    - ending y point
     * @param board - board of pieces
     * @return boolean whether spot is reachable or not
     */
    public static boolean reachable(String color, int x1, int y1, Board board) {
        boolean[][] colorArray = color.equals("white") ? board.whiteArray : board.blackArray;
        if (colorArray[x1][y1])
            return true;
        return false;
    }

    /**
     * determine if current color is in checkmate
     *
     * @param board - board of pieces
     * @return boolean to see if color is in checkmate
     */
    public boolean amIinCheckMate(Board board) {
        String color = (board.gameCounter % 2 == 0) ? "white" : "black";

        //board.updatePieces();

        for (int a = 0; a < 8; a++) {
            for (int b = 0; b < 8; b++) {
                // Board fakeboard = new Board(board);
                if (board.boardArray[a][b] != null && board.boardArray[a][b].getColor().equals(color)) {
                    ArrayList<Move> legalMovesPre = new ArrayList<Move>(board.boardArray[a][b].legalMoves);
                    for (Move m : legalMovesPre) {
                        //System.out.println(m.getX1() + " " + m.getY1() + " " + m.getX2() + " " + m.getY2() + " " + board.boardArray[a][b].getName());
                        Piece temp1 = board.boardArray[a][b];
                        Piece temp2 = board.boardArray[m.getX2()][m.getY2()];

                        board.boardArray[a][b] = null;
                        board.boardArray[m.getX2()][m.getY2()] = temp1;
                        board.updatePieces();
                        //board.printBoard();
                        //System.out.println("t/f");
                        //new Check().printArray(board.whiteArray);

                        if (!(new Check().amIinCheck(board))) { //if not in check anymore

                            board.boardArray[a][b] = temp1;
                            board.boardArray[m.getX2()][m.getY2()] = temp2;
                            board.updatePieces();

                            return false;
                        }
                        board.boardArray[a][b] = temp1;
                        board.boardArray[m.getX2()][m.getY2()] = temp2;
                        board.updatePieces();

                    }
                }
            }
        }
        return true;
    }

    /**
     * returns array of boolean values
     *
     * @return array of boolean values
     */
    public boolean[][] getCheckArray() {
        return checkArray;
    }

}
