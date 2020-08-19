package neointernship.web.client.player.bot.ai.decisiontree.base;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.story.IStoryGame;
import neointernship.web.client.player.bot.ai.possibleactionlist.IAIPossibleActionList;

public class Node {
    private final IMediator mediator;
    private final IBoard board;
    private final IStoryGame storyGame;

    private final IAnswer answerToGet;
    private Node child;

    private final Color activeColor;

    public Node(final IAnswer answerToGet,
                final IMediator mediator,
                final IBoard board,
                final IStoryGame storyGame,
                final Color activeColor) {
        this.mediator = mediator;
        this.board = board;
        this.storyGame = storyGame;

        this.answerToGet = answerToGet;
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

    public IAnswer getAnswerToGet() {
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
