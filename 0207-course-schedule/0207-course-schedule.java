/*
explanation: course schedule using graph and graph jumping method! The program works by first making a hashmap mapping course -> prerequisit, now we simply run our loop from course 0 to course n and start jumping from one requisit to another (while maintaining exhaust array), whenever we reach a key which is already in exhaust array we know for sure that there is loop therefore returns false!

testcase: [[0,1],[1,0]] -> Works
It works, because initially we build hashmap mapping 0 to 1, and 1 to 0. now iteratively we check for each course if it can be completed, for couse 0, we check if its already checked (exhausted), which isnt so we see the value of key 0 and gets 1! Now using hashmap we again check if key 1 appeared before and since it didnt, we go to value at key 1 (which is 0), now again we check for value 0, since it already appeared, we return false and say that there is loop in couses! (Or it cannot be completed)!

Time & Space Complexity: O(n) & O(n): Both time and space complexity of this program is linear since we iterate and check for each keys and hashmap is required to store the mappings!
*/
class Solution {
    HashMap<Integer, List<Integer>> map;
    int[] keyexhaust;
    int[] dp;
    
    public boolean findCycle(int st){
        System.out.println(st+"|");
        if(dp[st] == 1) return true;
        // Check further
        if(keyexhaust[st] == 1) return false;
        keyexhaust[st] = 1;
        
        boolean b;
        List<Integer> arr;
        if(map.containsKey(st)){
            arr = map.get(st);
            for(Integer itg : arr){
                b = findCycle(itg);
                if(!b) return false;
                dp[itg] = 1;
            }
        }
        
        return true;
    }
        
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        map = new HashMap<>();
        keyexhaust = new int[numCourses];
        dp = new int[numCourses];
        
        for(int[] ip : prerequisites){
            List<Integer> arr = new ArrayList<>();
            if(map.containsKey(ip[0]))
                arr = map.get(ip[0]);
            
            arr.add(ip[1]);
            map.put(ip[0], arr);
        }
        
        for(int i=0; i<numCourses; i++){
            Arrays.fill(keyexhaust, 0);
            boolean b = findCycle(i);
            System.out.println(i+"->"+b);
            if(!b) return false;
            dp[i] = 1;
        }
        
        return true;
    }
}