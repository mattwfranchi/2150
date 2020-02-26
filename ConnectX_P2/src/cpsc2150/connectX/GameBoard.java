/* Matt Franchi | CPSC 2150 | Spring 2020
 * Project 2 : Implementing ConnectX
 * File Description: GameBoard implementation code
 */


package cpsc2150.connectX;




public  class GameBoard implements IGameBoard {
    /**
     * @invariant (board must be 6x7, having 42 positions)
     */

    // board: representation of game board, as a 2-d character array with size numRows x numCols
    private char[][] board = new char[numRows][numCols];
    // lastRow: keeps track of row where last token was inserted, initialized to invalid value -1 for flagging
    private int lastRow = -1;
    // lastToken: keeps track of last token that was inserted, initialized to blank space for flagging
    private char lastToken = ' ';


    public GameBoard() {
        // nested for loops to set each board entry to blank
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
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
        BoardPosition pos = new BoardPosition(lastRow,c);
        return checkHorizWin(pos,lastToken) || checkVertWin(pos, lastToken)
                || checkDiagWin(pos, lastToken);

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
        String columnTokens = " ";
        String XWin = " ";
        String OWin = " ";

        int row = pos.getRow();

        for (int n = 0; n < numToWin; n++) {
            XWin+= "X";
            OWin+= "O";
        }

        for (int c = 0; c < numCols; c++) {
            columnTokens+= board[row][c];
        }

        return (columnTokens.contains(XWin) || columnTokens.contains(OWin));
    }


    public boolean checkVertWin(BoardPosition pos, char p) {
        String columnTokens = " ";
        String XWin = " ";
        String OWin = " ";

        int column = pos.getColumn();

        for (int n = 0; n < numToWin; n++) {
            XWin+= "X";
            OWin+= "O";
        }

        for (int r = 0; r < numRows; r++) {
            columnTokens+= board[r][column];
        }

        return (columnTokens.contains(XWin) || columnTokens.contains(OWin));
    }


    public boolean checkDiagWin(BoardPosition pos, char p) {

        String posTokens = " ";
        String negTokens = " ";
        String XWin = " ";
        String OWin = " ";

        int posRow = pos.getRow(), negRow = pos.getRow();
        int posCol = pos.getColumn(), negCol = pos.getColumn();

        for (int n = 0; n < numToWin; n++) {
            XWin+= "X" ;
            OWin+= "O" ;
        }

        while(posRow > 0 && posCol > 0){ posRow --; posCol --; }
        while(negRow > 0  && negCol < numCols-1){ negRow --; negCol++; }


        for(int r = posRow, c = posCol; r < numRows && c < numCols; r++, c++){
            posTokens+= board[r][c];
        }

        for(int r = negRow, c = negCol; r < numRows && c > 0; r++, c--){
            negTokens += board[r][c];
        }


        return (posTokens.contains(XWin) || posTokens.contains(OWin)
                || negTokens.contains(XWin) || negTokens.contains(OWin));
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