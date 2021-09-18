/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
/*
Explaination: Iterates loop element by element and fill any null spot with 0 (equalize length), then sums over the two elements and maintains a remainder variable to effectively change and set the next element remainder.

Testcases:
[0], [0]
Sum is added to 0+0=0 and this element is appened to output list, after this element is null so the loop stops and it returns [0], hence working.

[7,8,9], [1,3,4]
Adds elements in pairs, 7+1=8, adds 8 to the output list, then does the same for 8+3=11, here 1 is added to list and the other 1 is set as remainder, which is to be added in next element addition, thf. 9+4+1=14, 4 is added to list and the loop breaks, but the leftover 1 in remainder is then carried over and the exit condition statement checks if remainder zero or not, if not zero then it adds new list element which is equal to remainder! (0+0+remainder=remainder)!

*/
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int remainer=0;
        ListNode output;
        output = new ListNode(-1, null);
        ListNode start = output;
        while(l1!=null || l2!=null){
            int i1, i2;
            if(l1==null){
                i1=0;
                i2=l2.val;
                l2=l2.next;
            }
            else if(l2==null){
                i2=0;
                i1=l1.val;
                l1=l1.next;
            }
            else{
                i1=l1.val;
                i2=l2.val;
                l1 = l1.next;
                l2 = l2.next;
            }
            int sum = i1+i2+remainer;
            int b=sum % 10;
            remainer = sum/10;
            output.next = new ListNode(b, null);
            output = output.next;
        }
        
        if(remainer!=0){ // Remainder not zero, something still left.
            output.next = new ListNode(remainer, null);
        }
        
        return start.next;
    }
}