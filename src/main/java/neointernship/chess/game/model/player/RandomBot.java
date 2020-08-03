package neointernship.chess.game.model.player;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.List;
import java.util.Random;

public class RandomBot extends Player{

    Random random;

    public RandomBot(Color color) {
        super("Random bot",color);
        random = new Random();
    }

    @Override
    public IAnswer getAnswer(IMediator mediator, IPossibleActionList list) {
        List<Figure> figures = (List<Figure>) mediator.getFigures(getColor());
        List<IField> fields;
        Figure figure;
        int index;

        do {
            index = random.nextInt(figures.size());
            figure = figures.get(index);
            fields = (List<IField>) list.getRealList(figure);
        } while (fields.isEmpty());

        index = random.nextInt(fields.size());
        IField finalField = fields.get(index);

        IField startField = mediator.getField(figure);


//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        IAnswer answer = new Answer(startField.getXCoord(), startField.getYCoord(),
                finalField.getXCoord(), finalField.getYCoord(),'Q');
        printAnswer(startField,finalField);
        return answer;
    }

    private void printAnswer(final IField start,final IField finish){
        System.out.println("Color = " + super.getColor());
        System.out.println(start.showField() + " - " + finish.showField());
    }
}
