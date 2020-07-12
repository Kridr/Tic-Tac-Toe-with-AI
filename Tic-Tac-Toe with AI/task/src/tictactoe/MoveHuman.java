package tictactoe;

import java.util.Scanner;

public class MoveHuman implements Movable {

    @Override
    public void move(TicTacToeSkeleton skeleton,Symbol symbol) {

        Scanner scanner = new Scanner(System.in);

        boolean isOk = false;

        String answer = "";

        while (!isOk) {
            System.out.print("Enter the coordinates: ");

            String line = scanner.nextLine();
            if (!line.matches("^\\d\\s+\\d$")) {
                System.out.println("You should enter numbers!");
                continue;
            }

            String[] cs = line.split(" ");
            int first = Integer.parseInt(cs[0]);
            int second = Integer.parseInt(cs[1]);

            if (skeleton.isOutOfBorder(first, second)) {
                System.out.println("Coordinates should be from 1 to " + skeleton.getSize() + "!");
            } else if (skeleton.isOccupied(first, second)) {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                skeleton.setField(first, second, symbol);
                isOk = true;
                answer = Integer.toString(first) + second;
            }
        }

        System.out.println(skeleton);
    }
}
