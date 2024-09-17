// Did this code successfully run on Leetcode : Yes

/**
 * Try to balance the coins at a node == Deficit or Surplus at that node
 * == current -1 + left subtree + right subtree
 *
 * Math.abs (Deficit or Surplus at a node) == # of coins to move via that node
 *
 * NOTE: at the end, for a balanced tree, the root should return 0 as balance.
 */

/**
 * LC 979
 */
public class DistributeCoins {
    /**
     * Definition for a binary tree node.
     */
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * TC: O(n)
     * SC: O(height of tree)
     * @param root
     * @return
     */
    public int distributeCoins(TreeNode root) {
        int[] moves = new int[1];
        dfs(root, moves);
        return moves[0];
    }

    private int dfs(TreeNode root, int[] moves) {
        // base
        if (root == null) {
            return 0;
        }
        // logic
        int left = dfs(root.left, moves);
        int right = dfs(root.right, moves);
        int curr = root.val - 1 + left + right;
        moves[0] += Math.abs(curr);
        return curr;
    }
}