package Model;

import Model.Pieces.*;

import java.util.StringTokenizer;

/**
 * Board.java - Board class with the board array of Pieces along with gamestate and gamecounter
 * @author Zarir Hamza and Fares Easa
 */

public class Board {

    /**
     * boardArray - holds each piece in chess
     */
    public Piece[][] boardArray = new Piece[8][8];

    /**
     * holds T/F values for reachable spots for white pieces
     */
    public boolean[][] whiteArray = new boolean[8][8];

    /**
     * holds T/F values for reachable spots for black pieces
     */
    public boolean[][] blackArray = new boolean[8][8];

    /**
     * gamecounter - keeps track of turn number to help deduce color
     */
    public int gameCounter;

    /**
     *  gamestate - one of three states: 0 - continue, 1 - win , 2 - draw
     */
    public int gameState;

    /**
     * Board constructor creates all pieces for both colors for chess game and adds them to the Board array in starting positions
     * */
    public Board() {
        this.gameCounter = 0;
        this.gameState = 0;

        for (int a = 0; a < 8; a++){
            boardArray[1][a] = new Pawn("black",-1);
            boardArray[6][a] = new Pawn("white",-1);
        }

        boardArray[0][0] = new Rook("black", -1);
        boardArray[0][7] = new Rook("black", -1);
        boardArray[7][0] = new Rook("white",-1);
        boardArray[7][7] = new Rook("white",-1);


        boardArray[0][1] = new Knight("black", -1);
        boardArray[0][6] = new Knight("black", -1);
        boardArray[7][1] = new Knight("white", -1);
        boardArray[7][6] = new Knight("white", -1);

        boardArray[0][2] = new Bishop("black", -1);
        boardArray[0][5] = new Bishop("black", -1);
        boardArray[7][2] = new Bishop("white", -1);
        boardArray[7][5] = new Bishop("white", -1);

        boardArray[0][4] = new King("black", -1);
        boardArray[7][4] = new King("white", -1);


        boardArray[0][3] = new Queen("black", -1);
        boardArray[7][3] = new Queen("white", -1);

        this.updatePieces();

        whiteArray = new Check("white", this).getCheckArray();
        blackArray = new Check("black", this).getCheckArray();
    }


    /**
     * updates the legalmoves arraylist for all pieces on the board
     */
    public void updatePieces(){
        for(int a = 0; a < 8; a++){
            for(int b = 0; b < 8; b++){
                if(this.boardArray[a][b] != null)
                    this.boardArray[a][b].updateMoveList(a,b,this);
            }
        }

    }

    /**
     * prints the board of the game depending on the pieces in boardArray
     * */
    public void printBoard () {
        System.out.println();
        for(int a = 0; a < 8; a++){
            for(int b = 0; b < 8; b++){
                if(boardArray[a][b] == null){
                    if(!(a%2 == b%2))
                        System.out.print("## ");
                    else
                        System.out.print("   ");
                }
                else{
                    System.out.print(boardArray[a][b].toString() + " ");
                }
            }
            System.out.println(" " + (8 - a));
        }
        System.out.println(" a  " + "b  " + "c  " + "d  " + "e  " + "f  " + "g  " + "h");
        System.out.println();
    }

    /**
     * extracts piece at starting location and checks if all conditions are met for proper move
     * @param input is the string input taken from the user, i.e "e2 e4"
     * @return boolean to determine if move was legal or not
     * */
    public boolean doMove (String input) {
        Piece previousPiece;
        int previousCount = -1;
        StringTokenizer st = new StringTokenizer(input, " ");
        Move m = new Move(st.nextToken(), st.nextToken());
        Piece currentPiece = boardArray[m.getX1()][m.getY1()];
        if(currentPiece == null || (gameCounter % 2 == 0 && currentPiece.getColor().equals("black")) || (gameCounter % 2 == 1 && currentPiece.getColor().equals("white")) || (m.getX2() == m.getX1() && m.getY2() == m.getY1())) {
            System.out.println("\nIllegal move, try again\n");
            return false;
        }
        if(currentPiece.legalMove(m, this) == false) {
            System.out.println("\nIllegal move, try again\n");
            return false;
        }

        previousPiece = boardArray[m.getX1()][m.getY1()];
        previousCount = boardArray[m.getX1()][m.getY1()].getCount();
        boardArray[m.getX1()][m.getY1()] = null;
        //revert enpassant if needed

        if(previousPiece instanceof Pawn){
            if(((Pawn) previousPiece).update(m.getX2(), previousPiece.getColor())){
                if(st.hasMoreTokens())
                    boardArray[m.getX2()][m.getY2()] = ((Pawn) previousPiece).promotePawn(st.nextToken());
                else
                    boardArray[m.getX2()][m.getY2()] = ((Pawn) previousPiece).promotePawn("");
            }
            else
                boardArray[m.getX2()][m.getY2()] = currentPiece;

            boardArray[m.getX2()][m.getY2()].setCount(gameCounter);
        }
        //revert castle if needed - shouldnt be tho
        else if(previousPiece instanceof King && Math.abs(m.getY1()-m.getY2()) == 2){
            int direction = (m.getY2() > m.getY1()) ? 1 : -1;//right or left castle
            int rookPosition = direction == 1 ? 7 : 0; // right or left rook
            boardArray[m.getX2()][m.getY2()] = currentPiece;
            boardArray[m.getX2()][m.getY2()].setCount(gameCounter);

            boardArray[m.getX1()][rookPosition] = null;
            boardArray[m.getX1()][m.getY2() + (direction * -1)] = new Rook(currentPiece.getColor(),gameCounter);
        }
        else {
            boardArray[m.getX2()][m.getY2()] = currentPiece;
            boardArray[m.getX2()][m.getY2()].setCount(gameCounter);
        }


       //creates array of true and false values while obtaining positon of kings
        whiteArray = new Check("white", this).getCheckArray();
        blackArray = new Check("black", this).getCheckArray();
        boolean foundKingW = false, foundKingB = false;
        int wkx = 0, wky = 0, bkx = 0, bky = 0;
        for(int a = 0; a < 8; a++){
            for(int b = 0; b < 8; b++) {
                if (boardArray[a][b] != null && boardArray[a][b].getName().equals("King")){
                    if(boardArray[a][b].getColor().equals("white")) {
                        wkx = a; wky = b;
                        foundKingW = true;
                    }
                    else if(boardArray[a][b].getColor().equals("black")){
                        bkx = a; bky = b;
                        foundKingB = true;
                    }
                }
            }
        }


        //checks to see if in check and reverts back if needed
        String color = (gameCounter%2==0) ? "white":"black";
       // System.out.println(wkx + " " + wky + " " + bkx + " " + bky + " " + color);
       // new Check().printArray(blackArray);
        //new Check("black", this).printArray(blackArray);
        if(color.equals("white") && blackArray[wkx][wky] && foundKingW){
            boardArray[m.getX1()][m.getY1()] = previousPiece;
            boardArray[m.getX2()][m.getY2()] = null;
            previousPiece.setCount(previousCount);
            System.out.println("\nIllegal move, try again\n");
            return false;
        }
        else if(color.equals("black") && whiteArray[bkx][bky] && foundKingB){
            boardArray[m.getX1()][m.getY1()] = previousPiece;
            boardArray[m.getX2()][m.getY2()] = null;
            previousPiece.setCount(previousCount);
            System.out.println("\nIllegal move, try again\n");
            return false;
        }
        this.updatePieces();
        return true;
    }
}

