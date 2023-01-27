class Solution {
    int[] boardmap;
    int[] dp;
    
//     public int dysnakestep(int n){
//         if(n == boardmap.length-1) return 0;
//         if(n > boardmap.length-1) return 100000; // Overbound
//         if(dp[n] != -1) return dp[n];
        
//         // System.out.println("Calling for "+n);

//         // Start at n and find paths!
//         int min = 100000;
//         for(int i=n+1; i<=n+6; i++){
//             int target = i;
//             if(i >= boardmap.length) break;
//             if(boardmap[target] != -1) target = boardmap[target];
//             // System.out.println("Checking for "+i+" bmt:"+boardmap[i]);
//             if(target < i) continue; // Skip snakes
//             int ss = 1 + dysnakestep(target);
            
//             min = Math.min(min, ss);
//         }
        
//         // System.out.println("At "+n+" return minsteps: "+min);
//         return dp[n]=min;
//     }
    
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boardmap = new int[n*n + 1];
        dp = new int[boardmap.length+1];
        Arrays.fill(dp, -1);
        
        boolean linear = n % 2 == 0;
        
        int c = n*n;
        for(int[] b : board){
            if(linear){
                for(int i=0; i<b.length; i++){
                    boardmap[c--] = b[i];
                }
            } else {
                for(int i=b.length-1; i>=0; i--){
                    boardmap[c--] = b[i];
                }
            }
            linear = !linear;
        }
        
        // for(int i=1; i<= n*n; i++){
        //     if(boardmap[i] == -1) continue;
        //     System.out.println(i+" maps to "+boardmap[i]);
        // }
        
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int[] reachcost = new int[boardmap.length];
        Arrays.fill(reachcost, Integer.MAX_VALUE);
        reachcost[1] = 0;
        queue.offer(1);
        
        while(queue.size() > 0){
            int p = queue.poll();
            // Check for reachable nodes and set values!
            int curcost = reachcost[p];
            // System.out.println("Processing "+p+" with current reach: "+curcost);

            for(int i=p+1; i<=p+6; i++){
                if(i>n*n) break;
                int ncost = curcost + 1;
                // Find target node
                int target = i;
                if(boardmap[i] != -1) target = boardmap[i];
                
                // Check if this cost better
                if(ncost < reachcost[target]){
                    // Yes. add it to queue!
                    reachcost[target] = ncost;
                    queue.add(target);
                }
            }
        }
        // return ans;
        
        int ans = reachcost[n*n];
        if(ans == Integer.MAX_VALUE) return -1;
        return ans;
    }
}