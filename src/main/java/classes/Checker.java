package classes;


public class Checker {
    private final Board board;

    public Checker(Board board){
        this.board = board;
    }

    public int checkForEnd(){
        if (board.getEmptyPositions().size() == 0) return -1;

        return 0;
    }

}
