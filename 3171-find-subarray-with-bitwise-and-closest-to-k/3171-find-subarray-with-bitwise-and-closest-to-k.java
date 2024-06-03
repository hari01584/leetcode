/*
explanation: find subarray with bitwise AND using dp! The approach works by exploiting the property
of AND which simply set's off a bit, once a bit is set-off (ie 0'ed), then it doesn't matter what
comes after that, It will always be same. Using the same, it can be theoretically proved that this set
will have max 30 elements, Using the same and consequtively AND'in each number with set elements will give
our answer.

Time & Space Complexity: O(n) & O(n): Time complexity is linear due to inner loop running atmost 30 times, while space complexity is also linear due to storing set of max length 30 for each elements in input!
*/
class Solution {
    public int minimumDifference(int[] nums, int k) {
        int answer = Math.abs(k - nums[0]);

        ArrayList<Set<Integer>> dp = new ArrayList<Set<Integer>>();
        // Our dp array!

        dp.add(new HashSet<>(Arrays.asList(nums[0])));
        
        for (int i=1; i<nums.length; i++) {
            int curNum = nums[i];
            // First take previous elements of this set..
            Set<Integer> prev = dp.get(i-1);
            prev.add(curNum); // Singleton can be answer too
    
            Set<Integer> newset = new HashSet<>();
            for (Integer pv : prev) {
                Integer hash = pv & curNum;
                answer = Math.min(answer, Math.abs(k - hash));
                newset.add(hash);
            }
            
            // System.out.println("At "+i+" newset: "+Arrays.toString(newset.toArray())+" ans: "+answer);
            
            // Set this
            dp.add(newset);
        }

        return answer;
    }
}