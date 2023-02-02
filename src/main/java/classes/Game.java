package classes;


import java.util.Scanner;

public class Game extends Thread {
    private final Board board;
    private final Checker checker;

    public Game() {
        this.board = new Board();
        this.checker = new Checker(board);
    }

    @Override
    public void run() {
        do {
            board.show();
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();

            switch (line) {
                case "w" -> board.move(Move.UP);
                case "s" -> board.move(Move.DOWN);
                case "d" -> board.move(Move.RIGHT);
                case "a" -> board.move(Move.LEFT);
            }

        } while (checker.checkForEnd() == 0);

        if (checker.checkForEnd() == -1) System.out.println("Вы проиграли");
        else System.out.println("Вы выиграли");
    }
}