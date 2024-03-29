/* Matt Franchi | CPSC 2150 | Spring 2020
 * Project 2 : Implementing ConnectX
 * File Description: GameBoard interface code
 */

package cpsc2150.connectX;

/**
 GameBoard represents a 2 dimensional board with size (number of rows) * (number of columns)
 Indexing starts at xxx

 Initialization ensures:
 GameBoard only contains blank characters and board is of size (number of rows) x (number of columns)

 Defines:       (number of rows): Z
                (number of columns): Z
                (number of tokens needed to win) : Z
                (token symbol for player 1) : char
                (token symbol for player 2) : char
                board: 2-d array of chars (Z x Z)

 Constraints:   (number of rows) > 0
                (number of columns) > 0
                (number of tokens needed to win) > 0
                board has (number of rows) x (number of columns) entries
 */



public interface IGameBoard {



    // PART I FUNCTIONS

    /**
     * @pre 0 <= c < (number of columns)
     * @param c column to check
     * @return false if column is full, true if column has >= 1 empty space
     * @post board is unchanged
     *
     */
    boolean checkIfFree(int c);


    /**
     * @pre 0 <= c < (number of columns)
     * @param c column to simulate addition of token, then check
     * @return true if token addition resulted in win, false else
     * @post board is unchanged
     */
    boolean checkForWin(int c);


    /**
     * @pre p is a valid player token AND 0 <= c <= 6
     * @param p type of token to insert
     * @param c column to insert the token
     * @post token p is inserted in column c of board
     */
    void placeToken(char p, int c);


    /**
     * @pre 0 <= pos.row <= (number of rows) AND 0 <= pos.col <= (number of columns)
     *      AND p is a valid player token
     * @param pos BoardPosition representing an entry on board
     * @param p type of player token
     * @post p is inserted at pos of board
     * @return true if horizontal win occurred after insert, false else
     */
    boolean checkHorizWin(BoardPosition pos, char p);


    /**
     * @pre 0 <= pos.row <= (number of rows) AND 0 <= pos.col <= (number of columns)
     *      AND p is a valid player token
     * @param pos BoardPosition representing an entry on board
     * @param p type of player token
     * @post p is inserted at pos of board
     * @return true if vertical win occurred after insert, false else
     */
    boolean checkVertWin(BoardPosition pos, char p);


    /**
     * @pre 0 <= pos.row <= (number of rows) AND 0 <= pos.col <= (number of columns)
     *      AND p is a valid player token
     * @param pos BoardPosition representing an entry on board
     * @param p type of player token
     * @post p is inserted at pos of board
     * @return true if diagonal win occurred after insert, false else
     */
    boolean checkDiagWin(BoardPosition pos, char p);


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
    boolean isPlayerAtPos(BoardPosition pos, char player);


    /**
     *
     * @return readable, graphical depiction of the board
     */
    String toString();


    /**
     *
     * @return true if all entries of board are filled with tokens, false else
     */
    boolean checkTie();

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
