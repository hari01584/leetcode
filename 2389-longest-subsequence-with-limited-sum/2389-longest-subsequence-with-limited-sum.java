/*
explanation: longest subsequence using bsearch/sorting! The program works by first sorting the array and then after calculating longest prefix sum it simply finds the lps where the sum is barely less than the threshold, finding and returning its value.
*/
class Solution {
    class I{
        int item;
        int pos;
        
        I(int i, int p){
            item = i;
            pos = p;
        }
        
        public static int compare(I i1, I i2){
            return i1.item - i2.item;
        }
    }
    
    
    public int bSearch(int item, int[] arr){
        int s=0;
        int e=arr.length-1;
        while(s <= e){
            int m = (s+e)/2;
            int md = arr[m];
            if(item < md){
                e = m-1;
            } else {
                s = m+1;
            }
        }
        return e;
    }
    
    public int[] answerQueries(int[] nums, int[] queries) {
        I[] items = new I[nums.length];
        for(int i=0; i<items.length; i++){
            items[i] = new I(nums[i], i);
        }
        
        // Sorting the array elements
        Arrays.sort(items, I::compare);
        
        // Find prefix-sum-array
        int[] psa = new int[nums.length];
        psa[0] = items[0].item;
        for(int i=1; i<items.length; i++){
            psa[i] = psa[i-1] + items[i].item;
        }
        
        int[] ans = new int[queries.length];
        System.out.println(Arrays.toString(psa));
        for(int i=0; i<queries.length; i++){
            int q = queries[i];
            // Find binary search to get item
            int indx = bSearch(q, psa);
            if(indx == -1){
                ans[i] = 0;
                continue;
            }
            ans[i] = indx+1;
            // System.out.println("item "+q+" found at "+indx);
            // iterate thru index and 
        }
        
        return ans;
    }
}