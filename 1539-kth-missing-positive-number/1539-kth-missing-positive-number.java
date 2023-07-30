/*
explanation: kth missing using binary search, the algorithm works by using bsearch and properties of number to guess how
many missing number would be there at current index, using it we reach to the missing and add some more elements.

testcase: [2,3,4,7,11], 5 -> Works

Time & Space Complexity: O(logn) & O(1): Time complexity is logn due to simple binary search, space complexity is constant
due to countable variables used.
*/
class Solution {
    public int findKthPositive(int[] arr, int k) {
        int lastMissing = -1;
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int kthmissing = arr[mid] - mid - 1;
            // System.out.println("At "+start+" "+end+" mid: "+mid+" kthmissing:"+kthmissing);


            if (kthmissing < k) {
                lastMissing = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        if (lastMissing == -1) {
            return k;
        }

        int lastElement = arr[lastMissing];
        int how_many_missing_before_this = arr[lastMissing] - lastMissing - 1;
        int remaining = k - how_many_missing_before_this;
        int ans = lastElement + remaining;
        // This would be our last point of contact, after this element we will go remaining missing to find our answer!
        // System.out.println("lastElement: "+lastElement+" "+" howmanymiss:"+how_many_missing_before_this+" remain:"+remaining+" ans:"+ans);
        return ans;
    }
}