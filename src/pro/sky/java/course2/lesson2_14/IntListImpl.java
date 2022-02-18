package pro.sky.java.course2.lesson2_14;

import java.util.Arrays;

import static java.util.Arrays.copyOf;

public class IntListImpl implements IntList {
    private Integer[] array;
    private static int position = 0;
    private static final int INIT_NUM = 8;


    public IntListImpl() {
        array = new Integer[INIT_NUM];
    }

    @Override
    public Integer add(Integer item) {
        checkNotNull(item);
        checkArray();
        array[position++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkNotNull(item);
        checkIndex(index);
        checkNum();
        checkArray();
        System.arraycopy(array, index, array, index + 1, array.length - index - 1);
        array[position++] = item;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkNotNull(item);
        checkIndex(index);
        checkArray();
        array[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        checkItemExists(item);
        checkNotNull(item);
        checkNum();
        checkArray();
        int deletedIndex = indexOf(item);
        if ((position - 1) > deletedIndex) {
            System.arraycopy(array, deletedIndex + 1, array, deletedIndex, array.length - deletedIndex - 1);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
        return item;
    }


    @Override
    public void remove(int index) {
        checkIndex(index);
        Integer item = get(index);
        int deletedIndex = indexOf(item);
        if (position - 1 > deletedIndex) {
            System.arraycopy(array, deletedIndex + 1, array, deletedIndex, array.length - deletedIndex - 1);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] arrayForBinarySearch = copyOf(array, array.length);
        sort(arrayForBinarySearch);
        return binarySearch(arrayForBinarySearch, item);
    }

    @Override
    public int indexOf(Integer item) {
        checkNotNull(item);
        for (int i = 0; i < position; i++) {
            if (item.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkNotNull(item);
        for (int i = position - 1; i < 0; i--) {
            if (item.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public boolean equals(IntList otherList) {
        if (otherList == null) {
            throw new NullPointerException();
        }
        return Arrays.equals(array, otherList.toArray());
    }

    @Override
    public int size() {
        return position;
    }

    @Override
    public boolean isEmpty() {
        return position == 0;
    }

    @Override
    public void clear() {
        array = new Integer[array.length];
    }

    @Override
    public Integer[] toArray() {
        return copyOf(array, array.length);
    }

    private void grow() {
        array = copyOf(array, (int) (array.length * 1.5));
    }

    private void checkArray() {
        if (position == array.length - 1) {
            grow();
        }
    }

    private void sort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private static boolean binarySearch(Integer[] arr, int element) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (element == arr[mid]) {
                return true;
            }

            if (element < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    private Integer[] resize() {
        return array = copyOf(array, position * 2);
    }

    private void checkNotNull(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= position) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkItemExists(int item) {
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
