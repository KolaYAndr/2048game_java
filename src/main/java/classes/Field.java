package classes;

public class Field {
    private boolean isEmpty = true;
    private Tile tile;

    public void spawnTile() {
        isEmpty = false;
        tile = new Tile();
    }

    //смена идёт со старой на новую
    public void moveTo(Field newField) {
        newField.attachToTile(tile);
        this.detach();
    }

    private void attachToTile(Tile tile) {
        this.tile = tile;
        this.isEmpty = false;
    }

    private void detach() {
        this.tile = null;
        this.isEmpty = true;
    }

    //слияние идёт от верхнего при ходе вверх
    public void merge(Field contestant) {
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
    public String toString() {
        if (this.tile == null) return "-";
        else return "" + tile.getValue();
    }
}
