package cpy;

import java.io.PrintWriter;
import java.util.Scanner;

public class Matrix {
	static long mod = 1000000007L;
	static Scanner sc = new Scanner(System.in);
	static PrintWriter output = new PrintWriter(System.out);
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		long[][] a = {{1,1,0},{1,0,1},{1,0,0}};
		long[][] s = {{2,1,1},{1,1,0}}; 
		long N = sc.nextLong();
		long[][] result = matMul(s,nPowbMT(a,N-3));
		long ans = result[0][0];
		output.print(ans);
 		
		output.flush();
		sc.close();
	}
	//行列の掛け算
	public static long[][] matMul(long[][] a,long[][] b){
		long[][] ans = new long[a.length][b[0].length];
		for(int i=0;i<ans.length;i++) {
			for(int j=0;j<ans[0].length;j++) {
				long sum = 0;
				for(int k=0;k<a[0].length;k++) {
					sum += (a[i][k]*b[k][j])%mod;
					sum = sum%mod;
				}
				ans[i][j] = sum;
			}
		}
		return ans;
	}
	//行列の繰り返し二乗法
	public static long[][] nPowbMT(long[][] n,long b) {
		long[][] p = n;
		long[][] ans = new long[3][3];
		for(int i=0;i<3;i++)
			ans[i][i] = 1;
		
		while(0<b) {
			if((b&1) == 1) ans = matMul(ans,p);
			p = matMul(p,p);
			b = b>>1;
		}
		return ans;
	}
}