/* Matt Franchi | CPSC 2150 | Spring 2020
 * Project 2 : Implementing ConnectX
 * File Description: BoardPosition implementation code
 */

package cpsc2150.connectX;

public class BoardPosition {
    private int row;
    private int col;

    // Default Constructor

    /**
     * @post BoardPosition() = (0,0)
     */
    BoardPosition() {
        row = 0;
        col = 0;
    }


    // Parameterized Constructor

    /**
     * @param r row of proposed BoardPosition
     * @param c column of proposed BoardPosition
     * @post BoardPosition() = (r,c)
     */
    BoardPosition(int r, int c) {
        row = r;
        col = c;
    }


    /**
     * @return row stored in instance of BoardPosition
     * @pre BoardPosition instance is initialized, in usable condition
     * @post getRow() = row
     */
    public int getRow() {
        return row;
    }


    /**
     * @return column stored in instance of BoardPosition
     * @pre BoardPosition instance is initialized, in usable condition
     * @post getColumn = col
     */
    public int getColumn() {
        return col;
    }


    /**
     * @return string in format (r, c)
     * @pre BoardPosition instance is initialized, in usable condition
     * @post toString() = formatted string representation of position
     */
    @Override
    public String toString() {
        return "(" + Character.forDigit(row, 10) + ", " + Character.forDigit(col, 10) + ")";
    }


    /**
     * @param other BoardPosition to compare this to
     * @return true if two BoardPositions are equal, false else
     * @pre other is a valid, initialized BoardPosition
     * @post equals() = true or false
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) { return true; }

        if(other == null){ return false; }

        if (!(other instanceof BoardPosition)) { return false; }

        BoardPosition o = (BoardPosition) other;
        System.out.printf("%d = %d? && %d = %d? \n", row, o.row, col, o.col);

        return row == o.row && col == o.col;
    }

}



