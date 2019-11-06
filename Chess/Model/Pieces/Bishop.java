package Model.Pieces;

import Model.Board;
import Model.Piece;
import Model.Move;

import java.util.ArrayList;

/***
 * Bishop.java - contains legal moveset of Bishop
 * @author Zarir Hamza and Fares Easa
 */

public class Bishop extends Piece {

    /**
     * Constructor of Bishop piece
     * @param color - color of piece - black/white
     * @param count - -1 when initialized for the first time or turn number otherwise
     */
    public Bishop(String color, int count) {
        super(color, "Bishop", count);
        legalMoves = new ArrayList<Move>();

    }

    /**
     * legalMove - determines if user input is legal for bishop piece
     * @param m - move based on user input
     * @param B - board with all the pieces of the game
     * @return boolean to determine if legal move
     */
    @Override
    public boolean legalMove(Move m, Board B) {

        if(((B.boardArray[m.getX2()][m.getY2()] == null) || !(B.boardArray[m.getX2()][m.getY2()].getColor().equals(B.boardArray[m.getX1()][m.getY1()].getColor()))) && (Math.abs(m.getX1()-m.getX2()) == Math.abs(m.getY1()-m.getY2()))){
            //endpoint not of same color and movement is diagonal with equal change in x and y direction
            int directionX = (m.getX2() > m.getX1()) ? 1 : -1;
            int directionY = (m.getY2() > m.getY1()) ? 1 : -1;

            int a = m.getX1() + directionX;
            int b = m.getY1() + directionY;

            while(a != m.getX2() && b!= m.getY2()){
                if(B.boardArray[a][b] != null)
                    return false; //if not empty path then return false
                a += directionX;
                b += directionY;
            }
            return true; //if empty path then return true
        }
        return false; //if basic requirements not met then return false
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

        /*for (int a = 0; a < legalMoves.size(); a++) {
            System.out.println(legalMoves.get(a).getX1() + " " + legalMoves.get(a).getY1() + " " + legalMoves.get(a).getX2() + " " + legalMoves.get(a).getY2() + " " + this.getName() );
        }*/

    }

    /**
     * toString - return proper initials of bishop and color
     * @return - bishop + color initials
     */
    public String toString(){
        return Character.toLowerCase(this.getColor().charAt(0)) + "B";
    }

}
