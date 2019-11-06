package Model.Pieces;

import Model.Board;
import Model.Piece;
import Model.Move;

import java.util.ArrayList;

/**
 * Knight.java - contains legal moveset of Knight
 * @author Zarir Hamza and Fares Easa
 */
public class Knight extends Piece {
    /**
     * Constructor of Knight piece
     * @param color - color of piece - black/white
     * @param count - -1 when initialized for the first time or turn number otherwise
     */
    public Knight(String color, int count) {
        super(color, "Knight", count);
        legalMoves = new ArrayList<Move>();

    }

    /**
     * legalMove - determines if user input is legal for Knight piece
     * @param m - move based on user input
     * @param B - board with all the pieces of the game
     * @return boolean to determine if legal move
     */
    @Override
    public boolean legalMove(Move m, Board B) {
        if(((B.boardArray[m.getX2()][m.getY2()] == null) || !(B.boardArray[m.getX2()][m.getY2()].getColor().equals(B.boardArray[m.getX1()][m.getY1()].getColor())))){
           //check if destination is null or diff piece
            if(Math.abs((m.getX2() - m.getX1())) == 2 && Math.abs(m.getY2() - m.getY1()) == 1){
                return true;
            }
            else if(Math.abs((m.getX2() - m.getX1())) == 1 && Math.abs(m.getY2() - m.getY1()) == 2){
                return true;
            }
        }
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

        if(x1 + 2 < 8 && y1 + 1 < 8 && legalMove(new Move(x1,y1,x1+2,y1+1),b)) // move up 2 right 1
            legalMoves.add(new Move(x1,y1,x1+2,y1+1));

        if(x1 + 1 < 8 && y1 + 2 < 8 && legalMove(new Move(x1,y1,x1+1,y1+2),b)) // move right 2 up 1
            legalMoves.add(new Move(x1,y1,x1+1,y1+2));

        if(x1 + 2 < 8 && y1 - 1 >= 0 && legalMove(new Move(x1,y1,x1+2,y1-1),b)) // move up 2 left 1
            legalMoves.add(new Move(x1,y1,x1+2,y1-1));

        if(x1 + 1 < 8 && y1 - 2 >= 0 && legalMove(new Move(x1,y1,x1+1,y1-2),b)) // move left 2 up 1
            legalMoves.add(new Move(x1,y1,x1+1,y1-2));

        if(x1 - 1 >= 0 && y1 - 2 >= 0 && legalMove(new Move(x1,y1,x1-1,y1-2),b)) // move left 2 down 1
            legalMoves.add(new Move(x1,y1,x1-1,y1-2));

        if(x1 - 2 >= 0 && y1 - 1 >= 0 && legalMove(new Move(x1,y1,x1-2,y1-1),b)) // move down 2 left 1
            legalMoves.add(new Move(x1,y1,x1-2,y1-1));

        if(x1 - 2 >= 0 && y1 + 1 < 8 && legalMove(new Move(x1,y1,x1-2,y1+1),b)) // move down 2 left 1
            legalMoves.add(new Move(x1,y1,x1-2,y1+1));

        if(x1 + 2 < 8 && y1 - 1 >= 0 && legalMove(new Move(x1,y1,x1+2,y1-1),b)) // move right 2 down 1
            legalMoves.add(new Move(x1,y1,x1+2,y1-1));


        /*for (int a = 0; a < legalMoves.size(); a++) {
            System.out.println(legalMoves.get(a).getX1() + " " + legalMoves.get(a).getY1() + " " + legalMoves.get(a).getX2() + " " + legalMoves.get(a).getY2() + " " + this.getName() );
        }*/
    }

    /**
     * toString - return proper initials of Knight and color
     * @return - Knight + color initials
     */
    public String toString(){
        return Character.toLowerCase(this.getColor().charAt(0)) + "N";
    }




}
