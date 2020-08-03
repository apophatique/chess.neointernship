package neointernship.chess.game.gameplay.moveaction.commands.allow;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.communication.message.TurnStatus;

public interface IAllowCommand {
    void execute(IAnswer answer);
    boolean check(final IField startField,final IField finishField);
    TurnStatus getTurnStatus();

    boolean isCorrect(final Color colorFigure, final IPossibleActionList possibleActionList);
}
