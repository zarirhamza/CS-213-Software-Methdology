package Model.Pieces;

import Model.Board;
import Model.Piece;
import Model.Move;

import java.util.ArrayList;

/**
 * Queen.java - contains legal moveset of Queen
 * @author Zarir Hamza and Fares Easa
 */
public class Queen extends Piece {

    /**
     * Constructor of Queen piece
     * @param color - color of piece - black/white
     * @param count - -1 when initialized for the first time or turn number otherwise
     */
    public Queen(String color, int count) {
        super(color, "Queen", count);
        legalMoves = new ArrayList<Move>();

    }

    /**
     * legalMove - determines if user input is legal for Queen piece
     * @param m - move based on user input
     * @param B - board with all the pieces of the game
     * @return boolean to determine if legal move
     */
    @Override
    public boolean legalMove(Move m, Board B) {

        Rook testRook = new Rook("grey", -1);
        Bishop testBishop = new Bishop("grey", -1);

        if(testRook.legalMove(m,B) || testBishop.legalMove(m,B))
            return true;

        return false;
    }

    /**
     * Updates Move arraylist of possible moves for the current piece
     * @param x1 - starting x location
     * @param y1 - starting y location
     * @param b - board with all the other pieces
     */
    @Override
    public void updateMoveList(int x1, int y1, Board b) {

        legalMoves.clear();

        for(int a = 1; a + x1 < 8; a++){ // move right
            if(legalMove(new Move(x1,y1,x1+a,y1), b))
                legalMoves.add(new Move(x1,y1,x1+a,y1));
        }

        for(int a = 1; x1 - a >= 0; a++){ // move left
            if(legalMove(new Move(x1,y1,x1 - a,y1), b))
                legalMoves.add(new Move(x1,y1,x1 - a,y1));
        }

        for(int a = 1; a + y1 < 8; a++){ // move up
            if(legalMove(new Move(x1,y1,x1,y1 + a), b))
                legalMoves.add(new Move(x1,y1,x1,y1 + a));
        }

        for(int a = 1; y1 - a >= 0; a++){ // move down
            if(legalMove(new Move(x1,y1,x1,y1 - a), b))
                legalMoves.add(new Move(x1,y1,x1,y1 - a));
        }

        for(int a = 1; x1+a < 8 && y1+a < 8; a++){ //move up right
            if(legalMove(new Move(x1,y1,x1+a,y1+a),b))
                legalMoves.add(new Move(x1,y1,x1+a,y1+a));
        }

        for(int a = 1; x1+a < 8 && y1-a >= 0; a++){ // move up left
            if(legalMove(new Move(x1,y1,x1+a,y1-a),b))
                legalMoves.add(new Move(x1,y1,x1+a,y1-a));
        }

        for(int a = 1; x1-a >= 0 && y1+a < 8; a++){ // move down right
            if(legalMove(new Move(x1,y1,x1-a,y1+a),b))
                legalMoves.add(new Move(x1,y1,x1-a,y1+a));
        }

        for(int a = 1; x1-a >= 0 && y1-a >= 0; a++){ // move down left
            if(legalMove(new Move(x1,y1,x1-a,y1-a),b))
                legalMoves.add(new Move(x1,y1,x1-a,y1-a));
        }

       /* for (int a = 0; a < legalMoves.size(); a++) {
            System.out.println(legalMoves.get(a).getX1() + " " + legalMoves.get(a).getY1() + " " + legalMoves.get(a).getX2() + " " + legalMoves.get(a).getY2() + " " + this.getName() );
        }*/


    }

    /**
     * toString - return proper initials of Queen and color
     * @return - Queen + color initials
     */
    public String toString(){
        return Character.toLowerCase(this.getColor().charAt(0)) + "Q";
    }




}
