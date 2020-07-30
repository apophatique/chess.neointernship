package neointernship.chess.logger;


import neointernship.chess.game.gameplay.gamestate.controller.draw.IDrawController;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.communication.message.TurnStatus;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.IOException;
import java.util.HashMap;


public class GameLogger implements IGameLogger{
    private static final HashMap<Integer, IGameLogger> mapLogger = new HashMap<>();
    private final Logger logger;

    private GameLogger(final int lobbyId) {
        logger = Logger.getLogger(Integer.toString(lobbyId));

        try {
            final PatternLayout patternLayout = new PatternLayout();
            patternLayout.setConversionPattern("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
            logger.addAppender(new FileAppender(patternLayout, "logs\\server\\game_log_" + lobbyId + ".txt", false));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public static void addLogger(final int lobbuId) {
        mapLogger.put(lobbuId, new GameLogger(lobbuId));
    }

    public static IGameLogger getLogger(final int lobbyId) {
        return mapLogger.get(lobbyId);
    }

    @Override
    public void logStartGame(final String firstPlayerName, final String secondPlayerName) {
        logger.info("Игра между игроком " + firstPlayerName + " и игроком " + secondPlayerName + " началась!");
    }

    @Override
    public void logPlayerMoveAction(final Color color, final Figure figure,
                                    final IField startField, final IField finalField, final TurnStatus chessCodes) {
        logger.info(chessCodes.getMessage() + "- Игрок " + color + " сделал ход фигурой " + figure.getName() + " из клетки " +
                startField.showField() + " в клетку " + finalField.showField());
    }

    @Override
    public void logEndGame(final EnumGameState enumGameState) {
        logger.info("Конец игры - " + enumGameState.getMessage());
    }

    @Override
    public void logPlayerWrongAction(final Color color, final IField field) {
        logger.warn("Игрок " + color + " попыталя сделать ход из клетки " + field.showField() + ", но он невозможен");
    }

    @Override
    public void logPlayerWin(final Color color) {
        logger.info(color + " игрок победил!");
    }

    @Override
    public void logStalemate() {
        logger.info("Пат!");
    }

    @Override
    public void logDraw(final IDrawController drawController ) {
        logger.info("Ничья : " + drawController.getState().getMessage());
    }

}
