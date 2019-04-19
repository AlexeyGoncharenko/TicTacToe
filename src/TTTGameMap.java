import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class TTTGameMap extends JPanel{

    private final int PANEL_SIZE = 500; // задаём фиксированный размер игрового поля
    private int cellSize; // размер ячейки игрвого поля
    public int linesCount; // размер игрвого поля (rows=cols)
    public final char PLAYER_DOT = 'X';
    public final char AI_DOT = 'O';
    public final char EMPTY_DOT = '*';
    private char[][] gameField;
    private int lengthWinCombination = 3; // длина серии для победы
    public Random randAITurn = new Random();
    private boolean playerTurned = false;
    private boolean aiTurned = false;
    private int xHum, yHum;
    private int xAi, yAi;

    // конструктор с параметрами
    public TTTGameMap(int linesCount, HumanPlayer player, AIPlayer aiPlayer){
        this.linesCount = linesCount;
        cellSize = PANEL_SIZE/linesCount;
        setBackground(Color.white);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("X:" + e.getX() + " Y:" + e.getY() + " Cell(" + e.getX()/cellSize + "," + e.getY()/cellSize + ")");

                if(player.turn(TTTGameMap.this, e.getX(), e.getY())) {
                    playerTurned = true;
                    xHum = e.getX(); yHum = e.getY();
                    repaint();
                    printField();
                }

                if (checkWin(PLAYER_DOT)) {
                    System.out.println("PLAYER IS WIN!");
                    initField();
                }
                if (isFieldFull()) {
                    System.out.println("NOBODY IS WIN!");
                    initField();
                }

                if(aiPlayer.turn(TTTGameMap.this)){
                    playerTurned = true;
                    xAi = aiPlayer.x; yAi = aiPlayer.y;
                    repaint();
                    printField();
                }

                if (checkWin(AI_DOT)) {
                    System.out.println("AI IS WIN!");
                    initField();
                }
                if (isFieldFull()) {
                    System.out.println("NOBODY IS WIN!");
                    initField();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for (int i = 0; i <= linesCount; i++) {
            g.drawLine(0, i*cellSize, PANEL_SIZE, i*cellSize);
            g.drawLine(i*cellSize, 0, i*cellSize, PANEL_SIZE);
        }
        if(playerTurned) {
            g.setColor(Color.RED);
            g.fillOval( yHum, xHum, cellSize - 4, cellSize -4);
            playerTurned = false;
        }
        if (aiTurned){
            g.setColor(Color.GREEN);
            g.fillOval(xAi, yAi, 50, 50);
            aiTurned = false;
        }

    }


    public void initField() {
        /*boolean outputFlag = true;
        while(outputFlag) {
            System.out.print("Введите размер игрового поля: 3 5 10: "); // доступный размер поля 3 5 10
            sizeField = sc.nextInt();
            switch (sizeField ){
                case 3:
                    lengthWinCombination = sizeField;
                    outputFlag = false;
                    break;
                case 5:
                    System.out.print("Введите длину серии для победы(" + " в пределах от " + ((sizeField/2) + 1) + " до " + sizeField + "): ");
                    lengthWinCombination = sc.nextInt();
                    if (lengthWinCombination >= (sizeField/2)+1 && lengthWinCombination <= sizeField)
                        outputFlag = false;
                    else
                        lengthWinCombination = 0;
                    break;
                case 10:
                    System.out.print("Введите длину серии для победы(" + " в пределах от " + ((sizeField/2)+1) + " до " + sizeField + "): ");
                    lengthWinCombination = sc.nextInt();
                    if (lengthWinCombination >= (sizeField/2)+1 && lengthWinCombination <= sizeField)
                        outputFlag = false;
                    else
                        lengthWinCombination = 0;
                    break;
                default:
                    System.out.println("Повторите ввод...");
            }
        }*/

        gameField = new char[linesCount][linesCount];
        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < linesCount; j++) {
                gameField[i][j] = EMPTY_DOT;
            }
        }
    }

    public void printField() {
        System.out.print(" ");
        for (int i = 0; i < linesCount; i++)
            System.out.print(" " + (i+1));
        System.out.println();

        for (int i = 0; i < linesCount; i++) {
            System.out.print((i+1) + " ");
            for (int j = 0; j < linesCount; j++) {
                System.out.print(gameField[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean isFieldFull(){
        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < linesCount; j++)
                if (gameField[i][j] == EMPTY_DOT) return false;
        }
        return true;
    }

    public boolean isCellEmpty(int x, int y){
        if (x < 0 || y < 0 || x > linesCount || y > linesCount)
            return false;
        if (gameField[y][x] != EMPTY_DOT)
            return false;
        return true;
    }

    public void setDot(int x, int y, char charDot){
        gameField[y][x] = charDot;
    }


    public boolean checkLine(int x, int y, int vx, int vy, int length, char charDot) {
        for (int i = 0; i < length; i++) {
            if (gameField[y + i * vy][x + i * vx] != charDot) return false;
        }
        return true;
    }

    public boolean checkWin(char charDot) {
        for (int i = 0; i < linesCount; i++) {
            if (checkLine(0, i, 1, 0, lengthWinCombination, charDot)) return true;
            if (checkLine(i, 0, 0, 1, lengthWinCombination, charDot)) return true;
        }
        // проверка по диагоналям
        if (checkLine(0, 0, 1, 1, lengthWinCombination, charDot)) return true;
        if (checkLine(0, 2, 1, -1, lengthWinCombination, charDot)) return true;
        return false;
    }
}
