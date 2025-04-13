package Subconjuntos;

import java.util.ArrayList;
import java.util.List;

public class SubconjuntoPotenciasRestringidas {

    public static boolean subsetSumWithRestrictions(int[] arr) {
        int n = arr[0];
        if (arr.length < n + 2) {
            System.err.println("Error: El array de entrada no tiene la longitud esperada.");
            return false; // O lanza una excepción más apropiada
        }
        List<Integer> numsList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numsList.add(arr[i]);
        }
        int goal = arr[n + 1];
        List<Integer> nums = numsList;

        List<Integer> powersOfTwo = new ArrayList<>();
        int initialSum = 0;
        List<Integer> otherNumbers = new ArrayList<>();

        for (int num : nums) {
            if ((num & (num - 1)) == 0 && num != 0) {
                powersOfTwo.add(num);
                initialSum += num;
            } else {
                otherNumbers.add(num);
            }
        }

        if (initialSum > goal) {
            return false;
        }

        List<Integer> restrictedOtherNumbers = new ArrayList<>();
        for (int i = 0; i < otherNumbers.size(); i++) {
            int num = otherNumbers.get(i);
            if (num % 5 == 0 && i + 1 < otherNumbers.size() && otherNumbers.get(i + 1) % 2 != 0) {
                continue;
            }
            restrictedOtherNumbers.add(num);
        }

        return canSum(restrictedOtherNumbers, 0, initialSum, goal);
    }

    private static boolean canSum(List<Integer> nums, int index, int currentSum, int target) {
        if (currentSum == target) {
            return true;
        }
        if (index == nums.size() || currentSum > target) {
            return false;
        }

        // Include the current number
        if (canSum(nums, index + 1, currentSum + nums.get(index), target)) {
            return true;
        }

        // Exclude the current number
        if (canSum(nums, index + 1, currentSum, target)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(subsetSumWithRestrictions(new int[]{5, 4, 8, 10, 3, 5, 27}));
        System.out.println(subsetSumWithRestrictions(new int[]{5, 4, 8, 10, 3, 6, 27}));
        System.out.println(subsetSumWithRestrictions(new int[]{6, 2, 16, 5, 7, 10, 33}));
        System.out.println(subsetSumWithRestrictions(new int[]{6, 2, 16, 5, 3, 10, 33}));
        System.out.println(subsetSumWithRestrictions(new int[]{4, 2, 5, 1, 6, 13}));
    }
}