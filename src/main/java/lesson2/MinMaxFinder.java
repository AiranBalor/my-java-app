package lesson2;

public class MinMaxFinder {
    
    public static void findMinMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Массив совсем пустой");
            return;
        }
        int startIndex;
        int min, max;
        
        // проверка четности массива
        if (arr.length % 2 == 1) {
            min = max = arr[0];
            startIndex = 1;
        } else {
            if (arr[0] < arr[1]) {
                min = arr[0];
                max = arr[1];
            } else {
                min = arr[1];
                max = arr[0];
            }
            startIndex = 2;
        }

        // попарная проверка элементов
        for (int i = startIndex; i < arr.length; i += 2) {
            if (arr[i] < arr[i+1]) {
                if (arr[i] < min) min = arr[i];
                if (arr[i+1] > max) max = arr[i+1];
            } else {
                if (arr[i+1] < min) min = arr[i+1];
                if (arr[i] > max) max = arr[i];
            }
        }

        System.out.println("Минимальное значение: " + min);
        System.out.println("Максимальное значение: " + max);
    }

    public static void main(String[] args) {
        int[] array = {4, 2, 7, 5, 1, 3, 8, 6};
        findMinMax(array);
    }
}
