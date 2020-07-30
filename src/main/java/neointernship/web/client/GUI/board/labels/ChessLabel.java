package neointernship.web.client.GUI.board.labels;

import javax.swing.*;
import java.awt.*;

public class ChessLabel extends JLabel {
    private final String s;

    Font font = new Font("Ariel", Font.PLAIN, 24);
    Color bgLight = new Color(222, 184, 135);
    Color bgDark = new Color(139, 69, 19);

    public ChessLabel(String s) {
        super(s);
        this.s = s;
    }

    public void set(int idx, int row) {
        setFont(font);
        setOpaque(true);
        setBackground((idx + row) % 2 == 0 ? bgDark : bgLight);
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    public String getS() {
        return s;
    }
}