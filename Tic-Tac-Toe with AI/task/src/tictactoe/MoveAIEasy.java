package tictactoe;

import java.util.Random;

public class MoveAIEasy implements Movable {
    @Override
    public void move(TicTacToeSkeleton skeleton, Symbol symbol) {
        Random random = new Random();

        System.out.println("Making move level \"easy\"");

        int x, y;

        do {
            x = random.nextInt(skeleton.getSize()) + 1;
            y = random.nextInt(skeleton.getSize()) + 1;
        } while (skeleton.isOccupied(x, y));

        skeleton.setField(x, y, symbol);
        System.out.println(skeleton);
    }
}
