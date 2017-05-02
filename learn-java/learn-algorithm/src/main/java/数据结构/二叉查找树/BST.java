package 数据结构.二叉查找树;

/**
 * Created by mazhibin on 17/3/26
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int N;

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            N = n;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node n) {
        if (n == null) return 0;
        else return n.N;
    }

    public Value get(Key key) {
        if (key == null) return null;

        return get(key, root);
    }

    private Value get(Key key, Node node) {
        if (node == null) return null;

        int compareTo = key.compareTo(node.key);
        if (compareTo == 0)
            return node.value;
        else if (compareTo < 0)
            return get(key, node.left);
        else
            return get(key, node.right);
    }

    // 1. 没考虑root为null时，直接放在root上
    public void put(Key key, Value value) {
        if (key == null) return;

        root = put(key, value, root);
    }

    // 1. 如何做到更新N？ -> 这个put的用途不单单是添加，而是更新
    private Node put(Key key, Value value, Node node) {
        if (node == null) return new Node(key, value, 1);

        int compareTo = key.compareTo(node.key);
        if (compareTo == 0) node.value = value;
        else if (compareTo < 0) {
            node.left = put(key, value, node.left);
        } else {
            node.right = put(key, value, node.right);
        }

        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public Value max() {
        if (root == null) return null;

        return max(root);
    }

    private Value max(Node node) {
        if (node.right == null) return node.value;
        else return max(node.right);
    }

    public Value min() {
        if (root == null) return null;

        return min(root);
    }

    private Value min(Node node) {
        if (node.left == null) return node.value;
        else return max(node.left);
    }

    public Value floor(Key key) {
        if (root == null) return null;

        return floor(key, root);
    }

    // 这里对于右子树的处理需要记忆
    private Value floor(Key key, Node node) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            return floor(key, node.left);
        } else if (cmp > 0) {
            Value floor = floor(key, node.right);
            if (floor == null) return node.value;
            else return floor;
        } else {
            return node.value;
        }
    }

    public Key select(int k) {
        return select(root, k);
    }

    private Key select(Node node, int k) {
        if (node == null) return null;

        int t = size(node.left);
        if (k < t) return select(node.left, k);
        else if (k > t) return select(node.right, k - t - 1);
        else return node.key;
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node node, Key key) {
        if (node == null) return 0;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return rank(node.left, key);
        } else if (cmp > 0) {
            return size(node.left) + rank(node.right, key) + 1;
        } else {
            return size(node.left);
        }
    }
    
    public void preOrder(){
        preOrder(root);
    }
    
    private void preOrder(Node node){
        if(node == null) return;
        System.out.println(String.format("key:%s value:%s N:%s",node.key,node.value,node.N));
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();

        int[] input = new int[]{1, 5, 2, 9, 3, 4};
        for (int i : input) {
            tree.put(i, "v:" + i);
        }

        System.out.println("== preOrder ==");
        tree.preOrder();

        System.out.println("== get ==");
        System.out.println(tree.get(null));
        for (int i : input) {
            System.out.println(tree.get(i));
        }

        System.out.println("size : " + tree.size());
        System.out.println("min  : " + tree.min());
        System.out.println("max  : " + tree.max());

        for (int i = 0; i < 10; i++) {
            System.out.println("floor(" + i + "): " + tree.floor(i));
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("select(" + i + "): " + tree.select(i));
        }

        for (int i : input) {
            System.out.println("rank(" + i + "): " + tree.rank(i));
        }
    }
}
