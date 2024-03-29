/* Matt Franchi | CPSC 2150 | Spring 2020
 * Project 3 : ConnectX
 * File Description: GameScreen implementation code
 */

package cpsc2150.connectX;
import java.util.Scanner;

public class GameScreen {
    public static final int minPlayers = 2;
    public static final int maxPlayers = 10;


    // coding to the interface
    private static IGameBoard gameBoard;
    private static String PlayerTokens = "";
    private static int numPlayers = 2;
    private static int gameMode = 0;


    /**
     * @invariant player 1 always goes first
     * @invariant gameBoard board is initialized to all empty chars [' ']
     */
    private static char player;


    /**m
     * @pre checkWin and checkTie have both been run, returned false
     * @pre within while loop containing haveTurn()
     * @post active player, stored in char player, is switched from 'X' to 'O'
     *          or vice-versa; switchPlayer() = 'X' -> 'O' or vice-versa
     */
    private static void switchPlayer() {
        int currentIndex = PlayerTokens.indexOf(player);
        if(++currentIndex >= numPlayers)
        {
            currentIndex = 0;
        }

        player = PlayerTokens.charAt(currentIndex);
    }


    /**
     * @pre player enters an integer AND function is called within while loop containing haveTurn()
     * @post askForCol() = [integer representing user's valid column number]
     * @return int of column number inputted by active player
     */
    private static int askForCol(){
        // initialize Scanner to process user input
        Scanner inputHandle = new Scanner(System.in);
        System.out.printf("Player %c, what column do you want to place your marker in? \n", player);
        int column = inputHandle.nextInt();
        boolean correctFlag = false;
        while(!correctFlag)
        {
            // check for negative column number
            if(column < 0){
                System.out.println("Column cannot be less than 0. Try again. ");
                column = askForCol();
            }

            // check for out of range column number (>)
            else if(column > gameBoard.getNumColumns() - 1){
                System.out.printf("Column cannot be greater than %d. Try again. \n",gameBoard.getNumColumns()-1);
                column = askForCol();
            }

            // check that column can accept another token
            else if(!gameBoard.checkIfFree(column)){
                System.out.printf("Column %d is full. Try again. \n", column);
                column = askForCol();
            }
            else
            {
                correctFlag = true;
            }
        }
        return column;
    }


    /**
     * @pre within while loop containing haveTurn()
     * @pre assume column index return from askForCol() is valid (0 <= c <= 6)
     * @param c column to put the marker
     *
     */
    private static void placeToken(int c){ gameBoard.placeToken(player,c); }


