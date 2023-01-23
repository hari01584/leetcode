/*
explanation: find town judge using brute force, the program works by using trust array element to first filter all non satisfying pairs and then using bruteforce to check for possible mayors!

testcase: 2, [[1,2]] -> Works

Time & Space Complexity: O(n^2) & O(n): TIme complexity is quadratic due to two nested loops, also space complexity is linear due to the maintainence of array required throughout the program!
*/
class Solution {
    int[][] trust;
    int MAX_LEN;
    
    public boolean isMayor(int n){
        boolean[] all_trust_judge = new boolean[MAX_LEN+1];
        for(int[] t : trust){
            if(t[0] == n) return false; // Town judge trust nobody
            if(t[1] == n){
                all_trust_judge[t[0]] = true;
            }
        }
        
        // System.out.println("for "+n+" arr:"+Arrays.toString(all_trust_judge));
        
        for(int i=1; i<=MAX_LEN; i++){
            if(i == n) continue;
            // System.out.println("loop "+i);
            if(!all_trust_judge[i]){       
                // System.out.println("for "+n+", At "+i+" "+"we return false");
                return false;
            }
        }
        
        return true;
    }
    
    public int findJudge(int n, int[][] trust) {
        boolean[] isjudge = new boolean[n+1];
        MAX_LEN = n;
        Arrays.fill(isjudge, true);
        
        this.trust = trust;
        
        for(int[] t : trust){
            isjudge[t[0]] = false;
        }
        
        // Check candidates for trust now!
        for(int i=1; i<=n; i++){
            if(!isjudge[i]) continue;
            
            if(isMayor(i)){
                return i;
            }
            
        }
        
        return -1;
    }
}