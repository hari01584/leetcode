/*
explanation: odd even linked list using traversal techniques! The program works by simply setting the next pointer of previous node to the next node of current iterating list, we do this for all elements except the last node, to which we manually configure next by comparing length of list!

testcase: [1,2,3,4,5] -> Works

Time & Space Complexity: O(n) & O(1): time complexity of this program is linear while space complexity is constant! (Due to use of linkedlist)
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
    public ListNode oddEvenList(ListNode head) {
        if(head == null) return null;
        if(head.next == null) return head;
        if(head.next.next == null) return head;
        
        ListNode h1 = head;
        ListNode h2 = head.next;
        
        ListNode p = h1;
        ListNode t = h2;
        ListNode last = null;
        int len = 0;
        while(t.next!=null){
            p.next = t.next;
            p = t;
            t=t.next;
            len++;
        }
        if(len%2==0){
            t.next = null;
            p.next = h2;
        } else {
            p.next = null;
            t.next = h2;
        }
        
        // System.out.println(p.val+", "+t.val);
        // p.next = h2;
        return h1;
    }
}