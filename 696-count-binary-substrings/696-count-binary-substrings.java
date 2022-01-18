/*
// explaination: binary substring finder using linear scanning and substring checking algorithm, the string is scanned at definite length from left to right, first a substring size is picked (divisible by 2, ie 2 4 6 8) and then substrings of specific step size are formed and checked for valid binary strings, if correct then it records to variable t

// testcase:
// "00110011" => Works
// String splitted into partitions of size 2 4 6 8 respectively, for partition 2, the formed substrings were 00 01 11 10 00 01 11, all these are checked for valid substring and variable t updated if found valid, this goes on for all the step sizes and finally ends at step size 8, having sub string 00110011

// "100" => Works
// Step size is 2 only and hence 10 00 are scanned, since 00 is valid the count is updated by one and returns the correct answer!

// => Problem, TLE (Time Limit Exceeded, Don't know why [Maybe because all variables were being broken and rebuilt every iteration of loops and it doesnt cache as being done by approach 2]) 

// explanation (approach 2): again using fulltext algorithm but this time the scanning is done in caterpiller method, initially head of caterpiller is stuck to 0 and we move its tail to various points, ie 2 4 6 .... n, so basically trying to find start and end of scanning, this approach makes substring of various sizes and check for validity of string, if found valid it updates the counter variable.

// testcase:
// "1" => Works, since nothing to be scanned and string scans in a factor of 2
// "00110011" => Works
// in first iteration head is set to 0 and tail being set to 2 4 6 .. in subsequent loops, beyond this was the third loop which scans string between head to tail and check if it is correctly grouped together.

=> Problem, TLE Again, all the previous approach were taking n^2 loops to solve this problem, after taking minor hints from discussions I reached a better way to do this problem, that is to maintain a single stack which records the cumulative occurence of 1s and 0s, It linearly parse the string character by character and add one if same characters are found in succession!
for ex: 0001, the stack contains 0 ie (-1), another 0 adds -1 to top and makes it -2, similiarly another 0 makes it -3, respectively now 1 is added, whose sign is different, so normal 1 is added to stack, new stack will look like -3 1
please note that 0 is coded as -1 and 1 is coded as 1, this is to differentiate 0s and 1s later when counting

testcase:
00110011 => Works
Stack formed in last is -2 2 -2 2, see how consecutive 0s are marked with minus numbers and consecutive 1st with positive numbers! now we do compare numbers in pairs and adds Minimum of absolute of pairs.
*/

class CompactorStack {
    Stack<Integer> _stack;
    
    CompactorStack(){
        _stack = new Stack<Integer>();
    }
    
    int fenc(char c){
        if(c=='0') return -1;
        else return 1;
    }
    
    void push(char c){
        if(_stack.empty()){
            _stack.push(fenc(c));
            return;
        }
        int i = fenc(c);
        int top = _stack.peek();
        if((i>0) == (top>0)){
            // Same sign
            top = _stack.pop();
            top += i;
            _stack.push(top);
        }
        else{
            _stack.push(i);
        }
    }
    
    int t = 0;
    void riter(int r){
        if(_stack.isEmpty()) return;
        
        Integer top = _stack.pop();        
        riter(top);
        
        if(r != 0){
            t += Math.min(Math.abs(top), Math.abs(r));
        }
    }
    
    int iter(){
        riter(0);
        return t;
    }
}

class Solution {
    public char rev(char i){
        if(i=='0') return '1';
        else return '0';
    }
    public boolean isValid(String sub){
        int len = sub.length();
        char st = sub.charAt(0);
        char rv = rev(st);
        
        for(int i=0; i<len/2; i++){
            if(sub.charAt(i) != st || sub.charAt(len-i-1) != rv){
                return false;
            }
        }
        return true;
    }
    
    public int countBinarySubstrings(String s) {
        int m = s.length();
        int t = 0;
        // approach 1 (uncomment)
        // for(int slice=2; slice<=m; slice+=2){
        //     for(int i=0; i<m-slice+1; i++){
        //         String sub = s.substring(i, i+slice);
        //         if(isValid(sub)){
        //             t++;
        //         }
        //     }
        // }
        
        // approach 2
        // for(int i=0; i<m-1; i++){
        //     for(int end=2+i; end<=m; end+=2){
        //         char st = s.charAt(i);
        //         char rv = rev(st);
        //         boolean flag = false;
        //         for(int index=0; index<(end-i)/2; index++){
        //             // System.out.print(s.charAt(index+i));
        //             if(s.charAt(index+i) != st || s.charAt(end-index-1) != rv){
        //                 flag = true;
        //                 break;
        //             }
        //         }
        //         if(flag == false) t++;
        //         // System.out.println(" "+i+" "+end+" "+flag);
        //     }
        // }
        
        // approach 3: using compactor_stack
        CompactorStack stack = new CompactorStack();
        for(int i=0; i<m; i++){
            stack.push(s.charAt(i));
        }
        t = stack.iter();
        
        return t;
    }
}