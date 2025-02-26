package cpy;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StringPress {
	
	
	//aaaaabbccc → abc
	private static ArrayList<Character> PressChar(char[] S) {
		ArrayList<Character> list = new ArrayList<>();
		
		char pre = S[0];
		list.add(S[0]);
		
		for(int i=0;i<S.length;i++) {
			if(pre != S[i]) {
				list.add(S[i]);
				pre = S[i];
			}
		}
		
		return list;
	}
	
	//aaaaabbccc → 5,2,3
	private static ArrayList<Integer> PressInt(char[] S){
		ArrayList<Integer> list = new ArrayList<>();
		
		char pre = S[0];
		int count= 0;
		for(int i=0;i<S.length;i++) {
			if(pre != S[i]) {
				list.add(count);
				pre = S[i];
				count = 1;
			}
			else {
				count++;
			}
		}
		list.add(count);
		
		return list;
	}
	
	//aaaaabbccc → a5,b2,c3
	private static Object[][] PressCharInt(char[] S){
		ArrayList<Integer> IntList = PressInt(S);
		ArrayList<Character> CharList = PressChar(S);
		
		int s = CharList.size();
		
		Object[][] list = new Object[s][2];
		for(int i=0;i<s;i++) {
			list[i][0] = CharList.get(i);
			list[i][1] = IntList.get(i);
		}
		
		return list;
	}
	
	//aaaaabbccc → ab, bc
	private static String[] StringChange(char[] S){
		ArrayList<Character> CharList = PressChar(S);
		
		int s = CharList.size();
		String[] list = new String[s-1];
		
		for(int i=1;i<s;i++) {
			char value1 = CharList.get(i-1);
			char value2 = CharList.get(i);
			
			StringBuilder sb = new StringBuilder();
			sb.append(value1);
			sb.append(value2);
			list[i-1] = sb.toString();
		}
		
		return list;
	}
	
	//aaaaabbccc →　5, 7
	private static int[] StringChangePoint(char[] S){
		ArrayList<Integer> IntList = PressInt(S);
		int s =  IntList.size();
		int[] list = new int[s-1];
		
		int tmp = 0;
		
		for(int i=0;i<s-1;i++) {
			int value = IntList.get(i);
			list[i] = value+tmp;
			tmp += value;
		}
		
		return list;
	}
	
	//aaaaabbcccaaabb → (a,8),(b,4),(c,3)
	private static Map<Character,Integer> CountChar(char[] S){
		
		Map<Character,Integer> map = new HashMap<>();
		
		for(int i=0;i<S.length;i++) {
			if(map.containsKey(S[i])) {
				int value = map.get(S[i]);
				map.replace(S[i], value+1);
			}
			else {
				map.put(S[i], 1);
			}
		}
		return map;
	}
	
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		PrintWriter output = new PrintWriter(System.out);
		
		char[] in = sc.next().toCharArray();
		
		Map<Character,Integer> result = CountChar(in);
		for(char key:result.keySet()) output.println(key+" "+result.get(key));
		
		output.flush();
		sc.close();
	}

}

//文字列短縮(ここからここまでを削除)
//回文判定
