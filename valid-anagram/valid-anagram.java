/*
Explaination: Sorts the two string alphabetically and compares them, if they are equal then the strings are analgram, else they are not!

Testcase:
a, a -> Works
abc, acb -> Works
*/
class Solution {
    public String stringSort(String input){
        char[] charArray = input.toCharArray();
        Arrays.sort(charArray);
        String sortedString = new String(charArray);
        return sortedString;
    }
    
    public boolean isAnagram(String s, String t) {
        String ns = stringSort(s);
        String nt = stringSort(t);
        
        if(ns.equals(nt)) return true;
        else return false;
    }
}