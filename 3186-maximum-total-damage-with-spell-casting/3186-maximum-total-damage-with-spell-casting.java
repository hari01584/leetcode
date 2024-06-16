/*
explanation: modified house robber problem, this is not a medium but easy once you get gist of it,
in traditional house robber, you just do pick/nonpick and in simple pick you skip one index (ie go to i+2),
This problem is quite the same with twist that instead of skipping indices, we have to skip numbers.

Therefore the question is just three simple observations away to be solved!
Observation 1: If you sort the array, then you dont need to worry about one side of constraints, ie if you sort
array like 2 5 4 7 6, which will be 2 4 5 6 7, then once you take any element, you can safely skip to the right element witout worrying about left hand side, ie take val=5, to take this value, you must have not picked up 4 previously, therefore picking 4 will automatically remove 5, and vice versa.

Observation 2: Creating frequency map will help a lot in finding answer, as you see unlike house robber, indices of elements doesn't matter, if there are two values 5 5, and you are picking 5, then it is always
AND ALWAYS better to pick another 5, ie 5 + 5, there is no merit in taking single frequency of elements and skipping same value later! (Either pick all, or pick none :))

Observation 3: Rather than skipping constant indices (1 in case of House Robber problem), here you have to find how many numbers of indices you have to skip to get next safe damage, and we can theoretically proof that in a frequency sorted array (after applying Obs1 and 2), We can get this next safe index in atmost index+3, ie imagine a worst case arrangement like 2 3 4 5, here if you pick '2' then you can skip to index(2) + 2 and be sure that you will get next safe value, but it is not always true, for the cases when there are gaps in sorted elements, ie like 1 2 5 7, here you can cast spell with damage 5 (after casting spell with damage 1), therefore YOU NEED TO CHECK from index+1 and index+2 for possible next choice and call dp function on same value!

testcases:
[1,1,3,4]
[7,1,6,6]
[6,4,4,4,3,3,2,3]
[4,5,5,6,3,6,5,3,4]
[3,4,8,10,8,8,3]
[5,9,2,10,2,7,10,9,3,8] -> Works

Time & Space Complexity: O(nlogn + n) and O(n): Here time complexity is nlogn (for sorting) and O(n) for dp, while space complexity is linear due to storing modified frequency list
*/
class Solution {
    class E {
        int element;
        int freq;
        
        E(int e, int f) {
            element = e;
            freq = f;
        }
        
        long get_multiply() {
            return (long)element * freq;
        }
        
        public String toString() {
            return element + " " + freq;
        }
    }
    
    public long dydmg(int index, List<E> list, long[] dp) {
        // If index is >= length then 0 return
        if (index >= list.size()) {
            return 0;
        }
        
        if (dp[index] >= 0) {
            return dp[index];
        }
        
        int cur = list.get(index).element;
        // Else simply do pick/nopick XD,
        // First find next index which we should go if we pick this.. :)
        int next_index_if_picked = index + 3; // index + 3 would def. be safe, no matter what, ie 7..8..9..10, 7 to 10
        for (int i=1; i<=2; i++) {
            // This is to go +2 spaces ahead and see if we can use that is next_index.. (ie in case of spaces)
            int trial_next_index = index + i;
            if (trial_next_index < list.size()) {
                int trial_next_val = list.get(trial_next_index).element;
                if (trial_next_val > cur + 2) {
                    // Omg, we can use this, yay!
                    next_index_if_picked = trial_next_index;
                    break;
                }
            }
        }
        
        long pick_this = list.get(index).get_multiply() + dydmg(next_index_if_picked, list, dp);
        long no_pick = dydmg(index+1, list, dp);
        
        return dp[index] = Math.max(pick_this, no_pick);
    }

    public long maximumTotalDamage(int[] power) {
        List<E> list = new ArrayList<>();
        Arrays.sort(power);
        
        list.add(new E(power[0], 1));
        for (int i=1; i<power.length; i++) {
            // Get top element
            E top = list.get(list.size() - 1);
            int cur_val = power[i];
            // If top elemnet and current value same then inc freq
            if (cur_val == top.element) {
                list.set(list.size()-1, new E(cur_val, top.freq + 1));
            } else {
                // Else add new element
                list.add(new E(cur_val, 1));
            }
        }
        
//         for (int i=0; i<list.size(); i++) {
//             System.out.println("i: " + list.get(i));
//         }
        long[] dp = new long[list.size()];
        Arrays.fill(dp, -1);
        
        return dydmg(0, list, dp);
    }
}