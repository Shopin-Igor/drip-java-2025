package Task2;


import java.util.Arrays;
public class DynamicIntArray implements DynamicArray {
    private int size = 0;
    private int i;
    private int array[] = new int[1];

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(int element) {
        return (indexOf(element) != -1);
    }

    @Override
    public boolean add(int e) {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        array[size++] = e;
        return true;
    }

    @Override
    public boolean containsAll(DynamicIntArray c) {
        for (i = 0; i < size; i++) {
            if (!c.contains(c.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(DynamicIntArray c) {
        for (i = 0; i < c.size(); i++) {
            add(c.get(i));
        }
        return true;
    }




    public boolean addAll(int index, DynamicIntArray c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        int numNewElements = c.size();
        if (size + numNewElements > array.length) {
            int[] newArray = new int[array.length + numNewElements];
            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index, newArray, index + numNewElements, size - index);
            array = newArray;
        } else {
            System.arraycopy(array, index, array, index + numNewElements, size - index);
        }

        for (int i = 0; i < numNewElements; i++) {
            array[index + i] = c.get(i);
        }
        size += numNewElements;
        return true;
    }

    @Override
    public boolean removeAll(DynamicIntArray c) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (c.contains(array[i])) {
                remove(i);
                i--;
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(DynamicIntArray c) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (!c.contains(array[i])) {
                remove(i);
                --i;
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void sort() {
        Arrays.sort(array, 0, size);
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return array[index];
    }

    @Override
    public int set(int index, int element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        int old = array[index];
        array[index] = element;
        return old;
    }

    @Override
    public void add(int index, int element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        if (size == array.length) {
            int[] newArray = new int[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index, newArray, index + 1, size - index);
            array = newArray;
        } else {
            System.arraycopy(array, index, array, index + 1, size - index);
        }

        array[index] = element;
        size++;
    }

    @Override
    public long remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        int removedElement = array[index];


        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        size--;
        return removedElement;
    }

    @Override
    public int indexOf(int value) {
        for (int i = 0; i < size; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(int element) {
        for (int i = size - 1; i >= 0; --i) {
            if (array[i] == element) {
                return i;
            }
        }
        return -1;
    }


}