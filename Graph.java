package cpy;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import mathandalgorithm.ID0080;

public class Graph {
	
	
	//無向グラフの読み取り
	private static Map<Integer,HashSet<Integer>> readNonDirectGraph(int N,int M){
		
		Map<Integer,HashSet<Integer>> map = new HashMap<>();
		for(int i=1;i<=N;i++)
			map.put(i, new HashSet<>());
		for(int i=0;i<M;i++) {
			Integer u = sc.nextInt();
			Integer v = sc.nextInt();
			
			map.get(u).add(v);
			map.get(v).add(u);
		}
		return map;
	}
	
	//有向グラフの読み取り
		private static Map<Integer,Set<Integer>> readDirectGraph(int N,int M){
						
			Map<Integer,Set<Integer>> map = new HashMap<>();
			for(int i=1;i<N+1;i++) {
				map.put(i, new HashSet<Integer>());
			}
			for(int i=0;i<M;i++) {
				Integer u = sc.nextInt();
				Integer v = sc.nextInt();
				map.get(u).add(v);
			}
			return map;
		}
	
	//DFSサンプル(一筆書き　ABC054)
		private static int dfs(int N,Map<Integer,ArrayList<Integer>> map,boolean[] check,int next) {
			
			boolean allChecked = true;
			for(int i=1;i<N+1;i++) {
				if(check[i] == false) {
					allChecked = false;
					break;
				}
			}
			if(allChecked) return 1;
			
			int count = 0;
			ArrayList<Integer> list = map.get(next);
			for(int n:list) {
				if(check[n] == false) {
					check[n] = true;
					count +=dfs(N,map,check,n);
					check[n] = false;
				}
			}
			return count;
		}
	
	//DFSサンプル 連結検査 ABC075
	private static void dfs(int start,boolean[] visited,Map<Integer,ArrayList<Integer>> map,Set<Integer> set) {
			
		if(set.contains(start)) return ;
		set.add(start);
		ArrayList<Integer> value = map.get(start);
		for(int v:value) {
			if(visited[v]) continue;
			
			visited[v] = true;
			dfs(v,visited,map,set);
			visited[v] = false;
		}
	}
	
	//連結成分のそれぞれの要素数
    private static ArrayList<Integer> linkCount(Map<Integer,ArrayList<Integer>> graph) {
        ArrayList<Integer> result = new ArrayList<>();
        Set<Integer> visited = new TreeSet<>();
        for(int start:graph.keySet()) {
            if(visited.contains(start)) continue;
            visited.add(start);
            int count = 1;
            Deque<Integer> q = new ArrayDeque<>();
            q.add(start);
            while(!q.isEmpty()) {
                int now = q.poll();
                for(int n:graph.get(now)) {
                    if(visited.contains(n)) continue;
                    count++;
                    q.add(n);
                    visited.add(n);
                }
            }
            result.add(count);
        }
        return result;
    }
    
    //木の葉を探索
    private static Set<Integer> searchleaf(Map<Integer,ArrayList<Integer>> map,int root){
        Deque<Integer> q = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> leafs = new HashSet<>();
        q.add(root);
        visited.add(root);
        
        while(!q.isEmpty()){
            int now = q.poll();
            boolean flag = true;
            for(int e:map.get(now)){
                if(visited.contains(e)) continue;
                visited.add(e);
                flag= false;
                q.add(e);
            }
            if(flag) leafs.add(now);
        }
        return leafs;
     }
    
    //ダイクストラ法
    private static long[] Dijkstra(int start,int goal,ArrayList<Path>[] cost,int N) {
    	long[] d = new long[N+1];
		int[] pre = new int[N+1];
		Arrays.fill(d, Long.MAX_VALUE);
		d[start] = 0;
		
		boolean[] visited = new boolean[N+1];
		TreeSet<Integer> next = new TreeSet<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer x, Integer y) {
				return d[x] <= d[y] ? -1 : 1;
			}
		});
		next.add(start);
		while(!next.isEmpty()) {
			int u = next.pollFirst();
			if(visited[u]) continue;
			visited[u] = true;
			if(u == goal) break;
			for(Path k:cost[u]) {
				if(visited[k.to]) continue;
				long tmp = d[u] + k.c;
				if(tmp < d[k.to]) {
					d[k.to] = tmp;
					pre[k.to] = u;
					next.add(k.to);
				}
			}
		}
		return d;
    }
    private static long[] Dijkstra(int start,int goal,long[][] cost,int N) {
    	long[] d = new long[N+1];
		int[] pre = new int[N+1];
		Arrays.fill(d, Long.MAX_VALUE);
		d[start] = 0;
		
		boolean[] visited = new boolean[N+1];
		TreeSet<Integer> next = new TreeSet<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer x, Integer y) {
				return d[x] <= d[y] ? -1 : 1;
			}
		});
		next.add(start);
		while(!next.isEmpty()) {
			int u = next.pollFirst();
			if(visited[u]) continue;
			visited[u] = true;
			if(u == goal) break;
			for(int i=0;i<N;i++) {
				if(visited[i]) continue;
				long tmp = d[u] + cost[u][i];
				if(tmp < d[i]) {
					d[i] = tmp;
					pre[i] = u;
					next.add(i);
				}
			}
		}
		return d;
    }
    
    class Path{
    	int to;
    	long c;
    	Path(int to,long c){
    		this.to = to;
    		this.c = c;
    	}
    }
    
    //ワーシャルフロイド法
    private static long[][] Floyd_Warshall(long[][] cost){
    	int N = cost.length;
    	
    	for(int k=0;k<N;k++) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					cost[i][j] = Math.min(cost[i][j], cost[i][k]+cost[k][j]);
				}
			}
		}
    	return cost;
    }
    		
    class UnionFindD333 {
  	  private int[] root;
  	  private int connect;
  	  
  	  public void init(int sz){
  	    root = new int[sz+1];
  	    for(int i=0;i<=sz;i++)
  	    	root[i] = i;
  	    connect = sz;
  	  }
  	  
  	  public int root(int pos){
  	    if (root[pos] == pos) return pos;
  	    int pre = pos;
  	    int now = root[pos];
  	    Set<Integer> visited = new HashSet<>();
  	    while(pre != now) {
  	    	visited.add(pre);
  	    	pre = now;
  	    	now = root[pre];
  	    }
  	    for(int v:visited) root[v] = now;
  	    return now;
  	  }
  	  
  	  public void unite(int u, int v){
  	    u = root(u);
  	    v = root(v);
  	    if (u == v) return;
  	    root[u] = v;
  	    connect--;
  	  }
  	  
  	  public boolean same(int u, int v){
  	    if (root(u) == root(v)) return true;
  	    return false;
  	  }
  	  
  	  public int connect() {
  		  return connect;
  	  }
  }
	
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		PrintWriter output = new PrintWriter(System.out);
		
		Map<Integer,ArrayList<Integer>> map = readNonDirectGraph(30,10,sc);
		
		ArrayList<Integer> value = map.get(23);
		for(Integer i:value) {
			output.print(i+" ");
		}
		
		output.flush();
		sc.close();
	}

}
//2部グラフ判定
//連結代表要素一覧
//閉路判定
//閉路一覧
//カット点発見
//橋発見
