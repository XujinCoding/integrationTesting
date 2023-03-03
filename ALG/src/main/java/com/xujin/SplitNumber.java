package com.xujin;

import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author XuJ
 */
public class SplitNumber {
    private static final List<Integer> FIB = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        getFib(n);
        System.out.println(split(n, k) ? "Yes" : "No");
    }

    private static void getFib(int n) {
        int a = 0, b = 1, c = 1;
        FIB.add(b);
        while (c <= n) {
            FIB.add(c);
            a = b;
            b = c;
            c = a + b;
        }
    }

    private static boolean split(int n, int k) {
        if (n < k) {
            return false;
        }
        if (n == k) {
            return true;
        }
        while (n > 0 && k > 0) {
            for (int i = FIB.size() - 1; i >= 1; i--) {
                if (n >= FIB.get(i)) {
                    n -= FIB.get(i);
                    k--;
                    break;
                }
            }
        }
        return n == 0 && k >= 0;
    }
}
