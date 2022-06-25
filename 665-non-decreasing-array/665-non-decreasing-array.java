/*
explanation: works by creating a difference map and using it to check the negative values (ie when element placed in wrong way).

testcase:
[4,2,3] -> true
because its difference map is [4, -2, 1], here only single negative value is there therefore it can be possible

Time & Space Complexity: O(n) and O(n): Since linear loop is used and an array of length n is maintained.
*/
class Solution {
    public boolean checkPossibility(int[] N) {
        for (int i = 1, err = 0; i < N.length; i++)
            if (N[i] < N[i-1])
                if (err++ > 0 || (i > 1 && i < N.length - 1 && N[i-2] > N[i] && N[i+1] < N[i-1]))
                    return false;
        return true;
    }
}
