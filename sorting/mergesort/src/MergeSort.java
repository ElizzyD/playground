import java.util.Scanner;

/**
 * The basic merge sort algorithm.
 */
public class MergeSort {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("How many numbers would you like to sort: ");

        int numInts = keyboard.nextInt();
        int[] arr = new int[numInts];
        for (int i = 0; i < numInts; i++) {
            System.out.print("Enter a number: ");
            arr[i] = keyboard.nextInt();
        }

        System.out.println("Thanks! Sorting now.");

        for (int n : mergesort(arr)) {
            System.out.println(n);
        }
    }

    /*
     * Perform merge sort. The basic steps are to:
     *
     * 1) Perform mergesort on the first half
     * 2) Perform mergesort on the second half
     * 3) Merge the two arrays
     */
    private static int[] mergesort(int[] arr) {
        // If we only have one element, then the array is sorted
        if (arr.length == 1) {
            return arr;
        }
        // Else, perform the merge sort algorithm described above
        else {
            int half = arr.length / 2;

            // Array for the first half
            int[] first = new int[half];
            for (int i = 0; i < first.length; i++) {
                first[i] = arr[i];
            }

            // Array for the second half
            int[] second = new int[arr.length - half];
            for (int i = 0; i < second.length; i++) {
                second[i] = arr[i + half];
            }

            return merge(mergesort(first), mergesort(second));
        }
    }

    /*
     * Given two sorted arrays, return a sorted array containing all elements from both arrays.
     */
    private static int[] merge(int[] first, int[] second) {
        int[] arr = new int[first.length + second.length];

        int firstIndex = 0;
        int secondIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            // If we have already used all the elements from the first array, then take from the second
            if (firstIndex >= first.length) {
                arr[i] = second[secondIndex];
                secondIndex++;
            }
            // If we have already used all the elements from the second array, then take from the first
            else if (secondIndex >= second.length) {
                arr[i] = first[firstIndex];
                firstIndex++;
            }
            // If the next element of the first is less than the next of the second, then take from the first
            else if (first[firstIndex] < second[secondIndex]) {
                arr[i] = first[firstIndex];
                firstIndex++;
            }
            // Otherwise, take from the second
            else {
                arr[i] = second[secondIndex];
                secondIndex++;
            }
        }

        return arr;
    }
}
