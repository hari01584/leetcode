/*
explanation: minimum days to make m boutique using binary search, the algorithm works by using boutique function which
calculates boutiques that can be created within time if specific day is waited, this parameter is then optimized using
binary search!

testcase: bloomDay = [1,10,3,10,2], m = 3, k = 1 -> Works

Time & Space Complexity: O(nlogn) & O(1): Time complexity is nlogn whereas space complexity is constant.
*/
class Solution {
    
    // A function to calculate how many boutiques can be made
    // when you choose to bloom at 'day'th day!
    public int boutique(int[] bloom, int day, int k) {
        int ans = 0;
        // Find minimum day to grow this boutique!
        int daycounted = 0;

        for (int i=0; i<bloom.length; i++) {
            int element = bloom[i];
            if (element > day) {
                // bad element found :(, find how many boutiques of paired k adjacent flowers can be made using it!
                ans += daycounted / k;
                daycounted = 0;
            } else {
                daycounted++;
            }
        }
        
        // Check remaining as well
        ans += daycounted / k;

        return ans;
    }

    public int minDays(int[] bloomDay, int m, int k) {
        int ans = -1;

        int start = 1;
        int end = (int)1e9;
        while (start <= end) {
            int mid = ((end - start) / 2) + start;
            int boutiques_prepared = boutique(bloomDay, mid, k);
            // System.out.println("At "+start+" "+end+" mid:"+mid+" boutiques:"+boutiques_prepared);
            if (boutiques_prepared < m) {
                // Increase days
                start = mid + 1;
            } else {
                ans = mid;
                end = mid - 1;
            }
        }
        
        return ans;
    }
}