/*
explanation: fruit into baskets using sliding window algorithm! The program works by using sliding window and binary search to find for the best window size possible that gives minimum 2 distinct buckets! We perform bsearch on window size and iterative window size over the array with linear hashmap to find the bucket size possible!

testcase: [1,2,1] -> Works

Time & Space Complexity: O(nlogn) & O(1): Time complexity of this algorithm is nlogn, due to binary search time complexity is logn, while in each iteration we also iterate over array (hence time complexity linear), therefore overall tc is nlogn! Also space complexity is constant due finite variables stored!
*/
class Solution {    
    public int slidingWindowMin(int w, int[] fruits){
        if(w > fruits.length) return Integer.MAX_VALUE;
        // Build for initial hashmap
        HashMap<Integer, Integer> hash = new HashMap<>();
        int ans = Integer.MAX_VALUE;
        for(int i=0; i<w; i++){
            hash.put(fruits[i], hash.getOrDefault(fruits[i], 0)+1);
        }
        ans = Math.min(ans, hash.size());
        for(int i=w; i<fruits.length; i++){
            int add = fruits[i];
            int rem = fruits[i-w];
            
            hash.put(add, hash.getOrDefault(add, 0)+1);
            hash.put(rem, hash.getOrDefault(rem, 0)-1);
            
            if(hash.getOrDefault(rem, 0) <= 0){
                hash.remove(rem);
            }
            ans = Math.min(ans, hash.size());
        }
        return ans;
    }
    
    public int totalFruit(int[] fruits) {        
        int start = 0;
        int end = fruits.length;
        while(start <= end){
            int mid = (start + end)/2;
            // Check for sliding window size mid!
            int val = slidingWindowMin(mid, fruits);
            if(val > 2){
                end = mid - 1;
            } else if(val <= 2) {
                start = mid + 1;
            }
            
            // System.out.println("At "+mid+" val="+val+" start:"+start+", end:"+end);
            
            // if(val == 2) return mid;
        }
        
        return end;
    }
}