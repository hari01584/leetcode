/*
explanation: the program works by sorting each string alphabetically then adding them to a hashmap of list of strings, each of sorted element is added as key with its value being the unsorted item. In the last we loop over all the values and get our required lists of strings!

testcases:
[""] -> Works
["eat","tea","tan","ate","nat","bat"] -> Works
*/

class Solution {
    HashMap<String, ArrayList<String>> hashy = new HashMap<String, ArrayList<String>>();

    public String stringSort(String input){
        char[] charArray = input.toCharArray();
        Arrays.sort(charArray);
        String sortedString = new String(charArray);
        return sortedString;
    }
    
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ret = new ArrayList<List<String>>();
        
        for(int i=0; i<strs.length; i++){
            String s = stringSort(strs[i]);
            if (hashy.get(s) == null) {
                hashy.put(s, new ArrayList<String>());
            }
            
            hashy.get(s).add(strs[i]);
        }
    
        for (List<String> value : hashy.values()) {
            ret.add(value);
        }
        
        return ret;
    }
}