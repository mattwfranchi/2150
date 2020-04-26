package cpsc2150.connectX;

import org.junit.*;

import static org.junit.Assert.*;

public class TestGameBoard {

    /**
     *
     */
    private IGameBoard MakeAGameBoard(int nr, int nc, int ntw)
    {
        return new GameBoard(nr, nc, ntw);
    }


    /**
     *
     * @param contents
     * @return
     */
    private String simulateBoard(char[][] contents) {
        String output = "";

        for (int r = contents.length; r >= 0; r--) {
            // insert bar at beginning of each line
            output += "|";
            for (int c = 0; c < contents[0].length; c++) {
                if (r == contents.length) {
                    // header line of output
                    // handling single-digit columns
                    if(c > 9){ output += c + "|"; }
                    // handling double-digit columns
                    else {output += " " + c + "|"; }
                }
                else{
                    // print out contents of each entry
                    output += " " + contents[r][c] + "|";
                }
            }
            // new line at end of each row
            output += '\n';
        }
        return output;
    }

    // CONSTRUCTOR TESTS

    // Boundary Case: Smallest Possible Board, with Smallest Number To Win
    @Test
    public void test_constructor_Boundary_minNumRows_minNumCols_minNumToWin()
    {
        IGameBoard board = MakeAGameBoard(IGameBoard.minNumRows, IGameBoard.minNumCols, IGameBoard.minNumToWin);

        char[][] expectedBoardArray = new char[IGameBoard.minNumRows][IGameBoard.minNumCols];
        for(int r = 0; r < IGameBoard.minNumRows; r++)
        {
            for(int c = 0; c < IGameBoard.minNumCols; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);

    }

    // Boundary Case: Largest Possible Board, with Largest Possible Number To Win
    @Test
    public void test_constructor_Boundary_maxNumRows_maxNumCols_maxNumToWin()
    {
        IGameBoard board = MakeAGameBoard(IGameBoard.maxNumRows, IGameBoard.maxNumCols, IGameBoard.maxNumToWin);

        char[][] expectedBoardArray = new char[IGameBoard.maxNumRows][IGameBoard.maxNumCols];
        for(int r = 0; r < IGameBoard.maxNumRows; r++)
        {
            for(int c = 0; c < IGameBoard.maxNumCols; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);

    }

    // Routine Case: Rows = 6, Columns = 7, Number Tokens To Win = 4
    @Test
    public void test_constructor_Routine_6Rows_7Cols_4NumToWin()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;
        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
    }

    // CheckIfFree Tests
    // Boundary Case: Column is full
    @Test
    public void test_checkIfFree_Boundary_columnFull()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        for(int r = 0; r < nr; r++)
        {
            board.placeToken('X',0);
            expectedBoardArray[r][0] = 'X';
        }

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertFalse(board.checkIfFree(0));
    }

