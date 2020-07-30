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
    private final JFrame f;
    private final JButton b;
    private final JLabel askLabel;
    private final JTextField textfield;


    public Input() {
        f = new JFrame("Chess answer dialog");
        b = new JButton("Submit");
        b.setBounds(180,95,95, 32);

        askLabel = new JLabel();
        askLabel.setBounds(10, -18, 150, 100);

        postLabel = new JLabel();
        postLabel.setBounds(10, 110, 200, 100);

        textfield = new JTextField();
        textfield.setBounds(25, 50, 250, 35);

        f.add(postLabel);
        f.add(textfield);
        f.add(askLabel);
        f.add(b);
        f.setSize(320,175);
        f.setLayout(null);
        f.setVisible(true);
    }

    public String getUserName() throws InterruptedException {
        askLabel.setText("Enter your name: ");

        return getAnswer();
    }

    public Color getColor() throws InterruptedException {
        askLabel.setText("Choose play color (white/black/smth else random): ");
        String answer = getAnswer();

        return (answer.equals("white")) ? neointernship.chess.game.model.enums.Color.WHITE :
                neointernship.chess.game.model.enums.Color.BLACK;
    }

    public String getMoveAnswer() throws InterruptedException {
        askLabel.setText("Your move: ");

        return getAnswer();
    }

    public PlayerType getPlayerType() {
        return PlayerType.HUMAN;
    }

    private String getAnswer() throws InterruptedException {
        textfield.revalidate();
        textfield.repaint();
        textfield.setVisible(true);
        b.revalidate();
        b.setVisible(true);

        final String[] answer = new String[1];
        List<Integer> holder = new LinkedList<Integer>();

        b.addActionListener(new ActionListener() {
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
        b.validate();
        b.setVisible(false);

        return answer[0];
    }
}
