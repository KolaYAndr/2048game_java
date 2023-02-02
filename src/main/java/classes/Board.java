package classes;


import java.util.ArrayList;
import java.util.List;

public class Board {
    //[vertical][horizontal]
    private final ArrayList<ArrayList<Field>> fields;

    private final int size;

    public void move(Move move) {
        switch (move) {
            case UP -> moveUp();
            case DOWN -> moveDown();
            case RIGHT -> moveRight();
            case LEFT -> moveLeft();
            case NONE -> {/*does nothing*/}
        }
        spawnTileRandomly();
    }

    private void moveUp() {
        for (int i = 0; i < size; i++) {
            ArrayList<Field> column = getColumn(i);
            moveToFront(column);
        }
    }

    private void moveLeft() {
        for (int i = 0; i < size; i++) {
            ArrayList<Field> row = getRow(i);
            moveToFront(row);
        }
    }

    //new
    private void moveToFront(List<Field> column) {
        //соединяем, что можем
        for (int i = 0; i < column.size() - 1; i++) {
            Field upperField = column.get(i);
            int indexOfFirstNotEmpty = findIndexOfFirstNotEmpty(column, i + 1);

            if (indexOfFirstNotEmpty != -1) {
                Field lowerField = column.get(indexOfFirstNotEmpty);

                if (!upperField.isEmpty() & !lowerField.isEmpty()) upperField.merge(lowerField);
            }
        }
        //двигаем к началу
        for (int k = 0; k < size; k++) {
            int indexOfFirstNotEmpty = findIndexOfFirstNotEmpty(column, k);

            if (indexOfFirstNotEmpty != -1) {
                int indexOfFirstEmpty = findIndexOfFirstEmpty(column, k, indexOfFirstNotEmpty);

                if (indexOfFirstEmpty != -1) {
                    Field lower = column.get(indexOfFirstNotEmpty);
                    Field upper = column.get(indexOfFirstEmpty);
                    lower.moveTo(upper);
                }
            }
        }
    }


    private void moveDown() {
        //в каждой подобной функции находить field на который нужно встать старому и передавать команду об этом полям
        for (int i = 0; i < size; i++) {
            ArrayList<Field> column = getColumnReversed(i);
            moveToFront(column);
        }
    }

    private void moveRight() {
        for (int i = 0; i < size; i++) {
            ArrayList<Field> row = getRowReversed(i);
            moveToFront(row);
        }
    }

    private int findIndexOfFirstNotEmpty(List<Field> list, int startIndex) {
        for (int i = startIndex; i < list.size(); i++) {
            if (!list.get(i).isEmpty()) return i;
        }

        return -1;
    }

    private int findIndexOfFirstEmpty(List<Field> list, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            if (list.get(i).isEmpty()) return i;
        }

        return -1;
    }

    private void spawnTileRandomly() {
        ArrayList<Field> empty = getEmptyPositions();
        int currentSize = empty.size();

        int randomIndex = (int) Math.floor(Math.random() * currentSize);
        empty.get(randomIndex).spawnTile();
    }

//    public boolean notifyChanges() {
//        if (isChanged) {
//            isChanged = false;
//            return true;
//        } else return false;
//    }

    private ArrayList<Field> getRow(int index) {
        return fields.get(index);
    }

    private ArrayList<Field> getRowReversed(int index) {
        ArrayList<Field> row = getRow(index);

        ArrayList<Field> result = new ArrayList<>(size);

        for (int i = row.size() - 1; i >= 0; i--) {
            result.add(row.get(i));
        }

        return result;
    }

    private ArrayList<Field> getColumn(int index) {
        ArrayList<Field> result = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            result.add(fields.get(i).get(index));
        }

        return result;
    }

    private ArrayList<Field> getColumnReversed(int index) {
        ArrayList<Field> result = new ArrayList<>(size);

        for (int i = size - 1; i >= 0; i--) {
            result.add(fields.get(i).get(index));
        }

        return result;
    }

    public ArrayList<Field> getEmptyPositions() {
        ArrayList<Field> empty = new ArrayList<>(size * size);

        for (ArrayList<Field> fieldArray : fields) {
            for (Field field : fieldArray) {
                if (field.isEmpty()) empty.add(field);
            }
        }
        return empty;
    }

    public ArrayList<Field> getTakenPositions() {
        ArrayList<Field> taken = new ArrayList<>(size * size);

        for (ArrayList<Field> fieldArray : fields) {
            for (Field field : fieldArray) {
                if (!field.isEmpty()) taken.add(field);
            }
        }
        return taken;
    }

    public Board(int size) {
        this.size = size;
        this.fields = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            ArrayList<Field> fieldArrayList = new ArrayList<>(size);

            for (int j = 0; j < size; j++) {
                fieldArrayList.add(new Field());
            }
            fields.add(fieldArrayList);
        }

        spawnTileRandomly();
    }

    public Board() {
        this(4);
    }

    public void show() {
        for (ArrayList<Field> fieldArray : fields) {
            System.out.println(fieldArray);
        }
    }

}