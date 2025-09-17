package ru.ti.education;

public class BinarySearchTree<KEY extends Comparable<KEY>, VALUE> {
    
    private class Node {
        KEY key;
        VALUE value;
        Node left;
        Node right;

        Node(KEY key, VALUE value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public VALUE get(KEY key) {
        return getRecursive(root, key);
    }

    private VALUE getRecursive(Node current, KEY key) {
        // Если достигли пустого узла, ключ не найден
        if (current == null) {
            return null;
        }

        int cmp = key.compareTo(current.key);
        
        // Если ключ найден, возвращаем значение
        if (cmp == 0) {
            return current.value;
        }
        // Если ключ меньше текущего, ищем в левом поддереве
        else if (cmp < 0) {
            return getRecursive(current.left, key);
        }
        // Если ключ больше текущего, ищем в правом поддереве
        else {
            return getRecursive(current.right, key);
        }
    }

    public void add(KEY key, VALUE value) {
        root = addRecursive(root, key, value);
    }

    private Node addRecursive(Node current, KEY key, VALUE value) {
        // Если пустой узел, создаем новый
        if (current == null) {
            return new Node(key, value);
        }

        int cmp = key.compareTo(current.key);
        
        // Если ключ меньше текущего, налево
        if (cmp < 0) {
            current.left = addRecursive(current.left, key, value);
        }
        // Если ключ больше текущего, направо
        else if (cmp > 0) {
            current.right = addRecursive(current.right, key, value);
        }
        // Если ключ равен текущему, перезаписываем значение
        else {
            current.value = value;
        }

        return current;
    }


    public VALUE remove(KEY key) {
        String[] result = new String[1]; // Для хранения результата
        root = removeRecursive(root, key, result);
        return (VALUE) result[0]; // Приводим к типу VALUE. result[0] - массив выступает как контейнер для хранения результата в процессе
        // рекурсии
    }

    private Node removeRecursive(Node current, KEY key, String[] result) {
        if (current == null) {
            result[0] = null;
            return null;
        }

        int cmp = key.compareTo(current.key);
        
        if (cmp < 0) {
            current.left = removeRecursive(current.left, key, result);
        } else if (cmp > 0) {
            current.right = removeRecursive(current.right, key, result);
        } else {
            result[0] = (String) current.value;
            
            // Случай 1: узел без потомков или с одним потомком. Замена узла на наследника
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }
            
            // Случай 2: узел с двумя потомками
            // Находим минимальный элемент в правом поддереве (преемник)
            current.key = minValue(current.right);
            // Удаляем преемника-дубликат из правого поддерева
            current.right = removeRecursive(current.right, current.key, new String[1]);
        }
        
        return current;
    }

    private KEY minValue(Node node) {
        KEY minValue = node.key;
        while (node.left != null) {
            minValue = node.left.key;
            node = node.left;
        }
        return minValue;
    }
}