package nowcoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mazhibin on 16/6/3
 *
 * 输入一个链表，从尾到头打印链表每个节点的值。
 * 输入描述:输入为链表的表头
 * 输出描述:输出为需要打印的“新链表”的表头
 *
 * [从尾到头打印链表](http://www.nowcoder.com/practice/d0267f7f55b3412ba93bd35cfa8e8035?tpId=13&tqId=11156&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)
 */
public class 从尾到头打印链表 {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(listNode == null) return result;

        ListNode i = listNode;
        ListNode j = listNode.next;

        while(i!=null && j!=null){
            ListNode jj = j.next;
            j.next = i;
            i = j;
            j = jj;
        }
        listNode.next = null;

        while(i.next != null){
            result.add(i.val);
            i = i.next;
        }
        result.add(i.val);
        return result;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;

        List<Integer> r = new 从尾到头打印链表().printListFromTailToHead(l1);
        System.out.println(r);
    }

    static class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }
}