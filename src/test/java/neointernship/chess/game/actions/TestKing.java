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

public class TestKing {
    private static Map<Figure, IField> map;

    @BeforeClass
    public static void before() {
        map = new HashMap<>();
    }

    @After
    public void clear() {
        map.clear();
    }

    private void put(final Figure figure, final IField field) {
        map.put(figure, field);
    }

    /**
     * корректная рокировка
     */
    @Test
    public void testFreeCastling() {
        final Figure king = new King(Color.WHITE);
        final IField fieldKing = new Field(7, 4);

        final Figure rook = new Rook(Color.WHITE);
        final IField fieldRook = new Field(7, 7);

        Field[] expected = {
                new Field(7, 3), new Field(6, 3), new Field(6, 4),
                new Field(6, 5), new Field(7, 5), new Field(7, 6)};

        put(king, fieldKing);
        put(rook, fieldRook);

        checkPosition(map, king, expected);
    }

    /**
     * запрет рокировки в связи с тем, что на пути короля к ладье есть помеха
     */
    @Test
    public void testCloseCastling() {
        final Figure king = new King(Color.WHITE);
        final IField fieldKing = new Field(7, 4);

        final Figure rook = new Rook(Color.WHITE);
        final IField fieldRook = new Field(7, 7);


        final Figure bishop = new Bishop(Color.BLACK);
        final IField fieldBishop = new Field(7, 5);

        Field[] expected = {
                new Field(7, 3), new Field(6, 3), new Field(6, 5), new Field(7, 5)};

        put(king, fieldKing);
        put(rook, fieldRook);
        put(bishop, fieldBishop);

        checkPosition(map, king, expected);
    }

    /**
     * запрет рокировки в связи с атакой поля перемещения короля
     */
    @Test
    public void testAttackPathCastling() {
        final Figure king = new King(Color.WHITE);
        final IField fieldKing = new Field(7, 4);

        final Figure rook = new Rook(Color.WHITE);
        final IField fieldRook = new Field(7, 7);


        final Figure bishop = new Bishop(Color.BLACK);
        final IField fieldBishop = new Field(5, 7);

        Field[] expected = {
                new Field(7, 3), new Field(6, 3), new Field(6, 4), new Field(6, 5)};

        put(king, fieldKing);
        put(rook, fieldRook);
        put(bishop, fieldBishop);

        checkPosition(map, king, expected);
    }

    /**
     * проверка ситуации, когда у королю нет ходов
     */
    @Test
    public void testNothingMove() {
        final Figure king = new King(Color.WHITE);
        final IField fieldKing = new Field(7, 4);

        final Figure rook1 = new Rook(Color.BLACK);
        final IField fieldRook1 = new Field(7, 7);

        final Figure rook2 = new Rook(Color.BLACK);
        final IField fieldRook2 = new Field(6, 0);

        Field[] expected = {};

        put(king, fieldKing);
        put(rook1, fieldRook1);
        put(rook2, fieldRook2);

        checkPosition(map, king, expected);
    }

    /**
     * тест на то, что короли не могут быть близко (
     */
    @Test
    public void testTwoKing() {
        final Figure kingW = new King(Color.WHITE);
        final IField fieldKingW = new Field(7, 4);

        final Figure kingB = new King(Color.BLACK);
        final IField fieldKingB = new Field(5, 4);

        Field[] expected = {
                new Field(7, 3), new Field(7, 5)};

        put(kingW, fieldKingW);
        put(kingB, fieldKingB);

        checkPosition(map, kingW, expected);
    }

    /**
     * связка короля
     */
    @Test
    public void testTieUp() {
        final Figure king = new King(Color.WHITE);
        final IField fieldKing = new Field(6, 7);

        final Figure pawn = new Pawn(Color.WHITE);
        final IField fieldPawn = new Field(6, 6);

        final Figure rookB = new Rook(Color.BLACK);
        final IField fieldRookB = new Field(6, 0);

        Field[] expected = {};

        put(king, fieldKing);
        put(pawn, fieldPawn);
        put(rookB, fieldRookB);

        checkPosition(map, pawn, expected);
    }
}
