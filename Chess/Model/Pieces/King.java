package Model.Pieces;

import Model.Board;
import Model.Check;
import Model.Piece;
import Model.Move;

import java.util.ArrayList;

/**
 * King.java - contains legal moveset of King
 * @author Zarir Hamza and Fares Easa
 */
public class King extends Piece {
    /**
     * Constructor of King piece
     * @param color - color of piece - black/white
     * @param count - -1 when initialized for the first time or turn number otherwise
     */
    public King(String color, int count){
        super(color, "King", count);
        legalMoves = new ArrayList<Move>();
    }

    /**
     * legalMove - determines if user input is legal for King piece
     * @param m - move based on user input
     * @param B - board with all the pieces of the game
     * @return boolean to determine if legal move
     */
    @Override
    public boolean legalMove(Move m, Board B) {
        //cant move into check

        if(((B.boardArray[m.getX2()][m.getY2()] == null) || !(B.boardArray[m.getX2()][m.getY2()].getColor().equals(B.boardArray[m.getX1()][m.getY1()].getColor())))){
            if(Math.abs((m.getX2() - m.getX1())) == 1 && (m.getY2() - m.getY1()) == 0){ // change one in x direction
                return true;
            }
            else if(Math.abs((m.getY2() - m.getY1())) == 1 && (m.getX2() - m.getX1()) == 0){ // change one in y direction
                return true;
            }
            else if((Math.abs((m.getY2() - m.getY1())) == 1 && Math.abs(m.getX2() - m.getX1()) == 1)){ // change 1 in each direction
                return true;
            }
        }
        if((m.getX1() - m.getX2() == 0) && Math.abs(m.getY2()-m.getY1()) == 2 && B.boardArray[m.getX1()][m.getY1()].getCount() == -1){ //castling

            int direction = (m.getY2() > m.getY1()) ? 1 : -1;//right or left castle

            int rookPosition = direction == 1 ? 7 : 0; // right or left rook

            if(B.boardArray[m.getX1()][rookPosition] != null && B.boardArray[m.getX1()][rookPosition].getCount() != -1) //check if rook is untouched
                return false;


            int a = m.getY1();
            String color = (B.boardArray[m.getX1()][m.getY1()].getColor().equals("white")) ? "black" : "white";
           // Check c = new Check();
            while(a != m.getY2()){                              //only check if any spot beside starting is null
                if(Check.reachable(color, m.getX1(), a, B) || (B.boardArray[m.getX1()][a] != null && a != m.getY1())){ // if in check or piece in between
                    return false;
                }
                a += direction;
            }
            //if nothing in way then return true
            //B.boardArray[m.getX1()][rookPosition] = null;
            //B.boardArray[m.getX1()][m.getY1() + (direction * -1)] = new Rook(B.boardArray[m.getX1()][m.getY1()].getColor(),B.gameCounter);

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
        //check if u can move one spot in each direction
        legalMoves.clear();

        if(x1+1 < 8 && this.legalMove(new Move(x1,y1,x1+1,y1), b)) //move up one
            legalMoves.add(new Move(x1,y1,x1+1,y1));

        if(y1+1 < 8 && this.legalMove(new Move(x1,y1,x1,y1 + 1), b)) // move right
            legalMoves.add(new Move(x1,y1,x1,y1+1));

        if(x1+1 < 8 && y1+1 < 8 && this.legalMove(new Move(x1,y1,x1+1,y1+1), b)) //move right up
            legalMoves.add(new Move(x1,y1,x1+1,y1+1));

        if(x1-1 >= 0 && this.legalMove(new Move(x1,y1,x1-1,y1), b)) //move back
            legalMoves.add(new Move(x1,y1,x1-1,y1));

        if(y1-1 >= 0 && this.legalMove(new Move(x1,y1,x1,y1-1), b)) //move left
            legalMoves.add(new Move(x1,y1,x1,y1-1));

        if(x1-1 >= 0 && y1-1 >= 0 && this.legalMove(new Move(x1,y1,x1-1,y1-1), b)) //move back left
            legalMoves.add(new Move(x1,y1,x1-1,y1-1));

        if(x1+1 < 8 && y1-1 >= 0 && this.legalMove(new Move(x1,y1,x1+1,y1-1), b)) //move up left
            legalMoves.add(new Move(x1,y1,x1+1,y1-1));

        if(x1-1 >= 0 && y1+1 < 8 &&  this.legalMove(new Move(x1,y1,x1-1,y1+1), b)) //mover right back
            legalMoves.add(new Move(x1,y1,x1-1,y1+1));

        if(y1 + 2 < 8 && this.legalMove(new Move(x1,y1,x1,y1+2), b)) //castle right
            legalMoves.add(new Move(x1,y1,x1,y1+2));

        if(y1 - 2 >= 0 && this.legalMove(new Move(x1,y1,x1,y1-2), b)) //castle left
            legalMoves.add(new Move(x1,y1,x1,y1-2));

        /*for (int a = 0; a < legalMoves.size(); a++) {
            System.out.println(legalMoves.get(a).getX1() + " " + legalMoves.get(a).getY1() + " " + legalMoves.get(a).getX2() + " " + legalMoves.get(a).getY2() + " " + this.getName() );
        }*/



    }

    /**
     * toString - return proper initials of King and color
     * @return - King + color initials
     */
    public String toString(){
        return Character.toLowerCase(this.getColor().charAt(0)) + "K";
    }


}
