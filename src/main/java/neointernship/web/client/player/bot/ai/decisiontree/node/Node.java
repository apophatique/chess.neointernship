package neointernship.web.client.player.bot.ai.decisiontree.node;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.story.IStoryGame;

public class Node {
    private final IAnswer answerToGet;
    private final IMediator mediator;
    private final IBoard board;
    private final IStoryGame storyGame;
    private Node child;

    private final Color activeColor;

    public Node(final IAnswer answerToGet,
                final IMediator mediator,
                final IBoard board,
                final IStoryGame storyGame,
                final Color activeColor) {
        this.answerToGet = answerToGet;
        this.mediator = mediator;
        this.board = board;
        this.storyGame = storyGame;
        this.child = null;
        this.activeColor = activeColor;
    }

    public IBoard getBoard() {
        return board;
    }

    public IStoryGame getStoryGame() {
        return storyGame;
    }

    public IMediator getMediator() {
        return mediator;
    }

    public IAnswer getAnswerToCome() {
        return answerToGet;
    }

    public Node getChild() {
        return child;
    }

    public void setChild(final Node node) {
        child = node;
    }

    public Color getActiveColor() {
        return activeColor;
    }
}
