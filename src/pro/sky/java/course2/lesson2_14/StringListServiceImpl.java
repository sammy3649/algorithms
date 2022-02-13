package pro.sky.java.course2.lesson2_14;

import java.util.Arrays;

public class StringListServiceImpl implements StringListService {
    private String[] array;
    private int position = 0;
    private static final int INIT_NUM = 8;

    public StringListServiceImpl(String[] array) {
        this.array = array;
        array = new String[INIT_NUM];
    }

    // Добавление элемента.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    @Override
    public String add(String item) {
        checkNotNull(item);
        checkNum();
        array[position++] = item;
        return item;
    }

    // Добавление элемента
    // на определенную позицию списка.
    // Если выходит за пределы фактического
    // количества элементов или массива,
    // выбросить исключение.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    @Override
    public String add(int index, String item) {
        checkNotNull(item);
        checkIndex(index);
        checkNum();
        System.arraycopy(array, index, array, index + 1, array.length - index - 1);
        array[position++] = item;
        return item;
    }

    // Установить элемент
    // на определенную позицию,
    // затерев существующий.
    // Выбросить исключение,
    // если индекс меньше
    // фактического количества элементов
    // или выходит за пределы массива.
    @Override
    public String set(int index, String item) {
        checkNotNull(item);
        checkIndex(index);
        array[index] = item;
        return item;
    }

    // Удаление элемента.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public String remove(String item) {
        checkItemExists(item);
        checkNotNull(item);
        int deletedIndex = indexOf(item);
        if (position - 1 > deletedIndex) {
            System.arraycopy(array, deletedIndex + 1, array, deletedIndex, array.length - deletedIndex - 1);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
        return item;
    }

    // Удаление элемента по индексу.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public String remove(int index) {
        checkIndex(index);
        String item = get(index);
        int deletedIndex = indexOf(item);
        if (position - 1 > deletedIndex) {
            System.arraycopy(array, deletedIndex + 1, array, deletedIndex, array.length - deletedIndex - 1);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
        return item;
    }

    // Проверка на существование элемента.
    // Вернуть true/false;
    @Override
    public boolean contains(String item) {
        boolean j = true;
        if (item == null)
            return false;
        for (int i = 0; i < position; i++) {
            if (item.equals(array[i])) {
                return true;
            }
        }
        return j;
    }

    // Поиск элемента.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int indexOf(String item) {
        checkNotNull(item);
        for (int i = 0; i < position; i++) {
            if (item.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    // Поиск элемента с конца.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int lastIndexOf(String item) {
        checkNotNull(item);
        for (int i = position - 1; i < 0; i--) {
            if (item.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    // Получить элемент по индексу.
    // Вернуть элемент или исключение,
    // если выходит за рамки фактического
    // количества элементов.
    @Override
    public String get(int index) {
        checkIndex(index);
        return array[index];
    }

    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение,
    // если передан null.
    @Override
    public boolean equals(StringListService otherList) {
        if (otherList == null) {
            throw new NullPointerException();
        }
        return Arrays.equals(array, otherList.toArray());
    }
    // Вернуть фактическое количество элементов.

    @Override
    public int size() {
        return position;
    }

    // Вернуть true,
    // если элементов в списке нет,
    // иначе false.
    @Override
    public boolean isEmpty() {
        return position == 0;
    }
    // Удалить все элементы из списка.

    @Override
    public void clear() {
        array = new String[INIT_NUM];
    }

    // Создать новый массив
    // из строк в списке
    // и вернуть его.
    @Override
    public String[] toArray() {
        return Arrays.copyOf(array, array.length);
    }

    private String[] resize() {
        return array = Arrays.copyOf(array, position * 2);
    }

    private void checkNotNull(String item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= position) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkItemExists(String item) {
        if (indexOf(item) == -1) {
            throw new IllegalArgumentException();
        }
    }

    private void checkNum() {
        if (position == array.length) {
            array = resize();
        }
    }
}