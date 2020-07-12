package tictactoe;

public class MoveAIHard implements Movable {

    private int minimax(TicTacToeSkeleton skeleton, Symbol symbol, final Symbol original) {
        Symbol result = skeleton.analyzeField();

        if (result != null) {
            switch (result) {
                case O:
                    return (original == Symbol.O) ? 10 : -10;
                case X:
                    return (original == Symbol.O) ? -10 : 10;
                case EMPTY:
                    return 0;
            }
        }

        int bestScore = (symbol == original) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int i = 1; i <= skeleton.getSize(); i++) {
            for (int j = 1; j <= skeleton.getSize(); j++) {
                if (! skeleton.isOccupied(i, j)) {
                    skeleton.setField(i, j, symbol);
                    int score = minimax(skeleton, symbol.oppositeSymbol(), original);
                    skeleton.setField(i, j, Symbol.EMPTY);

                    if (symbol == original) {
                        bestScore = Math.max(score, bestScore); //maximize
                    } else {
                        bestScore = Math.min(score, bestScore); //minimize
                    }
                }
            }
        }
        return bestScore;
    }

    @Override
    public void move(TicTacToeSkeleton skeleton, Symbol symbol) {
        System.out.println("Making move level \"hard\"");

        int bestScore = Integer.MIN_VALUE;
        int first = 1;
        int second = 1;

        for (int i = 1; i <= skeleton.getSize(); i++) {
            for (int j = 1; j <= skeleton.getSize(); j++) {
                if (! skeleton.isOccupied(i, j)) {
                    skeleton.setField(i, j, symbol);
                    int score = minimax(skeleton, symbol.oppositeSymbol(), symbol);
                    skeleton.setField(i, j, Symbol.EMPTY);

                    if (score > bestScore) {
                        bestScore = score;
                        first = i;
                        second = j;
                    }
                }
            }
        }

        skeleton.setField(first, second, symbol);

        System.out.println(skeleton);
    }
}
