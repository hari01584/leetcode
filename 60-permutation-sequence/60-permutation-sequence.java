/*
explanation: solving the problem using pattern matching and using formulas that string[i] will be k-- (first time), k / (n-1)!, n = n - 1, k = k % (n-1)!, this formula place by place guesses the correct digit at any position and hence fills it in.

testcase: 
3 3 -> Works
here for n = 3, first it calculates first string character that is 2 / (3-1)! or 1, now it fetches the data from remaining array, removes the 1st index and append it to our result string, similiarly it runs two more times and appends 1 and 3 respectively, hence resulting in output string 213.

Time & Space Complexity: Time complexity is O(n2) and space complexity is O(n) since in time complexity the loop while(n > 0)... runs in n time and the inner list.remove operation take another n max loop to execute, hence n square time complexity. whereas for space since a simple list of n length is maintained therefore the space complexity is O(n).
*/
class Solution {
    public String getPermutation(int n, int k) {
        List<String> remaining = new ArrayList<>();
        for(int i=1; i<n+1; i++){
            remaining.add(i+"");
        }
        
        String r = "";
        k--; // Reduce one index
        
        int factorial[] = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};

        while(n > 0){
            int f = factorial[n-1];
            int rk = k % f;
            int index = k / f;
            r = r + remaining.remove(index);
            k = rk;
            n = n-1;
        }

        return r;
    }
}