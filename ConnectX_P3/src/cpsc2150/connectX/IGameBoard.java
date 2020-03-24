/* Matt Franchi | CPSC 2150 | Spring 2020
 * Project 3 : ConnectX
 * File Description: GameBoard interface code
 */

package cpsc2150.connectX;

/**
 GameBoard represents a 2 dimensional board with size (number of rows) * (number of columns)
 Indexing starts at 0

 Initialization ensures:
 GameBoard is initialized to an empty state and board is of size (number of rows) x (number of columns)

 Defines:       (number of rows): Z
                (number of columns): Z
                (number of tokens needed to win) : Z
                (token symbols for players) : char[]
                board: Z x Z

 Constraints:   (number of rows) >= 3 AND (number of rows) <= 100
                (number of columns) >= 3 AND (number of columns) <= 100
                (number of tokens needed to win) >= 3 AND (number of tokens needed to win) <= 25
                board has (number of rows) x (number of columns) entries
 */



public interface IGameBoard {
    public static final int minNumRows = 3;
    public static final int minNumCols = 3;
    public static final int minNumToWin = 3;

    public static final int maxNumRows = 100;
    public static final int maxNumCols = 100;
    public static final int maxNumToWin = 25;



    // PART I FUNCTIONS

    /**
     * @pre 0 <= c < (number of columns)
     * @param c column to check
     * @return false if column is full, true if column has >= 1 empty space
     * @post board is unchanged
     *
     */
    default boolean checkIfFree(int c) {
        // numEmpty: keeps track of number of empty entries within column
        int numEmpty = 0;
        for (int r = 0; r < getNumRows(); r++) {
            if (whatsAtPos(new BoardPosition(r,c)) == ' ') {
                numEmpty++;
            }
        }
        return numEmpty != 0;
    }


    /**
     * @pre 0 <= c < (number of columns)
     * @param c column to simulate addition of token, then check
     * @return true if token addition resulted in win, false else
     * @post board is unchanged
     */
    default boolean checkForWin(int c){
        // make new BoardPosition at position where last token was inserted
        int firstEmptyRow = 0;
        BoardPosition pos = new BoardPosition(firstEmptyRow,c);

        while(whatsAtPos(pos) != ' ')
        {
            pos = new BoardPosition(++firstEmptyRow,c);
        }

        int desiredRow = firstEmptyRow - 1;
        char desiredToken = whatsAtPos(new BoardPosition(desiredRow,c));

        // return true if ANY wins are registered
        return checkHorizWin(pos,desiredToken) || checkVertWin(pos, desiredToken)
                || checkDiagWin(pos, desiredToken);

    }



    /**
     * @pre p is a valid player token AND 0 <= c <= (number of columns)
     * @param p  token to insert
     * @param c column to insert the token
     * @post token p is inserted in column c of board
     */
    void placeToken(char p, int c);


    /**
     * @pre 0 <= pos.row <= (number of rows) AND 0 <= pos.col <= (number of columns)
     *      AND p is a valid player token
     * @param pos BoardPosition representing an entry on board
     * @param p player token
     * @post p is inserted at pos of board
     * @return true if horizontal win occurred after insert, false else
     */
    default boolean checkHorizWin(BoardPosition pos, char p)
    {
        // initialize to blank
        String rowTokens = "", Win = "";

        int row = pos.getRow();

        // create XWin, a string of numToWin player tokens
        for (int n = 0; n < getNumToWin(); n++) {
            Win += p;
        }

        // make string from row of board
        for (int c = 0; c < getNumColumns(); c++) {
            rowTokens += isPlayerAtPos(new BoardPosition(row,c),p)? p : ' ';
        }

        return rowTokens.contains(Win);
    }


