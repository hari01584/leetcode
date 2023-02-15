/*
explanation: add array using list addition! The algorithm works by converting to list and then iterating step by step doing old school mathematic addition!

testcase: 
[1,2,0,0]
34 -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity of this program is linear and linear respectively, due to the loops and space require!
*/
class Solution {
    public List<Integer> addToArrayForm(int[] nums, int k) {
        List<Integer> arr = new ArrayList<>();
        for(int i=0; i<nums.length; i++){
            arr.add(nums[i]);
        }
        
        List<Integer> ans = new ArrayList<>();
        
        int elen = arr.size()-1;
        int num = k;
        int carry = 0;
        while(elen >= 0 || num != 0){
            int digit;
            if(num != 0)
                digit = num%10;
            else
                digit = 0;
            int arrayelement;
            if(elen >= 0){
                arrayelement = arr.get(elen);
            } else arrayelement = 0;
            
            // add elements!
            int a1 = arrayelement;
            int a2 = digit;
            int sum = a1 + a2 + carry;
            carry = 0;
            if(sum > 9){
                // Ie set carry!
                sum = sum%10;
                carry = 1;
            }
            ans.add(0, sum);
            
            num/=10;
            elen--;
        }
        
        if(carry != 0){
            ans.add(0, 1);
        }
        
        return ans;
    }
}