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
Explaination: Using recrusive function we compare the elements of linkedlist from starting and ending, The recursive functions gives pairs of elements with same distance from start/end, After comparing each of pairs we can set an external variable palindrome to be true or false accordingly!

Test case:
[1,2,2,1]-> (h, i)
1 1
2 2
2 2
1 1
Since all pairs of variable h and i are matching, its truee

[1,2,4,1]-> (h, i)
1 1
4 2
2 4
1 1
More than one pair in the above recursion doesn't match, hence false;
*/
class Solution {
    int isPalindrome = 1;
    ListNode isPalindromeRecs(ListNode h, ListNode origin){
        if(h==null) return origin;
        ListNode i = isPalindromeRecs(h.next, origin);
        if(h.val != i.val) isPalindrome=0;
        System.out.println(h.val+" "+i.val);
        return i.next;
    }
    
    public boolean isPalindrome(ListNode head) {
        isPalindromeRecs(head, head);
        if(isPalindrome==1) return true;
        else return false;
    }
}