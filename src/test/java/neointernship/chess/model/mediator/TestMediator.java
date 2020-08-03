package neointernship.chess.model.mediator;

import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Bishop;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.figure.piece.Queen;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import org.junit.*;

import java.util.Collection;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TestMediator {

    private static Mediator mediator;
    private static IField fieldB;
    private static IField fieldQ;
    private static Figure figureB;
    private static Figure figureQ;

    @Before
    public void init(){
        mediator = new Mediator();

        fieldB = new Field(0,0);
        fieldQ = new Field(5,5);

        figureB = new Bishop(Color.BLACK);
        figureQ = new Queen(Color.WHITE);

        mediator.addNewConnection(fieldB,figureB);
        mediator.addNewConnection(fieldQ,figureQ);
    }

    @After
    public void clear(){
        mediator.clear();
    }

    @Test
    public void testGetFigure(){
        Figure result = mediator.getFigure(fieldB);

        assertEquals(figureB,result);
    }
    @Test
    public void testGetFigures(){
        Collection<Figure> figures = mediator.getFigures();

        assertEquals(figures.size(),2);
        assertTrue(figures.contains(figureB));
        assertTrue(figures.contains(figureQ));
    }
    @Test
    public void testGetField(){
        IField result = mediator.getField(figureQ);

        assertEquals(fieldQ,result);
    }
    @Test
    public void testGetKing(){
        Color color = Color.WHITE;
        King king = new King(color);
        IField fieldK = new Field(1,1);

        mediator.addNewConnection(fieldK,king);

        Figure resultFigure = mediator.getKing(color);
        IField resultField = mediator.getField(resultFigure);

        assertEquals(resultFigure,king);
        assertEquals(resultField,fieldK);

    }
    @Test
    public void testConnections(){
        // срубаю ферзем слона
        mediator.deleteConnection(fieldB);
        mediator.addNewConnection(fieldB,figureQ);
        mediator.deleteConnection(fieldQ);

        assertEquals(1,mediator.getFigures().size());
        assertEquals(1,mediator.getFigures(Color.WHITE).size());
        assertEquals(0,mediator.getFigures(Color.BLACK).size());
    }

    @Test
    public void testEqualsMediator(){

        HashMap<Position,Integer> map = new HashMap<>();


        Mediator newMediator = new Mediator(mediator);
        assertEquals(mediator, newMediator);

        Position position = new Position(mediator);

        assertNull(map.get(position));
        map.put(position,1);

        Position newPosition = new Position(newMediator);
        assertNotNull(map.get(newPosition));
        map.put(newPosition,2);

        assertEquals(position,newPosition);

        newMediator.deleteConnection(fieldQ);
        newMediator.addNewConnection(fieldQ,figureQ);

        Position copyPosition = new Position(newMediator);


        assertEquals(mediator, newMediator);
        assertEquals(position,copyPosition);

        assertNotNull(map.get(copyPosition));

        assertEquals(1,map.size());

    }
    @Test
    public void testEqualsPosition(){

    }
}

