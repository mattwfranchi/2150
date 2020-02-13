package cpsc2150.connectX;

public abstract class GameScreen {
    private GameBoard gameBoard;
    /**
     * @invariant player 'X' always goes first
     * @invariant gameBoard board is initialized to all empty strings [' ']
     * / All private methods are helper methods to the public methods
     * / haveTurn() and endgameSequence()
     */
    private char player = 'X';


    /**
     * @post runs GameBoard constructor to create gameBoard
     * @post GameScreen instance is in a usable state
     */
    public GameScreen();


    /**
     * @pre checkWin and checkTie have both been run, returned false
     * @pre within while loop containing haveTurn()
     * @post active player, stored in char player, is switched from 'X' to 'O'
     *          or vice-versa.
     */
    private void switchPlayer();


    /**
     * @pre within while loop containing haveTurn()
     * @post gets a character representing the active player symbol
     * @return char of the active player symbol
     */
    private char whoseTurn();


    /**
     * @pre within while loop containing haveTurn()
     * @post prompts user for column
     * @return int of column number inputted by active player
     */
    private int askForCol();


    /**
     * @pre within while loop containing haveTurn()
     * @pre assume column index return from askForCol() is valid (0 <= c <= 6)
     * @param c column to put the marker
     *
     */
    private void placeToken(int c);


    /**
     * @pre while loop was started with flag variable
     * @pre haveTurn() function is the primary code within the while loop
     * @post askForCol -> placeToken -> checkWin -> checkTie -> switchPlayer
     * @return 0 if loop should continue, 1 if win occured, 2 if tie occured
     */
    public abstract int haveTurn();


    /**
     * @pre haveTurn() loop has ended, and either 1 or 2 was returned for choice
     * @param choice signals outcome of game - 1: game win // 2: game tie
     * @post outputs outcome of the game, and asks if you want to play again
     */
    public abstract void endgameSequence(int choice);


    /**
     * @pre within while loop containing haveTurn()
     * @pre placeToken was just run before call
     * @param c column where last token was placed
     * @post run board.CheckForWin(c)
     * @return true if win conditions are met, false if game should continue
     */
    private boolean checkWin(int c);


    /**
     * @pre haveTurn() loop is active
     * @pre placeToken() was just run before call
     * @post run board.checkTie()
     * @return true if no spaces left, false if there are spots left
     */
    private boolean checkTie();


    /**
     * @pre haveTurn() loop has ended
     * @pre endgameSequence function is being run
     * @post re-initialize board to all empty spaces, set player to "X", haveTurn()
     */
    private void restart();



}
