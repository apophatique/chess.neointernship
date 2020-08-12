package neointernship.web.client.player.bot.ai.decisiontree.base;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.story.IStoryGame;

import java.util.List;

public class Tree {
    private final Node root;

    public Tree(final IAnswer answerToGet,
                final IMediator mediator,
                final IBoard board,
                final IStoryGame storyGame,
                final Color rootColor) {
        root = new Node(answerToGet, mediator, board, storyGame, rootColor);
    }

    public Node getRoot() {
        return root;
    }
}
