/*
explanation: repeating string match by using the fact that target string if to be found must contain inside the n or n+1 times duplicated source string! where n is the minimum number of times string should be repeated! for example a string of length 13 is to be matched again repeating sequence of string with length 3, then we can say at atleast we need to repeat the string ceil(13/3) = ceil(4.1) = 5 times to match upto the length of target string to match! repeating string anytime lesser than this number won't have sufficient length to match against! Now for repeating string we might need to repeat it one more time (n+1) to check for end terminating suffix-prefix combination, for example matching abcd against string da! we need to repeat it 2 times (to get abc[da]bcd --- square brackets represent matched part)! therefore the program simply creates a dummy string repeated and check for containing string using normal java contains function! returning the minimum amount of replication to match or -1 if not possible.

testcase:
"abcd"
"cdabcdab" -> Works

here n1 = 4 & n2 = 8
therefore minimum times string to repeated = ceil(8/2) = 2, now generating a 2 times repeated string we get abcdabcd, since the generated string does not contain cdabcdab therefore we move on to generate 3 times repeated string (which is abcdabcdabcd), since this string contains our source string cdabcdab therefore the program returns 3 as answer!
*/

class Solution {
    public int repeatedStringMatch(String a, String b) {
        int n1 = a.length();
        int n2 = b.length();
        
        int minrepeat = (int)Math.ceil(n2/(float)n1);
        int maxrep = minrepeat + 1;
        
        String repeated = new String(new char[minrepeat]).replace("\0", a);
        if(repeated.contains(b)) return minrepeat;
        
        repeated = new String(new char[maxrep]).replace("\0", a);
        if(repeated.contains(b)) return maxrep;
        
        return -1;
    }
}