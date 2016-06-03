package nowcoder;

import java.util.Stack;

/**
 * Created by mazhibin on 16/6/3
 *
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 */
public class 用两个栈实现队列 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if(stack2.size() > 0){
            return stack2.pop();
        }else{
            while(stack1.size() > 1){
                stack2.push(stack1.pop());
            }
        }
        return stack1.pop();
    }


}
