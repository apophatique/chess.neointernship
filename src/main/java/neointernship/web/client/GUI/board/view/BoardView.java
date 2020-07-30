package neointernship.web.client.GUI.board.view;

import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.GUI.board.labels.ChessLabel;
import neointernship.web.client.GUI.board.labels.labelsgetter.LabelsRepository;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JFrame {
    private final Board board;
    private final Mediator mediator;

    private final LabelsRepository labelsRepository;

    private ChessLabel[][] labels;
    Container contentPane = getContentPane();


    public BoardView(final Mediator mediator, final Board board) {
        this.mediator = mediator;
        this.board = board;

        labelsRepository = new LabelsRepository();
        labels = new ChessLabel[board.getSize()][board.getSize()];

        startUpdate();
    }

    public void display() throws InterruptedException {
        setTitle("Chess board");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GridLayout gridLayout = new GridLayout(8, 8);

        contentPane.setLayout(gridLayout);
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                labels[i][j].set(i, j);
                contentPane.add(labels[i][j]);
            }
        }

        setLocationRelativeTo(null);
        setSize(800, 768);
        setVisible(true);
    }

    public void update() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                getContentPane().remove(labels[i][j]);


                IField field = board.getField(i, j);
                Figure figure = mediator.getFigure(field);


                String label = " ";
                if (figure != null) {
                    label = labelsRepository.getLabel(figure.getColor(), figure.getGameSymbol());
                }
                labels[i][j] = new ChessLabel(label);


                labels[i][j].set(i, j);
                contentPane.add(labels[i][j]);
            }
        }
    }

    private void startUpdate() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                IField field = board.getField(i, j);
                Figure figure = mediator.getFigure(field);

                String label = " ";
                if (figure != null) {
                    label = labelsRepository.getLabel(figure.getColor(), figure.getGameSymbol());
                }
                labels[i][j] = new ChessLabel(label);


                labels[i][j].set(i, j);
                contentPane.add(labels[i][j]);
            }
        }
    }
}
