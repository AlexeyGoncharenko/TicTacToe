public class HumanPlayer extends Player{
    public int x;
    public int y;

    public boolean turn(TTTGameMap gameMap, int x, int y) {
        this.x = x;
        this.y = y;
        if(gameMap.isCellEmpty(x,y)) {
            gameMap.setDot(x, y, gameMap.PLAYER_DOT);
            return true;
        }
        return false;
    }
}
