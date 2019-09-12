package com.jinglitong.shop.controller;

import java.util.Scanner;

public class Ideone {

	int findmax(int A[]) {
		int max = A[0];
		for (int i = 0; i < A.length; i++) {
			if (A[i] > max)
				max = A[i];
		}
		return max;
	}

	boolean IsPrime(int num) {
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				System.out.println("不是素数" + num);
				return false;
			}
		}
		// System.out.println("是素数"+num);
		return true;
	}

	public static void main(String args[]) {

		Ideone m = new Ideone();
		int a[] = new int[100];
		int prime[] = new int[1000];

		Scanner sc = new Scanner(System.in);
		int q;
		q = sc.nextInt();
		for (int i = 0; i < q; i++)
			a[i] = sc.nextInt();
		int num = 0;
		int c = 2;
		//System.out.println(m.findmax(a));// 数组中最大的k
		while (num < q + m.findmax(a)) {// 只循环到最大的k值，这样就不用担心次数过多或者不够
			if (m.IsPrime(c)) {
				prime[++num] = c;
			}
			++c;
		}
		for (int j = 0; j < q; j++)
			System.out.println(prime[a[j]]);
	}
}
