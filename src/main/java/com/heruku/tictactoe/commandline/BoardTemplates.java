package com.heruku.tictactoe.commandline;

class BoardTemplates {

    private static final String THREE_BY_THREE =
                    " 1 | 2 | 3 \n"
                    + "---+---+---\n"
                    + " 4 | 5 | 6 \n"
                    + "---+---+---\n"
                    + " 7 | 8 | 9 \n\n";

    private static final String FOUR_BY_FOUR =
                      " 1 | 2 | 3 | 4 \n"
                    + "---+---+---+---\n"
                    + " 5 | 6 | 7 | 8 \n"
                    + "---+---+---+---\n"
                    + " 9 | 10| 11| 12\n"
                    + "---+---+---+---\n"
                    + " 13| 14| 15| 16\n\n";

    public static String forSize(int size) {
        switch (size) {
            case 9:  return THREE_BY_THREE;
            case 16: return FOUR_BY_FOUR;
            default: throw new RuntimeException("Cannot show board of size " + size);
        }
    }
}
