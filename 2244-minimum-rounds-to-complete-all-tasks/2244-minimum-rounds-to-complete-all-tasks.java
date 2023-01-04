/*
explanation: minimum rounds to complete all tasks using hashmap + variable maximization! The program works by first modelling the problem into linear equation of two variables, here we are given that we can either eliminate 2 or 3 tasks in a round, let x be the no. of x rounds needed, and y be number of y rounds needed, therefore in x + y rounds we will eliminate a total of 2x+3y number of tasks, to solve for minimum rounds we need to be able to eliminate and use y rounds more (as eliminating 3 will minimize number of rounds requires, it eliminates more tasks at a time), therefore using this conjecture and using loops to find the ideal x and y and doing this for frquency of all elements of same difficulty level will yield our answer!

testcase: [2,2,3,3,2,4,4,4,4,4] -> Works

Time & Space Complexity: O(n^2) & O(n): Time complexity of this program is quadratic since loops are used and in each loop we call maxround function which itself can take maximum of linear time complexity! Also space complexity is linear due to the need to store the frequency map required!
*/
class Solution {
    public int maxround(int count){
        // Eqn is 2x + 3y = count
        // and we need to maximize y
        // and return value x+y
        for(int x=0; x<(count/2)+1; x++){
            double y = (count - 2*x)/3.0;
            if((y % 1) == 0) return x + (int)y;
        }
        
        return -1;
    }
    
    public int minimumRounds(int[] tasks) {
        int ans = 0;
        HashMap<Integer, Integer> freq = new HashMap<>();
        for(Integer ig : tasks){
            freq.put(ig, freq.getOrDefault(ig, 0)+1);
        }
        
        for(Map.Entry<Integer, Integer> entry : freq.entrySet()){
            int gt = maxround(entry.getValue());
            // System.out.println(entry.getKey()+";"+entry.getValue()+"->"+gt);
            if(gt == -1) return -1;
            ans += gt;
        }
        
        return ans;
    }
}