package com.example.leetcodejava.Algorithm.DivideAndConquer;

import java.util.LinkedList;
import java.util.List;

public class LeetCode95 {
    /**
     * LeetCode95.不同的二叉搜索树 II   Medium
     *
     * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
     *
     * 示例：
     * 输入：3
     * 输出：
     * [
     *   [1,null,3,2],
     *   [3,2,null,1],
     *   [3,1,null,null,2],
     *   [2,1,3],
     *   [1,null,2,null,3]
     * ]
     * 解释：
     * 以上的输出对应以下 5 种不同结构的二叉搜索树：
     *
     *    1         3     3      2      1
     *     \       /     /      / \      \
     *      3     2     1      1   3      2
     *     /     /       \                 \
     *    2     1         2                 3
     *
     *  提示：
     *  0 <= n <= 8
     */

    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 分治算法
     * 思路：
     * 二叉搜索树关键的性质是根节点的值大于左子树所有节点的值，小于右子树所有节点的值，且左子树和右子树也同样为二叉搜索树。
     * 因此在生成所有可行的二叉搜索树的时候，假设当前序列长度为 n，如果我们枚举根节点的值为 i，那么根据二叉搜索树的性质
     * 我们可以知道左子树的节点值的集合为 [1…i−1]，右子树的节点值的集合为[i+1…n]。而左子树和右子树的生成相较于原问题
     * 是一个序列长度缩小的子问题，因此我们可以想到用分治并且递归的方法来解决这道题目。
     *
     * 我们定义 generateTrees(start, end) 函数表示当前值的集合为[start,end]，
     * 返回序列[start,end] 生成的所有可行的二叉搜索树。
     *
     * 按照上文的思路，我们考虑枚举[start,end] 中的值 i为当前二叉搜索树的根，
     * 那么序列划分为了[start,i−1] 和[i+1,end] 两部分。我们递归调用这两部分，
     * 即 generateTrees(start, i - 1) 和 generateTrees(i + 1, end)，
     * 获得所有可行的左子树和可行的右子树，那么最后一步我们只要从可行左子树集合中
     * 选一棵，再从可行右子树集合中选一棵拼接到根节点上，并将生成的二叉搜索树放入答案数组即可。
     *
     * 递归的入口即为 generateTrees(1, n)，出口为当start>end 的时候，当前二叉搜索树为空，返回空节点即可。
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<TreeNode>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        // 枚举可行根节点
        for (int i = start; i <= end; i++) {
            // 获得所有可行的左子树集合
            List<TreeNode> leftTrees = generateTrees(start, i - 1);

            // 获得所有可行的右子树集合
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode currTree = new TreeNode(i);
                    currTree.left = left;
                    currTree.right = right;
                    allTrees.add(currTree);
                }
            }
        }
        return allTrees;
    }

    /**
     * 复杂度分析
     *
     * 时间复杂度 : 整个算法的时间复杂度取决于「可行二叉搜索树的个数」，而
     * 对于n个点生成的二叉搜索树数量等价于数学上第 n个「卡特兰数」，用 Gn表示。
     * 卡特兰数具体的细节请读者自行查询，这里不再赘述，只给出结论。
     * 生成一棵二叉搜索树需要 O(n) 的时间复杂度，一共有 Gn棵二叉搜索树，也就是O(nGn）。
     * 而卡特兰数以 4^n / n ^1/2增长，因此总时间复杂度为 O(4^n / n ^1/2)
     *
     * 空间复杂度 : n个点生成的二叉搜索树有 Gn棵，每棵有 n 个节点，因此存储的空间
     * 需要 O(nGn) = O(4^n / n ^1/2)，递归函数需要 O(n)的栈空间，因此总空间复杂度为O(4^n / n ^1/2)
     */
}
