/*
explanation: finding non consecutive one integer with using digit dp+memoization! The program works by defining the base condition and using digit dp to check for each binary number using variables!

testcase: 5 -> Works

Time & Space Complexity: time complexity and space of this program is linear due to dynamic programming used!
*/
class Solution {
    int[][][] dp;
    public int bindp(int indx, int tight, int zr, List<Integer> repr){
        if(indx < 0) return 1;
        if(dp[indx][tight][zr] != 0) return dp[indx][tight][zr];
        // if(zr==1 && repr.get(indx) == zr) return 1;
        int sum = 0;
        int k = tight==0?1:repr.get(indx);
        
        // System.out.println(indx+" "+tight+" "+k+" "+zr+" "+repr.get(indx));

        for(int i=0; i<=k; i++){
            // System.out.print(i+"");
            int tigh = (tight==1 && k==i)?1:0;
            if(i == 1 && zr == 1) continue;
            sum += bindp(indx-1, tigh, i, repr);
        }
        
        // System.out.println();
        
        return dp[indx][tight][zr]=sum;
    }
    
    public int findIntegers(int n) {
        String btex = Integer.toBinaryString(n);
        // System.out.println("I: "+btex);
        
        ArrayList<Integer> digits = new ArrayList<>();
        for (int i = 0; i < btex.length(); i++) {
            digits.add(btex.charAt(i)-'0');
        }
        Collections.reverse(digits);

        dp = new int[digits.size()][2][2];
        
        return bindp(digits.size()-1, 1, 0, digits);
    }
}