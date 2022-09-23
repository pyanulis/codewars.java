public class TreeNode {

    TreeNode left;
    TreeNode right;

    int nodeCount;

    public static boolean isPerfect(TreeNode root) {
        if (root == null) return true;
        return Math.pow(2, getHeight(root)) - 1 == root.nodeCount;
    }

    private boolean isLeaf(){
        return left == null && right == null;
    }

    static TreeNode leaf() {
        return new TreeNode();
    }

    static TreeNode join(TreeNode left, TreeNode right) {
        return new TreeNode().withChildren(left, right);
    }

    static int getHeight(TreeNode root) {
        if (root == null) return 0;
        root.nodeCount++;

        int hl = getHeight(root.left);
        int hr = getHeight(root.right);

        root.nodeCount += root.left != null ? root.left.nodeCount : 0;
        root.nodeCount += root.right != null ? root.right.nodeCount : 0;

        return Math.max(hl, hr) + 1;
    }

    TreeNode withLeft(TreeNode left) {
        this.left = left;
        return this;
    }

    TreeNode withRight(TreeNode right) {
        this.right = right;
        return this;
    }

    TreeNode withChildren(TreeNode left, TreeNode right) {
        this.left = left;
        this.right = right;
        return this;
    }

    TreeNode withLeftLeaf() {
        return withLeft(leaf());
    }

    TreeNode withRightLeaf() {
        return withRight(leaf());
    }

    TreeNode withLeaves() {
        return withChildren(leaf(), leaf());
    }
}