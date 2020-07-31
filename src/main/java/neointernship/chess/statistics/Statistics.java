package neointernship.chess.statistics;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumMatchResult;

import java.io.*;

public class Statistics {
    private static final File file = new File("statistics.txt");

    private Statistics() {
    }

    public synchronized static void setStatistics(final String nameFirstPlayer,
                                     final Color colorFirstPlayer,
                                     final EnumMatchResult matchResultFirstPlayer,
                                     final String nameSecondPlayer,
                                     final Color colorSecondPlayer,
                                     final EnumMatchResult matchResultSecondPlayer) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }

        final BufferedReader in = new BufferedReader(new FileReader(file));
        final BufferedWriter out;

        String fileString = "";
        String string;
        while ((string = in.readLine()) != null) fileString += string + "\r\n";

        if (!fileString.equals("")) PersonsStatistics.setPersonsStatistics(fileString);


        if (!PersonsStatistics.getPersonStatistics().containsKey(nameFirstPlayer)) {
            PersonsStatistics.addPerson(nameFirstPlayer);
        }

        if (!PersonsStatistics.getPersonStatistics().containsKey(nameSecondPlayer)) {
            PersonsStatistics.addPerson(nameSecondPlayer);
        }

        incMatchResult(nameFirstPlayer, colorFirstPlayer, matchResultFirstPlayer);
        incMatchResult(nameSecondPlayer, colorSecondPlayer, matchResultSecondPlayer);

        out = new BufferedWriter(new FileWriter(file.getName()));
        out.write(PersonsStatistics.toStringStatic());
        out.flush();

        in.close();
        out.close();
    }

    private static void incMatchResult(final String name, final Color color, final EnumMatchResult matchResult) {
        switch (matchResult) {
            case WIN:
                if (color == Color.WHITE) {
                    PersonsStatistics.getPerson(name).incWinWhite();
                } else {
                    PersonsStatistics.getPerson(name).incWinBlack();
                }
                break;
            case DRAW:
                if (color == Color.WHITE) {
                    PersonsStatistics.getPerson(name).incDrawWhite();
                } else {
                    PersonsStatistics.getPerson(name).incDrawBlack();
                }
                break;
            case LOSE:
                if (color == Color.WHITE) {
                    PersonsStatistics.getPerson(name).incLoseWhite();
                } else {
                    PersonsStatistics.getPerson(name).incLoseBlack();
                }
                break;
        }
    }
}
