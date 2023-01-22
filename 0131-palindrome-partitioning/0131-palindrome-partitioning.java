/*
explanation: palindrome partitioning using dynamic programming! The program works by using difference indices to partition the string and checking for palindrome, it stores each palindrome to list and loads for next partition, finally when last element is reached (ie all good partition), it adds array to final answer and returns!

testcase: "aab" -> Works

Time & Space Complexity: Since each call to recursive function will call itself n-1 times, therefore time complexity is n! (n factorial), also space complexity is linear due to the storage required for arrays!
*/
class Solution {
    String s;
    List<List<String>> ans = new ArrayList<>();
    List<String> lst = new ArrayList<>();
    
    public static boolean isPalindrome(String str)
    {
        String rev = "";
 
        boolean ans = false;
 
        for (int i = str.length() - 1; i >= 0; i--) {
            rev = rev + str.charAt(i);
        }
 
        // Checking if both the strings are equal
        if (str.equals(rev)) {
            ans = true;
        }
        return ans;
    }

    public void backpart(int n){
        if(n >= s.length()){
            // Terminate
            ans.add(new ArrayList<>(lst));
            return;
        }
        
        // System.out.println("Calling "+n);
        for(int i=n+1; i<=s.length(); i++){
            String subst = s.substring(n, i);
            if(!isPalindrome(subst)) continue;
            // Is palindrome, add to list and loop to next!
            // System.out.println("Adding "+subst);
            lst.add(subst);
            backpart(i);
            lst.remove(lst.size()-1);
        }
    }
    public List<List<String>> partition(String s) {
        this.s = s;
        
        backpart(0);
        
        return ans;
    }
}