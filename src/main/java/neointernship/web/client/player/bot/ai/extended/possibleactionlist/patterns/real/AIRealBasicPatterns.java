package neointernship.web.client.player.bot.ai.extended.possibleactionlist.patterns.real;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.moveaction.commands.allow.AllowMoveCommand;
import neointernship.chess.game.gameplay.moveaction.commands.allow.IAllowCommand;
import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;
import neointernship.web.client.player.bot.ai.extended.possibleactionlist.patterns.potential.move.Move;

import java.util.ArrayList;
import java.util.Collection;

public class AIRealBasicPatterns implements IRealBasicPatternsAI {
    private final IMediator mediator;
    private final IBoard board;
    private final IStoryGame storyGame;

    public AIRealBasicPatterns(final IMediator mediator,
                               final IBoard board,
                               final IStoryGame storyGame) {
        this.mediator = mediator;
        this.board = board;
        this.storyGame = storyGame;
    }

    @Override
    public Collection<Move> getRealMoveList(Figure figure, Collection<Move> potentialMoveList) {
        IAllowCommand command;
        final ArrayList<Move> realList = new ArrayList<>();
        final IField startField = mediator.getField(figure);
        final Color colorFigure = figure.getColor();
        final Color colorOpponent = Color.swapColor(colorFigure);

        for (Move finishField : potentialMoveList) {

            IMediator newMediator = new Mediator(mediator);
            IStoryGame newStoryGame = new StoryGame((StoryGame) storyGame);
            IPossibleActionList newPossibleActionList = new PossibleActionList(board, newMediator, newStoryGame);

            AllowMoveCommand allowMoveCommand =
                    new AllowMoveCommand(newMediator, board, newStoryGame);

            command = allowMoveCommand.getCommand(startField, finishField.getFieldToMove());
            IAnswer answer = new Answer(
                    startField.getXCoord(),
                    startField.getYCoord(),
                    finishField.getFieldToMove().getXCoord(),
                    finishField.getFieldToMove().getYCoord(),
                    'Q');

            command.execute(answer);

            newPossibleActionList.updatePotentialLists(colorOpponent);

            if (command.isCorrect(colorFigure, newPossibleActionList)) {
                realList.add(finishField);
            }
        }
        return realList;
    }
}
