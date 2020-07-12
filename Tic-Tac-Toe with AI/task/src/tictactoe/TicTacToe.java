package tictactoe;

import java.util.Scanner;

public class TicTacToe {
    private Movable player1;
    private Movable player2;

    private boolean isCorrectPlayer(String arg) {
        return arg.matches("user|easy|medium|hard");
    }

    private boolean isCorrectCommand(String command) {
        String[] args = command.split("\\s");

        return args.length == 3 && args[0].matches("start") && isCorrectPlayer(args[1]) && isCorrectPlayer(args[2]);
    }

    private boolean isTerminalCommand(String command) {
        return command.matches("exit");
    }

    private Movable choosePlayer(String arg) {
        switch (arg) {
            case "user":
                return new MoveHuman();
            case "easy":
                return new MoveAIEasy();
            case "medium":
                return new MoveAIMedium();
            case "hard":
                return new MoveAIHard();
            default:
                return null;
        }
    }

    private boolean setPlayers() {
        Scanner scanner = new Scanner(System.in);

        String command;

        boolean askForCommand = true;
        do {
            System.out.print("Input command: ");
            command = scanner.nextLine();
            if (isCorrectCommand(command)) {
                askForCommand = false;
            } else if (isTerminalCommand(command)) {
                return false;
            } else {
                System.out.println("Bad parameters!");
            }
        } while (askForCommand);

        String[] arguments = command.split(" ");
        player1 = choosePlayer(arguments[1]);
        player2 = choosePlayer(arguments[2]);
        return true;
    }

    private void game() {
        TicTacToeSkeleton skeleton = new TicTacToeSkeleton();
        System.out.println(skeleton);

        Symbol goOn = null;
        while (goOn == null) {
            player1.move(skeleton, Symbol.X);
            goOn = skeleton.analyzeField();

            if (goOn == null) {
                player2.move(skeleton, Symbol.O);
                goOn = skeleton.analyzeField();
            }
        }

        switch (goOn) {
            case O:
                System.out.println(Symbol.O.getSymbol() + " wins");
                break;
            case X:
                System.out.println(Symbol.X.getSymbol() + " wins");
                break;
            case EMPTY:
                System.out.println("Draw");
        }
    }


    public void process() {
        while (setPlayers()) {
            game();
            System.out.println();
        }
    }
}
