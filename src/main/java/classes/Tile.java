package classes;

public class Tile {
    private int value = 2;

    public boolean mergeIfAble(Tile tile) {
        if (this.value == tile.value) {
            doubleValue();
            return true;
        } else return false;
    }

    private void doubleValue() {
        value *= 2;
    }

    public int getValue() {
        return value;
    }
}
