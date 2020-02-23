/* Matt Franchi | CPSC 2150 | Spring 2020
 * Project 2 : Implementing ConnectX
 * File Description: GameBoard implementation code
 */


package cpsc2150.connectX;



public  class GameBoard implements IGameBoard{
    /**
     * @invariant (board must be 6x7, having 42 positions)
     */


    private char[][] board = new char[numRows][numCols];

    public GameBoard() {

    }


    public boolean checkIfFree(int c) {
        return false;
    }


    public boolean checkForWin(int c) {


    }


    public void placeToken(char p, int c) {
    }



    public boolean checkHorizWin(BoardPosition pos, char p) {
        return false;
    }



    public boolean checkVertWin(BoardPosition pos, char p) {
        return false;
    }


    public boolean checkDiagWin(BoardPosition pos, char p) {
        return false;
    }



    public char whatsAtPos(BoardPosition pos) {
        return 0;
    }


    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        return false;
    }



    public String toString() {
        return null;
    }



    public boolean checkTie() {
        return false;
    }


}