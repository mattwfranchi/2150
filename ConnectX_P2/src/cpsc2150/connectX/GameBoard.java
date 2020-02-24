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
    private int lastRow = -1;
    private char lastToken = ' ';


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
        System.out.println(numEmpty);
        return numEmpty != 0;
    }


    public boolean checkForWin(int c){
        BoardPosition pos = new BoardPosition(lastRow,c);
        return checkHorizWin(pos,lastToken) || checkVertWin(pos, lastToken);

    }


    public void placeToken(char p, int c) {
        for (int r = 0; r < numRows; r++) {
            if (board[r][c] == ' ') {
                board[r][c] = p;
                lastRow = r;
                lastToken = p;
                return;
            }
        }
    }




    public boolean checkHorizWin(BoardPosition pos, char p) {
        String columnTokens = "";
        String XWin = "";
        String OWin = "";

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
        String columnTokens = "";
        String XWin = "";
        String OWin = "";

        int column = pos.getColumn();

        for (int n = 0; n < numToWin; n++) {
            XWin += "X";
            OWin += "O";
        }

        for (int r = 0; r < numRows; r++) {
            columnTokens += board[r][column];
        }

        return (columnTokens.contains(XWin) || columnTokens.contains(OWin));
    }


    public boolean checkDiagWin(BoardPosition pos, char p) {
        String columnTokens = "";
        String XWin = "";
        String OWin = "";

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
        String output = "";

        for (int r = numRows; r >= 0; r--) {
            output += "|";
            for (int c = 0; c < numCols; c++) {

                if (r == numRows) {
                    output +=  c + "|";
                }
                else{
                    BoardPosition iterator = new BoardPosition(r,c);
                    output += whatsAtPos(iterator) + "|";
                }
            }
            output += '\n';
        }
        return output;
    }


    public boolean checkTie() {
        int filledCount = 0;
        for (int c = 0; c < numCols; c++){
            filledCount += checkIfFree(c) ? 0 : 1;
        }
        System.out.println(filledCount);
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