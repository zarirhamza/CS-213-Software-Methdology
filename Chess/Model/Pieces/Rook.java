package Model.Pieces;

import Model.Board;
import Model.Piece;
import Model.Move;

import java.util.ArrayList;

/**
 * Rook.java - contains legal moveset of Rook
 * @author Zarir Hamza and Fares Easa
 */
public class Rook extends Piece {

    /**
     * Constructor of Rook piece
     * @param color - color of piece - black/white
     * @param count - -1 when initialized for the first time or turn number otherwise
     */
    public Rook(String color, int count) {
        super(color, "Rook", count);
        legalMoves = new ArrayList<Move>();

    }

    /**
     * legalMove - determines if user input is legal for Rook piece
     * @param m - move based on user input
     * @param B - board with all the pieces of the game
     * @return boolean to determine if legal move
     */
    @Override
    public boolean legalMove(Move m, Board B) {
        if(((B.boardArray[m.getX2()][m.getY2()] == null) || !(B.boardArray[m.getX2()][m.getY2()].getColor().equals(B.boardArray[m.getX1()][m.getY1()].getColor()))) && ((m.getY2() - m.getY1() == 0) || (m.getX2() - m.getX1() == 0))){
            //endpoint not same color or empty and moving along one same axis
            //check for no moves in doMove loop so dont worry about that here

            if(m.getX1() - m.getX2() == 0){ //no change in X
                int direction = (m.getY2() > m.getY1()) ? 1 : -1;

                int a = m.getY1() + direction;

                while(a != m.getY2()){
                    if(B.boardArray[m.getX1()][a] != null)
                        return false; //if not empty path then return false
                    a += direction;
                }
            }
            else if(m.getY2() - m.getY1() == 0){ // no change in Y
                int direction = (m.getX2() > m.getX1()) ? 1 : -1;

                int a = m.getX1() + direction;

                while(a != m.getX2()){
                    if(B.boardArray[a][m.getY1()] != null)
                        return false; //if not empty path then return false
                    a += direction;
                }
            }
            return true;
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

       /* for (int a = 0; a < legalMoves.size(); a++) {
            System.out.println(legalMoves.get(a).getX1() + " " + legalMoves.get(a).getY1() + " " + legalMoves.get(a).getX2() + " " + legalMoves.get(a).getY2() + " " + this.getName() );
        }*/

    }

    /**
     * toString - return proper initials of Rook and color
     * @return - Rook + color initials
     */
    public String toString(){
            return Character.toLowerCase(this.getColor().charAt(0)) + "R";
        }





}



