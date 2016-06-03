package nowcoder;

import java.util.ArrayList;

/**
 * Created by mazhibin on 16/6/3
 *
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 *
 * [重建二叉树](http://www.nowcoder.com/practice/8a19cbe657394eeaac2f6ea9b0f6fcf6?tpId=13&tqId=11157&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)
 */
public class 重建二叉树 {

    /**
     * 正确解法.参考了答案的.
     *
     * 其实我的思路基本上是正确的了.但是我为了确定前序遍历中的分界线再次搜索了一遍导致复杂度增加,导致没写出来
     * 其实利用中序遍历中的搜索结果,就可以进行划分了.
     */
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        return reConstructBinaryTreeImpl(pre,0,pre.length-1,in,0,in.length-1);
    }

    public TreeNode reConstructBinaryTreeImpl(int [] pre,int preBegin,int preEnd,int [] in,int inBegin,int inEnd) {
        if(preBegin > preEnd || inBegin > inEnd){
            return null;
        }
        TreeNode node = new TreeNode(pre[preBegin]);

        for (int i = inBegin; i <= inEnd; i++) {
            if(pre[preBegin] == in[i]){
                node.left = reConstructBinaryTreeImpl(pre,preBegin+1,preBegin+i-inBegin,in,inBegin,i-1);
                node.right = reConstructBinaryTreeImpl(pre,preBegin+i-inBegin+1,preEnd,in,i+1,inEnd);
            }
        }
        return node;
    }

    /**
     * 正确答案之一,不好,因为需要额外空间,代码也不是最简单的
     */
    public TreeNode reConstructBinaryTree_2(int [] pre,int [] in) {
        if(pre.length != 0){
            int rootValue = pre[0];
            TreeNode node = new TreeNode(rootValue);

            int gen = 0;
            for (int i = 0; i < in.length; i++) {
                if(rootValue == in[i]){
                    gen = i;
                    break;
                }
            }

            int[] leftPre = new int[gen];
            int[] leftIn = new int[gen];
            for (int i = 0; i < gen; i++) {
                leftPre[i] = pre[i+1];
                leftIn[i] = in[i];
            }

            int[] rightPre = new int[in.length-gen-1];
            int[] rightIn = new int[in.length-gen-1];
            for (int i = gen+1; i < in.length; i++) {
                rightPre[i-gen-1] = pre[i];
                rightIn[i-gen-1] = in[i];
            }

            node.left = this.reConstructBinaryTree_2(leftPre,leftIn);
            node.right = this.reConstructBinaryTree_2(rightPre,rightIn);
            return node;
        }
        return null;
    }

    /**
     * 运行不通过...
     */
    public TreeNode reConstructBinaryTree_1(int [] pre, int[] in) {
        return this.reConstructBinaryTreeImpl_1(pre,0,pre.length-1,in,0,in.length-1);
    }

    public TreeNode reConstructBinaryTreeImpl_1(int [] pre,int preBegin,int preEnd,int [] in,int inBegin,int inEnd) {
        System.out.println(preBegin+" "+preEnd+" "+inBegin+" "+inEnd);
        int rootValue = pre[preBegin];
        TreeNode node = new TreeNode(rootValue);

        if(preBegin == preEnd){
            return node;
        }

        int rootInInPos = 0;
        for(int i = inBegin;i<=inEnd;i++){
            if(rootValue == in[i]){
                rootInInPos = i;
                break;
            }
        }

        int rightInPre = -1;
        for(int i = inBegin;i<rootInInPos;i++){
            for(int j = preBegin+1;j<preEnd;j++){
                if(pre[j] == in[i] && j>rightInPre) rightInPre = j;
            }
        }
        rightInPre++;

        if(rightInPre != 0){
            System.out.print("left");
            node.left = this.reConstructBinaryTreeImpl_1(pre,preBegin+1,rightInPre-1,in,inBegin,rootInInPos-1);
        }
        if(rightInPre != preEnd){
            System.out.print("right");
            node.right = this.reConstructBinaryTreeImpl_1(pre,rightInPre,preEnd,in,rootInInPos+1,inEnd);
        }

        return node;
    }

    public void printPre(TreeNode node){
        if(node == null) return;
        System.out.println(node.val);
        printPre(node.left);
        printPre(node.right);
    }

    public void printIn(TreeNode node){
        if(node == null) return;
        printIn(node.left);
        System.out.println(node.val);
        printIn(node.right);
    }

    public static void main(String[] args) {
        重建二叉树 o = new 重建二叉树();

        TreeNode r = o.reConstructBinaryTree(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{3, 2, 4, 1, 5, 7, 6});
        o.printPre(r);
        o.printIn(r);
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
