package cpy;

import java.util.HashSet;

class SegTree{
	long[] val;
	int N;
	int n2;
	
	SegTree(int N){
		this.N = N;
		int n2 = 1;
		while(n2<N) n2 *= 2;
		this.val = new long[n2*2 - 1];
		this.n2 = n2;
	}
	public void setVal(int index,long v) {
		this.val[index] = v;
	}
	public long get(int index) {
		return this.val[index];
	}
	//葉のn番目のindexを返す
	public int valIndex(int n) {
		return this.n2-1+n;
	}
	//子から親へたどっていく sumの部分は変えてもよい
	public long segToTop(int T){
		int now = this.n2-1+T;
		long sum = 0;
		while(0 <= now) {
			sum += this.val[now];
			if(now == 0) break;
			now = (now-1)/2;
		}
		return sum;
	}
	//区間加算
	public void addToSeg(int v,int L,int R) {
		int size = this.n2;
		int len = 0;
		HashSet<Integer> now = new HashSet<>();
		
		now.add(0);
		while(!now.isEmpty()) {
			HashSet<Integer> next = new HashSet<>();
			for(int n:now) {
				if(R < size*(n-len) ||  size*(n-len+1)-1< L||this.val.length<=n) continue;
				if(L <= size*(n-len) &&  size*(n-len+1)-1 <= R) {
					val[n]+=(long)v;
					//System.out.println(n+" "+L+" "+R+" !!!!");
				}
				else {
					next.add(n*2+1);
					next.add(n*2+2);
				}
			}
			len = len*2+1;
			size = size/2;
			now = next;
		}
	}
}
