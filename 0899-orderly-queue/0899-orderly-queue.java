/*
explanation: https://leetcode.com/problems/orderly-queue/discuss/165878/C%2B%2BJavaPython-Sort-String-or-Rotate-String
*/
class Solution {
    public String orderlyQueue(String S, int K) {
        if (K > 1) {
            char S2[] = S.toCharArray();
            Arrays.sort(S2);
            return new String(S2);
        }

        else{
            String res = S;
            for (int i = 1; i < S.length(); i++) {
                String tmp = S.substring(i) + S.substring(0, i);
                if (res.compareTo(tmp) > 0) res = tmp;
            }
            return res;
        }
    }
}