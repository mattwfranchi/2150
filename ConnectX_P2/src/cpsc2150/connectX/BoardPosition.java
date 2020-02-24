/* Matt Franchi | CPSC 2150 | Spring 2020
 * Project 2 : Implementing ConnectX
 * File Description: BoardPosition implementation code
 */




package cpsc2150.connectX;



public class BoardPosition {
    private static int row; private static int col;

    // Default Constructor
    BoardPosition(){
        row = 0; col = 0;
    }

    // Parameterized Constructor
    BoardPosition(int r, int c){
        row = r;
        col = c;
    }

    public int getRow() { return row; }

    public int getColumn() { return col; }

    @Override
    public String toString(){
        return "Test";
    }

    @Override
    public boolean equals(Object other){
        return false;
    }


}
