package com.example.leetcodejava;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode112 {
    /**
     *  LeetCode112.路径总和
     *
     *  给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
     *
     *  说明: 叶子节点是指没有子节点的节点。
     *
     *  示例: 
     *  给定如下二叉树，以及目标和 sum = 22，
     *
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \      \
     *         7    2      1
     *
     *   返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
     *
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 方法一：递归
     * 思路及算法
     *
     * 观察要求我们完成的函数，我们可以归纳出它的功能：询问是否存在从当前节点root到叶子节点的路径，满足其路径和为sum。
     *
     * 假定从根节点到当前节点的值之和为val，我们可以将这个大问题转化为一个小问题：是否存在从当前节点的子节点到叶子的路径，满足其路径和为sum - val。
     *
     * 不难发现这满足递归的性质，若当前节点就是叶子节点，那么我们直接判断sum是否等于val 即可
     *（因为路径和已经确定，就是当前节点的值，我们只需要判断该路径和是否满足条件）。
     * 若当前节点不是叶子节点，我们只需要递归地询问它的子节点是否能满足条件即可。
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
    /**
     * 复杂度分析
     *
     * 时间复杂度：O(N)O，其中 N是树的节点数。对每个节点访问一次。
     *
     * 空间复杂度：O(H)，其中 H是树的高度。空间复杂度主要取决于递归时栈空间的开销，最坏情况下，树呈现链状，空间复杂度为 O(N)。
     * 平均情况下树的高度与节点数的对数正相关，空间复杂度为 O(logN)。
     *
     */

    /**
     *  方法二：广度优先搜索
     *
     *  思路及算法
     *
     * 首先我们可以想到使用广度优先搜索的方式，记录从根节点到当前节点的路径和，以防止重复计算。
     *
     * 这样我们使用两个队列，分别存储将要遍历的节点，以及根节点到这些节点的路径和即可。
     */
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queNode = new LinkedList<TreeNode>();
        Queue<Integer> queVal = new LinkedList<Integer>();
        queNode.offer(root);
        queVal.offer(root.val);
        while (!queNode.isEmpty()) {
            TreeNode now = queNode.poll();
            int temp = queVal.poll();
            if (now.left == null && now.right == null) {
                if (temp == sum) {
                    return true;
                }
                continue;
            }
            if (now.left != null) {
                queNode.offer(now.left);
                queVal.offer(now.left.val + temp);
            }
            if (now.right != null) {
                queNode.offer(now.right);
                queVal.offer(now.right.val + temp);
            }
        }
        return false;
    }
    ///ps:
    // offer()	将指定元素插入队列，成功返回true，否则返回false
    // poll()	获取并移除队头（若队列空则返回null）

    /**
     * 复杂度分析
     *
     * 时间复杂度：O(N)，其中 N是树的节点数。对每个节点访问一次。
     *
     * 空间复杂度：O(N)，其中 N是树的节点数。空间复杂度主要取决于队列的开销，队列中的元素个数不会超过树的节点数。
     */
}