    /**
     * @pre 0 <= pos.row <= (number of rows) AND 0 <= pos.col <= (number of columns)
     *      AND p is a valid player token
     * @param pos BoardPosition representing an entry on board
     * @param p type of player token
     * @post p is inserted at pos of board
     * @return true if vertical win occurred after insert, false else
     */
    default boolean checkVertWin(BoardPosition pos, char p)
    {
        String columnTokens = "", Win = "";

        int column = pos.getColumn();

        // create XWin, a string of numToWin player tokens
        for (int n = 0; n < getNumToWin(); n++) {
            Win += p;
        }

        // make string from column of board
        for (int r = 0; r < getNumRows(); r++) {
            columnTokens+= isPlayerAtPos(new BoardPosition(r,column),p)? p : ' ';
        }

        return columnTokens.contains(Win);
    }


    /**
     * @pre 0 <= pos.row <= (number of rows) AND 0 <= pos.col <= (number of columns)
     *      AND p is a valid player token
     * @param pos BoardPosition representing an entry on board
     * @param p type of player token
     * @post p is whatinserted at pos of board
     * @return true if diagonal win occurred after insert, false else
     */
    default boolean checkDiagWin(BoardPosition pos, char p)
    {
        // initialize to blank
        String posTokens = "", negTokens = "", Win = "";

        // make copies of pos' row and column, store in positive (pos) and negative (neg) : named for slope
        int posRow = pos.getRow(), negRow = pos.getRow();
        int posCol = pos.getColumn(), negCol = pos.getColumn();


        // create XWin, a string of numToWin player tokens
        for (int n = 0; n < getNumToWin(); n++) {
            Win += p;
        }

        // move down-left diagonally until we either hit the bottom row or the leftmost column
        while(posRow > 0 && posCol > 0){ posRow --; posCol --; }
        // move down-right diagonally until we either hit the bottom row or the rightmost column
        while(negRow > 0  && negCol < getNumColumns()-1){ negRow --; negCol++; }

        // read the up-right diagonal sequence from the board, store in posTokens
        for(int r = posRow, c = posCol; r < getNumRows() && c < getNumColumns(); r++, c++){
            posTokens += isPlayerAtPos(new BoardPosition(r,c),p)? p : ' ';
        }

        // read the down-right diagonal sequence from the board, store in negTokens
        for(int r = negRow, c = negCol; r < getNumRows() && c > 0; r++, c--){
            negTokens += isPlayerAtPos(new BoardPosition(r,c),p)? p : ' ';
        }

        // return true if ANY diagonal win occurred
        return posTokens.contains(Win) || negTokens.contains(Win);
    }


    /**
     * @pre 0 <= pos.row <= (number of rows) AND 0 <= pos.col <= (number of columns)
     * @param pos BoardPosition to index
     * @post board is unchanged AND whatsAtPos() = contents of inputted position on board
     * @return contents of pos of board
     */
    char whatsAtPos(BoardPosition pos);


    /**
     * @pre 0 <= pos.row <= (number of rows) AND 0 <= pos.col <= (number of columns)
     * @param pos BoardPosition to index
     * @param player token to check for
     * @post board is unchanged
     * @return true if contents of board at pos == player, false else
     */
    default boolean isPlayerAtPos(BoardPosition pos, char player)
    {
        return whatsAtPos(pos) == player;
    }


    /**
     *
     * @return readable, graphical depiction of the board
     */
    String toString();


    /**
     *
     * @return true if all entries of board are filled with tokens, false else
     */
    default boolean checkTie() {
        int filledCount = 0;
        for (int c = 0; c < getNumColumns(); c++){
            // increment filledCount if column c was full
            filledCount += checkIfFree(c) ? 0 : 1;
        }
        // true if all columns are full, false else
        return filledCount == getNumColumns();
    }

    // PART II FUNCTIONS

    /**
     * @post getNumRows() = numRows
     * @return (number of rows)
     */
    int getNumRows();


    /**
     * @post getNumColumns() = numCols
     * @return (number of columns)
     */
    int getNumColumns();


    /**
     * @post getNumToWin() = numToWin
     * @return number of consecutive tokens needed to win the game
     */
    int getNumToWin();



}
