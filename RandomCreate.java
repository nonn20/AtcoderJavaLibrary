package cpy;

import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class RandomCreate {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		PrintWriter output = new PrintWriter(System.out);
		
		 Random rand = new Random();
		 for(int i=0;i<2000;i++) {
			 int num = rand.nextInt(2000) + 1;
			 output.print(num+" ");
		 }
		output.println(); 
		for(int i=0;i<2000;i++) {
			 int num = rand.nextInt(2000) + 1;
			 output.print(num+" ");
		 }
		
		output.flush();
		sc.close();
	}
}
