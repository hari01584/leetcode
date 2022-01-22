/*
explanation: works by taking cross product of arrays and hence finding all the suitable combinations for dialpads!

testcase:
"4556" -> Works
*/


class Solution {
    public ArrayList<String> crossProduct(List<String> A, List<String> B){
        if(A.size() == 1 && B.size() == 1){
            // Singular cross product
            return new ArrayList<String>(Arrays.asList(A.get(0)+""+B.get(0)));
        }
        ArrayList<String> r = new ArrayList<>();
        if(A.size() == 1 && B.size() != 1){
            for(int i=0; i<B.size(); i++){
                r.add(crossProduct(A, Arrays.asList(B.get(i))).get(0));
            }
        }
        else{
            for(int i=0; i<A.size(); i++){
                r.addAll(crossProduct(Arrays.asList(A.get(i)), B));
            }
        }
        
        return r;
    }
    
    public List<String> letterCombinations(String digits) {
        ArrayList<String> out = new ArrayList<String>();
        
        List<String> dial = Arrays.asList("", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz");
        
        if(digits.length() == 0) return new ArrayList<String>(); // Empty string
        
        List<String> lc = Arrays.asList(dial.get(digits.charAt(0) - '0').split(""));
        for(int i=1; i<digits.length(); i++){
            List<String> rc = Arrays.asList(dial.get(digits.charAt(i) - '0').split(""));
            lc = crossProduct(lc, rc);
        }
        
        return lc;
    }
}