/*
explanation: string compression using two pointers approach! The algorithm works by having two pointers which store the store index and traversal index in arrays! Using these two we go over array and check for various conditions!

testcase: ["a","a","b","b","c","c","c"] -> Works

Time & Space Complexity: O(n) & O(1): Time and space complexity are linear and constant respectively due to linear looping and no extra space required for arrays!
*/
class Solution {
    public int writeIndice(int n, char[] arr, int base){
        if(n <= 0) return 0;
        int a = writeIndice(n/10, arr, base);
        
        int digit = n%10;
        arr[base+a] = (char)(digit+'0');
        
        // System.out.println("At "+n+" writing indx:"+(base+a)+" = "+digit);
        return a+1;
    }
    
    public int compress(char[] chars) {
        int store_pt = 0;
        int traverse_pt = 0;
        
        char prev = chars[0];
        int cnt = 1;
        for(traverse_pt=1;traverse_pt<chars.length; traverse_pt++){
            char curr = chars[traverse_pt];
            if(curr == prev){
                // No change in char, increase cnt to 1
                cnt++;
            } else {
                // Change, write to array!
                if(cnt == 1){
                    // Non compressed, simply write and increase storept!
                    chars[store_pt++] = prev;
                } else {
                    // Compress this by cnt and then write!
                    chars[store_pt++] = prev;
                    // Write number!
                    store_pt += writeIndice(cnt, chars, store_pt);
                    cnt = 1;
                }
            }
                
            prev = curr;
        }
        
        // Finish any remaining buffer
        // Change, write to array!
        if(cnt == 1){
            // Non compressed, simply write and increase storept!
            chars[store_pt++] = prev;
        } else {
            // Compress this by cnt and then write!
            chars[store_pt++] = prev;
            // Write number!
            store_pt += writeIndice(cnt, chars, store_pt);
            cnt = 1;
        }
        
        return store_pt;
    }
}