// There are n stairs, a person standing at the bottom wants to reach the top. 
// The person can climb either 1 stair or 2 stairs at a time. Count the number of 
// ways, the person can reach the top.
// Input: n = 1
// Output: 1
// There is only one way to climb 1 stair
// Input: n = 2
// Output: 2
// There are two ways: (1, 1) and (2)

import java.util.*;

class CountWaysToReachNthStair {

	static long[][] mul(long[][] A, long[][] B, long MOD) {
		int K = A.length;
		long[][] C = new long[K][K];
		for (int i = 1; i < K; i++)
			for (int j = 1; j < K; j++)
				for (int k = 1; k < K; k++)
					C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
		return C;
	}

	static long[][] power(long[][] A, long n) {
		if (n == 1)
			return A;
		if (n % 2 != 0) {
		
			A = mul(A, power(A, n - 1), 1000000007);
		}
	else {
			A = power(A, n / 2);
			A = mul(A, A, 1000000007);
		}
		return A;
	}

	static long ways(int n) {
		long[] F = new long[3];
		F[1] = 1;
		F[2] = 2;
		int K = 2;
		long MOD = 1000000007;
	
		long[][] C = new long[K + 1][K + 1];
		for (int i = 1; i < K; ++i) {
			C[i][i + 1] = 1;
		}
		C[K][1] = 1;
		C[K][2] = 1;

		if (n <= 2)
			return F[n];

		C = power(C, n - 1);

		long result = 0;

		for (int i = 1; i <= K; ++i) {
			result = (result + C[1][i] * F[i]) % MOD;
		}
		return result;
	}

	public static void main(String[] args) {
		int n = 4;
		System.out.print("Number of ways = " + ways(n) + "\n");
	}
}


