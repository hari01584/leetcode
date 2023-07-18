/*
explanation: dutch national flag algorithm to sort coloors! the algorithm works by maintaining multiple pointers
(here in this case 3), to create 4 different regions of array, to aggregate all elements, ie 0000011112222,
and by systematically swapping elements that comes in, we can get our answer!

testcase: [2,0,2,1,1,0] -> Works

Time & Space Complexity: O(n) & O(1): Time complexity is linear due iteration over each element, also space complexity is
constant due to countable extra variables.
*/
class Solution {
    public void sortColors(int[] nums) {
        int zero_before_this = 0;
        int ones_after_this = nums.length - 1;
        int twos_at_this = nums.length;
        
        // What we need to do, is to use these 3 pointers and send the coming element to their respective places.. ie
        while (zero_before_this <= ones_after_this) {
            int element = nums[zero_before_this];
            if (element == 0) {
                // System.out.println("0, pointer increase");

                // In this case, simply increase 0 pointer, (ie zero_before_this) as this becomes
                // part of window that has zero's!
                zero_before_this++;
            } else if (element == 1) {
                // System.out.println("1: Swapping "+nums[ones_after_this]+" and "+nums[zero_before_this]);

                // If this is the case, then make sure to swap this with element ones_after_this and
                // decrease it's pointer, this is to place 1 at current place and aggregate it with others
                int temp = nums[ones_after_this];
                nums[ones_after_this] = nums[zero_before_this];
                nums[zero_before_this] = temp;
                
                ones_after_this--;
            } else {
                // Now here's the tricky case, for 2.. in 2 what we do is to swap two times to place
                // it at correct position!, ie first create a position for 2 by  moving a '1' to its
                // starting of aggregate, and finally placing 2 to its correct cluster!
                
                // Step 1: Move 1 to appropriate location
                int temp = nums[twos_at_this - 1];
                nums[twos_at_this - 1] = nums[ones_after_this];
                nums[ones_after_this] = temp;
                ones_after_this--;
                
                // System.out.println("S1: Swapping "+temp+" and "+nums[twos_at_this - 1]);
                if (ones_after_this + 1 != zero_before_this) {
                    // Step 2: Now place and swap the empty spot created for two, and decrease pointer!
                    temp = nums[twos_at_this - 1];
                    nums[twos_at_this - 1] = nums[zero_before_this];
                    nums[zero_before_this] = temp;
                }

                twos_at_this--;
                
                // System.out.println("S2: Swapping "+temp+" and "+nums[twos_at_this - 1]);
            }
            
            // System.out.println("zero_before_this: "+zero_before_this+", ones_after_this:"+ones_after_this+", twos_at_this:"+twos_at_this+" arr: "+Arrays.toString(nums));
        }
    }
}