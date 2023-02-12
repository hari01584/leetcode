/*
explanation: count number of pairs using sortedlist and binarysearches! The program works by iterating over nums element by element and for each assuming the value to be j find using lower and upper bounds the element ranges for each of items! and finally giving answer

testcase:[0,1,7,4,4,5]
3
6 -> Works

Time & Space Complexity: O(nlogn) & O(n): Time complexity is nlogn due to iterating over each element and using logn binary searches for finding bounds! Also space complexity is linear due to storage required to save arraylist!
*/
class Solution {
    public class SortedList<E extends Comparable<E>> extends ArrayList<E> {

        @Override
        public boolean add(E e) {
            int index = Collections.binarySearch(this, e);
            super.add(index < 0 ? ~index : index, e);
            return true;
        };
    }
    
    public int strictly_smaller_than(int e, SortedList<Integer> slist){
        int start = 0;
        int end = slist.size()-1;
        int result = -1;
        
        while(start <= end){
            int mid = start + (end - start)/2;
            if(slist.get(mid) < e){
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        return result;
    }
    
    public int strictly_greater_than(int e, SortedList<Integer> slist){
        int start = 0;
        int end = slist.size()-1;
        int result = -1;
        
        while(start <= end){
            int mid = start + (end - start)/2;
            if(slist.get(mid) < e){
                start = mid + 1;
            } else {
                result = mid;
                end = mid - 1;
            }
        }
        
        return result;
    }
    
    public long countFairPairs(int[] nums, int lower, int upper) {
        SortedList<Integer> elements = new SortedList<Integer>();
        long ans = 0;
        for(int j=0; j<nums.length; j++){
            int jval = nums[j];
            // Get new range to check elements
            int l = lower - jval;
            int u = upper - jval;
            
            int idx = strictly_greater_than(l, elements);
            int udx = strictly_smaller_than(u+1, elements);
            
            if(idx != -1 && udx != -1){
                // System.out.println("Adding "+(udx-idx+1));
                ans += udx - idx + 1;
            }
     
            // System.out.println("At j="+jval+" ar: "+Arrays.toString(elements.toArray())+" strictly_greater_than:"+(l)+" is "+idx+" strictly_smaller_than:"+u+" is "+udx);
            
            elements.add(jval);
        }
        
        return ans;
    }
}