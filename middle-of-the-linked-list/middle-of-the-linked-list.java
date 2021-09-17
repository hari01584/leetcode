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
Explanation: Runs one pointer (this case x) twice the speed of another pointer, so when the faster pointer reaches null, we can say the slower will be in middle!

Testcase:
[] -> testcase not possible
[1] -> Again x.next is null so loop doesnt run and it returns null head
[1, 2] -> Fast pointer runs only one time and slower one also, So it points to second element thus output (2): Correct
[1, 2, 3] -> Fast pointer goes 1->3, slow moves only one time 1->2, Hence giving correct answer (2).
[1, 2, 3, 4] -> Fast 1->3->NULL, Slow 1->2->3, hence it works
*/
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode x = head;
        
        while(x!=null && x.next!=null){
            head = head.next;
            x = x.next.next;
        }
        return head;
    }
}