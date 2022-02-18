package pro.sky.java.course2.lesson2_14;

public interface IntList {
    Integer add(Integer item);

    Integer add(int index, Integer item);

    Integer set(int index, Integer item);

    Integer remove(Integer item);

    void remove(int index);

    boolean contains(Integer item);

    int indexOf(Integer item);

    int lastIndexOf(Integer item);

    Integer get(int index);

    boolean equals(IntList otherList);

    int size();

    boolean isEmpty();

    void clear();

    Integer[] toArray();
}

