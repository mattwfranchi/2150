package cpsc2150.connectX;

/**
 * The controller class will handle communication between our View and our Model (IGameBoard)
 *
 * This is where you will write code
 *
 * You will need to include your IGameBoard interface
 * and both of the IGameBoard implementations from Homework 3
 * If your code was correct you will not need to make any changes to your IGameBoard implementation class
 */

public class ConnectXController {
    //our current game that is being played
    private IGameBoard curGame;


    //The screen that provides our view
    private ConnectXView screen;



    public static final int MAX_PLAYERS = 10;
    //our play tokens are hard coded. We could make a screen to get those from the user, but
    // array for the possible player tokens
    private static char[] tokens = {'X','O','A','B','C','K','E','L','G','H'};
    // playerNum - the index of the current player
    private static int playerNum = 0;
    // get the first player token and store it in the player variable
    private static char player = tokens[playerNum];
    // restart flag is initially false
    private static boolean restart = false;

    // numPlayers - the number of players in the current game
    private static int numPlayers = 2;


    /**
     *
     * @param model the board implementation
     * @param view the screen that is shown
     * @post the controller will respond to actions on the view using the model.
     */
    ConnectXController(IGameBoard model, ConnectXView view, int np){
        this.curGame = model;
        this.screen = view;
        numPlayers = np;
        // must set flag back to false at the start of each game
        restart = false;
        // set player to the first in the tokens list
        playerNum = 0;
        player = tokens[playerNum];

    }

    /**
     *
     *
     * @param col the column of the activated button
     * @post will allow the player to place a token in the column if it is not full, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button
     */
    public void processButtonClick(int col) {
        // execute newGame if the restart flag has been set
        if(restart) { newGame(); }

        // only place token if the column is free
        if(curGame.checkIfFree(col))
        {
            int row = 0;
            // get the first empty position in the desired column
            while(curGame.whatsAtPos(new BoardPosition(row,col)) != ' ') { row ++; }
            // place the token on the model board
            curGame.placeToken(player, col);
            // set the marker on the view screen
            screen.setMarker(row,col,player);

            // win case
            if(curGame.checkForWin(col))
            {
                screen.setMessage("Player " + player + " Won! Press any button to start a new game.");
                restart = true;
                return;
            }
            // tie case
            else if(curGame.checkTie())
            {
                screen.setMessage("Tie game! Press any button to start a new game.");
                restart = true;
                return;
            }

            // increment playerNum or loop back to 0 if playerNum is on the last player
            playerNum = playerNum < numPlayers-1 ? ++playerNum : 0;
            player = tokens[playerNum];
            // It is the next player's turn message
            screen.setMessage("It is " + player + "'s turn.");
        }
        // error message for if the desired column was full
        else { screen.setMessage("Error: column " + col + " is full."); }

    }

    /**
     * This method will start a new game by returning to the setup screen and controller
     */
    private void newGame()
    {
        //close the current screen
        screen.dispose();
        //start back at the set up menu
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }
}
