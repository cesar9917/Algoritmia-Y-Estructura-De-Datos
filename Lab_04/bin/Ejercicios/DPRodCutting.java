package CortedeVarilla;

public class DPRodCutting {
	 static int getValue(int[] values, int rodLength) {
	 int[] dp = new int[rodLength + 1];
	 for (int i = 1; i <= rodLength; i++) {
	 int max = Integer.MIN_VALUE;
	 for (int j = 0; j < i; j++) {
	 max = Math.max(max, values[j] + dp[i - j - 1]);
	 }
	 dp[i] = max;
	 }
	 return dp[rodLength];
	 }
	 public static void main(String[] args) {
	 int[] values = {3, 7, 1, 3, 9};
	 int rodLength = values.length;
	 System.out.println("El valor máximo: " + getValue(values, rodLength));
	 }
	}

