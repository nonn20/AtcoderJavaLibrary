package cpy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Mod {
	long mod;
	ArrayList<Long> bits = new ArrayList<>();
	Map<Long,Long> cache = new HashMap<>();
	
	Mod(long m){
		this.mod = m;
		long tmp = m-2;
		while(tmp != 0) {
			bits.add((long) (tmp&1));
			tmp = tmp>>1;
		}
	}
	
	public long div(long n,long b) {
		if(cache.containsKey(b)) return this.mod(n*cache.get(b));
		
		long B = 1,np = b;
		for(long v:bits) {
			if(v==1) B = this.mod(B*np);
			np = this.mod(np*np);
		}
		cache.put(b, B);
		return this.mod(n*B);
	}
	
	public long mod(long n) {
		return n%this.mod;
	}
}

class Mods{
	public static long nPowb(long n,long b,long mod) {
		long p = n;
		long ans = 1;
		while(0<b) {
			if((b&1) == 1) ans = (ans * p)%mod;
			p = (p*p)%mod;
			b = b>>1;
		}
		return ans;
	}
}


