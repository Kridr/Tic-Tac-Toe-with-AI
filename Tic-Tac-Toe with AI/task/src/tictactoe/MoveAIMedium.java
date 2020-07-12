package tictactoe;

import java.util.Random;

public class MoveAIMedium implements Movable {

    int[] isAlmostHorizontalRow(TicTacToeSkeleton skeleton, Symbol symbol) {
        for (int i = 0; i < skeleton.getSize(); i++) {
            int countEmpty = 0;
            boolean isOpposite = false;

            int x = -1;
            int y = -1;

            for (int j = 0; j < skeleton.getSize() && countEmpty < 2 && !isOpposite; j++) {
                if (skeleton.getFieldReal(i, j) == Symbol.EMPTY) {
                    countEmpty++;
                    x = i;
                    y = j;
                } else if (skeleton.getFieldReal(i, j) == symbol.oppositeSymbol()) {
                    isOpposite = true;
                }
            }

            if (countEmpty == 1 && !isOpposite) {
                return new int[]{x, y};
            }
        }

        return null;
    }

    int[] isAlmostVerticalRow(TicTacToeSkeleton skeleton, Symbol symbol) {
        for (int i = 0; i < skeleton.getSize(); i++) {
            int countEmpty = 0;
            boolean isOpposite = false;

            int x = -1;
            int y = -1;

            for (int j = 0; j < skeleton.getSize() && countEmpty < 2 && !isOpposite; j++) {
                if (skeleton.getFieldReal(j, i) == Symbol.EMPTY) {
                    countEmpty++;
                    x = j;
                    y = i;
                } else if (skeleton.getFieldReal(j, i) == symbol.oppositeSymbol()) {
                    isOpposite = true;
                }
            }

            if (countEmpty == 1 && !isOpposite) {
                return new int[]{x, y};
            }
        }

        return null;
    }

    int[] isAlmostMainDiagonalRow(TicTacToeSkeleton skeleton, Symbol symbol) {
        int countEmpty = 0;
        boolean isOpposite = false;

        int x = -1;
        int y = -1;

        for (int i = 0; i < skeleton.getSize() && countEmpty < 2 && !isOpposite; i++) {
            if (skeleton.getFieldReal(i, i) == Symbol.EMPTY) {
                countEmpty++;
                x = i;
                y = i;
            } else if (skeleton.getFieldReal(i, i) == symbol.oppositeSymbol()) {
                isOpposite = true;
            }
        }

        if (countEmpty == 1 && !isOpposite) {
            return new int[]{x, y};
        }

        return null;
    }

    int[] isAlmostSideDiagonalRow(TicTacToeSkeleton skeleton, Symbol symbol) {
        int countEmpty = 0;
        boolean isOpposite = false;

        int x = -1;
        int y = -1;

        for (int i = 0; i < skeleton.getSize() && countEmpty < 2 && !isOpposite; i++) {
            if (skeleton.getFieldReal(i, skeleton.getSize() - i - 1) == Symbol.EMPTY) {
                countEmpty++;
                x = i;
                y = skeleton.getSize() - i - 1;
            } else if (skeleton.getFieldReal(i, skeleton.getSize() - i - 1) == symbol.oppositeSymbol()) {
                isOpposite = true;
            }
        }

        if (countEmpty == 1 && !isOpposite) {
            return new int[]{x, y};
        }

        return null;
    }

    boolean almostRowAction(TicTacToeSkeleton skeleton, Symbol symbol) {
        int[] coordinates;
        if ((coordinates = isAlmostHorizontalRow(skeleton, symbol.oppositeSymbol())) != null) {
            skeleton.setFieldReal(coordinates[0], coordinates[1], symbol);
            return true;
        } else if ((coordinates = isAlmostVerticalRow(skeleton, symbol.oppositeSymbol())) != null) {
            skeleton.setFieldReal(coordinates[0], coordinates[1], symbol);
            return true;
        } else if ((coordinates = isAlmostMainDiagonalRow(skeleton, symbol.oppositeSymbol())) != null) {
            skeleton.setFieldReal(coordinates[0], coordinates[1], symbol);
            return true;
        } else if ((coordinates = isAlmostSideDiagonalRow(skeleton, symbol.oppositeSymbol())) != null) {
            skeleton.setFieldReal(coordinates[0], coordinates[1], symbol);
            return true;
        } else {
            return false;
        }
    }
    //end of almost row functions

    @Override
    public void move(TicTacToeSkeleton skeleton, Symbol symbol) {
        System.out.println("Making move level \"medium\"");

        if (! almostRowAction(skeleton, symbol)) {
            Random random = new Random();

            int x, y;

            do {
                x = random.nextInt(skeleton.getSize()) + 1;
                y = random.nextInt(skeleton.getSize()) + 1;
            } while (skeleton.isOccupied(x, y));

            skeleton.setField(x, y, symbol);
        }

        System.out.println(skeleton);
    }
}
