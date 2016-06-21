package nowcoder;

/**
 * Created by mazhibin on 16/6/5
 *
 * 一个链表中包含环，请找出该链表的环的入口结点。
 *
 * [链表中环的入口结点](http://www.nowcoder.com/practice/253d2c59ec3e4bc68da16833f79a38e4?tpId=13&tqId=11208&rp=3&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)
 */
public class 链表中环的入口结点 {
    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        if(pHead == null || pHead.next == null) return null;
        if(pHead.next.next == pHead) return pHead;
        ListNode p1 = pHead.next,p2 = pHead.next.next;

        while(p1 != p2){
            if(p1.next == null || p2.next == null || p2.next.next == null) return null;
            p1 = p1.next;
            p2 = p2.next.next;
        }

        p1 = pHead;
        while(p1 != p2){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }



    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
