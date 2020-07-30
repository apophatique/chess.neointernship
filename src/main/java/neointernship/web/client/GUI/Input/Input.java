package neointernship.web.client.GUI.Input;

import neointernship.chess.game.model.enums.Color;
import neointernship.web.client.player.PlayerType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class Input {
    private final JLabel postLabel;
    private final JFrame frame;
    /**
     * кнопка
     */
    private final JButton button;
    /**
     * выводит сообщение
     */
    private final JLabel askLabel;
    /**
     * считывает сообщение
     */
    private final JTextField textfield;
    private String name = "name";


    public Input() {
        frame = new JFrame(name);
        button = new JButton("Submit");
        button.setBounds(180,95,95, 32);

        askLabel = new JLabel();
        askLabel.setBounds(10, -18, 250, 100);

        postLabel = new JLabel();
        postLabel.setBounds(10, 110, 200, 100);

        textfield = new JTextField();
        textfield.setBounds(25, 50, 250, 35);

        frame.add(postLabel);
        frame.add(textfield);
        frame.add(askLabel);
        frame.add(button);
        frame.setSize(320,175);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public String getUserName() throws InterruptedException {
        askLabel.setText("Enter your name: ");
        name = getAnswer();
        frame.setTitle(name);
        return name;
    }

    public Color getColor() throws InterruptedException {
        askLabel.setText("Выберите цвет");
        String answerColor = getAnswer();
        askLabel.setText("Ищем оппонента...");

       Color color = (answerColor.equals("white")) ? neointernship.chess.game.model.enums.Color.WHITE :
               neointernship.chess.game.model.enums.Color.BLACK;

        frame.setTitle(name + " " + color);
        return color;
    }

    public String getHandShakeAnswer() throws InterruptedException {
        askLabel.setText("Оппонент найден. Вы готовы?");

        return getAnswer();
    }
    public String getMoveAnswer() throws InterruptedException {
        askLabel.setText("Ваш ход");

        return getAnswer();
    }
    public String getTransformAnswer() throws InterruptedException {
        askLabel.setText("В какую фигуру обратить пешку?");

        return getAnswer();
    }

    public PlayerType getPlayerType() throws InterruptedException {
        askLabel.setText("Ho are you? ");
        String answerColor = getAnswer();

        return (answerColor.equals("human")) ? PlayerType.HUMAN :
               PlayerType.BOT;
    }

    private String getAnswer() throws InterruptedException {
        textfield.setText("");
        textfield.revalidate();
        textfield.repaint();
        textfield.setVisible(true);
        button.revalidate();
        button.setVisible(true);

        final String[] answer = new String[1];
        List<Integer> holder = new LinkedList<Integer>();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                synchronized (holder) {
                    holder.add(0);
                    holder.notify();
                }
                answer[0] = textfield.getText();
            }
        });
        synchronized (holder) {
            while (holder.isEmpty())
                holder.wait();
            holder.remove(0);
        }
        textfield.validate();
        textfield.setVisible(false);
        button.validate();
        button.setVisible(false);

        return answer[0];
    }
}
