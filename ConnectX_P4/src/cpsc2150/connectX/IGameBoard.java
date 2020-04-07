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
        // calculate the first empty row through iterative process
        while(whatsAtPos(pos) != ' ' && firstEmptyRow < getNumRows())
        {
            pos = new BoardPosition(++firstEmptyRow,c);
        }
        // dessired row is one below the first empty row
        int desiredRow = firstEmptyRow - 1;
        // update position
        pos = new BoardPosition(desiredRow,c);
        // get the token currently at pos' location
        char desiredToken = whatsAtPos(pos);

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
        // get row and column from pos
        int col = pos.getColumn();
        int row = pos.getRow();

        // initialize successiveTokens to -1, because token at pos is counted twice (one for each loop)
        int successiveTokens = -1;

        // for loop to handle left direction; stop processing as soon as invalid token is encounter
        for(int left = col; left >= 0 && isPlayerAtPos(new BoardPosition(row,left),p); left--)
        {
            successiveTokens ++;
        }

        // for loop to handle right direction; same behavior as left loop
        for(int right = col; right < getNumColumns() && isPlayerAtPos(new BoardPosition(row,right),p); right ++)
        {
           successiveTokens ++;
        }

        // return true if valid tokens meet or exceed the number of tokens needed to win
        return successiveTokens >= getNumToWin();
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
        // get row and column from pos
        int col = pos.getColumn();
        int row = pos.getRow();

        int successiveTokens = -1;

        // for loop to handle up direction
        for(int up = row; up < getNumRows() && isPlayerAtPos(new BoardPosition(up,col),p); up ++)
        {
            successiveTokens ++;
        }

        // for loop to handle down direction
        for(int down = row; row >= 0 && isPlayerAtPos(new BoardPosition(down,col),p); down --)
        {
            successiveTokens ++;
        }

        return successiveTokens >= getNumToWin();
    }


    /**
     * @pre 0 <= pos.row <= (number of rows) AND 0 <= pos.col <= (number of columns)
     *      AND p is a valid player token
     * @param pos BoardPosition representing an entry on board
     * @param p type of player token
     * @post p is inserted at pos of board
     * @return true if diagonal win occurred after insert, false else
     */
    default boolean checkDiagWin(BoardPosition pos, char p)
    {
        int col = pos.getColumn();
        int row = pos.getRow();

        // offset by one because first token is counted by all  loops
        int successiveTokens = -1;

        // two-variable for loop to handle up-right direction
        for(int up = row, right = col; up < getNumRows() && right < getNumColumns()
                && isPlayerAtPos(new BoardPosition(up,right),p); up++, right++)
        {
            successiveTokens ++;
        }

        // two-variable for loop to handle down-left direction
        for(int down = row, left = col; down >= 0 && left >= 0
                && isPlayerAtPos(new BoardPosition(down,left),p); down --, left --)
        {
            successiveTokens ++;
        }

        // return if up-right tokens are enough to win
        if(successiveTokens >= getNumToWin())
        {
            return true;
        }

        // reset successive tokens
        successiveTokens = -1;

        // two-variable for loop to handle up-left direction
        for(int up = row, left = col; up < getNumRows() && left >= 0
                && isPlayerAtPos(new BoardPosition(up,left),p); up++, left--)
        {
            successiveTokens ++;
        }

        // two-variable for loop to handle down-right direction
        for(int down = row, right = col; down >= 0 && right < getNumColumns()
                && isPlayerAtPos(new BoardPosition(down,right),p); down --, right ++)
        {
            successiveTokens ++;
        }


        return successiveTokens >= getNumToWin();
    }


    /**
     * @pre 0 <= pos.row < (number of rows) AND 0 <= pos.col < (number of columns)
     * @param pos BoardPosition to index
     * @post board is unchanged AND whatsAtPos() = contents of inputted position on board
     * @return contents of pos of board
     */
    char whatsAtPos(BoardPosition pos);


    /**
     * @pre 0 <= pos.row < (number of rows) AND 0 <= pos.col < (number of columns)
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
