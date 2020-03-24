/* Matt Franchi | CPSC 2150 | Spring 2020
 * Project 3 : ConnectX
 * File Description: GameBoard (Memory-Efficient) implementation code
 */

package cpsc2150.connectX;

import java.util.*;

public class GameBoardMem extends AbsGameBoard implements IGameBoard  {
    /**
     * @correspondence  (number of rows) = numRows
     * @correspondence  (number of columns) = numCols
     * @correspondence  (number of tokens needed to win) = numToWin
     * @correspondence  (board) is stored as a Map<Character, List<BoardPosition>>
     *
     * @invariant numRows >= minNumRows AND numRows <= maxNumRows
     * @invariant numCols >= minNumCols AND numCols <= maxNumCols
     * @invariant numToWin >= minNumToWin AND numToWin <= maxNumToWin
     *
     * @invariant player needs numToWin tokens in horizontal, vertical, or diagonal succession to win
     * @invariant board must be of size numRows x numCols, with (numRows x NumCols) possible entries
     * @invariant board must be stored as a java Map
     * @invariant board initially takes up no memory; size 0
     * @invariant only occupied positions are stored in the Map board
     * @invariant the Map keys of board must represent each player
     * @invariant the Map values of board must represent the positions where each player placed their token
     * @invariant each board position can only contain one token
     * @invariant tokens cannot be placed into a full column
     * @invariant isPlayerAtPos should be used over whatsAtPos when appropriate, as it is more efficient
     * @invariant the Map board should always be cleared / reset after each game
     *
     *
     */

    private int numRows = -1, numCols = -1, numToWin = -1;

    // board: map representation of game board
    // use HashMap constructor to initialize, as it implements the Map interface
    private Map<Character, List<BoardPosition>> board = new HashMap<>();

    public GameBoardMem(int nr, int nc, int ntw) {
        // erase board - only effective for new games after the first
        board.clear();
        // initialize variables using parameters in constructor
        numRows = nr;
        numCols = nc;
        numToWin = ntw;
    }

    public void placeToken(char p, int c)
    {
        // firstEmptyRow : highest entry on board not currently occupied by a player
        int firstEmptyRow = 0;
        BoardPosition proposedPos = new BoardPosition(firstEmptyRow,c);
        // initialize stack for player positions list
        Stack<BoardPosition> playerPositions;
        // CASE I: First turn for player
        if(!board.containsKey(p))
        {
            playerPositions = new Stack<>();
        }
        // CASE II: Player has already had at least one turn
        else
        {
            playerPositions = (Stack<BoardPosition>) board.get(p);
        }

        // Get first empty row
        for(Map.Entry<Character, List<BoardPosition>> player : board.entrySet()) {
            for (BoardPosition position : player.getValue()) {
                if (proposedPos.getColumn() == position.getColumn()) {
                    proposedPos = new BoardPosition(++firstEmptyRow, c);
                }
            }
        }
        // push first empty position in column onto the playerpositions stack
        playerPositions.push(proposedPos);
        // update the value for the key p in board
        board.put(p,playerPositions);
    }

    public char whatsAtPos(BoardPosition pos)
    {
        // check that position is in valid range
        if ((pos.getRow() >= 0 && pos.getRow() < numRows) &&
                (pos.getColumn() >= 0 && pos.getColumn() < numCols))
        {
            // nested iteration through every current occupied position
            for (Map.Entry<Character, List<BoardPosition>> player : board.entrySet()) {
                for(BoardPosition position : player.getValue()) {
                    if (position.equals(pos)) {
                        // get the key if the inputted pos is a match
                        return player.getKey();
                    }
                }
            }
            // assume pos is empty if this point is reached
            return ' ';
        }
        // flag for invalidity
       return '-';
    }

    @Override
    // see if the key player's value list contains the inputted pos
    public boolean isPlayerAtPos(BoardPosition pos, char player)
    {
        return board.get(player).contains(pos);
    }

    public int getNumRows(){ return numRows; }

    public int getNumColumns(){ return numCols; }

    public int getNumToWin(){ return numToWin; }

}
