/*
explanation: Shifting nodes to k right places by first calculating the skip-over amount by formula skip = l - k%l - 1 and then skipping over to (skip) nodes to initilize it as head of list, also creating a loop at the end of linkedlist to continue the starting elements. 

testcase:
[1,2,3,4,5], k = 2
here it first calculates skip, which is equal to 5 - 2 - 1 = 2, now it skips to two nodes, ie 1..2 -1st skip, and 2..3 -2nd skip, the node in our consideration is 3 now, when it reaches 3, it automatically backups the next pointer (ie 4) and sets next of 3 to NULL (ie terminates linkedlist), finally it uses the backup pointer (4) and loops to the end of list (to 5) changing next of 5 from null to 1, and returning 4 as head node.

Time & Space Complexity: O(n) and O(1)
Since only single level loop is required here, therefore time complexity is O(n), also since countable variables are used in program therefore space complexity is O(1).
*/

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
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        int l = 0;
        ListNode t = head;
        while(t!=null){
            t = t.next;
            l++;
        }
        if(l == 0) return head;
        if(k%l == 0) return head;
        
        int skip = l - k%l - 1;
        t = head;
        for(int i=0; i<skip; i++){
            t = t.next;
        }
        ListNode n = t.next;
        t.next = null;
        t = n;
        while(t.next!=null){
            t = t.next;
        }
        t.next = head;
        
        return n;
    }
}