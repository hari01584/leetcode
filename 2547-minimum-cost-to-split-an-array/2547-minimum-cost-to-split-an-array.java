class Solution {
    int[] nums;
    int ktarg;
    int[][] cost_cache;
    int[] dp;
    
    public int dycost(int n){
        if(n >= nums.length) return 0;
        if(dp[n] != -1) return dp[n];
        // System.out.println("finding for "+n);
        int cost = cost_cache[n][nums.length-1];
        for(int i=n+1; i<nums.length; i++){
            int cst = dycost(i);
            // System.out.println("At "+n+" and part: " + i + " we return: "+cst);
            cost = Math.min(cost,  cost_cache[n][i-1] + cst);
        }
        // System.out.println("At "+n+" we return: "+cost);
        return dp[n] = cost;
    }
    
    public int minCost(int[] nums, int k) {
        this.nums = nums;
        this.ktarg = k;
        this.dp = new int[nums.length];
        Arrays.fill(dp, -1);
        
        cost_cache = new int[nums.length][nums.length];

        for(int i=0; i<nums.length; i++){
            cost_cache[i][i] = k;
            HashMap<Integer, Integer> cache = new HashMap<>();
            cache.put(nums[i], 1);
            int cost = 0;
            
            for(int j=i+1; j<nums.length; j++){
                int val = nums[j];
                if(cache.containsKey(val)){
                    // Already contained, ie good, ie cost++
                    if(cache.get(val) == 1) cost += 2;
                    else cost+=1;
                    
                }
                cache.put(val, cache.getOrDefault(val,0)+1);
                
                cost_cache[i][j] = k + cost;
            }
        }
        
        // System.out.println(Arrays.deepToString(cost_cache).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

        
        return dycost(0);
    }
}