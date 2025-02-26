package cpy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Bits {
	//lenビットにN個の1があるビット列のリストを返す
	private static ArrayList<Integer[]> onezeroBits(int len,int N) {
		ArrayList<Integer[]> result = new ArrayList<>();
		Deque<Integer[]> q = new ArrayDeque<>();
		Deque<Integer[]> next = new ArrayDeque<>();
		Deque<Integer> end = new ArrayDeque<>();
		Integer[] start = new Integer[len];
		for(int i=0;i<len;i++) start[i] = 0;
		q.add(start);
		end.add(-1);
		
		for(int i=1;i<=N;i++) {
			while(!q.isEmpty()) {
				Integer[] arr = q.poll();
				int e = end.poll();
				for(int j=e+1;j<=len-1-(N-i);j++) {
					Integer[] tmp = arr.clone();
					tmp[j] = 1;
					next.add(tmp);
					end.add(j);
				}
			}
			q.clear();
			for(Integer[] n:next) q.add(n);
			next.clear();
		}
		for(Integer[] p:q) result.add(p);
		return result;
	}
	
	
}
