package neointernship.web.client.GUI.input;

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

public class Input implements IInput {
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
    private String name = "Name";
    private PlayerType type;


    public Input() {
        frame = new JFrame(name);
        button = new JButton("Enter");
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
        frame.setBounds(500, 250, 320, 175);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public String getUserName() throws InterruptedException {
        askLabel.setText("Your name: ");
        name = getAnswer();
        frame.setTitle(name);
        return name;
    }

    public Color getColor() throws InterruptedException {
        Map<String, Color> colorMap = new HashMap<>();
        colorMap.put("white", Color.WHITE);
        colorMap.put("black", Color.BLACK);

        askLabel.setText("white / black or press any to choose random");

        String answerColor = getAnswer();
        Color color = colorMap.getOrDefault(answerColor, Color.BOTH);

        askLabel.setText("Looking for opponent...");
        frame.setTitle(name + " " + color.getMessage());

        return color;
    }

    public String getHandShakeAnswer() throws InterruptedException {
        if (type != PlayerType.HUMAN) {
            askLabel.setText("Game is on");
        } else {
            askLabel.setText("Input 'yes' if ready");
            return getAnswer();
        }
        return "";
    }

    public void invise() {
        frame.setVisible(false);
    }

    public String getMoveAnswer() throws InterruptedException {
        askLabel.setText("Your turn");

        return getAnswer();
    }

    public String getTransformAnswer() throws InterruptedException {
        askLabel.setText("Choose figure for transforming");

        return getAnswer();
    }

    public PlayerType getPlayerType() throws InterruptedException {
        Map<String, PlayerType> typeMap = new HashMap<>();
        typeMap.put("human", PlayerType.HUMAN);
        typeMap.put("ai", PlayerType.AI_BOT);
        typeMap.put("random", PlayerType.RANDOM_BOT);
        askLabel.setText("Who are you? (ai/human/random)");
        String answerType = getAnswer();

        while (!typeMap.containsKey(answerType)) {
            answerType = getAnswer();
        }

        type = typeMap.get(answerType);

        if (type == PlayerType.AI_BOT || type == PlayerType.RANDOM_BOT) {
            askLabel.setText("Looking for opponent");
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

    public void endGame(final EnumGameState enumGameState, final Color color) throws InterruptedException {
        if (enumGameState == EnumGameState.MATE) {
            askLabel.setText("Mate. Winner is " + Color.swapColor(color).getMessage());
        } else {
            if (enumGameState == EnumGameState.RESIGNATION) {
                askLabel.setText("Resignation. Winnter is " + Color.swapColor(color).getMessage());
            } else {
                askLabel.setText(enumGameState.getMessage());
            }
        }
        button.revalidate();
        button.setVisible(true);
        button.setText("Exit");

        List<Integer> holder = new LinkedList<Integer>();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                synchronized (holder) {
                    holder.add(0);
                    holder.notify();
                }
            }
        });
        synchronized (holder) {
            while (holder.isEmpty())
                holder.wait();
            holder.remove(0);
        }

        button.validate();
        frame.dispose();
    }

    @Override
    public boolean isVoid() {
        return false;
    }
}
