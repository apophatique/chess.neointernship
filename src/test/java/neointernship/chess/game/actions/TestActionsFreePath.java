package neointernship.chess.game.actions;


import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.*;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static neointernship.chess.game.actions.TestStaticMethod.checkPosition;


public class TestActionsFreePath {
    private static Map<Figure, IField> map;

    @BeforeClass
    public static void before() {
        map = new HashMap<>();
    }

    @After
    public void clear() {
        map.clear();
    }

    private void put(Figure figure, IField field) {
        map.put(figure, field);
    }

    @Test
    public void testKingCenter() {
        Figure figure = new King(Color.BLACK);
        IField field = new Field(2, 2);


        Field[] expected = {
                new Field(1, 1), new Field(1, 2), new Field(1, 3),
                new Field(2, 1), new Field(2, 3),
                new Field(3, 1), new Field(3, 2), new Field(3, 3)
        };
        put(figure, field);
        checkPosition(map, figure, expected);

    }

    @Test
    public void testBishop() {
        Figure figure = new Bishop(Color.BLACK);
        IField field = new Field(0, 0);

        Field[] expected = {
                new Field(1, 1), new Field(2, 2), new Field(3, 3),
                new Field(4, 4), new Field(5, 5), new Field(6, 6),
                new Field(7, 7)
        };

        put(figure, field);
        checkPosition(map, figure, expected);

    }

    @Test
    public void testRook() {
        Figure figure = new Rook(Color.BLACK);
        IField field = new Field(4, 4);

        Field[] expected = {
                new Field(4, 0), new Field(4, 1), new Field(4, 2), new Field(4, 3),
                new Field(4, 5), new Field(4, 6), new Field(4, 7),

                new Field(0, 4), new Field(1, 4), new Field(2, 4),
                new Field(3, 4), new Field(5, 4), new Field(6, 4), new Field(7, 4)
        };

        put(figure, field);
        checkPosition(map, figure, expected);
    }

    @Test
    public void testPawnWhiteBegin() {
        Figure figure = new Pawn(Color.WHITE);
        IField field = new Field(6, 4);

        Field[] expected = {
                new Field(5, 4), new Field(4, 4)
        };

        put(figure, field);
        checkPosition(map, figure, expected);
    }

    @Test
    public void testPawnBlackBegin() {
        Figure figure = new Pawn(Color.BLACK);
        IField field = new Field(1, 4);

        Field[] expected = {
                new Field(2, 4), new Field(3, 4)
        };

        put(figure, field);
        checkPosition(map, figure, expected);
    }

    @Test
    public void testPawnBlackCenter() {
        Figure figure = new Pawn(Color.BLACK);
        IField field = new Field(3, 4);

        Field[] expected = {
                new Field(4, 4)
        };

        put(figure, field);
        checkPosition(map, figure, expected);
    }

    @Test
    public void testPawnWhiteCenter() {
        Figure figure = new Pawn(Color.WHITE);
        IField field = new Field(4, 4);

        Field[] expected = {
                new Field(3, 4)
        };

        put(figure, field);
        checkPosition(map, figure, expected);
    }

    @Test
    public void testPawnWhiteEnd() {
        Figure figure = new Pawn(Color.WHITE);
        IField field = new Field(1, 4);

        Field[] expected = {
                new Field(0, 4)
        };

        put(figure, field);
        checkPosition(map, figure, expected);
    }

    @Test
    public void testPawnBlackEnd() {
        Figure figure = new Pawn(Color.BLACK);
        IField field = new Field(6, 4);

        Field[] expected = {
                new Field(7, 4)
        };

        put(figure, field);
        checkPosition(map, figure, expected);
    }

    @Test
    public void testKnight() {
        Figure figure = new Knight(Color.WHITE);
        IField field = new Field(0, 2);

        Field[] expected = {
                new Field(1, 0), new Field(1, 4), new Field(2, 1), new Field(2, 3)
        };

        put(figure, field);
        checkPosition(map, figure, expected);
    }

}
