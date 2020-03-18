package cpsc2150.connectX;

public abstract class AbsGameBoard implements IGameBoard {
    @Override
    public String toString() {
        String output = "";

        for (int r = getNumRows(); r >= 0; r--) {
            // insert bar at beginning of each line
            output += "|";
            for (int c = 0; c < getNumColumns(); c++) {
                if (r == getNumRows()) {
                    // header line of output
                    if(c > 9){ output += c + "|"; }
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
