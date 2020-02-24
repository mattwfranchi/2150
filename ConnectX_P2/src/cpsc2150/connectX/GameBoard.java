/* Matt Franchi | CPSC 2150 | Spring 2020
 * Project 2 : Implementing ConnectX
 * File Description: GameBoard implementation code
 */


package cpsc2150.connectX;



public  class GameBoard implements IGameBoard {
    /**
     * @invariant (board must be 6x7, having 42 positions)
     */

    private char[][] board = new char[numRows][numCols];


    public GameBoard() {
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                board[r][c] = ' ';
            }
        }
    }


    public boolean checkIfFree(int c) {
        int numEmpty = 0;
        for (int r = 0; r < numRows; r++) {
            if (board[r][c] == ' ') {
                numEmpty++;
            }
        }
        return numEmpty != 0;
    }


    public boolean checkForWin(int c) {
        return false;
    }


    public void placeToken(char p, int c) {
        if (checkIfFree(c)) {
            for (int r = 0; r < numRows; r++) {
                if (board[r][c] == ' ') {
                    board[r][c] = p;
                    return;
                }
            }
        }
    }


    public boolean checkHorizWin(BoardPosition pos, char p) {
        String columnTokens = null;
        String XWin = null;
        String OWin = null;

        int row = pos.getRow();

        for (int n = 0; n < numToWin; n++) {
            XWin += "X";
            OWin += "O";
        }

        for (int c = 0; c < numCols; c++) {
            columnTokens += board[row][c];
        }

        return (columnTokens.contains(XWin) || columnTokens.contains(OWin));
    }


    public boolean checkVertWin(BoardPosition pos, char p) {
        String columnTokens = null;
        String XWin = null;
        String OWin = null;

        int column = pos.getColumn();

        for (int n = 0; n < numToWin; n++) {
            XWin += "X";
            OWin += "O";
        }

        for (int r = 0; r < numCols; r++) {
            columnTokens += board[r][column];
        }

        return (columnTokens.contains(XWin) || columnTokens.contains(OWin));
    }


    public boolean checkDiagWin(BoardPosition pos, char p) {
        String columnTokens = null;
        String XWin = null;
        String OWin = null;

        int row = pos.getRow();
        int column = pos.getColumn();

        for (int n = 0; n < numToWin; n++) {
            XWin += "X";
            OWin += "O";
        }

        return (columnTokens.contains(XWin) || columnTokens.contains(OWin));
    }


    public char whatsAtPos(BoardPosition pos) {
        if ((pos.getRow() >= 0 && pos.getRow() < numRows) &&
                (pos.getColumn() >= 0 && pos.getColumn() < numCols)) {
            return board[pos.getRow()][pos.getColumn()];

        }
        else return '-';
    }


    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        return whatsAtPos(pos) == player;
    }


    public String toString() {
        return null;
    }


    public boolean checkTie() {
        int filledCount = 0;
        for (int c = 0; c < numCols; c++){
            filledCount += checkIfFree(c) ? 1 : 0;
        }
        return filledCount == numCols;


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