package classes;


import java.util.ArrayList;
import java.util.List;

//класс, представляющий собой игральное поле, в нём есть методы ходов и анализа поля
public class Board {
    //двумерный массив, представляющий игральное поле
    private final ArrayList<ArrayList<Field>> fields;

    private final int size;

    public void move(Move move) { //метод для контроля хода
        switch (move) {
            case UP -> moveUp();
            case DOWN -> moveDown();
            case RIGHT -> moveRight();
            case LEFT -> moveLeft();
            case NONE -> {/*does nothing*/}
        }
    }

    private void moveUp() { //метод для хода вверх
        for (int i = 0; i < size; i++) {
            ArrayList<Field> column = getColumn(i);
            moveToFront(column);
        }

        spawnTileRandomly();
    }

    private void moveLeft() { //метод для хода влево
        for (int i = 0; i < size; i++) {
            ArrayList<Field> row = getRow(i);
            moveToFront(row);
        }

        spawnTileRandomly();
    }

    //метод соединяет и сдвигает к началу тайлы в коллекции
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

    private void moveDown() { //метод для хода вниз
        //в каждой подобной функции находить field на который нужно встать старому и передавать команду об этом полям
        for (int i = 0; i < size; i++) {
            ArrayList<Field> column = getColumnReversed(i);
            moveToFront(column);
        }

        spawnTileRandomly();
    }

    private void moveRight() { //метод для хода вправо
        for (int i = 0; i < size; i++) {
            ArrayList<Field> row = getRowReversed(i);
            moveToFront(row);
        }

        spawnTileRandomly();
    }

    //метод нахождения первого не пустого поля в коллекции
    private int findIndexOfFirstNotEmpty(List<Field> list, int startIndex) {
        for (int i = startIndex; i < list.size(); i++) {
            if (!list.get(i).isEmpty()) return i;
        }

        return -1;
    }

    //метод нахождения первого пустого поля в коллекции
    private int findIndexOfFirstEmpty(List<Field> list, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            if (list.get(i).isEmpty()) return i;
        }

        return -1;
    }

    //метод для создания тайла на случайном месте
    private void spawnTileRandomly() {
        ArrayList<Field> empty = getEmptyPositions();
        int currentSize = empty.size();

        int randomIndex = (int) Math.floor(Math.random() * currentSize);
        empty.get(randomIndex).spawnTile();
    }

    private ArrayList<Field> getRow(int index) { //метод получения строки
        return fields.get(index);
    }

    private ArrayList<Field> getRowReversed(int index) { //метод получения строки в обратном порядке
        ArrayList<Field> row = getRow(index);

        ArrayList<Field> result = new ArrayList<>(size);

        for (int i = row.size() - 1; i >= 0; i--) {
            result.add(row.get(i));
        }

        return result;
    }

    private ArrayList<Field> getColumn(int index) { //метод получения колонки
        ArrayList<Field> result = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            result.add(fields.get(i).get(index));
        }

        return result;
    }

    private ArrayList<Field> getColumnReversed(int index) { //метод получения колонки в обратном порядке
        ArrayList<Field> result = new ArrayList<>(size);

        for (int i = size - 1; i >= 0; i--) {
            result.add(fields.get(i).get(index));
        }

        return result;
    }

    public ArrayList<Field> getEmptyPositions() { //метод для получения пустых позиций на поле
        ArrayList<Field> empty = new ArrayList<>(size * size);

        for (ArrayList<Field> fieldArray : fields) {
            for (Field field : fieldArray) {
                if (field.isEmpty()) empty.add(field);
            }
        }
        return empty;
    }

    public ArrayList<Field> getTakenPositions() { //метод получения не пустых позиций
        ArrayList<Field> taken = new ArrayList<>(size * size);

        for (ArrayList<Field> fieldArray : fields) {
            for (Field field : fieldArray) {
                if (!field.isEmpty()) taken.add(field);
            }
        }
        return taken;
    }

    public Board(int size) { //конструктор с заданием размера
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

    public Board() { //дефольтный конструктор
        this(4);
    }

    public void show() { //вывод состояния в консоль
        for (ArrayList<Field> fieldArray : fields) {
            System.out.println(fieldArray);
        }
    }

}