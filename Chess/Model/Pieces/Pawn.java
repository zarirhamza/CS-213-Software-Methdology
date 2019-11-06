package Model.Pieces;

import Model.Board;
import Model.Piece;
import Model.Move;

import java.util.ArrayList;

/**
 * Pawn.java - extends Piece class with moves allowed by the pawn piece
 * @author Zarir Hamza and Fares Easa
 */


public class Pawn extends Piece {
    /**
     * -1 if black or 1 if white to determine forward direction of pawn
     */
    private int direction;

    /**
     * boolean that determines if the pawn moved two spots in the previous turn
     */

    public boolean moveTwo;



    /**
     * Constructor of pawn piece with color and count
     * @param color black or white
     * @param count -1 if new piece or turn number if not
     */
    public Pawn(String color, int count) {
        super(color, "Pawn", count);
        if(this.getColor().equals("black"))
            direction = -1;
        else
            direction = 1;
        legalMoves = new ArrayList<Move>();
    }

    /**
     * Checks if the move inputted by the user is valid for the pawn
     * @param m - User inputted move string with beginning and ending location
     * @param b - Board b with the locations of all the pieces on the board
     * @return boolean to determine if legal move or not
     * */
    @Override
    public boolean legalMove(Move m, Board b) {
        if(m.getY1() == m.getY2() && b.boardArray[m.getX2()][m.getY2()] == null ){ //same column to empty space
            if(((m.getX1() - m.getX2()) * this.direction) == 2 && this.getCount() == -1 && (b.boardArray[m.getX1() + (-1*direction)][m.getY1()] == null)){ //move forward 2
                this.moveTwo = true;
                //this.updateMoveList(m.getX2(), m.getY2(), b);
                return true;
            }
            else if(((m.getX1() - m.getX2()) * this.direction) == 1){ // move forward 1
                this.moveTwo = false;
                //this.updateMoveList(m.getX2(), m.getY2(), b);
                return true;
            }
        }

        else if((((m.getX1() - m.getX2()) * this.direction) == 1) && (Math.abs((m.getY1() - m.getY2())) == 1)){ //check if moving diagonally one
            if(b.boardArray[m.getX2()][m.getY2()] != null && !(b.boardArray[m.getX2()][m.getY2()].getColor().equals(this.getColor()))) { //if taking of opposite color
                this.moveTwo = false;
                //this.updateMoveList(m.getX2(), m.getY2(), b);
                return true;
            }
            else if(m.getY1() < 7 && (b.boardArray[m.getX1()][(m.getY1() + 1)] instanceof Pawn) && (((Pawn)(b.boardArray[m.getX1()][(m.getY1() + 1)])).moveTwo) && !(b.boardArray[m.getX1()][(m.getY1() + 1)].getColor().equals(this.getColor())) && (b.boardArray[m.getX1()][(m.getY1() + 1)].getCount() == b.gameCounter - 1)){ //enpassant right
                this.moveTwo = false;
                b.boardArray[m.getX1()][(m.getY1() + 1)] = null;
                //this.updateMoveList(m.getX2(), m.getY2(), b);
                return true;
            }
            else if(m.getY1() > 0 && (b.boardArray[m.getX1()][(m.getY1() - 1)] instanceof Pawn) && (((Pawn)(b.boardArray[m.getX1()][(m.getY1() - 1)])).moveTwo) && !(b.boardArray[m.getX1()][(m.getY1() - 1 )].getColor().equals(this.getColor())) && (b.boardArray[m.getX1()][(m.getY1() - 1 )].getCount() == b.gameCounter - 1)){ //enpassant right
                this.moveTwo = false;
                b.boardArray[m.getX1()][(m.getY1() - 1)] = null;
                //this.updateMoveList(m.getX2(), m.getY2(), b);
                return true;
            }
        }


        //if(m.getY2() - 1 > 0 && b.boardArray[m.getX1()][(m.getY1() - 1)] != null)
        //    System.out.println(m.getX1() + " " + m.getY1() + " " + m.getX2() + " " + m.getY2() + " " + (((Pawn)(b.boardArray[m.getX1()][(m.getY1() - 1)])).moveTwo)  + " " + b.boardArray[m.getX1()][(m.getY1() - 1)].getName() + " " + !(b.boardArray[m.getX1()][(m.getY1() - 1 )].getColor().equals(this.getColor())));

        return false;
    }

