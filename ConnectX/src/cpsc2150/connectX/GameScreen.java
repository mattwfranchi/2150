package cpsc2150.connectX;

public class GameScreen {
    private GameBoard gameBoard;
    /**
     * @invariant player 'X' always goes first
     * @invariant gameBoard board is initialized to all empty strings [' ']
     */
    private char player = 'X';


    /**
     * @post active player, stored in char player, is switched from 'X' to 'O'
     *          or vice-versa.
     */
    public void switchPlayer();

    /**
     * @post gets a character representing the active player symbol
     * @return char of the active player symbol
     */
    public char whoseTurn();

    /**
     * @post prompts user for column
     * @return int of column number inputted by active player
     */
    public int askForCol();

    /**
     * @pre 0 <= c <= 6
     * @param c column to put the marker
     *
     */
    private void placeToken(int c);

    /**
     * @post askForCol -> placeToken -> checkWin -> checkTie -> switchPlayer
     */
    public void haveTurn();


    /**
     * @pre client enters either int 1 or 2 as the function argument
     * @param choice signals outcome of game - 1: game win // 2: game tie
     * @post outputs outcome of the game, and asks if you want to play again
     */
    public void endgameSequence(int choice);

    /**
     * @param c column where last token was placed
     * @post run board.CheckForWin(c)
     * @return true if win conditions are met, false if game should continue
     */
    public boolean checkWin(int c);

    /**
     *
     * @post run board.checkTie()
     * @return true if no spaces left, false if there are spots left
     */
    public boolean checkTie();


    /**
     * @post re-initialize board to all empty spaces, set player to "X", haveTurn()
     */
    public void restart();



}
