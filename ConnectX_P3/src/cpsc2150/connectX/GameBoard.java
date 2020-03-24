/* Matt Franchi | CPSC 2150 | Spring 2020
 * Project 3 : ConnectX
 * File Description: GameBoard (Fast) implementation code
 */


package cpsc2150.connectX;

public class GameBoard extends AbsGameBoard implements IGameBoard {
    /**
     * @correspondence (number of rows) = numRows
     * @correspondence (number of columns) = numCols
     * @correspondence (number of tokens needed to win) = numToWin
     *
     * @invariant numRows >= minNumRows AND numRows <= maxNumRows
     * @invariant numCols >= minNumCols AND numCols <= maxNumCols
     * @invariant numToWin >= minNumToWin AND numToWin <= maxNumToWin
     *
     * @invariant player needs numToWin tokens in horizontal, vertical, or diagonal succession to win
     * @invariant board must be of size numRows x numCols, with (numRows x NumCols) possible entries
     */


    // board: representation of game board, as a 2-d character array with size numRows x numCols
    private char[][] board;
    // flag values for numRows, numCols, and numToWin : -1 indicates pre-initialization
    private int numRows = -1, numCols = -1, numToWin = -1;

    public GameBoard(int nr, int nc, int ntw) {
        // initialize board
        board = new char[nr][nc];
        numRows = nr;
        numCols = nc;
        numToWin = ntw;
        // nested for loops to set each board entry to blank
        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                board[r][c] = ' ';
            }
        }
    }

    public void placeToken(char  p, int c) {
        // get first row with a blank entry, put the token p in that spot;
        for (int r = 0; r < numRows; r++) {
            if (board[r][c] == ' ') {
                board[r][c] = p;
                return;
            }
        }
    }


    public char whatsAtPos(BoardPosition pos) {
        // get contents of board if preconditions are met
        if ((pos.getRow() >= 0 && pos.getRow() < numRows) &&
                (pos.getColumn() >= 0 && pos.getColumn() < numCols)) {
            return board[pos.getRow()][pos.getColumn()];

        }
        // return flag for invalidity
        else return '-';
    }

    public int getNumRows(){
        return numRows;
    }

    public int getNumColumns(){
        return numCols;
    }

    public int getNumToWin(){
        return numToWin;
    }
}