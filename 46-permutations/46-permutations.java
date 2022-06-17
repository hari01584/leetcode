/*
explanation: generated all possible solution using backtracking. maintains two lists current and remaining for it, the list remaining acts as a queue where each integer is popped, used and finally appened to last.

testcase:
[1,2,3] - > Works

Time & Space Complexity : O(n^2) and O(n^2) respectively, time complexity is O(n^2) because of recursive backtracking used, whereas space complexity is O(n) due to stacks stored in memory.
*/

class Solution {
    List<Integer> remaining = new ArrayList<>();
    List<Integer> current = new ArrayList<>();
    
    List<List<Integer>> result = new ArrayList<>();

    public void permutate(){
        if(remaining.size() == 0) result.add(new ArrayList<>(current));
        
        for(int i=0; i<remaining.size(); i++){
            current.add(remaining.remove(0));
            permutate();
            remaining.add(current.remove(current.size() - 1));
        }
    }
    
    public List<List<Integer>> permute(int[] nums) {
        for(int i=0; i<nums.length; i++){
            remaining.add(nums[i]);
        }
        permutate();
        return result;
    }
}