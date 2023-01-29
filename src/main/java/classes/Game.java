package classes;


import java.util.Scanner;

public class Game {
    private final Board board;
    private final Checker checker;

    public Game() {
        this.board = new Board();
        this.checker = new Checker(board);
    }

    public void start() {
        board.show();


        while (checker.checkForEnd() == 0) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();

            Move nextMove;
            switch (line) {
                case "w" -> nextMove = Move.UP;
                case "d" -> nextMove = Move.RIGHT;
                case "s" -> nextMove = Move.DOWN;
                case "a" -> nextMove = Move.LEFT;
                default -> nextMove = Move.NONE;
            }

            board.move(nextMove);
            board.show();
        }
    }
}