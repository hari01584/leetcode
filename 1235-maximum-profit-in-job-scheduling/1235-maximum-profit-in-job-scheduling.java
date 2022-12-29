/*
explanation: maximum profit job scheduling using dynamic programming, binary search! The program works by finding jobs avaliable after each starting time and calculating the max profit that can be gained after that time! See comments in code to understand!

testcase:
[1,2,3,3]
[3,4,5,6]
[50,10,40,70] -> Works

Time & Space Complexity: O(nlogn) & O(n): Time complexity of this memoized solution is nlogn due to memoized recursion used and the binary search with tc logn, also space complexity is linear due to dp array required to maintain it!
*/
class Solution {
    class Job implements Comparable<Job>{
        int st;
        int et;
        int pt;
        
        Job(int s, int e, int p){
            this.st = s;
            this.et = e;
            this.pt = p;
        }
        
        public int compareTo(Job j){
            return this.st - j.st;
        }
        
        @Override
        public String toString(){
            return String.format("%d-%d -> %d", st, et, pt);
        }
    }
    
    Job[] jobs;
    
    public int bSearch(int itm){
        int start = 0;
        int end = jobs.length-1;
        
        while(start <= end){
            int mid = (start+end)/2;
            if(itm <= jobs[mid].st){
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        
        return start;
    }
    
    int[] dp;
    
    public int dyjob(int n){
        if(dp[n] != -1) return dp[n];
        if(n >= jobs.length) return 0; // No job left for, reached outside all jobs line
                
        // Consider doing this job and finding max profit from here!
        Job j = jobs[n];
        // If job done this, then new time (on timeline) = endtime (of this project), thf find projs with s_time > this endtime!
        int prof = j.pt + dyjob(bSearch(j.et));
        int notdo = dyjob(n+1);
        return dp[n] = Math.max(prof, notdo);
    }
    
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        Job[] j = new Job[startTime.length];
        
        int maxtime = Integer.MIN_VALUE;
        int maxstime = Integer.MIN_VALUE;

        for(int i=0; i<j.length; i++){
            j[i] = new Job(startTime[i], endTime[i], profit[i]);
        }

        this.dp = new int[startTime.length+1];
        Arrays.fill(dp, -1);
        
        this.jobs = j;
        Arrays.sort(jobs);

        return dyjob(0);
    }
}