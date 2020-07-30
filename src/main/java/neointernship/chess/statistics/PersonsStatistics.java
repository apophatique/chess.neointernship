package neointernship.chess.statistics;

import java.util.HashMap;

public class PersonsStatistics {
    private static HashMap<String, PersonStatistics> personStatistics = new HashMap<>();

    private PersonsStatistics() {
    }

    public static void addPerson(final String name) {
        personStatistics.put(name, new PersonStatistics());
    }

    public static PersonStatistics getPerson(final String name) {
        return personStatistics.get(name);
    }

    public static void setPersonsStatistics(final String personStatistics) {
        final HashMap<String, PersonStatistics> map = new HashMap<>();

        final String[] params = personStatistics.split("#");

        String name;
        PersonStatistics statistics;

        for (int i = 1; i < params.length; i += 3) {
            name = params[i].split(":")[1].trim();
            statistics = new PersonStatistics(params[i + 1] + params[i + 2]);
            map.put(name, statistics);
        }

        PersonsStatistics.personStatistics = map;
    }

    public static HashMap<String, PersonStatistics> getPersonStatistics() {
        return personStatistics;
    }

    public static String toStringStatic() {
        String string = "";
        for (final String name : personStatistics.keySet()) {
            string += "#name: " + name + "\r\n" + personStatistics.get(name).toString() + "\r\n\r\n";
        }

        return string;
    }

    static class PersonStatistics {
        private int winWhite;
        private int drawWhite;
        private int loseWhite;
        private int winBlack;
        private int drawBlack;
        private int loseBlack;

        private PersonStatistics() {
            winWhite = 0;
            drawWhite = 0;
            loseWhite = 0;
            winBlack = 0;
            drawBlack = 0;
            loseBlack = 0;
        }

        private PersonStatistics(final String string) {
            final String[] params = string.split("/");
            winWhite = Integer.parseInt(params[1]);
            drawWhite = Integer.parseInt(params[2]);
            loseWhite = Integer.parseInt(params[3]);
            winBlack = Integer.parseInt(params[5]);
            drawBlack = Integer.parseInt(params[6]);
            loseBlack = Integer.parseInt(params[7]);
        }

        public void incWinWhite() {
            winWhite++;
        }

        public void incDrawWhite() {
            drawWhite++;
        }

        public void incLoseWhite() {
            loseWhite++;
        }

        public void incWinBlack() {
            winBlack++;
        }

        public void incDrawBlack() {
            drawBlack++;
        }

        public void incLoseBlack() {
            loseBlack++;
        }

        @Override
        public String toString() {
            return "#white: /" + winWhite + "/" + drawWhite + "/" + loseWhite + "/" +
                    "\r\n#black: /" + winBlack + "/" + drawBlack + "/" + loseBlack + '/';
        }
    }
}

