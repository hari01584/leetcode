/*
explanation: best team with no conflicts using sorting + lis algorithm! The program works by using sorting to first sort the array according to age of players, then we simply run longest increasing subsequence algorithm to find the best possible team score!

testcase: 
[1,3,5,10,15]
[1,2,3,4,5]

Time & Space Complexity: O(n^2) & O(n): Time complexity of this algorithm is quadratic since we use two loops, also space complexity is linear due to use of dp array!
*/
class Solution {
    class E implements Comparable<E>{
        int score;
        int age;
        
        E(int s, int a){
            score = s;
            age = a;
        }
        
        public int compareTo(E e){
            if(this.age == e.age) return this.score - e.score;
            return this.age - e.age;
        }
        
        public String toString(){
            return "{"+score+", "+age+"}";
        }
    }
    
    public int bestTeamScore(int[] scores, int[] ages) {
        E[] team = new E[scores.length];
        
        for(int i=0; i<scores.length; i++){
            team[i] = new E(scores[i], ages[i]);
        }
        
        Arrays.sort(team);
        
        // System.out.println(Arrays.toString(team));
        // LIS! >333333333333333
        
        int[] dp = new int[scores.length];
        Arrays.fill(dp, Integer.MIN_VALUE);
        int ans = Integer.MIN_VALUE;
        
        for(int i=team.length-1; i>=0; i--){
            E cur = team[i];            
            int csc = Integer.MIN_VALUE;
            for(int j=i+1; j<team.length; j++){
                if(team[j].score >= cur.score)
                    csc = Math.max(csc, dp[j]);
            }
            csc = Math.max(csc + cur.score, cur.score);
            dp[i] = csc;
            // System.out.println("Setting maximum of "+cur.score+" to be: "+dp[i]);
            ans = Math.max(ans, dp[i]);
        }
        
        return ans;
    }
}