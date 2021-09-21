/*
explanation: the program works by sorting each string alphabetically then adding them to a list of Strings, this list is sorted again and the resulting list is used as base to get anagrams groups! Additionally a class Element is made specifically to store and save the index of initial unsorted string in original array (String[] strs).

testcases:
[""] -> Works
["eat","tea","tan","ate","nat","bat"] -> Works
*/

public class Element {
    public int id;
    public String data;
    
    Element(int id, String data){
        this.id = id;
        this.data = data;
    }
}


class Solution {
    public String stringSort(String input){
        char[] charArray = input.toCharArray();
        Arrays.sort(charArray);
        String sortedString = new String(charArray);
        return sortedString;
    }
    
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ret = new ArrayList<List<String>>();
        
        List<Element> stSort = new ArrayList<Element>();
        for(int i=0; i<strs.length; i++){
            String s = stringSort(strs[i]);
            stSort.add(new Element(i, s));
        }
        
        Collections.sort(stSort, new Comparator<Element>() {
            @Override
            public int compare(final Element object1, final Element object2) {
                return object1.data.compareTo(object2.data);
            }
        });

        int p = 0;
        String last = null;
        List<String> l = null;
        for(Element e:stSort){
            System.out.println(e.data);
            if(!e.data.equals(last)){
                if(l!=null) ret.add(l);
                l = new ArrayList<String>();
                last = e.data;
            }
            l.add(strs[e.id]);
        }
        //temp.clear();
        ret.add(l);
        //ret.add(stSort);
        return ret;
    }
}