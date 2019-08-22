/**
 * @autrhor Alexey Goncharenko
 * That's simple realization of TicTacToe Game
 */

public class MainClass {
    public static void main(String[] args) {
        HumanPlayer humanPlayer = new HumanPlayer();
        AIPlayer aiPlayer = new AIPlayer();
        TTTGameMap gameMap = new TTTGameMap(3, humanPlayer, aiPlayer);
        TTTGameWnd gameWnd = new TTTGameWnd(gameMap);
        gameMap.initField();
        gameMap.printField();
    }
}
