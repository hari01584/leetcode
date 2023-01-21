/*
explanation: valid ip address founder using 3d nested loop! The program works by iterating through each position of string and placing dots at each point, it does this step by step and checks the validity of current formed part beforing looping to next level, finally if all the part is fine then it adds the final formatted string of ip address to answer array!

testcase: "25525511135" -> Works

Time & Space Complexity: O(n^3) & O(n): Time complexity of this program is cubic since three nested loops (inside each other is required), also space complexity is linear due to storage required to store the answer!
*/
class Solution {
    String s;

    public int convert(int start, int end){
        if(end - start >= 4) return -1;
        if(start == end) return -1;
        
        String subst = s.substring(start, end);

        int val = Integer.parseInt(subst);
        
        int length = String.valueOf(val).length();
        
        if(subst.length() != length) return -1; // Eliminate leading zeroes!
        
        if(val >= 0 && val <= 255) return val;
        return -1;
    }
    public List<String> restoreIpAddresses(String s) {
        this.s = s;
        
        int MAX_LEN = s.length();
        List<Integer> current = new ArrayList<>();
        List<String> ans = new ArrayList<>();
        
        int t0 = 0;
        for(int t1=t0+1; t1<MAX_LEN; t1++){
            int conv_t1 = convert(t0, t1);
            if(conv_t1 == -1) continue;

            for(int t2=t1+1; t2<MAX_LEN; t2++){
                int conv_t2 = convert(t1, t2);
                if(conv_t2 == -1) continue;

                for(int t3=t2+1; t3<MAX_LEN; t3++){
                    int conv_t3 = convert(t2, t3);
                    int conv_final = convert(t3, MAX_LEN);
                    if(conv_t3 == -1 || conv_final == -1) continue;
                    
                    // Store
                    ans.add(String.format("%d.%d.%d.%d", conv_t1, conv_t2, conv_t3, conv_final));
                }
            }
        }
        
        return ans;
    }
}