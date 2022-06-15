class Solution {
    List<List<String>> results = new ArrayList<>();
    List<String> currentSequence = new ArrayList<>();
    
    public void backPalindrome(String s, int start){
        if(start >= s.length()) results.add(new ArrayList<String>(currentSequence));
        
        for(int end=start; end<s.length(); end++){
            if (isPalindrome(s, start, end)) {

            currentSequence.add(s.substring(start, end + 1));
            backPalindrome(s, end+1);
            currentSequence.remove(currentSequence.size() - 1);
                
            }
        }
    }
    
    boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--)) return false;
        }
        return true;
    }

    public List<List<String>> partition(String s) {
        backPalindrome(s, 0);
        return results;
    }
}