/**
 * Created by mazhibin on 17/3/12
 */

/**
 * 单向链表类的定义：
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
public class ListTest {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {

//        ListNode list = createList(new int[]{1, 4, 3, 2, 5, 2});
//        print(list);

        ListTest listTest = new ListTest();
        ListNode result = listTest.partition2(createList(new int[]{}), 0);
        print(result);
    }

    public ListNode partition2(ListNode head, int x) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy, cur = head;
        while (prev.next != null && prev.next.val < x) prev = prev.next;
        cur = prev;

        while (cur.next != null) {
            if (cur.next.val < x) {
                ListNode temp = cur.next;
                cur.next = temp.next;
                temp.next = prev.next;
                prev.next = temp;
                prev = prev.next;
            } else {
                cur = cur.next;
            }
        }

        return dummy.next;
    }

    public ListNode partition(ListNode head, int x) {
        ListNode prev = null;
        ListNode cur = head;

        ListNode firstPart = null;
        ListNode firstPartEnd = null;

        ListNode secondPart = null;

        while (cur != null) {
            if (cur.val < x) {
                if (prev != null) {
                    prev.next = cur.next;
                }

                if (firstPart == null) {
                    firstPart = cur;
                    cur = cur.next;
                    firstPartEnd = firstPart;
                    firstPartEnd.next = null;
                } else {
                    firstPartEnd.next = cur;
                    firstPartEnd = cur;
                    cur = cur.next;
                    firstPartEnd.next = null;
                }
            } else {
                if (secondPart == null) {
                    secondPart = cur;
                }
                prev = cur;
                cur = cur.next;
            }
        }

        if (firstPartEnd != null) {
            firstPartEnd.next = secondPart;
        }
        if (firstPart == null) return secondPart;
        else return firstPart;
    }

    public static void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static ListNode createList(int[] input) {
        ListNode node = null;
        ListNode end = null;
        for (int i : input) {
            if (node == null) {
                node = new ListNode(i);
                end = node;
            } else {
                end.next = new ListNode(i);
                end = end.next;
            }
        }
        return node;
    }
}
