public class AIPlayer extends Player {
    public int x;
    public int y;

    public boolean turn(TTTGameMap gameMap) {

        int x = -1,y = -1; // AI вначале хода не знает куда ходить
        // AI определяет свою близжайщую победную комбинацию
        for (int iy = 0; iy < gameMap.linesCount && (x == -1 && y == -1); iy++) {
            for (int jx = 0; jx < gameMap.linesCount; jx++) {
                if(gameMap.isCellEmpty(jx, iy)){
                    gameMap.setDot(jx,iy,gameMap.AI_DOT);
                    if(gameMap.checkWin(gameMap.AI_DOT)) {
                        x = jx;
                        y = iy;
                        return true;
                    }else
                        gameMap.setDot(jx,iy,gameMap.EMPTY_DOT);
                }
            }
        }

        // Если AI не определил своей победной комбинации, он ищет победную комбинацию PLAYER и блокирует её
        for (int iy = 0; iy < gameMap.linesCount && (x == -1 && y == -1); iy++) { // (x == -1 && y == -1) - ограничивает запуск этого цикла, если AI нашёл свою победную комибнацию, тогда этоту проверку не выполняем
            for (int jx = 0; jx < gameMap.linesCount; jx++) {
                if(gameMap.isCellEmpty(jx, iy)){
                    gameMap.setDot(jx,iy,gameMap.PLAYER_DOT);
                    if(gameMap.checkWin(gameMap.PLAYER_DOT)) {
                        x = jx;
                        y = iy;
                        gameMap.setDot(jx,iy, gameMap.AI_DOT);
                        return true;
                    }else
                        gameMap.setDot(jx,iy,gameMap.EMPTY_DOT);
                }
            }
        }

        // если подходящий ход не найден, AI ходит на угад.
        if(x == -1 && y == -1) { // (x == -1 && y == -1) - ограничивает запуск этого цикла, если AI нашёл свою победную комибнацию || победную комбинацию PLAYER, тогда этоту проверку не выполняем
            do {
                x = gameMap.randAITurn.nextInt(3); // генерируем значения в диапазоне [0..2]
                y = gameMap.randAITurn.nextInt(3);
            } while (!gameMap.isCellEmpty(x, y));
            gameMap.setDot(x,y,gameMap.AI_DOT);
        }
        return true;
    }
}
