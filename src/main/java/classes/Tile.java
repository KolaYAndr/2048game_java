package classes;

//класс, представляющий собой контейнер значений на поле
public class Tile {
    private int value = 2;

    //метод слияния тайлов при соответствии значений
    public boolean mergeIfAble(Tile tile) {
        if (this.value == tile.value) {
            doubleValue();
            return true;
        } else return false;
    }

    //удваивает значение тайла
    private void doubleValue() {
        value *= 2;
    }

    public int getValue() {
        return value;
    }
}
