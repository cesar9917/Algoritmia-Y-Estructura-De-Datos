package CortedeVarilla;

public class NaiveRodCutting {
	 static int getValue(int[] values, int length) {
	 if (length <= 0) return 0;
	 int max = Integer.MIN_VALUE;
	 for (int i = 0; i < length; i++) {
	 max = Math.max(max, values[i] + getValue(values, length - i - 1));
	 }
	 return max;
	 }
	 public static void main(String[] args) {
	 int[] values = {3, 7, 1, 3, 9};
	 int rodLength = values.length;
	 System.out.println("El valor máximo: " + getValue(values, rodLength));
	 }
}
