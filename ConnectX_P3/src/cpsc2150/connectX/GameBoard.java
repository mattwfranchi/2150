/* Matt Franchi | CPSC 2150 | Spring 2020
 * Project 2 : Implementing ConnectX
 * File Description: GameBoard implementation code
 */


package cpsc2150.connectX;

public class GameBoard extends AbsGameBoard implements IGameBoard {
    /**
     * @invariant (board must be 6x7, having 42 positions)
     * @invariant players need 4 tokens in succession to win
     * @invariant numRows > 0
     * @invariant numCols > 0
     * @invariant numToWin > 0
     * @invariant p1 != p2
     * @correspondence (number of rows) = numRows
     * @correspondence (number of columns) = numCols
     * @correspondence (number of tokens needed to win) = numToWin
     * @correspondence (token symbol for player 1) = p1
     * @correspondence (token symbol for player 2) = p2
     */

    public int numRows = 6;
    public int numCols = 7;
    public int numToWin = 4;

    public static final char p1 = 'X';
    public static final char p2 = 'O';

    public static final int minPlayers = 2;
    public static final int maxPlayers = 10;

    public static final int minNumRows = 3;
    public static final int minNumCols = 3;
    public static final int minNumToWin = 3;

    public static final int maxNumRows = 100;
    public static final int maxNumCols = 100;
    public static final int maxNumToWin = 25;

    // board: representation of game board, as a 2-d character array with size numRows x numCols
    private char[][] board;
    // lastRow: keeps track of row where last token was inserted, initialized to invalid value -1 for flagging
    private int lastRow = -1;
    // lastToken: keeps track of last token that was inserted, initialized to blank space for flagging
    private char lastToken = ' ';

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


    public boolean checkIfFree(int c) {
        // numEmpty: keeps track of number of empty entries within column
        int numEmpty = 0;
        for (int r = 0; r < numRows; r++) {
            if (board[r][c] == ' ') {
                numEmpty++;
            }
        }
        return numEmpty != 0;
    }


    public boolean checkForWin(int c){
        // make new BoardPosition at position where last token was inserted
        BoardPosition pos = new BoardPosition(lastRow,c);
        // return true if ANY wins are registered
        return checkHorizWin(pos,lastToken) || checkVertWin(pos, lastToken)
                || checkDiagWin(pos, lastToken);

    }


    public void placeToken(char  p, int c) {
        // get first row with a blank entry, put the token p in that spot;
        for (int r = 0; r < numRows; r++) {
            if (board[r][c] == ' ') {
                board[r][c] = p;
                // update lastRow and lastToken
                lastRow = r;
                lastToken = p;
                return;
            }
        }
    }




    public boolean checkHorizWin(BoardPosition pos, char p) {
        // initialize to blank
        String rowTokens = " ", Win = " ";

        int row = pos.getRow();

        // create XWin, a string of numToWin player tokens
        for (int n = 0; n < numToWin; n++) {
            Win += p;
        }

        // make string from row of board
        for (int c = 0; c < numCols; c++) {
            rowTokens+= board[row][c];
        }

        return rowTokens.contains(Win);
    }


    public boolean checkVertWin(BoardPosition pos, char p) {
        String columnTokens = " ", Win = " ";

        int column = pos.getColumn();

        // create XWin, a string of numToWin player tokens
        for (int n = 0; n < numToWin; n++) {
            Win += p;
        }

        // make string from column of board
        for (int r = 0; r < numRows; r++) {
            columnTokens+= board[r][column];
        }

        return columnTokens.contains(Win);
    }


    public boolean checkDiagWin(BoardPosition pos, char p) {
        // initialize to blank
        String posTokens = " ", negTokens = " ", Win = " ";

        // make copies of pos' row and column, store in positive (pos) and negative (neg) : named for slope
        int posRow = pos.getRow(), negRow = pos.getRow();
        int posCol = pos.getColumn(), negCol = pos.getColumn();


        // create XWin, a string of numToWin player tokens
        for (int n = 0; n < numToWin; n++) {
            Win += p;
        }

        // move down-left diagonally until we either hit the bottom row or the leftmost column
        while(posRow > 0 && posCol > 0){ posRow --; posCol --; }
        // move down-right diagonally until we either hit the bottom row or the rightmost column
        while(negRow > 0  && negCol < numCols-1){ negRow --; negCol++; }

        // read the up-right diagonal sequence from the board, store in posTokens
        for(int r = posRow, c = posCol; r < numRows && c < numCols; r++, c++){
            posTokens+= board[r][c];
        }

        // read the down-right diagonal sequence from the board, store in negTokens
        for(int r = negRow, c = negCol; r < numRows && c > 0; r++, c--){
            negTokens += board[r][c];
        }

        // return true if ANY diagonal win occurred
        return posTokens.contains(Win) || negTokens.contains(Win);
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

    // utilize whatsAtPos
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        return whatsAtPos(pos) == player;
    }

    public boolean checkTie() {
        int filledCount = 0;
        for (int c = 0; c < numCols; c++){
            // increment filledCount if column c was full
            filledCount += checkIfFree(c) ? 0 : 1;
        }
        // true if all columns are full, false else
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