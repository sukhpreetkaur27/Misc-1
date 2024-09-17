import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

// Did this code successfully run on Leetcode : Yes

/**
 * Recursive Solution -> Apply DFS
 * <p>
 * Try BFS.
 *
 * TC: O(number of integers)
 * SC: O(max depth)
 */
public class NestedListWeightSum {

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    private class NestedInteger {
        // Constructor initializes an empty nested list.
        public NestedInteger() {

        }

        // Constructor initializes a single integer.
        public NestedInteger(int value) {

        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return true;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return null;
        }

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {

        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {

        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return null;
        }
    }

    public int depthSum_dfs(List<NestedInteger> nestedList) {
        return helper(nestedList, 1);
    }

    private int helper(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                sum += nestedInteger.getInteger() * depth;
            } else {
                sum += helper(nestedInteger.getList(), depth + 1);
            }
        }
        return sum;
    }

    public int depthSum(List<NestedInteger> nestedList) {
        Deque<List<NestedInteger>> queue = new ArrayDeque<>();
        queue.offer(nestedList);

        int sum = 0;
        int depth = 0;

        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                List<NestedInteger> list = queue.poll();
                for (NestedInteger nestedInteger : list) {
                    if (nestedInteger.isInteger()) {
                        sum += nestedInteger.getInteger() * depth;
                    } else {
                        queue.offer(nestedInteger.getList());
                    }
                }

            }
        }

        return sum;
    }
}
