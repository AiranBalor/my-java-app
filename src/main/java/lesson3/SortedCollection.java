package lesson3;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortedCollection<T> {
    private final List<T> list;
    private final Comparator<? super T> comparator;

    public SortedCollection(Comparator<? super T> comparator) {
        this.list = new ArrayList<>();
        this.comparator = comparator;
    }

    public SortedCollection() {
        this.list = new ArrayList<>();
        this.comparator = null;
    }

    // добавление элемента с сохранением сортировки (O(n))
    public void add(T element) {
        int index = findInsertionIndex(element);
        list.add(index, element);
    }

    // Получение элемента по индексу (O(1))
    public T get(int index) {
        return list.get(index);
    }

    // Проверка наличия элемента (O(log n))
    public boolean contains(T element) {
        return Collections.binarySearch(list, element, comparator) >= 0;
    }

    // Получение индекса элемента (O(log n))
    public int indexOf(T element) {
        return Collections.binarySearch(list, element, comparator);
    }

    // Удаление элемента по индексу (O(n))
    public T remove(int index) {
        return list.remove(index);
    }

    // Поиск индекса для вставки (O(log n))
    private int findInsertionIndex(T element) {
        if (comparator != null) {
            return Collections.binarySearch(list, element, comparator);
        } else {
            // некоторые сомнения относительно null вместо компаратора присутствуют, с другой стороны, я совсем не знаю Java
            return Collections.binarySearch(list, element, null);
        }
    }

    // Дополнительные методы на случай востребования
    // public int size() {
    //     return list.size();
    // }

    // public boolean isEmpty() {
    //     return list.isEmpty();
    // }
}
