package com.xujin;

import org.springframework.util.StopWatch;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FibonacciSplit {
    public static String isDivisible(int N, int K) {
        int[] fib = new int[1005];
        fib[0] = 0;
        fib[1] = 1;
        int idx = 2;
        while (fib[idx - 1] <= N) {
            fib[idx] = fib[idx - 1] + fib[idx - 2];
            idx++;
        }
        boolean[][] dp = new boolean[N + 1][K + 1];
        dp[0][0] = true;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                for (int k = 1; fib[k] <= i && k < idx; k++) {
                    dp[i][j] |= dp[i - fib[k]][j - 1];
                }
            }
        }
        return dp[N][K] ? "Yes" : "No";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        System.out.println(isDivisible(N, K));
    }

}