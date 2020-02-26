package cpsc2150.connectX;

public class Tester {
    public static void main(String[] args) {
        GameScreen gameScreen = new GameScreen();
        int endFlag = gameScreen.haveTurn();
        while(endFlag == 0){
            endFlag = gameScreen.haveTurn();
            if(endFlag != 0){
                endFlag = gameScreen.endgameSequence(endFlag);
            }
        }
    }
}