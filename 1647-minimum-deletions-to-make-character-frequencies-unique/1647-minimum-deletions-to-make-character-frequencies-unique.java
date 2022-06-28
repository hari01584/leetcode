/*
explanation: minimum deletions by first storing the frequency appearance of each character and then sorting this array, which gives the appearance of each unique character, now if this array contains duplicate elements then it means we need to remove characters, so we reduce the element and swap it with required element, always maintaining the sorted array!
(explained below)

testcase:
aaabbbcc -> Works
first sorted frequency list becomes 2, 3, 3!
Now the loop is run which compares each element with previous element, ie 2..3..3, when it reaches i=2 (last element 3) then it triggers the duplicate condition and reduces the element by 1, ie it becomes 2 and the 2 is swapped with previous element, so array becomes 2..2..3, also using while loop we again check for same condition (ie arr[current]==arr[current-1]) here it satisfies again and thus the 2(at pos i=1) becomes 1 and swapped with previous element, therefore array final becomes 1..2..3, also each operation of swapping and reducing increases the character deletion required by 1, thus here answer returned is 2!

Time & Space Complexity: O(n^2) and O(n): Since the back checking algorithm steps (ie arr[inx]==arr[inx-1]) can take n steps which inside another array can make the total time complexity as O(n^2), also since a hashmap and linear array of length n is used therefore space complexity is O(n)
*/

class Solution {
    public void swap(Integer[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public int minDeletions(String s) {
        HashMap<Character, Integer> freq = new HashMap<>();
        for(int i=0; i<s.length(); i++){
            Integer n = freq.get(s.charAt(i));
            if(n == null){
                freq.put(s.charAt(i), 1);
            }
            else{
                freq.put(s.charAt(i), ++n);
            }
        }
        
        Integer[] farr = freq.values().toArray(new Integer[0]);
        Arrays.sort(farr);
        
        System.out.println(Arrays.toString(farr));
        int ans = 0;
        for(int i=1; i<farr.length; i++){
            int curr = farr[i];
            int prev = farr[i-1];
            
            int inx = i;
            while(inx > 0 && farr[inx] > 0 && farr[inx] <= farr[inx-1]){
                farr[inx] = farr[inx] - 1;
                swap(farr, inx, inx-1);
                inx--;
                ans++;
            }
        }
        
        System.out.println(Arrays.toString(farr));
        
        return ans;
    }
}