package cpy;


import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class PrimeNumber {
	
	
	//N以下の素数
	private static Set<Integer> primeNumber(int N){
		
		Set<Integer> prime = new TreeSet<>();
		prime.add(2);
		for(int i=3;i<N+1;i+=2) {
			boolean flag = false; 
			for(int j=2;j<=Math.sqrt(i);j++) {
				if(i%j == 0) {
					flag = true;
					break;
				}
			}
			if(!flag) prime.add(i);
		}
		return prime;
	}
	//実行時間 N=100000 600ms～900ms
	
	
	//Nを素因数分解
		private static Map<Integer,Integer> primeFactor(long N,Set<Integer> prime) {
			Map<Integer,Integer> list = new HashMap<>();
					
			long tmp = N;
			int sqrt = (int)Math.sqrt(N);
			for(int p:prime) {
				if(sqrt<p) break;
				if(tmp % p == 0) {
					int count = 0;
					while(true) {
						if(tmp%p == 0) {
							count++;
							tmp/=p;
						}
						else break;
					}
					list.put(p,count);
				}
				if(tmp == 1) break;
			}
			if(tmp != 1&&tmp!=0) list.put((int)tmp, 1);
				
			return list;
		}
	
	//ユーグリット
	private static long Ugrid(long a,long b) {
		if(b<a) {
			long tmp = a;
			a = b;
			b = tmp;
		}
		long r = a%b;
		while(r !=0) {
			a = b;
			b =r;
			r = a%b;
		}
		return b;
	}
	
	//aとbの最小公倍数
	private static long lcm(long a,long b) {
		long gcm = Ugrid(a,b);
		long ans = gcm*(a/gcm)*(b/gcm);
		return ans;
	}
	
	static long MOD = 1000000007;
	static long MAX = 666667;
	// テーブルを作る前処理
		static void COMinit(long[] fac, long[] finv,long[] inv) {
		    fac[0] = fac[1] = 1;
		    finv[0] = finv[1] = 1;
		    inv[1] = 1;
		    for (int i = 2; i < MAX; i++){
		        fac[i] = fac[i - 1] * i % MOD;
		        inv[i] = MOD - inv[(int)MOD%i] * (MOD / i) % MOD;
		        finv[i] = finv[i - 1] * inv[i] % MOD;
		    }
		}
		
		// 二項係数計算
		static long COM(int n, int k){
			long[] fac = new long[(int)MAX];
			long[] finv = new long[(int)MAX];
			long[] inv = new long[(int)MAX];
			COMinit(fac,finv,inv);
			
		    if (n < k) return 0;
		    if (n < 0 || k < 0) return 0;
		    return fac[n] * (finv[k] * finv[n - k] % MOD) % MOD;
		}
	
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		PrintWriter output = new PrintWriter(System.out);

		long N = sc.nextLong();
		
		long startTime = System.currentTimeMillis();
		
		
		Set<Integer> prime = primeNumber(3162);
		output.println(prime.size());
		Map<Integer,Integer> primeFactor = primeFactor(16575647,prime);
		for(int k:primeFactor.keySet()) {
			output.println(k+" "+primeFactor.get(k));
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("処理時間：" + (endTime - startTime) + " ms");
		
		output.flush();
		sc.close();
	}
}