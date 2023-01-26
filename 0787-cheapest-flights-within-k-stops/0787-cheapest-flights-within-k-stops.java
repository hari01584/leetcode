/*
explanation: cheapest flight using dp and memoization! The program works by using recursive dp to find the minimum possible path sourcing from source to dest, and caching this into dp array!

testcase: 
4
[[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]]
0
3
1 -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity of this algorithm is linear, this is due to iteration of each node once and using dp!
*/
class Solution {
    class E {
        int from;
        int to;
        int cost;
        
        E(int f, int t, int c){
            from = f;
            to = t;
            cost = c;
        }
        
        @Override
        public String toString(){
            return "{" + from + ", " + to + ", " + cost + "}";
        }
    }

    HashMap<Integer, List<E>> map = new HashMap<>();

    int[][] flights;
    int src;
    int dst;
    int k;
    int[][] dp;
    
    public int dymincost(int n, int k){
        if(k < 0) return Integer.MAX_VALUE;
        if(n == dst) return 0;
        
        if(dp[n][k] != 0) return dp[n][k];
        // System.out.println("Call "+n+" with k:"+k);
        
        List<E> neigh = map.getOrDefault(n, new ArrayList<>());
        int min = Integer.MAX_VALUE;
        for(E e : neigh){
            int cst = dymincost(e.to, k-1);
            if(cst == Integer.MAX_VALUE) continue;
            min = Math.min(min, e.cost + cst);
        }
        
        // System.out.println("At "+n+" with steps: "+k+" min:"+min);
        return dp[n][k]=min;
    }
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        this.flights = flights;
        this.src = src;
        this.dst = dst;
        this.k = k;
        
        dp = new int[n][k+2];
        
        for(int[] f : flights){
            List<E> lst = map.getOrDefault(f[0], new ArrayList<>());
            lst.add(new E(f[0], f[1], f[2]));
            map.put(f[0], lst);
        }
        
        int ans = dymincost(src, k+1);
        if(ans == Integer.MAX_VALUE) return -1;
        return ans;
    }
}