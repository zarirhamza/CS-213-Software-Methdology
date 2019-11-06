package Model;

import java.util.ArrayList;

/**
 * Piece.java - abstract class with all necessary common fields of chess pieces
 * @author Zarir Hamza and Fares Easa
 */
public abstract class Piece {
    /**
     * color of the piece
     */
    private String color;
    /**
     * name of the piece
     */
    private String name;
    /**
     * count of the last time piece was interacted with
     */
    private int count;


    /**
     * Arraylist of legal moves by current piece
     */
    public ArrayList<Move> legalMoves;

    /**
     * Constructor of Piece
     * @param color - either black or white
     * @param name - name of the piece
     * @param count - -1 if never moved or update with last gameCounter of moved
     */
    public Piece(String color, String name, int count){
        this.setColor(color);
        this.setName(name);
        this.setCount(count);
    }

    /**
     * legalMove function to be implemented in specific piece classes
     * @param m - move based on user input
     * @param B - board with all the pieces of the game
     * @return - true/false if legal move or not
     */
    public abstract boolean legalMove(Move m,  Board B);

    /**
     * Updates arraylist of possible moves per piece
     * @param x1 - starting x point
     * @param y1 - starting y point
     * @param b - board of pieces
     */
    public abstract void updateMoveList(int x1, int y1, Board b);

    /**
     * get color of piece
     * @return String of the color of piece
     */
    public String getColor() {
        return color;
    }

    /**
     * Set color of piece
     * @param color - either black or white
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Returns name of the piece
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Set new name of the piece
     * @param name - name of piece
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get count of last time piece is succesfully moved
     * @return integer of the last time piece was moved
     */
    public int getCount() {
        return count;
    }

    /**
     * set time of interaction of the piece
     * @param count - integer of the game turn
     */
    public void setCount(int count) {
        this.count = count;
    }
}

