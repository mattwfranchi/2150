package cpsc2150.connectX;

// Matt Franchi // CPSC 2150 // Spring 2020
// Project Part 1: ConnectX
// Program Description:




//

public class GameBoard{
    /**
     * @invariant (board must be 6x7, having 42 positions)
     */
    private char[][] board = new char[6][7];

    /**
     * @post constructor sets all board spaces to empty char - [' ']
     */
    GameBoard();

    /**
     * @pre 0 <= c <= 6
     * @param c column to check if free
     * @return true if column is free,
     *          false is column is not free
     */
    public boolean checkIfFree(int c);

    /**
     * @pre 0 <= c <= 6
     * @param c column where last token was placed
     * @return true if someone won,
     *          false is game can continue
     */
    public boolean checkForWin(int c);

    /**
     * @pre [p is a valid player symbol] and 0 <= c <= 6
     * @param p player symbol
     * @param c column where token is to be placed
     */
    public void placeToken(char p, int c);

    /**
     * @pre pos is a valid BoardPosition and [p is a valid player symbol]
     * @param pos BoardPosition representing last placed token's location
     * @param p player symbol
     * @return true if someone won horizontally,
     *          false if no horizontal win
     */
    public boolean checkHorizWin(BoardPosition pos, char p);

    /**
     * @pre pos is a valid BoardPosition and [p is a valid player symbol]
     * @param pos BoardPosition representing last placed token's location
     * @param p player symbol
     * @return true if someone won vertically,
     *          false if no vertical win
     */
    public boolean checkVertWin(BoardPosition pos, char p);

    /**
     * @pre pos is a valid BoardPosition and [p is a valid player symbol]
     * @param pos BoardPosition representing last placed token's location
     * @param p player symbol
     * @return true is someone won diagonally,
     *          false if no diagonal win
     */
    public boolean checkDiagWin(BoardPosition pos, char p);

    /**
     * @pre pos is a valid BoardPosition
     * @param pos BoardPosition representing a cell on the game board
     * @return char representing the player symbol, or " " if position is empty
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * @pre pos is a valid BoardPosition
     * @param pos BoardPosition representing a cell on the game board
     * @param player char representing a player symbol
     * @return true if cell referenced by BoardPosition contains player,
     *          false if it contains another symbol or empty
     */
    public bool isPlayerAtPos(BoardPosition pos, char player);

    /**
     *
     * @return String of entire board's contents
     */
    public String toString();

    /**
     * @post run checkIfFree for each column, must be false for all columns
     *          in order to know that all board spaces are filled
     * @return true if all board spaces are filled,
     *          false if empty spaces are still present
     */
    public boolean checkTie();



}