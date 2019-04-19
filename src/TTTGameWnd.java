import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TTTGameWnd extends JFrame{

    public TTTGameWnd(TTTGameMap tttGameMap){
        setSize(507, 555); // задаём размеры окна
        setLocation(50, 50); // задаём нач.коориднаты лев.верх.угла окна
        setResizable(false); // создаём окно без возможности изменения размеров
        setTitle("TicTacToe"); // задём заголовок окна
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Задаём по умолчанию операцию закрытия окна

        /*
        * Реализовать взаимодействие с пользователем для установки желаемого размера игрвого поля
        * */

        JPanel jpBottom = new JPanel(new GridLayout(), true); // создаем поле с командными кнопками
        jpBottom.setBackground(new Color(255, 210, 0, 100));
        add(tttGameMap, BorderLayout.CENTER);
        add(jpBottom, BorderLayout.SOUTH);

        Icon jiNewGameIcon = new ImageIcon("newGame.ico");
        Icon jiExitGameIcon = new ImageIcon("exitGame.ico");

        JButton jbNewGame = new JButton("New Game", jiNewGameIcon);
        JButton jbExitGame = new JButton("Exit Game", jiExitGameIcon);

        jpBottom.add(jbNewGame);
        jpBottom.add(jbExitGame);

        jbExitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jbNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tttGameMap.initField();
            }
        });

        setVisible(true); // отображаем созданое окно
    }

}
