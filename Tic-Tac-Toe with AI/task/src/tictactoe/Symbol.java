package tictactoe;

public enum Symbol {
    X('X'), O('O'), EMPTY(' ');

    private final char symbol;

    Symbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public Symbol oppositeSymbol() {
        switch (symbol) {
            case 'X':
                return O;
            case 'O':
                return X;
            default:
                return EMPTY;
        }
    }
}
