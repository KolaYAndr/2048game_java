package classes;

//класс для контроля конца игры
public class Checker {
    private final Board board;

    public Checker(Board board) {
        this.board = board;
    }

    public int checkForEnd() { //метод контроля конца игры
        for (Field field : board.getTakenPositions()) { //проверяет непустые клетки на совпадения значения тайла с 2048
            if (field.getTile().getValue() == 2048) return 1;
        }

        if (board.getEmptyPositions().size() == 0) return -1; //проверяет остались ли пустые клетки

        return 0;
    }

}