    /**
     * @pre while loop was started with flag variable
     * @pre haveTurn() function is the primary code within the while loop
     * @post askForCol -> placeToken -> checkWin -> checkTie -> switchPlayer
     * @return 0 if loop should continue, 1 if win occured, 2 if tie occured
     */
    private static int haveTurn(){
        // get valid column from user input
        int column = askForCol();

        // place the active player's token in column
        placeToken(column);

        // print out the upated gameboard
        System.out.println(gameBoard);

        // if...else sequence to process whether game should end or continue
        if(gameBoard.checkForWin(column)){
            return 1; // win code
        }
        else if (gameBoard.checkTie()){
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
    private static int endgameSequence(int choice){
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

        return retryChoice.equals("N") ? 1 : 0;
    }


// MAIN FUNCTION

    public static void main(String[] args){
        // run first instance of haveTurn to start while loop
        int endFlag = 0;
        boolean firstRun = true;
        Scanner inputHandle = new Scanner(System.in);

        while(endFlag == 0){
            if(firstRun)
            {
                boolean correctFlag = false;
                int numRows = 0, numCols = 0, numToWin = 0;

                // numPlayers processing
                while(!correctFlag) {

                    System.out.print("How many players? ");
                    numPlayers = inputHandle.nextInt();
                    if (numPlayers > maxPlayers) {
                        System.out.printf("Cannot be more than %d players. \n", maxPlayers);
                    } else if (numPlayers < minPlayers) {
                        System.out.printf("Must be at least %d players. \n", minPlayers);
                    } else {
                        correctFlag = true;
                    }
                }

                // player tokens processing
                char Token;
                for(int n = 1; n <= numPlayers; n++){

                    System.out.printf("Enter Player %d's token: ",n);
                    Token = inputHandle.next().toUpperCase().charAt(0);
                    while(PlayerTokens.indexOf(Token) != -1)
                    {
                        System.out.printf("The token %c is already taken. \n",Token);
                        System.out.printf("Enter Player %d's token: ",n);
                        Token = inputHandle.next().toUpperCase().charAt(0);
                    }
                    PlayerTokens += Token;
                }

                // numRows processing
                correctFlag = false;
                while (!correctFlag) {


                    System.out.print("How many rows should be on the board? ");
                    numRows = inputHandle.nextInt();
                    if(numRows > GameBoard.maxNumRows)
                    {
                        System.out.printf("Cannot be more than %d rows. \n",GameBoard.maxNumRows);
                    }
                    else if(numRows < GameBoard.minNumRows)
                    {
                        System.out.printf("Must be at least %d rows.\n",GameBoard.minNumRows);
                    }
                    else
                    {
                        correctFlag = true;
                    }
                }

                correctFlag = false;
                while(!correctFlag) {

                    //numCols processing
                    System.out.print("How many columns should be on the board? ");
                    numCols = inputHandle.nextInt();
                    if (numCols > GameBoard.maxNumCols)
                    {
                        System.out.printf("Cannot be more than %d columns. \n", GameBoard.maxNumCols);
                    }
                    else if (numCols < GameBoard.minNumCols)
                    {
                        System.out.printf("Must be at least %d rows. \n", GameBoard.minNumCols);
                    }
                    else {
                        correctFlag = true;
                    }
                }

                correctFlag = false;
                while(!correctFlag) {
                    //numToWin processing
                    System.out.print("How many in a row to win? ");
                    numToWin = inputHandle.nextInt();

                    if(numToWin > GameBoard.maxNumToWin)
                    {
                        System.out.printf("Cannot be more than %d tokens in a row. \n", GameBoard.maxNumToWin);
                    }
                    else if(numToWin < GameBoard.minNumToWin)
                    {
                        System.out.printf("Must be at least %d tokens in a row. \n", GameBoard.minNumToWin);
                    }
                    else if(numToWin > numRows)
                    {
                        System.out.printf("Cannot be more than %d (number of rows on board). \n", numRows);
                    }
                    else if(numToWin > numCols)
                    {
                        System.out.printf("Cannot be more than %d : (number of column on board).\n", numCols);
                    }
                    else
                    {
                        correctFlag = true;
                    }


                }
                // implementation processing
                correctFlag = false;
                while(!correctFlag)
                {
                    System.out.println("Which Implementation: Fast [F/f] or Memory Efficient [M/m]? ");
                    gameMode = inputHandle.next().toUpperCase().charAt(0);
                    if(gameMode != 'F' && gameMode != 'M')
                    {
                        System.out.println("Invalid argument. Try again.");
                    }
                    else
                    {
                        correctFlag = true;
                    }
            }


                // at this point, assume all user inputs are valid
                gameBoard = gameMode == 'F'? new GameBoard(numRows,numCols,numToWin) :
                                                new GameBoardMem(numRows,numCols,numToWin);

                // set player to first token
                player = PlayerTokens.charAt(0);

                // no longer first run
                firstRun = false;

            }

            endFlag = haveTurn();
            // endgame code; endFlag is updated to indicate restart (0) or exit game (1)
            if(endFlag != 0){
                endFlag = endgameSequence(endFlag);
                firstRun = true;
                PlayerTokens = "";
            }
        }

    }
}
