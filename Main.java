import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Введите размер массива: ");
        int size = input.nextInt();
        int[] arr = new int[size];

        for (int i = 0; i < size; i++){
            System.out.println("Введите элемент массива: ");
            arr[i] = input.nextInt();
        }

        System.out.println(Arrays.toString(arr));

        System.out.println("Введите вид сортировки (1 - по возрастанию, 2 - по убыванию): ");
        int operation = input.nextInt();
        Expression func;
        if (operation == 1) {
            func = (x, y) -> x > y;
            heapSort(arr, func);
            System.out.println(Arrays.toString(arr));
        }
        else if (operation == 2) {
            func = (x, y) -> x < y;
            heapSort(arr, func);
            System.out.println(Arrays.toString(arr));
        }
        else
            System.out.println("Неверная команда!");
    }
    private static void bubbleSort(int[] arr, Expression ex) {
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (ex.isEqual(arr[j], arr[j + 1])) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    static void heapSort(int[] sortArr, Expression func) {
        int n = sortArr.length;
        int i = (n - 2) / 2;
        while (i >= 0) {
            heapify(sortArr, i--, n, func);
        }
        while (n > 0) {
            sortArr[n - 1] = pop(sortArr, n, func);
            n--;
        }
    }
    static int pop(int[] sortArr, int size, Expression func) {
        if (size <= 0) {
            return -1;
        }
        int top = sortArr[0];
        sortArr[0] = sortArr[size - 1];
        heapify(sortArr, 0, size - 1, func);
        return top;
    }

    static void heapify(int[] sortArr, int i, int size, Expression func){
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < size && func.isEqual(sortArr[left], sortArr[largest])) {
            largest = left;
        }
        if (right < size && func.isEqual(sortArr[right], sortArr[largest])){
            largest = right;
        }
        if (largest != i) {
            swap(sortArr, i, largest);
            heapify(sortArr, largest, size, func);
        }
    }

    static void swap(int[] sortArr, int i, int j){
        int temp = sortArr[i];
        sortArr[i] = sortArr[j];
        sortArr[j] = temp;
    }
}

interface Expression{
    boolean isEqual(int a, int b);
}