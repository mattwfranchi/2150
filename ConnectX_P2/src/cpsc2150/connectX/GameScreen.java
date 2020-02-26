/* Matt Franchi | CPSC 2150 | Spring 2020
 * Project 2 : Implementing ConnectX
 * File Description: GameScreen implementation code
 */

package cpsc2150.connectX;
import java.util.Scanner;

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
        Scanner inputHandle = new Scanner(System.in);
        System.out.printf("Player %c, what column do you want to place your marker in? \n", player);
        int column = inputHandle.nextInt();

        // check for negative column number
        while(column < 0){
            System.out.println("Column cannot be less than 0. Try again. ");
            column = askForCol();
        }

        // check for out of range column number (>)
        while(column > gameBoard.getNumColumns() - 1){
            System.out.printf("Column cannot be greater than %d. Try again. \n",gameBoard.getNumColumns()-1);
            column = askForCol();
        }

        // check that column can accept another token
        while(!gameBoard.checkIfFree(column)){
            System.out.printf("Column %d is full. Try again. \n", column);
            column = askForCol();
        }

        return column;
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
        // get valid column from user input
        int column = askForCol();

        // place the active player's token in column
        placeToken(column);

        // print out the upated gameboard
        System.out.println(gameBoard);

        // if...else sequence to process whether game should end or continue
        if(checkWin(column)){
            return 1; // win code
        }
        else if (checkTie()){
            return 2; // tie code
        }
        else {
            // continue game, and switch active player
            switchPlayer();
            return 0;
        }
    }


    /**
     * @pre haveTurn() loop has ended, and either 1 or 2 was returned for choice
     * @param choice signals outcome of game - 1: game win // 2: game tie
     * @post outputs outcome of the game, and asks if you want to play again
     */
    public int endgameSequence(int choice){
        // initiialize new Scanner instance to read user input
        Scanner inputHandle = new Scanner(System.in);

        // win case
        if(choice == 1){
            System.out.printf("Player %c Won!\n",player);
        }
        // tie case
        else{
            System.out.println("The game ended in a tie!\n");
        }

        // take user input for play again, make it uppercase; simplifies processing
        System.out.println("Would you like to play again? Y/N ");
        String retryChoice = inputHandle.next().toUpperCase();

        // process choice to make sure it is valid (Y or N)
        while(!retryChoice.equals("N") && !retryChoice.equals("Y")){
            System.out.println("Would you like to play again? Y/N ");
            retryChoice = inputHandle.next().toUpperCase();
        }

        // reset board
        if(retryChoice.equals("Y")){ restart(); }

        return retryChoice.equals("N") ? 1 : 0;
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
        player = 'X';
        gameBoard = new GameBoard();


    };

    /**
     * @pre gameBoard has been initialized, is in usable condition
     * @return String representing the gameBoard, in readable format
     * @post gameBoard.toString() = toString()
     */
    public String toString(){
        return gameBoard.toString();
    }


    public static void main(String[] args){
        int endFlag = haveTurn();
        while(endFlag == 0){
            endFlag = haveTurn();
            if(endFlag != 0){
                endFlag = endgameSequence(endFlag);
            }
        }
    }






}
