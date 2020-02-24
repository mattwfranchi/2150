/* Matt Franchi | CPSC 2150 | Spring 2020
 * Project 2 : Implementing ConnectX
 * File Description: GameScreen implementation code
 */

package cpsc2150.connectX;

public class GameScreen {
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
    public GameScreen(){
        gameBoard = new GameBoard();
    }


    /**
     * @pre checkWin and checkTie have both been run, returned false
     * @pre within while loop containing haveTurn()
     * @post active player, stored in char player, is switched from 'X' to 'O'
     *          or vice-versa.
     */
    private void switchPlayer() {
        player = (player == 'X' ? 'O' : 'X');
    }
    /**
     * @pre within while loop containing haveTurn()
     * @post gets a character representing the active player symbol
     * @return char of the active player symbol
     */
    char whoseTurn(){ return player; }


    /**
     * @pre within while loop containing haveTurn()
     * @post prompts user for column
     * @return int of column number inputted by active player
     */
    private int askForCol(){
        return 2; // sample value for testing, implement scanner later
    }


    /**
     * @pre within while loop containing haveTurn()
     * @pre assume column index return from askForCol() is valid (0 <= c <= 6)
     * @param c column to put the marker
     *
     */
    private void placeToken(int c){ gameBoard.placeToken(player,c); }


    /**
     * @pre while loop was started with flag variable
     * @pre haveTurn() function is the primary code within the while loop
     * @post askForCol -> placeToken -> checkWin -> checkTie -> switchPlayer
     * @return 0 if loop should continue, 1 if win occured, 2 if tie occured
     */
    public int haveTurn(){
        int column = askForCol();
        placeToken(column);
        if(checkWin(column)){
            return 1; // win code
        }
        else if (checkTie()){
            return 2; // tie code
        }
        else {
            switchPlayer();
            return 0;
        }
    }


    /**
     * @pre haveTurn() loop has ended, and either 1 or 2 was returned for choice
     * @param choice signals outcome of game - 1: game win // 2: game tie
     * @post outputs outcome of the game, and asks if you want to play again
     */
    public void endgameSequence(int choice){
        if(choice == 1){
            System.out.println("Game win.");
        }
        else{
            System.out.println("Game tie.");
        }
        // ADD PLAY AGAIN CODE
    }


    /**
     * @pre within while loop containing haveTurn()
     * @pre placeToken was just run before call
     * @param c column where last token was placed
     * @post run board.CheckForWin(c)
     * @return true if win conditions are met, false if game should continue
     */
    private boolean checkWin(int c){ return gameBoard.checkForWin(c); }


    /**
     * @pre haveTurn() loop is active
     * @pre placeToken() was just run before call
     * @post run board.checkTie()
     * @return true if no spaces left, false if there are spots left
     */
    private boolean checkTie(){ return gameBoard.checkTie(); }


    /**
     * @pre haveTurn() loop has ended
     * @pre endgameSequence function is being run
     * @post re-initialize board to all empty spaces, set player to "X", haveTurn()
     */
    private void restart(){
        System.out.println("Restart");
    };



}
