/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
    ListNode* last;
    ListNode* reverse(ListNode* l){
        if(l==NULL || l->next==NULL){
            last = l;
            return l;
        }
        ListNode* m = reverse(l->next);
        m->next = l;
        return l;
    }
public:
    ListNode* reverseList(ListNode* head) {
        if(head==NULL) return NULL;
        ListNode *h = head;
        ListNode* n = reverse(head);
        n->next = NULL;
        // n->next->next = NULL;
        return last;
    }
};