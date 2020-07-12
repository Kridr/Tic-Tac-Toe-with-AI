package tictactoe;

public class TicTacToeSkeleton {
    private Symbol[][] field;
    final private int SIZE = 3;

    public int getSize() {
        return SIZE;
    }

    void setField(int first, int second, Symbol symbol) {
        field[SIZE - second][first - 1] = symbol;
    }

    void setFieldReal(int first, int second, Symbol symbol) {
        field[first][second] = symbol;
    }

    Symbol getFieldReal(int first, int second) {
        return field[first][second];
    }

    TicTacToeSkeleton() {
        field = new Symbol[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = Symbol.EMPTY;
            }
        }
    }

    public boolean isEmptyCells() {
        boolean answer = false;

        for (int i = 0; i < SIZE && !answer; i++) {
            for (int j = 0; j < SIZE && !answer; j++) {
                answer = field[i][j] == Symbol.EMPTY;
            }
        }

        return answer;
    }

    //start of row functions
    private boolean isHorizontalRow(Symbol symbol) {
        boolean answer = false;

        for (int i = 0; i < SIZE && !answer; i++) {

            boolean tempAnswer = true;
            for (int j = 0; j < SIZE && tempAnswer; j++) {
                tempAnswer = field[i][j] == symbol;
            }

            if (tempAnswer) {
                answer = true;
            }
        }

        return answer;
    }

    private boolean isVerticalRow(Symbol symbol) {
        boolean answer = false;

        for (int i = 0; i < SIZE && !answer; i++) {

            boolean tempAnswer = true;
            for (int j = 0; j < SIZE && tempAnswer; j++) {
                tempAnswer = field[j][i] == symbol;
            }

            if (tempAnswer) {
                answer = true;
            }
        }

        return answer;
    }

    private boolean isMainDiagonalRow(Symbol symbol) {
        boolean answer = true;

        for (int i = 0; i < SIZE && answer; i++) {
            answer = field[i][i] == symbol;
        }

        return answer;
    }

    private boolean isSideDiagonalRow(Symbol symbol) {
        boolean answer = true;

        for (int i = 0; i < SIZE && answer; i++) {
            answer = field[i][SIZE - i - 1] == symbol;
        }

        return answer;
    }

    boolean isRow(Symbol symbol) {

        return isHorizontalRow(symbol) ||
                isVerticalRow(symbol) ||
                isMainDiagonalRow(symbol) ||
                isSideDiagonalRow(symbol);
    }
    //end of row functions

    public Symbol analyzeField() {
        if (isRow(Symbol.O)) {
            return Symbol.O; // o wins
        } else if (isRow(Symbol.X)) {
            return Symbol.X; // x wins
        } else if (isEmptyCells()) {
            return null; // game continues
        } else {
            return Symbol.EMPTY; // tie
        }
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        out.append("-".repeat(3 + SIZE * 2)).append("\n");
        for (int i = 0; i < SIZE; i++) {
            out.append("| ");
            for (int j = 0; j < SIZE; j++) {
                out.append(field[i][j].getSymbol()).append(" ");
            }
            out.append("|\n");
        }
        out.append("-".repeat(3 + SIZE * 2));

        return out.toString();
    }

    boolean isOccupied(int first, int second) {
        return field[SIZE - second][--first] != Symbol.EMPTY;
    }

    boolean isOutOfBorder(int first, int second) {
        return first < 1 || first > SIZE || second < 1 || second > SIZE;
    }

}
