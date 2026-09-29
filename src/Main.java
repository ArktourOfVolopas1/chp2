import java.util.Arrays;
public class Main {

    public static void mergeSort(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2; //(left + right) / 2.

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];

        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k] = arr[i];
                i++;
            } else {
                temp[k] = arr[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            temp[k] = arr[i];
            i++;
            k++;
        }

        while (j <= right) {
            temp[k] = arr[j];
            j++;
            k++;
        }

        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        int pivotIndex = partition(arr, low, high);

        quickSort(arr, low, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, high);
    }

    public static int partition(int[] arr, int low, int high) {
        int mid = low + (high - low) / 2;
        int pivotIndex;

        if (arr[low] < arr[mid]) {
            if (arr[mid] < arr[high]) {
                pivotIndex = mid;
            } else

                if (arr[low] < arr[high]) {
                pivotIndex = high;
            } else {
                pivotIndex = low;
            }
        } else {

            if (arr[low] < arr[high]) {
                pivotIndex = low;
            } else

                if (arr[mid] < arr[high]) {
                pivotIndex = high;
            } else {
                pivotIndex = mid;
            }
        }

        swap(arr, pivotIndex, high);

        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);

        return i + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int binarySearch(int[] sortedArr, int target) {
        int left = 0;
        int right = sortedArr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (sortedArr[mid] == target) {
                return mid;
            }

            if (sortedArr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static int lowerBound(int[] sortedArr, int target) {
        int left = 0;
        int right = sortedArr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (sortedArr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static int upperBound(int[] sortedArr, int target) {
        int left = 0;
        int right = sortedArr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (sortedArr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {

        int[] original = {7, 2, 9, 1, 5, 2, 8, 3, 2};

        int[] mergeArray = Arrays.copyOf(original, original.length);
        int[] correctArray = Arrays.copyOf(original, original.length);

        mergeSort(mergeArray, 0, mergeArray.length - 1);

        Arrays.sort(correctArray);

        System.out.println("Merge sort:");
        System.out.println(Arrays.toString(mergeArray));
        System.out.println("Правильно: " + Arrays.equals(mergeArray, correctArray));

        int[] quickArray = Arrays.copyOf(original, original.length);
        quickSort(quickArray, 0, quickArray.length - 1);

        System.out.println();
        System.out.println("Quick sort:");
        System.out.println(Arrays.toString(quickArray));
        System.out.println("Правильно: " + Arrays.equals(quickArray, correctArray));

        int target = 5;

        int myIndex = binarySearch(mergeArray, target);
        int javaIndex = Arrays.binarySearch(mergeArray, target);

        System.out.println();
        System.out.println("Бинарный поиск:");
        System.out.println("Мой результат: " + myIndex);
        System.out.println("Arrays.binarySearch: " + javaIndex);

        int lower = lowerBound(mergeArray, 2);
        int upper = upperBound(mergeArray, 2);

        System.out.println();
        System.out.println("Поиск границ для значения 2:");
        System.out.println("lowerBound: " + lower);
        System.out.println("upperBound: " + upper);
        System.out.println("Количество вхождений: " + (upper - lower));
    }
}