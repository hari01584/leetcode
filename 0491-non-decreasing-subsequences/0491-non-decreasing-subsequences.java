class Solution {
    Set<ArrayList<Integer>> anset = new HashSet<>();
    List<List<Integer>>[][] dp;
    
    int MAX_LEN;
    int[] nums;
    
    public String twoDPrint(List<List<Integer>> myBoard){
      String result = "";
      for(int i = 0; i < myBoard.size(); i++){
          System.out.println(Arrays.toString(myBoard.get(i).toArray()));
      }
      return result;
    }
    
    public List<List<Integer>> dysub(int n, int gt){
        if(n >= MAX_LEN) return new ArrayList<>();
        
        int limit = nums[n];
        List<List<Integer>> nondecr = new ArrayList<>();
        // List<List<Integer>> nondecr_exclude = dysub(n+1, -1);
        for(int i=n+1; i<MAX_LEN; i++){
            List<List<Integer>> l = dysub(i, n);
            nondecr.addAll(l);
        }
        
        // nondecr.addAll(nondecr_exclude);
        // System.out.println("Initially list: "+twoDPrint(nondecr));
        for(List<Integer> lst : nondecr){
            if(limit <= lst.get(0)){
                lst.add(0, limit);
                
                if(lst.size()>=2){
                    anset.add(new ArrayList<>(lst));
                }
            }
        }
        
        // Add last element
        if(limit >= nums[gt]){
            nondecr.add(new ArrayList<>(Arrays.asList(limit)));
        }
        
        // System.out.println("At "+n+" with params " + limit + " should be gt " + gt + " we are retur: "+twoDPrint(nondecr));
        return nondecr;
    }
    
    public List<List<Integer>> findSubsequences(int[] nums) {
        this.nums = nums;
        // this.dp = new 
        MAX_LEN = nums.length;
        // dp = new List<List<Integer>>[16][16];
        
        dysub(0, 0);
        
        List<List<Integer>> ret = new ArrayList<>();
        ret.addAll(anset);
        
        return ret;
    }
}