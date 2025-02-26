package cpy;
import java.util.ArrayList;
import java.util.Arrays;

public class Zentansaku {
	
	//順列全探索
    private static  ArrayList<Integer[]> permutation(int[] seed) {
        Integer[] perm = new Integer[seed.length];
        boolean[] used = new boolean[seed.length];
        ArrayList<Integer[]> result = new ArrayList<>();
        buildPerm(seed, perm, used, 0,result);
        
        return result;
    }

    private static void buildPerm(int[] seed, Integer[] perm, boolean[] used, int index,ArrayList<Integer[]> result) {
        if (index == seed.length) {
        	Integer[] tmp = new Integer[seed.length];
        	for(int i=0;i<seed.length;i++)
        		tmp[i] = perm[i];
            result.add(tmp);
            return;
        }

        for (int i = 0; i < seed.length; i++) {
            if (used[i])
                continue;
            perm[index] = seed[i];
            used[i] = true;
            buildPerm(seed, perm, used, index + 1,result);
            used[i] = false;
        }
    }
    
    //二分探索 (ソートした配列に対し、入力値未満の値がいくつあるか出力)
    private static long twoSearch(long N,int[] B) {
    	int M = B.length;
    	
    	long x = 0,y = M-1;
    	
    	if(N < B[0]) y = 0;
		else if(B[M-1] < N) y = M;
		else {
			while(true) {
				if(y - x <= 1) {
					break;
				}
				
				long next = (x+y)/2;
				if(B[(int)next] < N) {
					x = next;
				}
				else {
					y = next;
				}
			}
		}
    	return y;
    }
    
  //q以下の要素で最大のもの(index)を探す二分探索
    public static int bitSearchUpper(long[] key,long q) {
        int a = 1,b = key.length-1;
        int ans = -1;
        while(a<=b) {
            int c = (a+b)/2;
            if((long)key[c-1]<=q && (long)key[c]>q) {
                //一致したときの処理
                ans = c-1;
                break;
            }
            if(a==b) break;
            else {
                if((long)key[c-1]>q) b = c-1;
                else if((long)key[c]<=q) a = c+1;
            }
        }
        //末尾の要素が一致するとき
        if(ans == -1 && (long)key[key.length-1]<=q)
            ans = key.length-1;

        return ans;
    }
    
  //二分探索 (ソートした配列に対し、入力値以下の値がいくつあるか出力)
    private static long twoSearch(long N,Object[] B) {
    	int M = B.length;
    	
    	long x = 0,y = M-1;
    	
    	if(N < (long)B[0]) y = 0;
		else if((long)B[M-1] < N) y = M;
		else {
			while(true) {
				if(y - x <= 1) {
					break;
				}
				
				long next = (x+y)/2;
				if((long)B[(int)next] < N) {
					x = next;
				}
				else if((long)B[(int)next] == N) {
					y = next+1;
					break;
				}
				else {
					y = next;
				}
			}
		}
    	return y;
    }

    public static void main(String[] args) throws Exception {
    	ArrayList<Integer[]> ans  = permutation(new int[] { 1, 2, 3 });
        
        for(Integer[] a: ans) {
        	System.out.println(Arrays.toString(a));
        }
    }
}


