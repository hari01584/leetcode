/*
explanation: Solving majority element using Boyer-Moore Majority Voting Algorithm (solution. referenced), the algorithm works by first setting candidate and votes to first element and 0 respectively! The algorithm increases the count for elements that match candidate, if not match it decrements counts, for count=0 we realize that candidate is some other element, thus changing to other element. This algorithm works so well because majority of elements (n/2) are same and thus overall the count for majority element will be positive!

testcase:
[2,2,1,1,1,2,2] -> Works
for looping over array and elements..
2 -> candidate = 2, count 1
now again 2 -> cnd = 2, cnt = 2
for next 1 -> cnd = 2, cnt = 1
next 1 -> cnd = 2, cnt=0 => cnd becomes = 1 and cnt becomes = 1
next 1 -> cnd = 1, cnt=2
for next 2 -> cnd = 1, cnt = 1
next 2 -> cnd = 1, cnt = 0 => cnd = 2
loop ends
Hence final candidate becomes 2! Which is returned.

Time & Space Complexity -> O(n) and O(1), Since a linear loop over array is required, time complexity is O(n), whereas since few count-able extra variables are used, the space complexity is O(1).
*/
class Solution {
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int vote = 0;
        
        for(int i=0; i<nums.length; i++){
            if(candidate == nums[i]){
                vote++;
            }
            else{
                vote--;
            }
            
            if(vote == 0){
                candidate = nums[i];
                vote = 1;
            }
        }
        
        return candidate;
        
    }
}