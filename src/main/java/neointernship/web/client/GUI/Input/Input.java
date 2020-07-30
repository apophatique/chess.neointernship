package neointernship.web.client.GUI.Input;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.web.client.player.PlayerType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    private String name = "имя";
    private PlayerType type;


    public Input() {
        frame = new JFrame(name);
        button = new JButton("Ввод");
        button.setBounds(180, 95, 95, 32);

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
        frame.setBounds(500,250,320,175);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public String getUserName() throws InterruptedException {
        askLabel.setText("Введите ваше имя: ");
        name = getAnswer();
        frame.setTitle(name);
        return name;
    }

    public Color getColor() throws InterruptedException {
        Map<String,Color> colorMap = new HashMap<>();
        colorMap.put("белые",Color.WHITE);
        colorMap.put("черные",Color.BLACK);
        colorMap.put("любой",Color.BOTH);

        askLabel.setText("Выберите цвет");

        String answerColor = getAnswer();

        while (!colorMap.containsKey(answerColor)){
            askLabel.setText("белые / черные / любой");
            answerColor = getAnswer();
        };

        Color color = colorMap.get(answerColor);

        askLabel.setText("Ищем оппонента...");
        frame.setTitle(name + " " + color.getMessage());
        return color;
    }

    public String getHandShakeAnswer() throws InterruptedException {
        if(type == PlayerType.BOT){
            askLabel.setText("я играю не мешайте)");
        }else{
            askLabel.setText("Оппонент найден. Вы готовы?");
            return getAnswer();
        }
        return "";
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
        Map<String,PlayerType> typeMap = new HashMap<>();
        typeMap.put("человек",PlayerType.HUMAN);
        typeMap.put("бот",PlayerType.BOT);
        askLabel.setText("Кто ты?");
        String answerType = getAnswer();

        while (!typeMap.containsKey(answerType)) {
            askLabel.setText("человек / бот ?");
            answerType = getAnswer();
        }

        type = typeMap.get(answerType);

        if(type == PlayerType.BOT){
            askLabel.setText("Я жду противника...");
        }

        return type;
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

    public void endGame(final EnumGameState enumGameState,final Color color){
        if(enumGameState == EnumGameState.MATE){
            askLabel.setText("Конец игры победа " + color.getMessage());
        }else {
            askLabel.setText(enumGameState.getMessage());
        }
    }
}