    /**
     * Checks if the move inputted by the user is valid for the pawn without changing move2 variable
     * @param m - User inputted move string with beginning and ending location
     * @param b - Board b with the locations of all the pieces on the board
     * @param z - dummy boolean to confirm method use
     * @return boolean to determine if legal move or not
     * */
    public boolean legalMove(Move m, Board b, boolean z) {
        if(m.getY1() == m.getY2() && b.boardArray[m.getX2()][m.getY2()] == null ){ //same column to empty space
            if(((m.getX1() - m.getX2()) * this.direction) == 2 && this.getCount() == -1 && (b.boardArray[m.getX1() + (-1*direction)][m.getY1()] == null)){ //move forward 2
                //this.updateMoveList(m.getX2(), m.getY2(), b);
                return true;
            }
            else if(((m.getX1() - m.getX2()) * this.direction) == 1){ // move forward 1
                //this.updateMoveList(m.getX2(), m.getY2(), b);
                return true;
            }
        }

        else if((((m.getX1() - m.getX2()) * this.direction) == 1) && (Math.abs((m.getY1() - m.getY2())) == 1)){ //check if moving diagonally one
            if(b.boardArray[m.getX2()][m.getY2()] != null && !(b.boardArray[m.getX2()][m.getY2()].getColor().equals(this.getColor()))) { //if taking of opposite color
                //this.updateMoveList(m.getX2(), m.getY2(), b);
                return true;
            }
            else if(m.getY1() < 7 && (b.boardArray[m.getX1()][(m.getY1() + 1)] instanceof Pawn) && (((Pawn)(b.boardArray[m.getX1()][(m.getY1() + 1)])).moveTwo) && !(b.boardArray[m.getX1()][(m.getY1() + 1)].getColor().equals(this.getColor())) && (b.boardArray[m.getX1()][(m.getY1() + 1)].getCount() == b.gameCounter - 1)){ //enpassant right
                //this.updateMoveList(m.getX2(), m.getY2(), b);
                return true;
            }
            else if(m.getY1() > 1 && (b.boardArray[m.getX1()][(m.getY1() - 1)] instanceof Pawn) && (((Pawn)(b.boardArray[m.getX1()][(m.getY1() - 1)])).moveTwo) && !(b.boardArray[m.getX1()][(m.getY1() - 1 )].getColor().equals(this.getColor())) && (b.boardArray[m.getX1()][(m.getY1() - 1 )].getCount() == b.gameCounter - 1)){ //enpassant right
                //this.updateMoveList(m.getX2(), m.getY2(), b);
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
    public void updateMoveList(int x1, int y1, Board b){

        legalMoves.clear();
        int forward = direction * -1;

        //b.printBoard();
        if( x1+forward < 8 && x1+forward >= 0){ //check if can move forward
            //System.out.println(x1 + " " + y1 + " " + forward + " " + getColor());
            if(this.legalMove(new Move(x1,y1,x1+forward,y1), b, true)) // if one forward is legal
                legalMoves.add(new Move(x1,y1, x1+forward,y1));
            if(y1+1 < 8 && this.legalMove(new Move(x1,y1,x1+forward,y1+1), b, true)) // if one diagonal is legal - enpassant or not
                legalMoves.add(new Move(x1,y1, x1+forward,y1+1));
            if(y1-1 >= 0 && this.legalMove(new Move(x1,y1,x1+forward,y1-1), b, true)) // if one other diagonal is legal - enpassant or not
                legalMoves.add(new Move(x1,y1, x1+forward,y1-1));
        }
        if( x1+forward+forward < 8 && x1+forward+forward >= 0 && this.legalMove(new Move(x1,y1,x1+forward+forward,y1), b, true)){ //check if can move forward two
            //System.out.println("legal");
            legalMoves.add(new Move(x1,y1, x1+forward+forward,y1));
        }

       /* for (int a = 0; a < legalMoves.size(); a++) {
            System.out.println(legalMoves.get(a).getX1() + " " + legalMoves.get(a).getY1() + " " + legalMoves.get(a).getX2() + " " + legalMoves.get(a).getY2() + " " + this.getName());
        }*/

    }


    /**
     * function to determine if pawn needs to be promoted
     * @param x1 - position of pawn
     * @param color - color of pawn
     * @return - boolean to be promoted or not
     */
    public boolean update(int x1, String color){
        if(color.equals("white") && x1 == 0)
            return true;
        else if(color.equals("black") && x1 == 7)
            return true;
        return false;
    }

    /**
     * Promotes pawn depending on string input
     * @param promoteString - string corresponding to chess piece
     * @return - Piece that corresponds to string input
     */
    public Piece promotePawn(String promoteString){
        if(promoteString.equals("N"))
            return new Knight(this.getColor(), this.getCount());
        else if(promoteString.equals("B"))
            return new Bishop(this.getColor(), this.getCount());
        else if(promoteString.equals("R"))
            return new Rook(this.getColor(), this.getCount());
        else if(promoteString.equals("p"))
            return new Pawn(this.getColor(), this.getCount());
        else
            return new Queen(this.getColor(), this.getCount());
    }

    /**
     * toString function to print out proper name of the piece
     * @return String of the piece with color and initial
     */
    public String toString(){
        return Character.toLowerCase(this.getColor().charAt(0)) + "p";
    }
}
