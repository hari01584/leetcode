/*
explanation: sort character by frequency by first collecting them into hashmap, converting it to list of entry sets, sorting according to values and finally reconstructing from list to frequency string!

testcase: "tree" -> Works

Time & Space Complexity: O(nlogn) & O(n): Since linear loops with sorting are used everywhere therefore time complexity is of sorting, ie nlogn, also since space is required to store each character therefore space complexity is linear!
*/
class Solution {
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        
        for(int i=0; i<s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
        }
        
        String sr = "";
        ArrayList<Map.Entry<Character, Integer>> list = new ArrayList<>();

        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            list.add(entry);
        }
        
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> m1, Map.Entry<Character, Integer> m2) {
                return m2.getValue().compareTo(m1.getValue());
            }
        });
        
        for(Map.Entry<Character, Integer> entry : list){
            for(int i=0; i<entry.getValue(); i++){
                sr+=""+entry.getKey();
            }
        }
        
        return sr;
    }
}