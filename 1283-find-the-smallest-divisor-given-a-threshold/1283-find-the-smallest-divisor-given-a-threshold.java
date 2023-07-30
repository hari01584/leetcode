/*
explanation: find smallest divisor using binary search algorithm! The program works by using bsearch to progressively scan
an interval looking for smallestDivisor using calcSum function, It uses simulation + binary search to achieve this result

testcase: [1,2,5,9], 6 -> Works

Time & Space Complexity: O(nlogn) & O(1): Time complexity is nlogn due to logn for binary search, and n for checking for
simulation inside loop! Space complexity is constant due to no extra variables required.
*/
class Solution {
    public int calcSum(int[] arr, int divisor) {
        // To calculate sum here, first divide element by divisor, take its ceil and add to results
        int ans = 0;
        for (int i=0; i<arr.length; i++) {
            ans += (int)Math.ceil(arr[i] / (divisor * 1.0));
        }
        return ans;
    }
    public int smallestDivisor(int[] nums, int threshold) {
        int start = 1;
        int end = (int)1e6;
        int ans = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int sum = calcSum(nums, mid);
                        // System.out.println("start:"+start+", end:"+end+" mid:"+mid+" sum:"+sum);

            // If this sum, is greater less than or equal to threshold take it, and check for right
            if (sum <= threshold) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        
        return ans;
    }
}