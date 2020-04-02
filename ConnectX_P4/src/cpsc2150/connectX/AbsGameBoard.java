package cpsc2150.connectX;

public abstract class AbsGameBoard implements IGameBoard {
    @Override
    /**
     * @pre IGameBoard-implementing object has been initialized
     * @post toString = string of grid-like output for the game board
     */
    public String toString() {
        String output = "";

        for (int r = getNumRows(); r >= 0; r--) {
            // insert bar at beginning of each line
            output += "|";
            for (int c = 0; c < getNumColumns(); c++) {
                if (r == getNumRows()) {
                    // header line of output
                    // handling single-digit columns
                    if(c > 9){ output += c + "|"; }
                    // handling double-digit columns
                    else {output += " " + c + "|"; }
                }
                else{
                    BoardPosition iterator = new BoardPosition(r,c);
                    // print out contents of each entry
                    output += " " + whatsAtPos(iterator) + "|";
                }
            }
            // new line at end of each row
            output += '\n';
        }
        return output;
    }
}
