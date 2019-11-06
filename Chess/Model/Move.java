package Model;

/**
 * Move.java - Class that holds starting and ending indexes for board array by converting user input
 * @author Zarir Hamza and Fares Easa
 */

public class Move {
    /**
     * x coordinate for the origin
     */
    private int x1;
    /**
     * y coordinate for the origin
     */
    private int y1;
    /**
     * x coordinate for the destination
     */
    private int x2; /**
     * y coordinate for the destination
     */
    private int y2;

    /**
     * Move constructor that takes in beginning coordinate and ending coordinate
     * @param coord1 - first fileRank string - start
     * @param  coord2 - second fileRank string - end
     */
    public Move(String coord1, String coord2){
        this.setX1(8 - (int)(coord1.charAt(1) - 48));
        this.setY1((int)(coord1.charAt(0) - 97));
        this.setX2(8 - (int)(coord2.charAt(1) - 48));
        this.setY2((int)(coord2.charAt(0) - 97));
    }

    /**
     * Move constructor that takes in coordinates rather than strings
     * @param x1 - x position of origin
     * @param y1 - y position of origin
     * @param x2 - x position of destination
     * @param y2 - y position of destination
     */
    public Move(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Getter for first coordinate, X value
     * @return first coordinate, X value
     */
    public int getX1() {
        return x1;
    }

    /**
     * Setter for first coordinate, x value
     * @param x1 - new x value for first coordinate
     */

    public void setX1(int x1) {
        this.x1 = x1;
    }

    /**
     * Getter for first coordinate, Y value
     * @return first coordinate, Y value
     */
    public int getY1() {
        return y1;
    }

    /**
     * Setter for first coordinate, y value
     * @param y1 - new y value for first coordinate
     */
    public void setY1(int y1) {
        this.y1 = y1;
    }

    /**
     * Getter for second coordinate, X value
     * @return second coordinate, X value
     */
    public int getX2() {
        return x2;
    }

    /**
     * Setter for second coordinate, x value
     * @param x2 - new x value for second coordinate
     */
    public void setX2(int x2) {
        this.x2 = x2;
    }

    /**
     * Getter for second coordinate, Y value
     * @return second coordinate, Y value
     */
    public int getY2() {
        return y2;
    }

    /**
     * Setter for second coordinate, y value
     * @param y2 - new y value for second coordinate
     */
    public void setY2(int y2) {
        this.y2 = y2;
    }
}
