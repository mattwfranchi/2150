package cpsc2150.connectX;

public class Tester {
    public static void main(String[] args){
        GameScreen gameScreen = new GameScreen();
        boolean endFlag;    `
        do{
            endFlag = gameScreen.haveTurn();
        } while(endFlag == 0);

        if(endFlag > 0){ gameScreen.endgameSequence(endFlag); }

    }
}
