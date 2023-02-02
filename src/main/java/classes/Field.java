package classes;

//класс предствляет из себя единичную клетку поля
public class Field {
    private boolean isEmpty = true;
    private Tile tile;

    public void spawnTile() { //метод для генерации тайла на клетке
        isEmpty = false;
        tile = new Tile();
    }

    //смена идёт со старой на новую
    public void moveTo(Field newField) { //метод переезда тайла
        newField.attachToTile(tile);
        this.detach();
    }

    private void attachToTile(Tile tile) { //метод для прицепления тайла к клетке
        this.tile = tile;
        this.isEmpty = false;
    }

    private void detach() { //метод для отсоединения тайла от клетки
        this.tile = null;
        this.isEmpty = true;
    }

    public void merge(Field contestant) { //метод для соединения тайлов на клетках
        Tile contestantTile = contestant.getTile();

        boolean result = tile.mergeIfAble(contestantTile);
        if (result) contestant.detach();
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public Tile getTile() {
        return tile;
    }

    @Override
    public String toString() { //переопределили метод вывода
        if (this.tile == null) return "-";
        else return "" + tile.getValue();
    }
}