    // Boundary Case: Column is empty
    @Test
    public void test_checkIfFree_Boundary_columnEmpty()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertTrue(board.checkIfFree(0));
    }


    // Routine Case: Column is non-empty
    @Test
    public void test_checkIfFree_Routine_columnNonEmpty()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        for(int r = 0; r < nr/2; r++)
        {
            board.placeToken('X',2);
            expectedBoardArray[r][2] = 'X';
        }

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertTrue(board.checkIfFree(2));
    }


    // CheckHorizWin Tests
    // Routine Case: Win will occur after insert
    @Test
    public void test_checkHorizWin_Routine_winOccurs()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        for(int c = 1; c <= ntw; c++)
        {
            board.placeToken('X',c);
            expectedBoardArray[0][c] = 'X';
        }

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertTrue(board.checkHorizWin(new BoardPosition(0,ntw),'X'));
    }

    // Routine Case: Win will not occur after insert
    @Test
    public void test_checkHorizWin_Routine_noWin()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        for(int c = 0; c < ntw; c++)
        {
            board.placeToken('X',c);
            expectedBoardArray[0][c] = 'X';
        }

        board.placeToken('O',ntw);
        expectedBoardArray[0][ntw] = 'O';

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertFalse(board.checkHorizWin(new BoardPosition(0,ntw),'O'));
    }

    // Challenging Case: Token placed in the middle of successive tokens
    @Test
    public void test_checkHorizWin_Challenging_winningTokenInMiddle()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        board.placeToken('X',1);
        board.placeToken('X',2);
        board.placeToken('X',4);
        expectedBoardArray[0][1] = 'X';
        expectedBoardArray[0][2] = 'X';
        expectedBoardArray[0][4] = 'X';

        board.placeToken('X',3);
        expectedBoardArray[0][3] = 'X';

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertTrue(board.checkHorizWin(new BoardPosition(0,3),'X'));

    }

    // Boundary Case: Winning token placed in rightmost column
    @Test
    public void test_checkHorizWin_Boundary_winningTokenInRightmostCol()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        for(int c = 3; c < 6; c++)
        {
            board.placeToken('X',c);
            expectedBoardArray[0][c] = 'X';
        }

        board.placeToken('X',nc-1);
        expectedBoardArray[0][nc-1] = 'X';

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertTrue(board.checkHorizWin(new BoardPosition(0,nc-1),'X'));
    }

    // CheckVertWin Tests
    // Routine Case : Typical win scenario
    @Test
    public void test_checkVertWin_Routine_winScenario()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        for(int r = 0; r < ntw-1; r++)
        {
            board.placeToken('X',0);
            expectedBoardArray[r][0] = 'X';
        }

        board.placeToken('X',0);
        expectedBoardArray[ntw-1][0] = 'X';

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertTrue(board.checkVertWin(new BoardPosition(ntw-1,0),'X'));
    }
    // Routine Case:  Typical no-win scenario
    @Test
    public void test_checkVertWin_Routine_noWinScenario()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;
        int col = 3;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        for(int r = 0; r < ntw-1; r++)
        {
            board.placeToken('X',col);
            expectedBoardArray[r][col] = 'X';
        }

        board.placeToken('O',col);
        expectedBoardArray[ntw-1][col] = 'O';

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertFalse(board.checkVertWin(new BoardPosition(ntw-1,3),'O'));
    }

    // Boundary Case: Winning token placed in highest row
    @Test
    public void test_checkVertWin_Boundary_winningTokenInHighestRow()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        board.placeToken('O',0);
        board.placeToken('O',0);
        expectedBoardArray[0][0] = 'O';
        expectedBoardArray[1][0] = 'O';

        for(int r = 2; r < nr-1; r++)
        {
            board.placeToken('X',0);
            expectedBoardArray[r][0] = 'X';
        }

        board.placeToken('X',0);
        expectedBoardArray[nr-1][0] = 'X';

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertTrue(board.checkVertWin(new BoardPosition(nr-1,0),'X'));
    }
    // Challenging Case: Only one token on board
    @Test
    public void test_checkVertWin_Challenging_onlyOneTokenOnBoard()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        board.placeToken('X',1);
        expectedBoardArray[0][1] = 'X';

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertFalse(board.checkVertWin(new BoardPosition(0,1),'X'));
    }

    // CheckDiagWin Tests
    // Routine Case: Typical up-right win scenario
    @Test
    public void test_checkDiagWin_Routine_upRightWinScenario()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        board.placeToken('X',0);
        board.placeToken('O',1);
        board.placeToken('X',1);
        board.placeToken('O',2);
        board.placeToken('O',2);
        board.placeToken('X',2);
        board.placeToken('O',3);
        board.placeToken('O',3);
        board.placeToken('O',3);
        board.placeToken('X',3);

        expectedBoardArray[0][0] = 'X';
        expectedBoardArray[0][1] = 'O';
        expectedBoardArray[1][1] = 'X';
        expectedBoardArray[0][2] = 'O';
        expectedBoardArray[1][2] = 'O';
        expectedBoardArray[2][2] = 'X';
        expectedBoardArray[0][3] = 'O';
        expectedBoardArray[1][3] = 'O';
        expectedBoardArray[2][3] = 'O';
        expectedBoardArray[3][3] = 'X';

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();
        assertEquals(observedBoard,expectedBoard);
        assertTrue(board.checkDiagWin(new BoardPosition(3,3),'X'));
    }

    // Routine Case: Typical down-right win scenario
    @Test
    public void test_checkDiagWin_Routine_downRightWinScenario()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        board.placeToken('X',3);
        board.placeToken('O',2);
        board.placeToken('X',2);
        board.placeToken('O',1);
        board.placeToken('O',1);
        board.placeToken('X',1);
        board.placeToken('O',0);
        board.placeToken('O',0);
        board.placeToken('O',0);
        board.placeToken('X',0);

        expectedBoardArray[0][3] = 'X';
        expectedBoardArray[0][2] = 'O';
        expectedBoardArray[1][2] = 'X';
        expectedBoardArray[0][1] = 'O';
        expectedBoardArray[1][1] = 'O';
        expectedBoardArray[2][1] = 'X';
        expectedBoardArray[0][0] = 'O';
        expectedBoardArray[1][0] = 'O';
        expectedBoardArray[2][0] = 'O';
        expectedBoardArray[3][0] = 'X';

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertTrue(board.checkDiagWin(new BoardPosition(0,3),'X'));
    }

    // Challenging Case: Winning token placed in middle of succession (up-right)
    @Test
    public void test_checkDiagWin_Challenging_upRight_winningTokenInMiddle()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        board.placeToken('X',0);
        board.placeToken('O',1);
        board.placeToken('X',1);
        board.placeToken('O',2);
        board.placeToken('O',2);
        board.placeToken('X',2);
        board.placeToken('O',3);
        board.placeToken('O',3);
        board.placeToken('O',3);
        board.placeToken('X',3);

        expectedBoardArray[0][0] = 'X';
        expectedBoardArray[0][1] = 'O';
        expectedBoardArray[1][1] = 'X';
        expectedBoardArray[0][2] = 'O';
        expectedBoardArray[1][2] = 'O';
        expectedBoardArray[2][2] = 'X';
        expectedBoardArray[0][3] = 'O';
        expectedBoardArray[1][3] = 'O';
        expectedBoardArray[2][3] = 'O';
        expectedBoardArray[3][3] = 'X';

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertTrue(board.checkDiagWin(new BoardPosition(1,1),'X'));
    }

    // Challenging Case: Winning token placed in middle of succession (down-right)
    @Test
    public void test_checkDiagWin_Challenging_downRight_winningTokenInMiddle()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        board.placeToken('X',3);
        board.placeToken('O',2);
        board.placeToken('X',2);
        board.placeToken('O',1);
        board.placeToken('O',1);
        board.placeToken('X',1);
        board.placeToken('O',0);
        board.placeToken('O',0);
        board.placeToken('O',0);
        board.placeToken('X',0);

        expectedBoardArray[0][3] = 'X';
        expectedBoardArray[0][2] = 'O';
        expectedBoardArray[1][2] = 'X';
        expectedBoardArray[0][1] = 'O';
        expectedBoardArray[1][1] = 'O';
        expectedBoardArray[2][1] = 'X';
        expectedBoardArray[0][0] = 'O';
        expectedBoardArray[1][0] = 'O';
        expectedBoardArray[2][0] = 'O';
        expectedBoardArray[3][0] = 'X';

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertTrue(board.checkDiagWin(new BoardPosition(1,2),'X'));
    }

    // Boundary Case: Only one token on board
    @Test
    public void test_checkDiagWin_Boundary_onlyOneTokenOnBoard()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        board.placeToken('X',1);
        expectedBoardArray[0][1] = 'X';

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertFalse(board.checkDiagWin(new BoardPosition(0,1),'X'));

    }

    // Challenging Case: > ntw tokens in succession, but not diagonal
    @Test
    public void test_checkDiagWin_Challenging_algorithmTest()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        board.placeToken('X',0);
        expectedBoardArray[0][0] = 'X';

        for(int r = 0; r < ntw; r ++)
        {
            board.placeToken('X',1);
            expectedBoardArray[r][1] = 'X';
        }

        for(int r = 0; r < 2; r++)
        {
            board.placeToken('X',2);
            expectedBoardArray[r][2] = 'X';
            board.placeToken('X',3);
            expectedBoardArray[r][3] = 'X';
        }

        board.placeToken('X',2);
        expectedBoardArray[2][2] = 'X';

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();


        assertEquals(observedBoard,expectedBoard);
        assertFalse(board.checkDiagWin(new BoardPosition(2,2),'X'));
    }

    // Routine Case: typical up-right no win scenario
    @Test
    public void test_checkDiagWin_Routine_upRight_noWinScenario()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        board.placeToken('X',0);
        board.placeToken('O',1);
        board.placeToken('X',1);
        board.placeToken('O',2);
        board.placeToken('O',2);
        board.placeToken('X',2);
        board.placeToken('X',3);
        board.placeToken('O',3);
        board.placeToken('O',3);


        expectedBoardArray[0][0] = 'X';
        expectedBoardArray[0][1] = 'O';
        expectedBoardArray[1][1] = 'X';
        expectedBoardArray[0][2] = 'O';
        expectedBoardArray[1][2] = 'O';
        expectedBoardArray[2][2] = 'X';
        expectedBoardArray[0][3] = 'X';
        expectedBoardArray[1][3] = 'O';
        expectedBoardArray[2][3] = 'O';

        board.placeToken('O',3);
        expectedBoardArray[3][3] = 'O';

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertFalse(board.checkDiagWin(new BoardPosition(3,3),'O'));
    }


    // CheckTie Tests
    // Boundary Case: board is completely full
    @Test
    public void test_checkTie_Boundary_fullBoard()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        for(int r = 0; r < nr; r ++)
        {
            for(int c = 0; c < nc; c ++)
            {
                expectedBoardArray[r][c] = 'X';
                board.placeToken('X',c);
            }
        }

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertTrue(board.checkTie());
    }

    // Boundary Case: board is empty
    @Test
    public void test_checkTie_Boundary_emptyBoard()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertFalse(board.checkTie());
    }

    // Boundary Case: board only has one token
    @Test
    public void test_checkTie_Boundary_onlyOneTokenOnBoard()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        board.placeToken('X',0);
        expectedBoardArray[0][0] = 'X';

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertFalse(board.checkTie());
    }

    // Routine Case: board is non-empty
    @Test
    public void test_checkTie_Routine_nonEmptyBoard()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        board.placeToken('X',0);
        board.placeToken('O',0);
        board.placeToken('X',0);
        board.placeToken('O',0);

        expectedBoardArray[0][0] = 'X';
        expectedBoardArray[1][0] = 'O';
        expectedBoardArray[2][0] = 'X';
        expectedBoardArray[3][0] = 'O';

        for(int n = 0; n < 3; n ++)
        {
            board.placeToken('X',1);
            expectedBoardArray[n][1] = 'X';
        }

        board.placeToken('O',2);
        board.placeToken('X',3);
        expectedBoardArray[0][2] = 'O';
        expectedBoardArray[0][3] = 'X';

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertFalse(board.checkTie());
    }


    // WhatsAtPos Tests
    // Challenging Case: Contents of position are empty
    @Test
    public void test_whatsAtPos_challenging_emptyPosition()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        int pr = 2;
        int pc = 3;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        for(int r = 0; r < pr; r++)
        {
            board.placeToken('X',pc);
            expectedBoardArray[r][pc] = 'X';
        }

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertEquals(board.whatsAtPos(new BoardPosition(pr,pc)),' ');
    }

    // Routine Case: Contents of position are not empty
    @Test
    public void test_whatsAtPos_routine_nonEmptyPosition()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        int pr = 2;
        int pc = 3;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        for(int r = 0; r <= pr; r++)
        {
            board.placeToken('X',pc);
            expectedBoardArray[r][pc] = 'X';
        }

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertEquals(board.whatsAtPos(new BoardPosition(pr,pc)),'X');
    }

    // Boundary Case: Getting position at (0,0)
    @Test
    public void test_whatsAtPos_boundary_Position_r0_c0()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        int pr = 0;
        int pc = 0;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        for(int r = 0; r <= pr; r++)
        {
            board.placeToken('X',pc);
            expectedBoardArray[r][pc] = 'X';
        }

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertEquals(board.whatsAtPos(new BoardPosition(pr,pc)),'X');
    }

    // Boundary Case: Getting position at (numRows,0)
    @Test
    public void test_whatsAtPos_boundary_Position_rnumRows_c0()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        int pr = 0;
        int pc = 0;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        for(int r = 0; r <= pr; r++)
        {
            board.placeToken('X',pc);
            expectedBoardArray[r][pc] = 'X';
        }

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertEquals(board.whatsAtPos(new BoardPosition(pr,pc)),'X');
    }

    // Boundary Case: Getting position (numCols,numCols)
    @Test
    public void test_whatsAtPos_boundary_Position_rnumRows_cnumCols()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        int pr = nr-1;
        int pc = nc-1;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        for(int r = 0; r < nr; r++)
        {
            board.placeToken('X',pc);
            expectedBoardArray[r][pc] = 'X';
        }

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertEquals(board.whatsAtPos(new BoardPosition(pr,pc)),'X');
    }

    // IsPlayerAtPos Tests
    // Challenging Case: Contents of position are empty
    @Test
    public void test_isPlayerAtPos_challenging_emptyPosition()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        int pr = 2;
        int pc = 3;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }



        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertFalse(board.isPlayerAtPos(new BoardPosition(0,1),'X'));
    }

    // Routine Case: Contents of position are not empty
    @Test
    public void test_isPlayerAtPos_routine_nonEmptyPosition()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        int pr = 2;
        int pc = 3;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        board.placeToken('O',0);
        board.placeToken('X',1);
        board.placeToken('O',1);
        board.placeToken('X',1);

        expectedBoardArray[0][0] = 'O';
        expectedBoardArray[0][1] = 'X';
        expectedBoardArray[1][1] = 'O';
        expectedBoardArray[2][1] = 'X';

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertTrue(board.isPlayerAtPos(new BoardPosition(2,1),'X'));
    }

    // Boundary Case: Getting position at (0,0)
    @Test
    public void test_isPlayerAtPos_boundary_Position_r0_c0()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        int pr = 0;
        int pc = 0;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        board.placeToken('X',0);
        expectedBoardArray[pr][pc] = 'X';

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertTrue(board.isPlayerAtPos(new BoardPosition(pr,pc),'X'));
    }

    // Boundary Case: Getting position at (numRows,0)
    @Test
    public void test_isPlayerAtPos_Challenging_doesTokenMatch_OccupiedPosition()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        int pr = 0;
        int pc = 0;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        board.placeToken('X',1);
        expectedBoardArray[0][1] = 'X';

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertFalse(board.isPlayerAtPos(new BoardPosition(0,1),'O'));
    }

    // Boundary Case: Getting position (numCols,numCols)
    @Test
    public void test_isPlayerAtPos_boundary_Position_rnumRows_cnumCols()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        int pr = nr-1;
        int pc = nc-1;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        for(int r = 0; r < nr; r++)
        {
            board.placeToken('X',pc);
            expectedBoardArray[r][pc] = 'X';
        }

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
        assertEquals(board.whatsAtPos(new BoardPosition(pr,pc)),'X');
    }

    // PlaceToken Tests
    // Boundary Case: Placing token in empty column
    @Test
    public void test_placeToken_boundary_emptyColumn()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        int pr = nr-1;
        int pc = nc-1;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        board.placeToken('X',2);
        expectedBoardArray[0][2] = 'X';

        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
    }


    // Boundary Case: Placing token in nearly-full column
    @Test
    public void test_placeToken_boundary_columnIsNearlyFull()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        int pr = nr-1;
        int pc = 2;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        for(int r = 0; r < pr; r ++)
        {
            board.placeToken('X',pc);
            expectedBoardArray[r][pc] = 'X';
        }


        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
    }

    // Routine Case: Placing token in non-empty column
    @Test
    public void test_placeToken_routine_nonEmptyColumn()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        int pr = 3;
        int pc = 2;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        for(int r = 0; r < pr; r ++)
        {
            board.placeToken('X',pc);
            expectedBoardArray[r][pc] = 'X';
        }


        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
    }

    // Boundary Case: Placing token in first column
    @Test
    public void test_placeToken_boundary_placeinFirstColumn()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        int pr = 4;
        int pc = 1;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        for(int r = 0; r < pr; r ++)
        {
            board.placeToken('X',pc);
            expectedBoardArray[r][pc] = 'X';
        }


        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
    }

    // Boundary Case: Placing token in last column
    @Test
    public void test_placeToken_boundary_placeInLastColumn()
    {
        int nr = 6;
        int nc = 7;
        int ntw = 4;

        int pr = 4;
        int pc = nc-1;

        IGameBoard board = MakeAGameBoard(nr,nc,ntw);

        char[][] expectedBoardArray = new char[nr][nc];
        for(int r = 0; r < nr; r++)
        {
            for(int c = 0; c < nc; c++)
            {
                expectedBoardArray[r][c] = ' ';
            }
        }

        for(int r = 0; r < pr; r ++)
        {
            board.placeToken('X',pc);
            expectedBoardArray[r][pc] = 'X';
        }


        String expectedBoard = simulateBoard(expectedBoardArray);
        String observedBoard = board.toString();

        assertEquals(observedBoard,expectedBoard);
    }




}

