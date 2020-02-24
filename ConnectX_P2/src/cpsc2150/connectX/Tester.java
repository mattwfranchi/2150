package cpsc2150.connectX;

public class Tester {
    public static void main(String[] args){
        GameScreen gameScreen = new GameScreen();
        int endFlag = 0;
        do{
            endFlag = gameScreen.haveTurn();
        } while(endFlag == 0);
    }
}
