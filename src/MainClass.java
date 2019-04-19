/**
 * Created by Alexey Goncharenko
 * TicTacToe Game
 */

public class MainClass {
    public static void main(String[] args) {
        HumanPlayer player = new HumanPlayer();
        AIPlayer aiPlayer = new AIPlayer();
        TTTGameMap gameMap = new TTTGameMap(3, player, aiPlayer);
        TTTGameWnd tttWnd = new TTTGameWnd(gameMap);

        gameMap.initField();
        gameMap.printField();
    }
}
